package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.*;
import com.example.banking.bank_app.service.AccountService;
import com.example.banking.bank_app.service.TransactionRequestService;
import com.example.banking.bank_app.service.TransactionService;
import com.example.banking.bank_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class TransferController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionRequestService transactionRequestService;

    @Autowired
    AccountService accountService;

    @GetMapping("/transfer/{type}")
    public String transactionForm(Model model, @PathVariable String type, Authentication authentication, @ModelAttribute("message") String message) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        String role = "0";
        if(roles.contains("TIER1")){
            role = "1";
        }
        Transfer transfer = new Transfer();
        model.addAttribute("transfer", transfer);
        model.addAttribute("type", type);
        model.addAttribute("message",message);
        Long id =  userService.findUserByEmail(authentication.getName());
        model.addAttribute("accounts",userService.getUserByUserId(id).getAccounts());
        model.addAttribute("role", role);
        return "transaction";
    }

    @RequestMapping(value = "/transfer/{type}", method= RequestMethod.POST)
    public String formSubmit(@Valid Transfer transfer,  @PathVariable String type, BindingResult bindingResult, Authentication authentication, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message","Please correct the errors!");
            return "redirect:/transfer/"+type;
        }
        try{
            accountService.getAccountByAccountNo(transfer.getTo_account_no());
            accountService.getAccountByAccountNo(transfer.getFrom_account_no());
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Account number doesn't exists!");
            return "redirect:/transfer/"+type;
        }
        //Dont delete the comment
        Long id =  userService.findUserByEmail(authentication.getName());
        Transaction from_transaction = new Transaction();
        Transaction to_transaction = new Transaction();
        from_transaction.setTransaction_amount(transfer.getTransaction_amount());
        from_transaction.setTransaction_timestamp(new Timestamp(System.currentTimeMillis()));
        from_transaction.setTransaction_type(Config.DEBIT);
        from_transaction.setDescription("Transfer to "+transfer.getTo_account_no()+ " || Comments: "+transfer.getDescription());
        from_transaction.setAccount_no(transfer.getFrom_account_no());
        from_transaction.setStatus(Config.APPROVED);
        float from_balance = accountService.getAccountByAccountNo(transfer.getFrom_account_no()).getBalance();
        from_transaction.setBalance(from_balance - transfer.getTransaction_amount());

        to_transaction.setTransaction_amount(transfer.getTransaction_amount());
        to_transaction.setTransaction_timestamp(new Timestamp(System.currentTimeMillis()));
        to_transaction.setTransaction_type(Config.CREDIT);
        to_transaction.setDescription("Transfer from "+transfer.getFrom_account_no()+ " || Comments: "+transfer.getDescription());
        to_transaction.setAccount_no(transfer.getTo_account_no());
        to_transaction.setStatus(Config.APPROVED);
        float to_balance = accountService.getAccountByAccountNo(transfer.getTo_account_no()).getBalance();
        to_transaction.setBalance(to_balance+transfer.getTransaction_amount());

        Long request_id = 0L;
        if(transfer.getTransaction_amount() > Config.LIMIT){
            TransactionRequest transactionRequest = new TransactionRequest();
            transactionRequest.setCreated_by(id);
            transactionRequest.setFrom_account(transfer.getFrom_account_no());
            transactionRequest.setTo_account(transfer.getTo_account_no());
            transactionRequest.setStatus_id(Config.PENDING);
            transactionRequest.setTransaction_amount(transfer.getTransaction_amount());
            transactionRequest.setCreated_at(new Timestamp(System.currentTimeMillis()));
            transactionRequest.setDescription("Fund Transfer || Comments: "+transfer.getDescription());
            transactionRequest.setType(Config.TRANSFER);
            transactionRequest.setCritical(Config.CRITICAL_YES);
            request_id = transactionRequestService.saveOrUpdate(transactionRequest).getRequest_id();
            from_transaction.setRequest_id(request_id);
            from_transaction.setStatus(Config.PENDING);
            from_transaction.setBalance(from_balance);

            to_transaction.setRequest_id(request_id);
            to_transaction.setStatus(Config.PENDING);
            to_transaction.setBalance(to_balance);
        }else{
            Account from_account = accountService.getAccountByAccountNo(transfer.getFrom_account_no());
            from_account.setBalance(from_balance-transfer.getTransaction_amount());

            Account to_account = accountService.getAccountByAccountNo(transfer.getTo_account_no());
            to_account.setBalance(to_balance+transfer.getTransaction_amount());

            accountService.saveOrUpdate(from_account);
            accountService.saveOrUpdate(to_account);
        }
        transactionService.saveOrUpdate(from_transaction);
        transactionService.saveOrUpdate(to_transaction);
        redirectAttributes.addFlashAttribute("message","Successfully transferred");
        return "redirect:/transfer/"+type;
    }
}
