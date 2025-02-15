package springstudies.springboot_essentials.client;

import lombok.extern.log4j.Log4j2;
import org.apache.catalina.util.ParameterMap;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import springstudies.springboot_essentials.domain.Student;

import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Student> entity = new RestTemplate().getForEntity("http://localhost:8080/students/1", Student.class);
        log.info(entity);

        ResponseEntity<Student> entity2 = new RestTemplate().getForEntity("http://localhost:8080/students/{id}", Student.class, 1, 2);
        log.info(entity2);

        Student object = new RestTemplate().getForObject("http://localhost:8080/students/1", Student.class);
        log.info(object);

        ResponseEntity<List<Student>> studentsList = new RestTemplate().exchange("http://localhost:8080/students/1", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Student>>() {});
        log.info(studentsList.getBody());

        Student johndoe = Student.builder().name("John Doe").build();
        Student johndoeSaved = new RestTemplate().postForObject("http://localhost:8080/students/", johndoe, Student.class);
        log.info("Saved student {}", johndoeSaved);
    }
}
