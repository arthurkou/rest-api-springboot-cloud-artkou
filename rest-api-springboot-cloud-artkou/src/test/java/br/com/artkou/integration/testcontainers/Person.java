package br.com.artkou.integration.testcontainers;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}
