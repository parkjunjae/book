package project.book.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import project.book.exceptionhanding.CustomAccessDeniedHandler;
import project.book.exceptionhanding.CustomBasicAuthenticationEntryPoint;
import project.book.filter.CsrfCookieFilter;
import project.book.filter.RequestValidationAfterFilter;
import project.book.filter.RequestValidationBeforeFilter;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@Profile("!prod")
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

        http
                .cors(corsConfigurer -> corsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)) // security 로 jsessionId를 생성하지 않는다.
                .securityContext(contextConfig -> contextConfig.requireExplicitSave(false))
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // only http
                .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/contact","/register") // csrf이 필요없는 api
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new RequestValidationAfterFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount").hasRole("USER")
                        .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/myCards").hasRole("USER")
                        .requestMatchers("/myLoans").hasRole("USER")
                        .requestMatchers("/user").authenticated()
                .requestMatchers("/notices","/error","/register","/invalidSession","/contact","/debug/roles").permitAll()

        );

        http.formLogin(withDefaults());
        http.httpBasic(hdc -> hdc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler())); // global config
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();

    }
}
