package com.minion.jwt.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JWTResponse {
    private String jwtToken;
    private String username;
}
