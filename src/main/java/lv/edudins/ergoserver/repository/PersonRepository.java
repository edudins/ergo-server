package lv.edudins.ergoserver.repository;

import lv.edudins.ergoserver.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PersonRepository extends JpaRepository<Person, Long> {
}
