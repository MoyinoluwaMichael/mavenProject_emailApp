package michael.moyinoluwa.emailApp.controllers;

import michael.moyinoluwa.emailApp.dtos.request.EditProfileRequest;
import michael.moyinoluwa.emailApp.dtos.request.LoginRequest;
import michael.moyinoluwa.emailApp.dtos.request.RegisterRequest;
import michael.moyinoluwa.emailApp.services.profileService.ProfileService;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;


    @GetMapping("/")
    public String test() {
        return "Hello world";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            return new ResponseEntity<>(profileService.register(request), HttpStatus.CREATED);
        } catch (IllegalArgumentException | DuplicateRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProfile/{username}")
    public ResponseEntity<?> findProfileByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(profileService.findByUsername(username), HttpStatus.FOUND);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> findProfileById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(profileService.findById(id), HttpStatus.FOUND);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return new ResponseEntity<>(profileService.login(loginRequest.getUsername(), loginRequest.getPassword()), HttpStatus.FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editUsername")
    public ResponseEntity<?> editUsername(@RequestBody EditProfileRequest request){
        try{
            return new ResponseEntity<>(profileService.editUsername(request.getOldUsername(), request.getNewUsername()), HttpStatus.ACCEPTED);
        }catch (InstanceNotFoundException | DuplicateRequestException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/editPassword")
    public ResponseEntity<?> editPassword(@RequestBody EditProfileRequest request){
        try{
            return new ResponseEntity<>(profileService.editPassword(
                    request.getId(), request.getOldPassword(), request.getNewPassword()), HttpStatus.ACCEPTED);
        }catch (InstanceNotFoundException | DuplicateRequestException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }


}
