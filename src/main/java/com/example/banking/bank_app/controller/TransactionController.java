package com.example.banking.bank_app.controller;


import com.example.banking.bank_app.model.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class TransactionController {

    @GetMapping("/transaction")
    public String transactionForm(Model model) {
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        return "transaction";
    }

    @RequestMapping(value = "/transaction", method= RequestMethod.POST)
    public String formSubmit(@Valid Transaction transaction, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "transaction";
        }

        //if there are no errors, show form success screen
        return "transaction_success";
    }

//    @PostMapping("/transaction")
//    public String transactionSuccess(@ModelAttribute Transaction transaction) {
//        return "transaction_success";
//    }
}
