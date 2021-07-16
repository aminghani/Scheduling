package com.example.scheduling.Controllers.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/Auth/Login").permitAll()
                .antMatchers("/api/Masters").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/Students").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE,"/api/Students/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE,"/api/Master/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT,"/api/Students/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT,"/api/Master/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/api/Students/Add").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/api/Master/Add").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/api/Students/AddList").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/api/Master/AddList").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/api/Days").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE,"/api/Days/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT,"/api/Days/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/api/Bells").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE,"/api/Bells/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT,"/api/Bells/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/api/Announcements").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MASTER')")
                .antMatchers(HttpMethod.GET,"/api/Courses").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET,"/api/Courses/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/api/Courses").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE,"/api/Courses/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT,"/api/Course/{id}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/api/Courses/{id}/Choose").access("hasRole('ROLE_MASTER')")
                .antMatchers(HttpMethod.GET,"/api/TimeTableBells").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MASTER')")
                .antMatchers(HttpMethod.GET,"/api/TimeTableBells/{id}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MASTER')")
                .antMatchers(HttpMethod.POST,"/api/TimeTableBells").access("hasRole('ROLE_MASTER')")
                .antMatchers(HttpMethod.DELETE,"/api/TimeTableBells/{id}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MASTER')")
                .antMatchers(HttpMethod.POST,"/api/TimeTables/{id}/Choose").access("hasRole('ROLE_STUDENT')")
                .anyRequest().access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MASTER') or hasRole('ROLE_STUDENT')")
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
