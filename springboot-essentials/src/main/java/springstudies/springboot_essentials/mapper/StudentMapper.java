package springstudies.springboot_essentials.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springstudies.springboot_essentials.domain.Student;
import springstudies.springboot_essentials.requests.StudentPostRequestBody;
import springstudies.springboot_essentials.requests.StudentPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {
    public static final StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    public abstract Student toStudent(StudentPostRequestBody studentPostRequestBody);
    public abstract Student toStudent(StudentPutRequestBody studentPutRequestBody);

}
