package lv.edudins.ergoserver.service;

import lv.edudins.ergoserver.domain.Gender;
import lv.edudins.ergoserver.domain.Person;
import lv.edudins.ergoserver.repository.person.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepository mockRepository;

    @Mock
    private LogService mockLogService;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personService = new PersonService(mockRepository, mockLogService);
    }

    @Test
    void shouldReturnListOfPersons() {
        List<Person> expectedPersons = Collections.singletonList(new Person());
        when(mockRepository.findAll()).thenReturn(expectedPersons);

        List<Person> actualPersons = personService.findAll();

        assertEquals(expectedPersons, actualPersons);
        verify(mockLogService).logAndSave("Finding all entities.");
        verify(mockRepository).findAll();
    }

    @Test
    void shouldSavePerson() {
        Person person = new Person();
        when(mockRepository.save(person)).thenReturn(person);

        Person savedPerson = personService.save(person);

        assertEquals(person, savedPerson);
        verify(mockLogService).logAndSave("Saving " + person);
        verify(mockRepository).save(person);
    }

    @Test
    void shouldReturnOptionalOfPerson() {
        Long id = 1L;
        Person expectedPerson = new Person();
        when(mockRepository.findById(id)).thenReturn(Optional.of(expectedPerson));

        Optional<Person> actualPerson = personService.findById(id);

        assertEquals(Optional.of(expectedPerson), actualPerson);
        verify(mockLogService).logAndSave("Finding person id[" + id + "]");
        verify(mockRepository).findById(id);
    }

    @Test
    void shouldReturnEmptyOptionalOfPerson() {
        Long id = 1L;
        when(mockRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Person> actualPerson = personService.findById(id);

        assertEquals(Optional.empty(), actualPerson);
        verify(mockLogService).logAndSave("Finding person id[" + id + "]");
        verify(mockRepository).findById(id);
    }

    @Test
    void shouldReturnMatchingPersons() {
        String firstName = "John";
        String lastName = "Doe";
        Gender gender = Gender.MALE;
        String dateOfBirth = "2000-01-01";
        String phoneNumber = "+37111111111";
        String email = "john.doe@test.com";
        Person person1 = new Person(1L, firstName, lastName, gender, LocalDate.parse(dateOfBirth), phoneNumber, email);
        Person person2 = new Person(2L, firstName, lastName, gender, LocalDate.parse(dateOfBirth), phoneNumber, email);
        List<Person> expectedPersons = List.of(person1, person2);
        when(mockRepository.findAll()).thenReturn(expectedPersons);

        List<Person> actualPersons = personService.search(firstName, lastName, dateOfBirth);

        assertEquals(expectedPersons, actualPersons);
        verify(mockLogService).logAndSave("Searching for person firstName[" + firstName + "], lastName[" + lastName + "] dateOfBirth[" + dateOfBirth + "]");
        verify(mockRepository).findAll();
    }

    @Test
    void shouldReturnUpdatedPerson() {
        Long id = 1L;
        Person existingPerson = new Person(id, "John", "Doe", Gender.MALE, LocalDate.parse("2000-01-01"), "+37111111111", "john.doe@test.com");
        Person newPerson = new Person(id, "Jane", "Smith", Gender.FEMALE, LocalDate.parse("1990-02-02"), "+37122222222", "jane.smith@test.com");
        when(mockRepository.findById(id)).thenReturn(Optional.of(existingPerson));
        when(mockRepository.save(existingPerson)).thenReturn(newPerson);

        Person replacedPerson = personService.replace(newPerson, id);

        assertEquals(newPerson, replacedPerson);
        verify(mockLogService).logAndSave("Updating person id[" + id + "] with: " + newPerson);
        verify(mockRepository).findById(id);
        verify(mockRepository).save(existingPerson);
    }

    @Test
    void shouldDeletePerson() {
        Long id = 1L;

        personService.deleteById(id);

        verify(mockLogService).logAndSave("Deleting person id[" + id + "]");
        verify(mockRepository).deleteById(id);
    }
}
