package michael.moyinoluwa.emailApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class EmailAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailAppApplication.class, args);
    }

}
