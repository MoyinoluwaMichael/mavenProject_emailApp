package michael.moyinoluwa.emailApp.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("Mails")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mail {
    @Id
    private String id;
    private String profileId;
    private String subject;
    private String body;
    private String sender;
    private String recipient;
    private LocalDateTime timeCreated;
    @NonNull
    private MailType type;
}
