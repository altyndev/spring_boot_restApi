package noygut.spring_boot_restapi.service;

import lombok.RequiredArgsConstructor;
import noygut.spring_boot_restapi.dto.StudentRequest;
import noygut.spring_boot_restapi.dto.StudentResponse;
import noygut.spring_boot_restapi.dto.StudentResponseView;
import noygut.spring_boot_restapi.entity.Student;
import noygut.spring_boot_restapi.mapper.StudentEditMapper;
import noygut.spring_boot_restapi.mapper.StudentViewMapper;
import noygut.spring_boot_restapi.repository.StudentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    private final StudentEditMapper editMapper;

    private final StudentViewMapper viewMapper;

    public StudentResponse create(StudentRequest studentRequest) {

        Student student = editMapper.create(studentRequest);

        repository.save(student);

        return viewMapper.viewStudent(student);
    }

    public StudentResponse update(Long id, StudentRequest studentRequest) {

        Student student = repository.findById(id).get();

        editMapper.update(student, studentRequest);

        return viewMapper.viewStudent(student);
    }

    public StudentResponse findById(Long id) {

        Student student = repository.findById(id).get();

        return viewMapper.viewStudent(student);
    }

    public StudentResponse deleteById(Long id) {

        Student student = repository.findById(id).get();

        repository.delete(student);

        return viewMapper.viewStudent(student);
    }

    public List<StudentResponse> findAllStudents() {

        return viewMapper.view(repository.findAll());
    }

    public StudentResponseView findAllStudentsPagination(String text, int page, int size) {

        StudentResponseView responseView = new StudentResponseView();

        Pageable pageable = PageRequest.of(page-1, size);

        responseView.setResponses(view(search(text, pageable)));

        return responseView;
    }

    private List<StudentResponse> view(List<Student> students) {

        List<StudentResponse> responses = new ArrayList<>();

        for (Student student : students) {

            responses.add(viewMapper.viewStudent(student));
        }

        return responses;
    }

    private List<Student> search(String name, Pageable pageable) {

        String text = name == null ? "" : name;

        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}
