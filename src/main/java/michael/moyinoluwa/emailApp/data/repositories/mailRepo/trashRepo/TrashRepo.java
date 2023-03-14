package michael.moyinoluwa.emailApp.data.repositories.mailRepo.trashRepo;

import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.repositories.mailRepo.MailRepo;

import java.util.ArrayList;
import java.util.List;

public interface TrashRepo extends MailRepo {
    @Override
    default long countByProfileId(String profileId){
        long count = 0;
        for (Mail mail : findAll()) {
            boolean itIsATrash = mail.getType().equals("TRASH");
            if (mail.getProfileId().equals(profileId) && itIsATrash) count++;
        }
        return count;
    }

    @Override
    default List<Mail> findAllByProfileId(String profileId) {
        List <Mail> mails = new ArrayList<>();
        for (Mail mail : findAll()){
            boolean itIsATrash = mail.getType().equals("TRASH");
            if (mail.getProfileId().equals(profileId) && itIsATrash) mails.add(mail);
        }
        return mails;
    }
    @Override
    default Mail findByProfileIdAndId(String profileId, String id){
        for (Mail mail : findAll()) {
            boolean itIsATrash = mail.getType().equals("TRASH");
            if (mail.getProfileId().equals(profileId) && mail.getId().equals(id) && itIsATrash) return mail;
        }
        return null;
    }
}
