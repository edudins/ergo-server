package lv.edudins.ergoserver.service;

import lv.edudins.ergoserver.domain.Person;
import lv.edudins.ergoserver.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person save(Person newPerson) {
        return repository.save(newPerson);
    }

    public Optional<Person> findById(Long id) {
        return repository.findById(id);
    }

    public List<Person> search(String firstName, String lastName, String dateOfBirth) {
        return repository.findAll()
                .stream()
                .filter(p -> firstName == null || p.getFirstName().equals(firstName))
                .filter(p -> lastName == null || p.getLastName().equals(lastName))
                .filter(p -> dateOfBirth == null || dateOfBirth.equals(p.getDateOfBirth().toString()))
                .toList();
    }

    public Person replace(Person newPerson, Long id) {
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
        repository.deleteById(id);
    }
}
