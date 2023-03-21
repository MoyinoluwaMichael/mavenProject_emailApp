package michael.moyinoluwa.emailApp.data.repositories.profileRepo;


import michael.moyinoluwa.emailApp.data.models.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepo extends MongoRepository <Profile, String> {
//    default Profile findByUsername(String username) {
//        for (Profile profile : findAll()) if (profile.getUsername().equalsIgnoreCase(username)) return profile;
//        return null;
//    }
    Profile findByUsername(String username);

    default void editUsername(String oldUsername, String newUsername) {
        Profile optionalProfile = findByUsername(oldUsername);
        optionalProfile.setUsername(newUsername);
        save(optionalProfile);
    }

    default void editPassword(String id, String newPassword){
        Profile profile = findById(id).get();
        profile.setPassword(newPassword);
        save(profile);
    }

    boolean existsByUsername(String username);
}
