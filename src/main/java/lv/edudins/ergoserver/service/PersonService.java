package lv.edudins.ergoserver.service;

import lv.edudins.ergoserver.domain.Person;
import lv.edudins.ergoserver.repository.person.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;
    private final LogService logService;

    PersonService(PersonRepository repository, LogService logService) {
        this.logService = logService;
        this.repository = repository;
    }

    public List<Person> findAll() {
        logService.logAndSave("Finding all entities.");
        return repository.findAll();
    }

    public Person save(Person person) {
        logService.logAndSave("Saving " + person);
        return repository.save(person);
    }

    public Optional<Person> findById(Long id) {
        logService.logAndSave("Finding person id[" + id + "]");
        return repository.findById(id);
    }

    public List<Person> search(String firstName, String lastName, String dateOfBirth) {
        logService.logAndSave("Searching for person firstName[" + firstName + "], lastName[" + lastName + "] dateOfBirth[" + dateOfBirth + "]");
        return repository.findAll()
                .stream()
                .filter(p -> firstName == null || p.getFirstName().equals(firstName))
                .filter(p -> lastName == null || p.getLastName().equals(lastName))
                .filter(p -> dateOfBirth == null || dateOfBirth.equals(p.getDateOfBirth().toString()))
                .toList();
    }

    public Person replace(Person newPerson, Long id) {
        logService.logAndSave("Updating person id[" + id + "] with: " + newPerson);
        return repository.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setGender(newPerson.getGender());
                    person.setDateOfBirth(newPerson.getDateOfBirth());
                    person.setPhoneNumber(newPerson.getPhoneNumber());
                    person.setEmail(newPerson.getEmail());
                    return repository.save(person);
                })
                .orElseGet(() -> repository.save(newPerson));
    }

    public void deleteById(Long id) {
        logService.logAndSave("Deleting person id[" + id + "]");
        repository.deleteById(id);
    }
}
