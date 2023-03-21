package michael.moyinoluwa.emailApp.services.mailService.trashService;

import michael.moyinoluwa.emailApp.dtos.request.MailRequest;
import michael.moyinoluwa.emailApp.dtos.response.MailResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;
@Service
@Profile("test")
public class trashEmailServiceImpl2 implements TrashService{
    @Override
    public MailResponse save(MailRequest mail) {
        return null;
    }

    @Override
    public long countByProfileId(String profileId) {
        return 0;
    }

    @Override
    public long countAll() {
        return 0;
    }

    @Override
    public MailResponse findByProfileIdAndId(String profileId, String id) throws InstanceNotFoundException {
        return null;
    }

    @Override
    public List<MailResponse> findAllByProfileId(String profileId) throws InstanceNotFoundException {
        return null;
    }

    @Override
    public List<MailResponse> deleteByProfileIdAndId(String profileId, String id) throws InstanceNotFoundException {
        return null;
    }

    @Override
    public List<MailResponse> deleteAllByProfileId(String profileId) throws InstanceNotFoundException {
        return null;
    }
}
