package lv.edudins.ergoserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private @Id @GeneratedValue Long id;
    private Long personalId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private OffsetDateTime dateOfBirth;
    private String phoneNumber;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(personalId, person.personalId) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && gender == person.gender && Objects.equals(dateOfBirth, person.dateOfBirth) && Objects.equals(phoneNumber, person.phoneNumber) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalId, firstName, lastName, gender, dateOfBirth, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", personalId=" + personalId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

