package springstudies.springboot_essentials.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import springstudies.springboot_essentials.domain.Student;
import springstudies.springboot_essentials.exception.BadRequestException;
import springstudies.springboot_essentials.mapper.StudentMapper;
import springstudies.springboot_essentials.repository.StudentRepository;
import springstudies.springboot_essentials.requests.StudentPostRequestBody;
import springstudies.springboot_essentials.requests.StudentPutRequestBody;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Page<Student> listAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student findByIdOrThrowBadRequestException(long id) {
        return studentRepository.findById(id)
                .orElseThrow(
                        () -> new BadRequestException("Student Not Found"));
                        //() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student Not Found"));

    }

    @Transactional
    public Student save(StudentPostRequestBody studentPostRequestBody) {
        return studentRepository.save(StudentMapper.INSTANCE.toStudent(studentPostRequestBody));
    }

    public void delete(long id) {
        studentRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void update(StudentPutRequestBody studentPutRequestBody) {
        Student savedStudent = findByIdOrThrowBadRequestException(studentPutRequestBody.getId());

        Student student = StudentMapper.INSTANCE.toStudent(studentPutRequestBody);
        student.setId(savedStudent.getId());

        studentRepository.save(student);
    }
}
