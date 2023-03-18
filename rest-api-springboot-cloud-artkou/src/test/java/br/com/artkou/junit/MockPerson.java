package br.com.artkou.junit;

import br.com.artkou.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

        public Person mockEntity() {
            return mockEntity(0);
        }

        public Person mockVO() {
            return mockVO(0);
        }

        public List<Person> mockEntityList() {
            List<Person> persons = new ArrayList<Person>();
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

        public Person mockEntity(Integer number) {
            Person person = new Person();
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
            person.setId(number.longValue());
            person.setLastName("Last Name Test" + number);
            return person;
        }

}
