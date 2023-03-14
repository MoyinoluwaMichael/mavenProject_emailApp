package michael.moyinoluwa.emailApp.services.mailService.inboxService;

import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.repositories.mailRepo.inboxRepo.InboxRepo;
import michael.moyinoluwa.emailApp.dtos.request.MailRequest;
import michael.moyinoluwa.emailApp.dtos.response.MailResponse;
import michael.moyinoluwa.emailApp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Service
public class InboxServiceImpl implements InboxService {
    @Autowired
    private InboxRepo inboxRepo;

    @Override
    public MailResponse save(MailRequest request) {
        request.setType("INBOX");
        return Mapper.map(inboxRepo.save(Mapper.map(request)));
    }

    @Override
    public long countByProfileId(String profileId) {
        return inboxRepo.countByProfileId(profileId);
    }

    @Override
    public long countAll() {
        return inboxRepo.count();
    }

    @Override
    public MailResponse findByProfileIdAndId(String profileId, String id) throws InstanceNotFoundException {
        Mail mail = inboxRepo.findByProfileIdAndId(profileId, id);
        if (mail == null) throw new InstanceNotFoundException("Mail does not exist or has been deleted.");
        return Mapper.map(mail);
    }

    @Override
    public List<MailResponse> findAllByProfileId(String profileId) throws InstanceNotFoundException {
        List <MailResponse> inbox =  Mapper.map(inboxRepo.findAllByProfileId(profileId));
        if (inbox.isEmpty()) throw new InstanceNotFoundException("Inbox is empty");
        return inbox;
    }

    @Override
    public List <MailResponse> deleteByProfileIdAndId(String profileId, String id) throws InstanceNotFoundException {
        inboxRepo.delete(inboxRepo.findByProfileIdAndId(profileId, id));
        return (findAllByProfileId(profileId));
    }

    @Override
    public List<MailResponse> deleteAllByProfileId(String profileId) throws InstanceNotFoundException {
        inboxRepo.deleteAll(inboxRepo.findAllByProfileId(profileId));
        return (findAllByProfileId(profileId));
    }

}
