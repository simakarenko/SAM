package com.gmail.simakarenko93.sam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   // @Bean
    //public ClientRegistrationRepository clientRegistrationRepository() {
     //   return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    //}

   // private ClientRegistration googleClientRegistration() {
     //   return ClientRegistration.withRegistrationId("google")
       //         .clientId("google-client-id")
         //       .clientSecret("google-client-secret")
           //     .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
             //   .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
              //  .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
               // .scope("openid", "profile", "email", "address", "phone")
               // .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
               // .tokenUri("https://www.googleapis.com/oauth2/v4/token")
               // .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                //.userNameAttributeName(IdTokenClaimNames.SUB)
                //.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                //.clientName("Google")
                //.build();
    //}
   private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login.html", "/js/**", "/css/**", "/favicon.ico", "/logout")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(login -> login
                        .loginPage("/login.html")
                        .successHandler(authenticationSuccessHandler)
                )
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                );
        return http.build();
    }
}