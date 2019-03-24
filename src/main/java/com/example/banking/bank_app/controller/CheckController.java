package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.AddUser;
import com.example.banking.bank_app.model.Check;
import com.example.banking.bank_app.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value="/checks")
public class CheckController {

    @Autowired
    CheckService checkService;

    @RequestMapping(value="/list/{page}", method= RequestMethod.GET)
    public ModelAndView list(@PathVariable("page") int page) {
        ModelAndView modelAndView = new ModelAndView("check_list");
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<Check> checkPage = checkService.getPaginated(pageable);
        int totalPages = checkPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("activeCheckList", true);
        modelAndView.addObject("CheckList", checkPage.getContent());
        return modelAndView;
    }

    @RequestMapping(value = "/issue", method = RequestMethod.GET)
    public ModelAndView AddUserForm(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        Check check = new Check();
        modelAndView.addObject("check", check);
        modelAndView.setViewName("issueCheck"); // resources/template/register.html
        modelAndView.addObject("message",message);
        return modelAndView;
    }
}
