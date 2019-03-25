package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.AccountRequest;
import com.example.banking.bank_app.model.Config;
import com.example.banking.bank_app.model.Employee;
import com.example.banking.bank_app.service.AccountRequestService;
import com.example.banking.bank_app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    AccountRequestService accountRequestService;

    @RequestMapping(value="/list/{page}", method= RequestMethod.GET)
    public ModelAndView list(@PathVariable("page") int page, Authentication authentication, @ModelAttribute("message") String message) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        ModelAndView modelAndView;
        int tier;
        if(roles.contains("ADMIN")){
            modelAndView = new ModelAndView("employee_admin");
            tier = -1; // so that admin can view all accounts
        }else{
            modelAndView = new ModelAndView("employee_list");
            tier = Config.ADMIN; // tier2 can't view admin's details
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
        modelAndView.addObject("message", message);
        return modelAndView;
    }


    @RequestMapping(value="/get/{employee_id}", method= RequestMethod.GET)
    public Employee getEmployee(@PathVariable("employee_id") Long employee_id) {
        return employeeService.getEmployeeById(employee_id);
    }

    @RequestMapping(value="/edit", method= RequestMethod.POST) //admin
    public ModelAndView editEmployee(@Valid Employee employee, Authentication authentication, RedirectAttributes redirectAttributes) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        if (roles.contains("ADMIN")) {
            Employee newEmployee = employeeService.getEmployeeById(employee.getEmployee_id());
            employee.setCreated(newEmployee.getCreated());
            employee.setUpdated(new Timestamp(System.currentTimeMillis()));
            employeeService.saveOrUpdate(employee);
            redirectAttributes.addFlashAttribute("message","Successfully saved!");
        }
        return new ModelAndView("redirect:/employee/list/1");
    }

    @RequestMapping(value="/edit1", method= RequestMethod.POST)//tier2 homepage
    public ModelAndView editEmployee1(@Valid Employee employee, Authentication authentication, RedirectAttributes redirectAttributes) {
        Long userId =  employeeService.findUserByEmail(authentication.getName());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        String name = employeeService.getEmployeeById(userId).getEmployee_name();
        Employee newEmployee = employeeService.getEmployeeById(employee.getEmployee_id());
        AccountRequest accountRequest = new AccountRequest();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("employee_id", employee.getEmployee_id());
        attributes.put("employee_name",employee.getEmployee_name());
        attributes.put("gender", employee.getGender());
        attributes.put("age", employee.getAge());
        attributes.put("tier_level", newEmployee.getTier_level());
        attributes.put("designation_id", newEmployee.getDesignation_id());
        attributes.put("contact_no", employee.getContact_no());
        attributes.put("email_id", employee.getEmail_id());
        attributes.put("address", employee.getAddress());
        accountRequest.setDescription("Edit Account");
        accountRequest.setEmployee(attributes);
        accountRequest.setCreated_by(name);
        accountRequest.setType(Config.EMPLOYEE_TYPE);
        if (roles.contains("ADMIN")){
            accountRequest.setRole(Config.ADMIN);
            accountRequest.setStatus_id(Config.APPROVED);
        }else{
            accountRequest.setRole(Config.ADMIN);
            accountRequest.setStatus_id(Config.PENDING);
        }
        accountRequest.setCreated_at(new Timestamp(System.currentTimeMillis()));
        try {
            accountRequest.serializeemployee();
        }
        catch(Exception e){
            System.out.println("Exception");
        }
        accountRequestService.saveOrUpdate(accountRequest);
        if(roles.contains("ADMIN")){
            redirectAttributes.addFlashAttribute("message","Successfully saved!");
        }
        else{
            redirectAttributes.addFlashAttribute("message","Successfully saved, pending approval!");
        }
        return new ModelAndView("redirect:/tier2");
    }

    @RequestMapping(value="/edit2", method= RequestMethod.POST)//tier2 employee page
    public ModelAndView editEmployee2(@Valid Employee employee, Authentication authentication, RedirectAttributes redirectAttributes) {
        Long userId =  employeeService.findUserByEmail(authentication.getName());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        String name = employeeService.getEmployeeById(userId).getEmployee_name();
        Employee newEmployee = employeeService.getEmployeeById(employee.getEmployee_id());
        AccountRequest accountRequest = new AccountRequest();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("employee_id", employee.getEmployee_id());
        attributes.put("employee_name",employee.getEmployee_name());
        attributes.put("gender", employee.getGender());
        attributes.put("age", employee.getAge());
        attributes.put("tier_level", newEmployee.getTier_level());
        attributes.put("designation_id", newEmployee.getDesignation_id());
        attributes.put("contact_no", employee.getContact_no());
        attributes.put("email_id", employee.getEmail_id());
        attributes.put("address", employee.getAddress());
        accountRequest.setDescription("Edit Account");
        accountRequest.setEmployee(attributes);
        accountRequest.setCreated_by(name);
        accountRequest.setType(Config.EMPLOYEE_TYPE);
        if (roles.contains("ADMIN")){
            accountRequest.setRole(Config.ADMIN);
            accountRequest.setStatus_id(Config.APPROVED);
        }else{
            accountRequest.setRole(Config.ADMIN);
            accountRequest.setStatus_id(Config.PENDING);
        }
        accountRequest.setCreated_at(new Timestamp(System.currentTimeMillis()));
        try {
            accountRequest.serializeemployee();
        }
        catch(Exception e){
            System.out.println("Exception");
        }
        accountRequestService.saveOrUpdate(accountRequest);
        if(roles.contains("ADMIN")){
            redirectAttributes.addFlashAttribute("message","Successfully saved!");
        }
        else{
            redirectAttributes.addFlashAttribute("message","Successfully saved, pending approval!");
        }
        return new ModelAndView("redirect:/employee/list/1");
    }

    @RequestMapping(value="/delete/{employee}", method= RequestMethod.POST)
    public ModelAndView deleteAccount(@PathVariable("employee") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ModelAndView("redirect:/account/list/1");
    }

}
