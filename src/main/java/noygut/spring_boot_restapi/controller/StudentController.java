package noygut.spring_boot_restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import noygut.spring_boot_restapi.dto.StudentRequest;
import noygut.spring_boot_restapi.dto.StudentResponse;
import noygut.spring_boot_restapi.dto.StudentResponseView;
import noygut.spring_boot_restapi.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
@Tag(name = "Student API", description = "User with role admin can add, update, delete or get all students")
public class StudentController {

    private final StudentService service;

    @PostMapping("/save")
    @Operation(summary = "create student", description = "we can create student")
    public StudentResponse create(@RequestBody StudentRequest request) {

        return service.create(request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "update student", description = "we can update student by id")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request) {

        return service.update(id, request);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "find student", description = "we can find student by id")
    public StudentResponse findById(@PathVariable Long id) {

        return service.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete student", description = "we can delete student by id")
    public StudentResponse delete(@PathVariable Long id) {

        return service.deleteById(id);
    }

    @GetMapping("/all")
    public List<StudentResponse> findAll() {

        return service.findAllStudents();
    }

    @GetMapping
    @Operation(summary = "Get allStudentAndSearch", description = "We can get all students and search")
    public StudentResponseView findAllStudents(
            @RequestParam(name = "text", required = false) String text,
            @RequestParam int page, @RequestParam int size) {

        return service.findAllStudentsPagination(text, page, size);
    }
}
