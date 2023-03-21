package michael.moyinoluwa.emailApp.data.repositories.mailRepo.sentEmailsRepo;

import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.models.MailType;
import michael.moyinoluwa.emailApp.data.repositories.mailRepo.MailRepo;
import michael.moyinoluwa.emailApp.dtos.request.MailRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static michael.moyinoluwa.emailApp.data.models.MailType.SENT_MAIL;

public interface SentMailsRepo extends MailRepo {
    @Override
    default long countByProfileId(String profileId){
        long count = 0;
        for (Mail mail : findAll()) {
            boolean itIsASentMailbox = mail.getType().equals(SENT_MAIL);
            if (mail.getProfileId().equals(profileId) && itIsASentMailbox) count++;
        }
        return count;
    }
//    long countByProfileId(String profileId);

    @Override
    default List<Mail> findAllByProfileId(String profileId) {
        List <Mail> mails = new ArrayList<>();
        for (Mail mail : findAll()){
            boolean itIsASentMailbox = mail.getType().equals(SENT_MAIL);
            if (mail.getProfileId().equals(profileId) && itIsASentMailbox) {
                mails.add(mail);
            }
        }
        return mails;
    }
    List<Mail> findByProfileId(String profileId);
    @Override
    default Mail findByProfileIdAndId(String profileId, String id){
        for (Mail mail : findAll()) {
            boolean itIsASentMailbox = mail.getType().equals(SENT_MAIL);
            if (mail.getProfileId().equals(profileId) && mail.getId().equals(id) && itIsASentMailbox) return mail;
        }
        return null;
    }
    Optional<Mail> findByIdAndProfileId(String id, String profileId);


}
