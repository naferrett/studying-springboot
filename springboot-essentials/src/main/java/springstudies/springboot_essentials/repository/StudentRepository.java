package springstudies.springboot_essentials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstudies.springboot_essentials.domain.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
  List<Student> findByName(String name);
}
