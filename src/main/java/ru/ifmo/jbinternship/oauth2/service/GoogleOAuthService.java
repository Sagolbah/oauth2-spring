package ru.ifmo.jbinternship.oauth2.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ifmo.jbinternship.oauth2.domain.TokenResponse;
import ru.ifmo.jbinternship.oauth2.domain.UserProfile;
import ru.ifmo.jbinternship.oauth2.properties.GoogleOAuthProperties;

@Service
public class GoogleOAuthService {
    private final GoogleOAuthProperties properties;
    private final String queryURI;

    public GoogleOAuthService(GoogleOAuthProperties properties) {
        this.properties = properties;
        // Example of URL construction: https://developers.google.com/identity/protocols/oauth2/web-server#httprest_2
        // Incremental authorization will happen always.
        UriComponents components = UriComponentsBuilder
                .fromUriString(properties.getMainURL())
                .queryParam("scope", properties.getScopeURL())
                .queryParam("access_type", properties.getAccessType())
                .queryParam("include_granted_scopes", "true")
                .queryParam("response_type", properties.getResponseType())
                .queryParam("redirect_uri", properties.getRedirect())
                .queryParam("client_id", properties.getId()).build();
        queryURI = components.toUriString();
    }

    public TokenResponse getAccessToken(final String authCode) {
        String uri = UriComponentsBuilder
                .fromUriString(properties.getExchangeURL())
                .queryParam("code", authCode)
                .queryParam("client_id", properties.getId())
                .queryParam("client_secret", properties.getSecret())
                .queryParam("redirect_uri", properties.getRedirect())
                .queryParam("grant_type", "authorization_code")
                .build()
                .toUriString();
        return WebClient
                .create(uri)
                .post()
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();
    }

    public UserProfile getUserProfile(final String accessToken) {
        String uri = UriComponentsBuilder
                .fromUriString(properties.getProfileApiURL())
                .queryParam("access_token", accessToken)
                .build()
                .toUriString();
        return WebClient
                .create(uri)
                .get()
                .retrieve()
                .bodyToMono(UserProfile.class)
                .block();
    }

    public String getQueryURI() {
        return queryURI;
    }
}
