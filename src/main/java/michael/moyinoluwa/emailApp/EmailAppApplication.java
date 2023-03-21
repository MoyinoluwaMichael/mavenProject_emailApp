package michael.moyinoluwa.emailApp;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import michael.moyinoluwa.emailApp.data.models.Profile;
import michael.moyinoluwa.emailApp.data.repositories.profileRepo.ProfileRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
//@RequiredArgsConstructor
public class EmailAppApplication {
//    private final ProfileRepo profileRepo;

    public static void main(String[] args) {
        SpringApplication.run(EmailAppApplication.class, args);
    }
//    @PostConstruct
//    public void addDummyData(){
//        profileRepo.save(new Profile("mikee"));
//    }
//    @PreDestroy
//    public void  deleteDummy(){
//        profileRepo.deleteAll();
//    }

}
