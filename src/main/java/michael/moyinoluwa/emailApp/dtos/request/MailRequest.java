package michael.moyinoluwa.emailApp.dtos.request;

import lombok.Data;

@Data
public class MailRequest {
    private String subject;
    private String body;
    private String profileId;
    private String sender;
    private String recipient;
    private String type;
}
