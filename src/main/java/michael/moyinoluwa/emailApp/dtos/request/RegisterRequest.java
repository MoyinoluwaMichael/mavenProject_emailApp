package michael.moyinoluwa.emailApp.dtos.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
}
