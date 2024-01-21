package com.shoppingmall.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthRequest {

    private String username;
    private String password;
}