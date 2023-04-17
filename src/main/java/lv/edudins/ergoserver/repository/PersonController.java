package lv.edudins.ergoserver.repository;

import lv.edudins.ergoserver.domain.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/persons")
    List<Person> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/persons")
    Person newPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    // Single item

    @GetMapping("/persons/{id}")
    Person one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/persons/{id}")
    Person replaceEmployee(@RequestBody Person newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(person -> {
                    person.setPersonalId(newPerson.getPersonalId());
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setGender(newPerson.getGender());
                    person.setDateOfBirth(newPerson.getDateOfBirth());
                    person.setPhoneNumber(newPerson.getPhoneNumber());
                    person.setEmail(newPerson.getEmail());
                    return repository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

    @DeleteMapping("/persons/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
