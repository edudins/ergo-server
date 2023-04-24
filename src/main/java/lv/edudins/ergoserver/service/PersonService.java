package lv.edudins.ergoserver.service;

import lv.edudins.ergoserver.domain.Person;
import lv.edudins.ergoserver.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
