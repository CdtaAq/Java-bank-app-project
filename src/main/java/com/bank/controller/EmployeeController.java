package com.bank.controller;

import com.bank.model.Employee;
import com.bank.model.Role;
import com.bank.model.Branch;
import com.bank.service.EmployeeService;
import com.bank.service.RoleService;
import com.bank.service.BranchService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final RoleService roleService;
    private final BranchService branchService;

    public EmployeeController(EmployeeService employeeService, RoleService roleService, BranchService branchService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
        this.branchService = branchService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employeeList";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("branches", branchService.getAllBranches());
        return "employeeForm";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("branches", branchService.getAllBranches());
        return "employeeForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/list";
    }
}
