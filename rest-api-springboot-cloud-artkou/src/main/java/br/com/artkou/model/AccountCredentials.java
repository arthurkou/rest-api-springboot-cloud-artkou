package br.com.artkou.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class AccountCredentials implements Serializable{

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
}
