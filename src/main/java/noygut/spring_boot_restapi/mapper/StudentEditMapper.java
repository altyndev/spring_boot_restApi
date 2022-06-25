package noygut.spring_boot_restapi.mapper;

import noygut.spring_boot_restapi.dto.StudentRequest;
import noygut.spring_boot_restapi.entity.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StudentEditMapper {

    public Student create(StudentRequest request) {

        if (request == null) {

            return null;
        }

        Student student = new Student();

        student.setName(request.getName());

        student.setSurname(request.getSurname());

        student.setAge(request.getAge());

        student.setEmail(request.getEmail());

        student.setCreated(LocalDate.now());

        student.setEnabled(true);

        return student;
    }

    public void update(Student student, StudentRequest request) {

        student.setName(request.getName());

        student.setSurname(request.getSurname());

        student.setAge(request.getAge());

        student.setEmail(request.getEmail());
    }
}
