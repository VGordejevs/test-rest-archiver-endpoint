package lv.vladislavs.archiver.model.database;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity(name = "request_log")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RequestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress;

    private String servletPath;

    private OffsetDateTime requestTime;
}