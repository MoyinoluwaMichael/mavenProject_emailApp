package michael.moyinoluwa.emailApp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("Mails")
@Data
public class Mail {
    @Id
    private String id;
    private String profileId;
    private String subject;
    private String body;
    private String sender;
    private String recipient;
    private LocalDateTime timeCreated;
    private String type;
}
