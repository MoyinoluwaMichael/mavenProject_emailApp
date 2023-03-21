package michael.moyinoluwa.emailApp.data.repositories.mailRepo;

import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.models.MailType;
import michael.moyinoluwa.emailApp.dtos.request.MailRequest;
import michael.moyinoluwa.emailApp.dtos.response.MailResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

public interface MailRepo extends MongoRepository<Mail, String> {
    long countByProfileId(String profileId);
    List<Mail> findAllByProfileId(String profileId);
    Mail findByProfileIdAndId(String profileId, String id);

    List<Mail> findByType(MailType mailType);
}
