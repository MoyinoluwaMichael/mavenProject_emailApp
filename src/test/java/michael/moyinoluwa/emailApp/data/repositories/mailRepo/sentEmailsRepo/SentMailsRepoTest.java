package michael.moyinoluwa.emailApp.data.repositories.mailRepo.sentEmailsRepo;

import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.models.MailType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SentMailsRepoTest {
    @Autowired
    private SentMailsRepo sentMailsRepo;

    @Test
    void findByIdAndProfileId() {
        Mail savedMAil = sentMailsRepo.save(Mail.builder()
                .profileId("p1")
                .type(MailType.SENT_MAIL)
                .build());
        Optional<Mail> mail = sentMailsRepo.findByIdAndProfileId(savedMAil.getId(), "p1");
        assertNotNull(mail);
        assertTrue(mail.isPresent());
    }
}