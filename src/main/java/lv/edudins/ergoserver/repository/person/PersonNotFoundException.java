package lv.edudins.ergoserver.repository.person;

class PersonNotFoundException extends RuntimeException {

    PersonNotFoundException(Long id) {
        super("Could not find person: " + id + "\n");
    }

    PersonNotFoundException(String name) {
        super("Could not find person: " + name + "\n");
    }
}
