package michael.moyinoluwa.emailApp.controllers;

import michael.moyinoluwa.emailApp.dtos.request.MailRequest;
import michael.moyinoluwa.emailApp.dtos.response.MailResponse;
import michael.moyinoluwa.emailApp.dtos.response.RegisterResponse;
import michael.moyinoluwa.emailApp.services.mailService.inboxService.InboxService;
import michael.moyinoluwa.emailApp.services.mailService.sentMailsService.SentMailsService;
import michael.moyinoluwa.emailApp.services.mailService.trashService.TrashService;
import michael.moyinoluwa.emailApp.services.profileService.ProfileService;
import michael.moyinoluwa.emailApp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private InboxService inbox;

    @Autowired
    private SentMailsService sentMails;

    @Autowired
    private TrashService trash;

    @GetMapping("/getSentMailsCount/{profileId}")
    public ResponseEntity<?> getSentMailsCountByProfileId(@PathVariable String profileId) {
        return new ResponseEntity<>(sentMails.countByProfileId(profileId), HttpStatus.FOUND);
    }

    @GetMapping("/findSentMailByProfileIdAndId/{profileId}/{id}")
    public ResponseEntity<?> findSentMailByProfileIdAndId(@PathVariable String profileId, @PathVariable String id){
        try{
            return new ResponseEntity<>(sentMails.findByProfileIdAndId(profileId, id), HttpStatus.FOUND);
        }catch (InstanceNotFoundException e){return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }

    @GetMapping("/findSentMails/{profileId}")
    public ResponseEntity<?> findSentMailsByProfileId(@PathVariable String profileId) {
        try{
            return new ResponseEntity<>(sentMails.findAllByProfileId(profileId), HttpStatus.FOUND);
        }catch (InstanceNotFoundException e){ return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }

    @DeleteMapping("/deleteMailFromSentMails/{profileId}/{id}")
    public ResponseEntity<?> deleteMailFromSentMailsByProfileIdAndId(@PathVariable String profileId, @PathVariable String id) {
        try{
            trash.save(Mapper.map(sentMails.findByProfileIdAndId(profileId, id)));
            return new ResponseEntity<> (sentMails.deleteByProfileIdAndId(profileId, id), HttpStatus.ACCEPTED);
        }catch (InstanceNotFoundException e){return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }


    @GetMapping("/getInboxCount/{profileId}")
    public ResponseEntity<?> getInboxCountByProfileId(@PathVariable String profileId) {
        return new ResponseEntity<>(inbox.countByProfileId(profileId), HttpStatus.FOUND);
    }

    @GetMapping("/findInboxByProfileIdAndId/{profileId}/{id}")
    public ResponseEntity<?> findInboxByProfileIdAndId(@PathVariable String profileId, @PathVariable String id){
        try{
            return new ResponseEntity<>(inbox.findByProfileIdAndId(profileId, id), HttpStatus.FOUND);
        }catch (InstanceNotFoundException e){return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }

    @GetMapping("/findInbox/{profileId}")
    public ResponseEntity<?> findInboxByProfileId(@PathVariable String profileId) {
        try{
            return new ResponseEntity<>(inbox.findAllByProfileId(profileId), HttpStatus.FOUND);
        }catch (InstanceNotFoundException e){ return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }

    @DeleteMapping("/deleteMailFromInbox/{profileId}/{id}")
    public ResponseEntity<?> deleteMailFromInboxByProfileIdAndId(@PathVariable String profileId, @PathVariable String id) throws InstanceNotFoundException {
        try{
            trash.save(Mapper.map(inbox.findByProfileIdAndId(profileId, id)));
            return new ResponseEntity<> (inbox.deleteByProfileIdAndId(profileId, id), HttpStatus.ACCEPTED);
        }catch (InstanceNotFoundException e){return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }

    @GetMapping("/getTrashCount/{profileId}")
    public ResponseEntity<?> getTrashCountByProfileId(@PathVariable String profileId) {
        return new ResponseEntity<>(trash.countByProfileId(profileId), HttpStatus.FOUND);
    }

    @GetMapping("/findTrashByProfileIdAndId/{profileId}/{id}")
    public ResponseEntity<?> findTrashByProfileIdAndId(@PathVariable String profileId, @PathVariable String id){
        try{
            return new ResponseEntity<>(trash.findByProfileIdAndId(profileId, id), HttpStatus.FOUND);
        }catch (InstanceNotFoundException e){return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }

    @GetMapping("/findTrash/{profileId}")
    public ResponseEntity<?> findTrashByProfileId(@PathVariable String profileId) {
        try{
            return new ResponseEntity<>(trash.findAllByProfileId(profileId), HttpStatus.FOUND);
        }catch (InstanceNotFoundException e){ return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }

    @DeleteMapping("/deleteMailFromTrash/{profileId}/{id}")
    public ResponseEntity<?> deleteMailFromTrashByProfileIdAndId(@PathVariable String profileId, @PathVariable String id) throws InstanceNotFoundException {
        try{
            return new ResponseEntity<> (trash.deleteByProfileIdAndId(profileId, id), HttpStatus.ACCEPTED);
        }catch (InstanceNotFoundException e){return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }

    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody MailRequest mailRequest) {
        RegisterResponse sender, recipient;
        try {
            sender = profileService.findByUsername(mailRequest.getSender());
            recipient = profileService.findByUsername(mailRequest.getRecipient());
        } catch (InstanceNotFoundException e) {return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
        MailResponse sentMail = sentMails.save(mailRequest);
        mailRequest.setProfileId(recipient.getId());
        inbox.save(mailRequest);
        try {
            return new ResponseEntity<>(sentMails.findByProfileIdAndId(sender.getId(), sentMail.getId()), HttpStatus.CREATED);
        } catch (InstanceNotFoundException e) {return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}
    }
}
