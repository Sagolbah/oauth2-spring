package ru.ifmo.jbinternship.oauth2.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.google")
public class GoogleOAuthProperties {
    private String id;
    private String secret;
    private String redirect;
    private String mainURL;
    private String scopeURL;
    private String exchangeURL;
    private String profileApiURL;
    private String responseType;
    private String accessType;

    public String getProfileApiURL() {
        return profileApiURL;
    }

    public void setProfileApiURL(String profileApiURL) {
        this.profileApiURL = profileApiURL;
    }

    public String getExchangeURL() {
        return exchangeURL;
    }

    public void setExchangeURL(String exchangeURL) {
        this.exchangeURL = exchangeURL;
    }


    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getScopeURL() {
        return scopeURL;
    }

    public void setScopeURL(String scopeURL) {
        this.scopeURL = scopeURL;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getMainURL() {
        return mainURL;
    }

    public void setMainURL(String mainURL) {
        this.mainURL = mainURL;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }

    public String getId() {
        return id;
    }
}
