package springstudies.springboot_essentials.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springstudies.springboot_essentials.domain.Student;
import springstudies.springboot_essentials.requests.StudentPostRequestBody;
import springstudies.springboot_essentials.requests.StudentPutRequestBody;
import springstudies.springboot_essentials.service.StudentService;
import springstudies.springboot_essentials.util.DateUtil;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("students")
@Log4j2
public class StudentController {
    private final DateUtil dateUtil;
    private final StudentService studentService;

    public StudentController(DateUtil dateUtil, StudentService studentService) {
        this.dateUtil = dateUtil;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Page<Student>> list(Pageable pageable) {
        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        //returns status message
        return new ResponseEntity<>(studentService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Page<Student>> listAll(Pageable pageable) {
        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        //also returns status message
        return new ResponseEntity<>(studentService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}") //quando colocar o id na url, ele vai retornar o objeto mapeado
    public ResponseEntity<Student> findById(@PathVariable long id) {
        //HttpStatus.OK or ResponseEntity.ok
        return ResponseEntity.ok(studentService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Student>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(studentService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody @Valid StudentPostRequestBody studentPostRequestBody) {
        return new ResponseEntity<>(studentService.save(studentPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody StudentPutRequestBody studentPutRequestBody) {
        studentService.update(studentPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

