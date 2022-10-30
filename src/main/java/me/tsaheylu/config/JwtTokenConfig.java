package me.tsaheylu.config;

// import my.demo.filters.JwtTokenFilter;

import me.tsaheylu.component.JwtAuthenticationEntryPoint;
import me.tsaheylu.security.oauth2.CustomOAuth2UserService;
import me.tsaheylu.security.oauth2.CustomOidcUserService;
import me.tsaheylu.security.oauth2.OAuth2AuthenticationFailureHandler;
import me.tsaheylu.security.oauth2.OAuth2AuthenticationSuccessHandler;
import me.tsaheylu.service.UserService;
import me.tsaheylu.component.JwtRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class JwtTokenConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenConfig.class);


    @Autowired
    private UserService userService;
    //  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    //  @Autowired private UserDetailsService userDetailsService;
    //  private JwtRequestFilter jwtRequestFilter;

    //  public JwtTokenConfig(
    //      JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
    //      UserService userService,
    //      JwtRequestFilter jwtRequestFilter) {
    //    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    //    this.userService = userService;
    //    this.jwtRequestFilter = jwtRequestFilter;
    //  }
    //

    @Bean
    public JwtRequestFilter authenticationTokenFilterBean() {
        return new JwtRequestFilter();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        //    BCryptPasswordEncoder()
        return new MessageDigestPasswordEncoder("MD5");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private CustomOidcUserService customOidcUserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/error", "/api/auth/**", "/login**/**", "/oauth2/**", "/swagger**/**", "/webjars/**", "/v3/**", "/doc.html").permitAll().antMatchers(HttpMethod.OPTIONS).permitAll()
                // all other requests need to be authenticated
                .anyRequest().authenticated().and()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(customOidcUserService)
                .userService(customOAuth2UserService)
                .and()
                .failureHandler(oAuth2AuthenticationFailureHandler)
                .successHandler(oAuth2AuthenticationSuccessHandler);


        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }

}
