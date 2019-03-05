package com.example.banking.bank_app.controller;
import com.example.banking.bank_app.model.Employee;
import com.example.banking.bank_app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value="/list/{page}", method= RequestMethod.GET)
    public ModelAndView list(@PathVariable("page") int page) {
        ModelAndView modelAndView = new ModelAndView("employee_list");
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<Employee> employeePage = employeeService.getPaginated(pageable);
        int totalPages = employeePage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("activeEmployeeList", true);
        modelAndView.addObject("EmployeeList", employeePage.getContent());
        return modelAndView;
    }


}
