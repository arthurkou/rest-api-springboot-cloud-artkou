package br.com.artkou.entity;

import br.com.artkou.model.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "person")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PersonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 6)
    private String gender;

    public PersonEntity(Person personVO) {
        this.id = personVO.getKey();
        this.firstName = personVO.getFirstName();
        this.lastName = personVO.getLastName();
        this.address = personVO.getAddress();
        this.gender = personVO.getGender();
    }

    public static PersonEntity toEntity(PersonEntity personEntity, Person person) {
          personEntity.setFirstName(person.getFirstName());
          personEntity.setLastName(person.getLastName());
          personEntity.setGender(person.getGender());
          personEntity.setAddress(person.getAddress());
          return personEntity;
    }
}
