package com.userRegistrationService.userregistration.domain;

import org.springframework.data.annotation.Id;

public class loginCredential {
    private String email;
    private String password;

    public loginCredential(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
