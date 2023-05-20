package br.com.artkou.junit.person;

import br.com.artkou.entity.PersonEntity;
import br.com.artkou.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

        public PersonEntity mockEntity() {
            return mockEntity(0);
        }

        public Person mockVO() {
            return mockVO(0);
        }

        public List<PersonEntity> mockEntityList() {
            List<PersonEntity> persons = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                persons.add(mockEntity(i));
            }
            return persons;
        }

        public List<Person> mockVOList() {
            List<Person> persons = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                persons.add(mockVO(i));
            }
            return persons;
        }

        public PersonEntity mockEntity(Integer number) {
            PersonEntity person = new PersonEntity();
            person.setAddress("Addres Test" + number);
            person.setFirstName("First Name Test" + number);
            person.setGender(((number % 2)==0) ? "Male" : "Female");
            person.setId(number.longValue());
            person.setLastName("Last Name Test" + number);
            return person;
        }

        public Person mockVO(Integer number) {
            Person person = new Person();
            person.setAddress("Addres Test" + number);
            person.setFirstName("First Name Test" + number);
            person.setGender(((number % 2)==0) ? "Male" : "Female");
            person.setKey(number.longValue());
            person.setLastName("Last Name Test" + number);
            return person;
        }

}
