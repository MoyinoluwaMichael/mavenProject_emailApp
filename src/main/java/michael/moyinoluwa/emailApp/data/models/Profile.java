package michael.moyinoluwa.emailApp.data.models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Profiles")
@Data
@RequiredArgsConstructor
public class Profile {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private LocalDateTime timeCreated;
    @NonNull
    private String username;
    private String password;



}
