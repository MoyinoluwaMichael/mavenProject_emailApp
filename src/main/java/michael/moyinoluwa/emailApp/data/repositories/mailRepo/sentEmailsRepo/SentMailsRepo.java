package michael.moyinoluwa.emailApp.data.repositories.mailRepo.sentEmailsRepo;

import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.repositories.mailRepo.MailRepo;

import java.util.ArrayList;
import java.util.List;

public interface SentMailsRepo extends MailRepo {
    @Override
    default long countByProfileId(String profileId){
        long count = 0;
        for (Mail mail : findAll()) {
            boolean itIsASentMailbox = mail.getType().equals("SENT_MAILBOX");
            if (mail.getProfileId().equals(profileId) && itIsASentMailbox) count++;
        }
        return count;
    }

    @Override
    default List<Mail> findAllByProfileId(String profileId) {
        List <Mail> mails = new ArrayList<>();
        for (Mail mail : findAll()){
            boolean itIsASentMailbox = mail.getType().equals("SENT_MAILBOX");
            if (mail.getProfileId().equals(profileId) && itIsASentMailbox) {
                mails.add(mail);
            }
        }
        return mails;
    }
    @Override
    default Mail findByProfileIdAndId(String profileId, String id){
        for (Mail mail : findAll()) {
            boolean itIsASentMailbox = mail.getType().equals("SENT_MAILBOX");
            if (mail.getProfileId().equals(profileId) && mail.getId().equals(id) && itIsASentMailbox) return mail;
        }
        return null;
    }

}
