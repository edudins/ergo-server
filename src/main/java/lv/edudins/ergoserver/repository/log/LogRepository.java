package lv.edudins.ergoserver.repository.log;

import lv.edudins.ergoserver.domain.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogEntry, Long> {}
