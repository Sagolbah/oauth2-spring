package ru.ifmo.jbinternship.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.ifmo.jbinternship.oauth2.domain.TokenResponse;
import ru.ifmo.jbinternship.oauth2.domain.UserProfile;
import ru.ifmo.jbinternship.oauth2.properties.GoogleOAuthProperties;
import ru.ifmo.jbinternship.oauth2.service.GoogleOAuthService;

import javax.servlet.http.HttpSession;

@Controller
public class SignInPage extends Page {

    private final GoogleOAuthService service;
    private final GoogleOAuthProperties properties;

    public SignInPage(GoogleOAuthService service, GoogleOAuthProperties properties) {
        this.service = service;
        this.properties = properties;
    }

    @GetMapping("/signin")
    public String login() {
        return "SignInPage";
    }

    @GetMapping("/signinAction")
    public String signInAction(HttpSession session) {
        if (getUser(session) != null) {
            return "SignInPage";
        }
        return "redirect:" + service.getQueryURI();
    }

    @GetMapping("/oauthRedirect")
    public String handleOAuthRedirect(HttpSession session, Model model, @RequestParam(value = "error", required = false) String error,
                                      @RequestParam(value = "code", required = false) String authCode) {
        if (error != null) {
            return raiseError(model, "Service returned error: " + error);
        }
        TokenResponse token;
        try {
            token = service.getAccessToken(authCode);
            if (token == null) {
                return raiseError(model, "Could not receive an access token because of empty response");
            }
            if (token.getScope() == null || token.getScope().contains(properties.getMainURL())) {
                return raiseError(model, "Access to user information was denied");
            }
        } catch (WebClientResponseException e) {
            return raiseError(model, "Could not receive access token - " + e.getStatusText());
        }
        try {
            UserProfile user = service.getUserProfile(token.getAccessToken());
            if (user == null) {
                return raiseError(model, "Could not receive profile information because of empty response");
            }
            setUser(session, user);
            return "redirect:";
        } catch (WebClientResponseException e) {
            return raiseError(model, "Could not receive profile information - " + e.getStatusText());
        }
    }

    private String raiseError(Model model, String message) {
        model.addAttribute("error", message);
        return "SignInPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        unsetUser(session);
        return "redirect:";
    }
}
