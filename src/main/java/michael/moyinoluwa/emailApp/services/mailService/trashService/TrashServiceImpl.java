package michael.moyinoluwa.emailApp.services.mailService.trashService;

import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.repositories.mailRepo.trashRepo.TrashRepo;
import michael.moyinoluwa.emailApp.dtos.request.MailRequest;
import michael.moyinoluwa.emailApp.dtos.response.MailResponse;
import michael.moyinoluwa.emailApp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Service
public class TrashServiceImpl implements TrashService{
    @Autowired
    private TrashRepo trashRepo;

    @Override
    public MailResponse save(MailRequest request) {
        request.setType("TRASH");
        return Mapper.map(trashRepo.save(Mapper.map(request)));
    }

    @Override
    public long countByProfileId(String profileId) {
        return trashRepo.countByProfileId(profileId);
    }

    @Override
    public long countAll() {
        return trashRepo.count();
    }

    @Override
    public MailResponse findByProfileIdAndId(String profileId, String id) throws InstanceNotFoundException {
        Mail mail = trashRepo.findByProfileIdAndId(profileId, id);
        if (mail == null) throw new InstanceNotFoundException("Mail does not exist or has been deleted.");
        return Mapper.map(mail);
    }

    @Override
    public List<MailResponse> findAllByProfileId(String profileId) throws InstanceNotFoundException {
        List <MailResponse> trash =  Mapper.map(trashRepo.findAllByProfileId(profileId));
        if (trash.isEmpty()) throw new InstanceNotFoundException("Trash is empty");
        return trash;
    }

    @Override
    public List <MailResponse> deleteByProfileIdAndId(String profileId, String id) throws InstanceNotFoundException {
        trashRepo.delete(trashRepo.findByProfileIdAndId(profileId, id));
        return (findAllByProfileId(profileId));
    }
    @Override
    public List<MailResponse> deleteAllByProfileId(String profileId) throws InstanceNotFoundException {
        trashRepo.deleteAll(trashRepo.findAllByProfileId(profileId));
        return (findAllByProfileId(profileId));
    }
}
