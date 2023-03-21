package michael.moyinoluwa.emailApp.data.repositories.mailRepo;

import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.models.MailType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class MailRepoTest {
    @Autowired
    private MailRepo mailRepo;

    @BeforeEach
    void cleanUp(){
//        mailRepo.deleteAll();
    }

    @Test
    void findByType() {
        mailRepo.save(new Mail(MailType.SENT_MAIL));

        List<Mail> mails = mailRepo.findByType(MailType.SENT_MAIL);
        assertNotNull(mails);
        assertEquals(1, mails.size());

    }
}