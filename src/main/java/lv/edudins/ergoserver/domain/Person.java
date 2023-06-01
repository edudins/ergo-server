package lv.edudins.ergoserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private @Id @GeneratedValue Long personalId;
    private @NotBlank String firstName;
    private @NotBlank String lastName;
    private @NotNull Gender gender;
    private @NotNull @Past LocalDate dateOfBirth;
    private @Pattern(regexp = "\\+371\\d{8}") String phoneNumber;
    private @Email String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personalId, person.personalId)
                && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && gender == person.gender
                && Objects.equals(dateOfBirth, person.dateOfBirth)
                && Objects.equals(phoneNumber, person.phoneNumber)
                && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalId, firstName, lastName, gender, dateOfBirth, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personalId=" + personalId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

