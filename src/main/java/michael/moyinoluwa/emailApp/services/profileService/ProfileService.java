package michael.moyinoluwa.emailApp.services.profileService;

import michael.moyinoluwa.emailApp.dtos.request.RegisterRequest;
import michael.moyinoluwa.emailApp.dtos.response.RegisterResponse;

import javax.management.InstanceNotFoundException;

public interface ProfileService {
    RegisterResponse register(RegisterRequest request);

    RegisterResponse findById(String id) throws InstanceNotFoundException;

    RegisterResponse findByUsername(String username) throws InstanceNotFoundException;

    RegisterResponse login(String username, String password);

    String editUsername(String oldUsername, String newUsername) throws InstanceNotFoundException;

    String editPassword(String id, String oldPassword, String newPassword) throws InstanceNotFoundException;
}
