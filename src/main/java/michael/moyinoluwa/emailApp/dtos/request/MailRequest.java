package michael.moyinoluwa.emailApp.dtos.request;

import lombok.Data;
import michael.moyinoluwa.emailApp.data.models.MailType;

@Data
public class MailRequest {
    private String subject;
    private String body;
    private String profileId;
    private String sender;
    private String recipient;
    private MailType type;
}
