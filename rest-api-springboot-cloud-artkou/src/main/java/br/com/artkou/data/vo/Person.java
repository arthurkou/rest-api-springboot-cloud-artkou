package br.com.artkou.data.vo;

import br.com.artkou.entity.PersonEntity;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public static Person toEntity(PersonEntity person) {
        return Person.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .gender(person.getGender())
                .build();
    }
}
