package lv.edudins.ergoserver.repository;

import lv.edudins.ergoserver.domain.Person;
import lv.edudins.ergoserver.service.PersonService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class PersonController {

    private final PersonModelAssembler assembler;
    private final PersonService service;

    PersonController(PersonModelAssembler assembler, PersonService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping("/persons")
    CollectionModel<EntityModel<Person>> all() {
        List<EntityModel<Person>> persons = service.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(persons, linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }

    @PostMapping("/persons")
    ResponseEntity<?> newPerson(@RequestBody Person newPerson) {
        EntityModel<Person> entityModel = assembler.toModel(service.save(newPerson));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/persons/{id}")
    EntityModel<Person> one(@PathVariable Long id) {
        Person person = service.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return assembler.toModel(person);
    }

    @GetMapping("/persons/find")
    CollectionModel<EntityModel<Person>> multiple(@RequestParam Map<String, String> params) {
        String firstName = params.get("firstName");
        String lastName = params.get("lastName");
        String dateOfBirth = params.get("dateOfBirth");

        List<EntityModel<Person>> persons = service.search(firstName, lastName, dateOfBirth)
                .stream()
                .map(assembler::toModel)
                .toList();

        if (persons.size() == 0) {
            throw new PersonNotFoundException(firstName + " " + lastName + " " + dateOfBirth);
        }

        return CollectionModel.of(persons, linkTo(methodOn(PersonController.class).multiple(params)).withSelfRel());
    }

    @PutMapping("/persons/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return service.replace(newPerson, id);
    }

    @DeleteMapping("/persons/{id}")
    ResponseEntity<?> deletePerson(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
