package noygut.spring_boot_restapi.repository;

import noygut.spring_boot_restapi.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where upper(s.name) like concat('%',:text,'%') " +
            "or upper(s.surname) like concat('%',:text,'%') or upper(s.email) like concat('%',:text,'%')")
    List<Student> searchAndPagination(@Param("text") String text, Pageable pageable);
}