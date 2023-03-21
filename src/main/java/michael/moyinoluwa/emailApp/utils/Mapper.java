package michael.moyinoluwa.emailApp.utils;

import michael.moyinoluwa.emailApp.data.models.Mail;
import michael.moyinoluwa.emailApp.data.models.Profile;
import michael.moyinoluwa.emailApp.dtos.request.MailRequest;
import michael.moyinoluwa.emailApp.dtos.request.RegisterRequest;
import michael.moyinoluwa.emailApp.dtos.response.MailResponse;
import michael.moyinoluwa.emailApp.dtos.response.RegisterResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static Profile map(RegisterRequest request) {
        Profile profile = new Profile("mikee");
        profile.setTimeCreated(LocalDateTime.now());
        profile.setFirstName(convertToSentenceCase(request.getFirstName()));
        profile.setLastName(convertToSentenceCase(request.getLastName()));
        profile.setDayOfBirth(request.getDayOfBirth());
        profile.setMonthOfBirth(request.getMonthOfBirth());
        profile.setYearOfBirth(request.getYearOfBirth());
        profile.setUsername(request.getUsername());
        profile.setPassword(request.getPassword());
        return profile;
    }

    public static Mail map(MailRequest mailRequest) {
        Mail mail = new Mail();
        mail.setType(mailRequest.getType());
        mail.setTimeCreated(LocalDateTime.now());
        mail.setSubject(mailRequest.getSubject());
        mail.setBody(mailRequest.getBody());
        mail.setProfileId(mailRequest.getProfileId());
        mail.setSender(mailRequest.getSender());
        mail.setRecipient(mailRequest.getRecipient());
        return mail;
    }

    public static RegisterResponse map(Profile profile) {
        RegisterResponse response = new RegisterResponse();
        response.setName(profile.getFirstName().concat(" ").concat(profile.getLastName()));
        response.setId(profile.getId());
        response.setUsername(profile.getUsername());
        response.setDob(profile.getDayOfBirth()+"/"+profile.getMonthOfBirth()+"/"+profile.getYearOfBirth());
        response.setDateRegistered(profile.getTimeCreated().toLocalDate());
        response.setTimeRegistered(profile.getTimeCreated().toLocalTime());
        return response;
    }

    public static MailResponse map(Mail mail) {
        MailResponse response = new MailResponse();
        response.setId(mail.getId());
        response.setProfileId((mail.getProfileId()));
        response.setSubject(mail.getSubject());
        response.setBody(mail.getBody());
        response.setRecipient(mail.getRecipient());
        response.setSender(mail.getSender());
        response.setDateGenerated(mail.getTimeCreated().toLocalDate());
        response.setTimeGenerated(mail.getTimeCreated().toLocalTime());
        return response;
    }
    public static MailRequest map(MailResponse response) {
        MailRequest request = new MailRequest();
        request.setProfileId(response.getProfileId());
        request.setBody(response.getBody());
        request.setSubject(response.getSubject());
        request.setRecipient(response.getRecipient());
        request.setSender(response.getSender());
        return request;
    }

    public static List<MailResponse> map(List<Mail> mails) {
        List <MailResponse> response = new ArrayList<>();
        for (var mail: mails) {
            response.add(map(mail));
        }
        return response;
    }
    private static String convertToSentenceCase(String word){
        return word.replace(word.charAt(0), Character.toUpperCase(word.charAt(0)));
    }
}
