package me.tsaheylu.config;

// import my.demo.filters.JwtTokenFilter;

import com.google.api.Authentication;
import me.tsaheylu.component.CustomOAuth2UserService;
import me.tsaheylu.component.JwtAuthenticationEntryPoint;
import me.tsaheylu.controller.AuthorizationController;
import me.tsaheylu.model.CustomOAuth2User;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private CustomOAuth2UserService oauthUserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // dont authenticate this particular request
                .authorizeRequests()
                .antMatchers("/error", "/api/auth/**", "/login**/**", "/oauth2/**", "/swagger**/**", "/webjars/**", "/v3/**", "/doc.html")
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // all other requests need to be authenticated
                .anyRequest().authenticated().and().oauth2Login()
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and().failureHandler((request, response, exception) -> {
                    logger.error("OAuth2 login failure", exception);
                    response.sendRedirect("/login?error");
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
                        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
                        userService.processOAuthLogin(oAuth2User,response);
//                        logger.debug("OAuth2User", principal.getAttributes());
                    }


                });
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }

}
