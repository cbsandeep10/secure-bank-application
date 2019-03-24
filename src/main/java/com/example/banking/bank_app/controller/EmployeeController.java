package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.model.Employee;
import com.example.banking.bank_app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value="/list/{page}", method= RequestMethod.GET)
    public ModelAndView list(@PathVariable("page") int page, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        ModelAndView modelAndView;
        int tier = 3;
        if(roles.contains("ADMIN")){
            modelAndView = new ModelAndView("employee_admin");
            tier = 0;
        }else{
            modelAndView = new ModelAndView("employee_list");
        }
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<Employee> employeePage = employeeService.getPaginated(pageable, tier);
        int totalPages = employeePage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("activeEmployeeList", true);
        modelAndView.addObject("EmployeeList", employeePage.getContent());
        Employee employee= new Employee();
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }


    @RequestMapping(value="/get/{employee_id}", method= RequestMethod.GET)
    public Employee getEmployee(@PathVariable("employee_id") Integer employee_id) {
        return employeeService.getEmployeeById(employee_id);
    }

    @RequestMapping(value="/edit", method= RequestMethod.POST)
    public ModelAndView editEmployee(@Valid Employee employee) {

        return new ModelAndView("redirect:/employee/list/1");
    }

    @RequestMapping(value="/delete/{employee}", method= RequestMethod.POST)
    public ModelAndView deleteAccount(@PathVariable("employee") Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ModelAndView("redirect:/account/list/1");
    }

}
