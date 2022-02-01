package me.tsaheylu.config;

// import my.demo.filters.JwtTokenFilter;
import me.tsaheylu.service.UserService;
import me.tsaheylu.component.JwtRequestFilter;
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

  @Autowired public UserService userService;
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

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf()
        .disable()
        // dont authenticate this particular request
        .authorizeRequests()
        .antMatchers("/api/user/check**", "/api/user/emailcheck**")
        .permitAll()
        .antMatchers(HttpMethod.OPTIONS)
        .permitAll()
        // all other requests need to be authenticated
        .anyRequest()
        .authenticated()
        .and()

        // make sure we use stateless session; session won't be used to
        // store user's state.
        // 覆盖默认登录
        //        exceptionHandling()
        //        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        //        .and()
        .sessionManagement()
        // 基于token，所以不需要session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    httpSecurity.addFilterBefore(
        authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    httpSecurity.headers().cacheControl();
  }
}
