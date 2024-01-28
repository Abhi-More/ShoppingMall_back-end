package com.shoppingmall.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
public class AuthenticateObj {
    private String name;
    private String password;
    private Integer id;
    private List<GrantedAuthority> authorities;
}
