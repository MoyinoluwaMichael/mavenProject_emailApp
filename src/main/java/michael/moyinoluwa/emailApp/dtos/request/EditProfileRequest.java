package michael.moyinoluwa.emailApp.dtos.request;

import lombok.Data;

@Data
public class EditProfileRequest {
    private String id;
    private String oldUsername;
    private String newUsername;
    private String oldPassword;
    private String newPassword;

}
