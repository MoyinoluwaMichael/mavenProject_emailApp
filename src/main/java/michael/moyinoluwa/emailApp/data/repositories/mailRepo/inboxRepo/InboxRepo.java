package michael.moyinoluwa.emailApp.data.repositories.mailRepo.inboxRepo;
import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.repositories.mailRepo.MailRepo;

import java.util.ArrayList;
import java.util.List;

public interface InboxRepo extends MailRepo {
    @Override
    default long countByProfileId(String profileId){
        long count = 0;
        for (Mail mail : findAll()) {
            boolean itIsAnInbox = mail.getType().equals("INBOX");
            if (mail.getProfileId().equals(profileId) && itIsAnInbox) count++;
        }
        return count;
    }
    @Override
    default List<Mail> findAllByProfileId(String profileId) {
        List <Mail> mails = new ArrayList<>();
        for (Mail mail : findAll()){
            boolean itIsAnInbox = mail.getType().equals("INBOX");
            if (mail.getProfileId().equals(profileId) && itIsAnInbox) mails.add(mail);
        }
        return mails;
    }
    @Override
    default Mail findByProfileIdAndId(String profileId, String id){
        for (Mail mail : findAll()) {
            boolean itIsAnInbox = mail.getType().equals("INBOX");
            if (mail.getProfileId().equals(profileId) && mail.getId().equals(id) && itIsAnInbox) return mail;
        }
        return null;
    }
}
