package lv.edudins.ergoserver;

import lv.edudins.ergoserver.domain.Gender;
import lv.edudins.ergoserver.domain.Person;
import lv.edudins.ergoserver.repository.person.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Person(
                    1L,
                    "John",
                    "Pleasant",
                    Gender.MALE,
                    LocalDate.of(2000, 1, 1),
                    "+37111111111",
                    "johnpleasant@info.lv")));
            log.info("Preloading " + repository.save(new Person(
                    2L,
                    "Christina",
                    "Pleasant",
                    Gender.FEMALE,
                    LocalDate.of(1999, 1, 1),
                    "+37122222222",
                    "christinapleasant@info.lv")));
        };
    }
}
