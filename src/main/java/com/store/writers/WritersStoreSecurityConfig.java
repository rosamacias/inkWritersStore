package com.store.writers;

import com.store.writers.model.Role;
import com.store.writers.security.RefererAuthenticationSuccessHandler;
import com.store.writers.service.impl.UserDetailsServiceImpl;
import com.store.writers.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WritersStoreSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  public static final String[] PUBLIC_MATCHERS ={
          "/css/**",
          "/js/**",
          "/image/**",
          "/account",
          "/h2-console/**",
          "/login",
          "/register",
          "/forgot",
          "/catalog",
          "/catalogIem",
          "/newUser",
          "/userprofile",
          "/"
  };

  public static final String[] ADMIN_MATCHERS ={
          "/admin/**"
  };

  public static final String[] CUSTOMER_MATCHERS ={
          "/userprofile/**"
  };


  @Override
  protected void configure(HttpSecurity http) throws Exception {


    http
            .authorizeRequests()
            .antMatchers(PUBLIC_MATCHERS).permitAll()
            .antMatchers(CUSTOMER_MATCHERS).authenticated()
            .antMatchers(ADMIN_MATCHERS).hasRole(Role.ADMIN.toString())
            .antMatchers("/anonymous*").anonymous()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/login") // This endpoint will be mapped internally.
            // This URL will be our Login form post action.
            .defaultSuccessUrl("/welcome")   // If the login is successful,
                                                         // user will be redirected to this URL.
            //.successHandler(new RefererAuthenticationSuccessHandler())
            .failureUrl("/login?error=true") // If the user fails to login, application will redirect the user to this endpoint
            .and()
            .logout()
            .and()
            .logout()
            .logoutSuccessUrl("/index").deleteCookies("remember-me").permitAll()
            .and()
            .csrf().disable()
            .exceptionHandling().accessDeniedPage("/403")
            .and()
            .headers().frameOptions().disable()
            .and()
            .rememberMe();


  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager customAuthenticationManager() throws Exception {
    return authenticationManager();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }

}