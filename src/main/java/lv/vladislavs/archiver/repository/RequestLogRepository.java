package lv.vladislavs.archiver.repository;

import lv.vladislavs.archiver.model.database.RequestLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends CrudRepository<RequestLog, Long> {

}
