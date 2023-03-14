package michael.moyinoluwa.emailApp.dtos.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class MailResponse {
    private String id;
    private String profileId;
    private String subject;
    private String body;
    private String sender;
    private String recipient;
    private LocalDate dateGenerated;
    private LocalTime timeGenerated;
}
