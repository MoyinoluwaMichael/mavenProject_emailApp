package michael.moyinoluwa.emailApp.services.profileService;

import michael.moyinoluwa.emailApp.data.models.Profile;
import michael.moyinoluwa.emailApp.data.repositories.profileRepo.ProfileRepo;
import michael.moyinoluwa.emailApp.dtos.request.RegisterRequest;
import michael.moyinoluwa.emailApp.dtos.response.RegisterResponse;
import michael.moyinoluwa.emailApp.utils.Mapper;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ProfileServiceImp implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        validateRequest(request);
        return Mapper.map(profileRepo.save(Mapper.map(request)));
    }

    private void validateRequest(RegisterRequest request) {
        request.setUsername(addBrandToUsername(request.getUsername()));
        validateUsername(request.getUsername());
        validateDob(request);
        validateFirstname(request.getFirstName());
        validateLastname(request.getLastName());
    }

    private String addBrandToUsername(String username) {
        return !username.contains("@regnos.com") ?
                (username + "@regnos.com").toLowerCase() : username;
    }

    private void validateLastname(String lastName) {
        if (lastName == null) throw new IllegalArgumentException("You must specify your first name");
    }

    private void validateFirstname(String firstname) {
        if (firstname == null) throw new IllegalArgumentException("You must specify your first name");
    }

    private void validateDob(RegisterRequest request) {
        validateYear(request.getYearOfBirth());
        validateMonth(request.getMonthOfBirth());
        validateDay(request);
    }

    private void validateMonth(String month) {
        if (month.length() != 2) throw new IllegalArgumentException("Wrong format, month format should be: 'mm'");
        int month2 = Integer.parseInt(month);
        if (month2 < 1 || month2 > 12) throw new IllegalArgumentException("Month should be between 1 and 12");
    }

    private void validateYear(String year) {
        if (year.length() != 4) throw new IllegalArgumentException("Wrong format, Year format should be 'yyyy'");
    }

    private void validateDay(RegisterRequest request) {
        if (request.getDayOfBirth().length() != 2)
            throw new IllegalArgumentException("Wrong format, day format should be: 'dd'");
        int year = Integer.parseInt(request.getYearOfBirth());
        int month = Integer.parseInt(request.getMonthOfBirth());
        int day2 = Integer.parseInt(request.getDayOfBirth());
        int lengthOfMonth = LocalDate.of(year, month, 1).lengthOfMonth();
        if (day2 < 1 || day2 > lengthOfMonth)
            throw new IllegalArgumentException("Day specified is not within month range (1 - " + lengthOfMonth + ")");
    }

    private void validateUsername(String username) {
        if (profileRepo.findByUsername(username) != null) {
            throw new DuplicateRequestException("Username already exist!");
        }
    }

    @Override
    public RegisterResponse findById(String id) throws InstanceNotFoundException {
        Optional<Profile> profile = profileRepo.findById(id);
        if (profile.isEmpty()) throw new InstanceNotFoundException("Profile not found or has been deleted.");
        return Mapper.map(profile.get());
    }

    @Override
    public RegisterResponse findByUsername(String username) throws InstanceNotFoundException {
        Profile profile = profileRepo.findByUsername(username);
        validateProfile(profile, username);
        return Mapper.map(profile);
    }

    private void validateProfile(Profile profile, String username) throws InstanceNotFoundException {
        if (profile == null) throw new InstanceNotFoundException(username+" does not exist");
    }

    public RegisterResponse login(String username, String password){
        Profile profile = profileRepo.findByUsername(username);
        if (profile == null || !profile.getPassword().equals(password))
            throw new IllegalArgumentException("Username or Password is incorrect!");
        return Mapper.map(profileRepo.findByUsername(username));
    }

    @Override
    public String editUsername(String oldUsername, String newUsername) throws InstanceNotFoundException {
        newUsername = addBrandToUsername(newUsername);
        findByUsername(oldUsername);
        validateUsername(newUsername);
        profileRepo.editUsername(oldUsername, newUsername);
        return "Username has been updated successfully!";
    }

    @Override
    public String editPassword(String id, String oldPassword, String newPassword) throws InstanceNotFoundException {
        findById(id);
        comparePasswords(oldPassword, newPassword);
        profileRepo.editPassword(id, newPassword);
        return "Password has been updated successfully!";
    }

    private void comparePasswords(String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) throw new IllegalArgumentException("New password is same as old password");
    }
}
