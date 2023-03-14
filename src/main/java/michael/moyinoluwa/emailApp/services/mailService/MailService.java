package michael.moyinoluwa.emailApp.services.mailService;


import michael.moyinoluwa.emailApp.dtos.request.MailRequest;
import michael.moyinoluwa.emailApp.dtos.response.MailResponse;

import javax.management.InstanceNotFoundException;
import java.util.List;

public interface MailService {

    MailResponse save(MailRequest mail);

    long countByProfileId(String profileId);

    long countAll();

    MailResponse findByProfileIdAndId(String profileId, String id) throws InstanceNotFoundException;
    List <MailResponse> findAllByProfileId(String profileId) throws InstanceNotFoundException;

    List <MailResponse> deleteByProfileIdAndId(String profileId, String id) throws InstanceNotFoundException;
    List <MailResponse> deleteAllByProfileId(String profileId) throws InstanceNotFoundException;

}
