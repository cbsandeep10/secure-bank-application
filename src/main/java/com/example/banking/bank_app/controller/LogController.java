package com.example.banking.bank_app.controller;


import com.example.banking.bank_app.model.Log;

import com.example.banking.bank_app.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value="/log")
public class LogController {

   // private LogRepository logRepository;
    @Autowired
    LogService logService;

    // Display records in table format
//    @RequestMapping(value="/list", method= RequestMethod.GET)
//
//    public ModelAndView list() {
//
//        List<Log> logList = logService.getAllLogs();
//
//        System.out.println(logList.size() +"there");
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.addObject("logList", logList);
//
//        modelAndView.setViewName("log_list");
//
//        return modelAndView;
//
//    }

    @RequestMapping(value="/list/{page}", method= RequestMethod.GET)
    public ModelAndView list(@PathVariable("page") int page) {
        ModelAndView modelAndView = new ModelAndView("log_list");
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<Log> logPage = logService.getPaginated(pageable);
        int totalPages = logPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("activeLogList", true);
        modelAndView.addObject("LogList", logPage.getContent());
        return modelAndView;
    }

//    @RequestMapping(value="/list")
//    public String showPage(Model model)
//    {
//        List<Log> data = (List<Log>) logService.getAllLogs();
//        model.addAttribute("data",data);
//
//        return log;
//    }

//    @PostMapping("/save")
//    public String save(Log l)
//    {
//        logRepository.save(l);
//
//        return "redirect:/";
//    }
//
//    @GetMapping("/delete")
//    public String deletelog(Integer id)
//    {
//        logRepository.deleteById(id);
//
//        return "redirect:/";
//    }

//    @GetMapping("/findOne")
//    @ResponseBody
//    public Log findOne(Integer id)
//    {
//        return logRepository.findById(id);
//
//
//    }




//
}
