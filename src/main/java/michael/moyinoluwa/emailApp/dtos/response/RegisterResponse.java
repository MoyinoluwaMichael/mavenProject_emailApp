package michael.moyinoluwa.emailApp.dtos.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class RegisterResponse {
    private String id;
    private String name;
    private String username;
    private String dob;
    private LocalDate dateRegistered;
    private LocalTime timeRegistered;
}
