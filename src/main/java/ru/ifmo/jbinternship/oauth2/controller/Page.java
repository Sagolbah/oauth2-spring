package ru.ifmo.jbinternship.oauth2.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import ru.ifmo.jbinternship.oauth2.domain.UserProfile;

import javax.servlet.http.HttpSession;

public class Page {
    private static final String USER_KEY = "user";

    @ModelAttribute("user")
    public UserProfile getUser(HttpSession httpSession) {
        return (UserProfile) httpSession.getAttribute(USER_KEY);
    }

    void setUser(HttpSession httpSession, UserProfile profile) {
        if (profile != null) {
            httpSession.setAttribute(USER_KEY, profile);
        } else {
            unsetUser(httpSession);
        }
    }

    void unsetUser(HttpSession httpSession) {
        httpSession.removeAttribute(USER_KEY);
    }


}
