package ru.ifmo.jbinternship.oauth2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfile {
    @JsonProperty("sub")
    private String sub;

    @JsonProperty("name")
    private String name;

    @JsonProperty("locale")
    private String locale;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
