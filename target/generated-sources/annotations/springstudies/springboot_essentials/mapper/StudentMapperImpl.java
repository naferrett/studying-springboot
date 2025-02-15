package springstudies.springboot_essentials.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import springstudies.springboot_essentials.domain.Student;
import springstudies.springboot_essentials.requests.StudentPostRequestBody;
import springstudies.springboot_essentials.requests.StudentPutRequestBody;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-04T19:57:38-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Azul Systems, Inc.)"
)
@Component
public class StudentMapperImpl extends StudentMapper {

    @Override
    public Student toStudent(StudentPostRequestBody studentPostRequestBody) {
        if ( studentPostRequestBody == null ) {
            return null;
        }

        Student student = new Student();

        student.setName( studentPostRequestBody.getName() );

        return student;
    }

    @Override
    public Student toStudent(StudentPutRequestBody studentPutRequestBody) {
        if ( studentPutRequestBody == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( studentPutRequestBody.getId() );
        student.setName( studentPutRequestBody.getName() );

        return student;
    }
}
