package lv.edudins.ergoserver.repository;

import lv.edudins.ergoserver.domain.Person;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class PersonController {

    private final PersonRepository repository;
    private final PersonModelAssembler assembler;

    PersonController(PersonRepository repository, PersonModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/persons")
    CollectionModel<EntityModel<Person>> all() {

        List<EntityModel<Person>> persons = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(persons, linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }

    @PostMapping("/persons")
    ResponseEntity<?> newPerson(@RequestBody Person newPerson) {

        EntityModel<Person> entityModel = assembler.toModel(repository.save(newPerson));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/persons/{id}")
    EntityModel<Person> one(@PathVariable Long id) {

        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return assembler.toModel(person);
    }

    @PutMapping("/persons/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {

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
    void deletePerson(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
