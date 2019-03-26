package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Help;
import com.example.banking.bank_app.model.User;
import com.example.banking.bank_app.service.HelpService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value="/help")
public class HelpController {

    @Autowired
    HelpService helpService;



    @RequestMapping(value="/list/{page}", method= RequestMethod.GET)
    public ModelAndView list(@PathVariable("page") int page) {
        ModelAndView modelAndView = new ModelAndView("help_list");
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<Help> helpPage = helpService.getPaginated(pageable);
        int totalPages = helpPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("activeCheckList", true);
        modelAndView.addObject("helpList", helpPage.getContent());
        return modelAndView;
    }

    @RequestMapping(value = "/helpform", method = RequestMethod.GET)
    public ModelAndView AddHelpForm(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        Help help = new Help();
        modelAndView.addObject("help", help);
        modelAndView.setViewName("help");
        modelAndView.addObject("message",message);
        return modelAndView;
    }


    @RequestMapping(value="/helpform", method= RequestMethod.POST)
    public ModelAndView issue(@Valid Help help,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("help");
        }
        User user = new User();
        help.setAuth_user_id(user.getUserId());
        helpService.saveOrUpdate(help);
        return new ModelAndView("redirect:/help/list/1");
    }

//    @GetMapping("/helps")
//    public String helpform(Model model) {
//
//        Help help = new Help();
//        model.addAttribute("help", help);
//        return "help";
//    }
//
//    @RequestMapping(value = "/helps", method = RequestMethod.POST)
//    public String formSubmit(@Valid Help help, BindingResult bindingResult, Model model) {
//        //check for errors
//        if (bindingResult.hasErrors()) {
//            return "help";
//        }
//
//        //if there are no errors, show form success screen
//        return "help_success";
//    }
}
