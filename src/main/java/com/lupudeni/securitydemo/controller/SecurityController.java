package com.lupudeni.securitydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String getPublicMessage() {
        return "Welcome to the public page!";
    }

    @GetMapping("/user")
    public String getUserMessage() {
        return "Welcome, user!";
    }

    @GetMapping("/admin")
    public String getAdminMessage() {
        return "Welcome, your admin highness!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/privilege")
    public String getVeryPrivilegedMessage() {
        return "ADMIN-only endpoint accessed!";
    }
}
