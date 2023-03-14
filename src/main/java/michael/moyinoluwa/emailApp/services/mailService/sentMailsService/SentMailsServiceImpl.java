package michael.moyinoluwa.emailApp.services.mailService.sentMailsService;

import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.repositories.mailRepo.sentEmailsRepo.SentMailsRepo;
import michael.moyinoluwa.emailApp.dtos.request.MailRequest;
import michael.moyinoluwa.emailApp.dtos.response.MailResponse;
import michael.moyinoluwa.emailApp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Service
public class SentMailsServiceImpl implements SentMailsService {
    @Autowired
    private SentMailsRepo sentMailsRepo;

    @Override
    public MailResponse save(MailRequest request) {
        request.setType("SENT_MAILBOX");
        return Mapper.map(sentMailsRepo.save(Mapper.map(request)));
    }

    @Override
    public long countByProfileId(String profileId) {
        return sentMailsRepo.countByProfileId(profileId);
    }

    @Override
    public long countAll() {
        return sentMailsRepo.count();
    }

    @Override
    public MailResponse findByProfileIdAndId(String profileId, String id) throws InstanceNotFoundException {
        Mail mail = sentMailsRepo.findByProfileIdAndId(profileId, id);
        if (mail == null) throw new InstanceNotFoundException("Mail does not exist or has been deleted.");
        return Mapper.map(mail);
    }

    @Override
    public List<MailResponse> findAllByProfileId(String profileId) throws InstanceNotFoundException {
        List <MailResponse> sentMails =  Mapper.map(sentMailsRepo.findAllByProfileId(profileId));
        if (sentMails.isEmpty()) throw new InstanceNotFoundException("Sent mailbox is empty");
        System.out.println(sentMails);
        return sentMails;
    }

    @Override
    public List <MailResponse> deleteByProfileIdAndId(String profileId, String id) throws InstanceNotFoundException {
        sentMailsRepo.delete(sentMailsRepo.findByProfileIdAndId(profileId, id));
        return (findAllByProfileId(profileId));
    }
    @Override
    public List<MailResponse> deleteAllByProfileId(String profileId) throws InstanceNotFoundException {
        sentMailsRepo.deleteAll(sentMailsRepo.findAllByProfileId(profileId));
        return (findAllByProfileId(profileId));
    }
}
