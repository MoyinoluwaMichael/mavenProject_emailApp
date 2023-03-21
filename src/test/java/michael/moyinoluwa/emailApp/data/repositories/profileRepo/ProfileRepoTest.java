package michael.moyinoluwa.emailApp.data.repositories.profileRepo;

import michael.moyinoluwa.emailApp.data.models.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProfileRepoTest {
    @Autowired
    private ProfileRepo profileRepo;

    @BeforeEach
    void cleanUp(){
        profileRepo.deleteAll();
    }

    @Test
    void findByUsername() {
        profileRepo.save(new Profile("mikee"));
        assertTrue(profileRepo.existsByUsername("mikee"));
        Profile profile = profileRepo.findByUsername("mikee");
        assertNotNull(profile);
    }
}