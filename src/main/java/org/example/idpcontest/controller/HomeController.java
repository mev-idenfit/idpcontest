package org.example.idpcontest.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author: Mevlüt Beder
 * Company: Idenfit
 * Date: 2025-03-26 15:01:22
 * Changelog:
 * - 2025-03-26 15:01:22: Initial creation
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        if (principal != null) {
            model.addAttribute("name", principal.getName());
            String email = principal.getFirstAttribute("email");
            model.addAttribute("emailAddress", email);

        }
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        if (principal != null) {
            model.addAttribute("name", principal.getName());
            String email = principal.getFirstAttribute("email");
            model.addAttribute("emailAddress", email);
            model.addAttribute("authorized", email != null);
            model.addAttribute("userAttributes", principal.getAttributes());
        } else {
            model.addAttribute("authorized", false);
        }

        return "admin";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        String email = principal.getFirstAttribute("email");
        model.addAttribute("emailAddress", email);
        model.addAttribute("userAttributes", principal.getAttributes());
        return "index";
    }

}
