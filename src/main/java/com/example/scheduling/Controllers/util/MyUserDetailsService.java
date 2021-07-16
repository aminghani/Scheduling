package com.example.scheduling.Controllers.util;

import DataAccess.AdminDAO;
import DataAccess.MasterDAO;
import DataAccess.StudentDAO;
import models.Admin;
import models.Master;
import models.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = new StudentDAO().findByUsername2(username);
        if(student != null){
            return new User(student.getCode(),student.getPassword(),getAuthoritiesStudent());
        }else{
            Master master = new MasterDAO().findByUsername2(username);
            if(master != null){
                return new User(master.getCode(),master.getPassword(),getAuthoritiesMaster());
            }else{
                Admin admin = new AdminDAO().findByUsername2(username);
                return new User(admin.getCode(),admin.getPassword(),getAuthoritiesAdmin());
            }

        }
    }

    public Collection<? extends GrantedAuthority> getAuthoritiesStudent() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority("ROLE_STUDENT"));

        return list;
    }

    public Collection<? extends GrantedAuthority> getAuthoritiesMaster() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority("ROLE_MASTER"));

        return list;
    }

    public Collection<? extends GrantedAuthority> getAuthoritiesAdmin() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return list;
    }
}
