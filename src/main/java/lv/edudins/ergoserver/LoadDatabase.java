package lv.edudins.ergoserver;

import lv.edudins.ergoserver.domain.Gender;
import lv.edudins.ergoserver.domain.Person;
import lv.edudins.ergoserver.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Person(
                    1L,
                    1000L,
                    "John",
                    "Pleasant",
                    Gender.MALE,
                    OffsetDateTime.now(),
                    "+37111111111",
                    "johnpleasant@info.lv")));
            log.info("Preloading " + repository.save(new Person(
                    2L,
                    2000L,
                    "Christina",
                    "Pleasant",
                    Gender.FEMALE,
                    OffsetDateTime.now(),
                    "+37122222222",
                    "christinapleasant@info.lv")));
        };
    }
}
