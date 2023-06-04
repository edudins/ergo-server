package lv.edudins.ergoserver.service;

import lv.edudins.ergoserver.domain.LogEntry;
import lv.edudins.ergoserver.repository.log.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {
    private static final Logger logger  = LoggerFactory.getLogger(LogService.class);

    private final LogRepository repository;

    LogService(LogRepository repository) {
        this.repository = repository;
    }

    public List<LogEntry> findAll() {
        return repository.findAll();
    }

    public LogEntry save(LogEntry entry) {
        return repository.save(entry);
    }

    public void logAndSave(String message) {
        logger.info("Logging message: {}", message);

        LogEntry log = new LogEntry();
        log.setMessage(message);
        repository.save(log);
    }
}
