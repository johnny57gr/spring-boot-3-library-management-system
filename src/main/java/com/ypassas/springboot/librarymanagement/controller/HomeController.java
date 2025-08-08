package com.ypassas.springboot.librarymanagement.controller;

import com.ypassas.springboot.librarymanagement.model.DashboardStats;
import com.ypassas.springboot.librarymanagement.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final DashboardService dashboardService;

    @Autowired
    public HomeController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        DashboardStats stats = dashboardService.getDashboardStats();
        model.addAttribute("stats", stats);
        return "home";
    }
}
