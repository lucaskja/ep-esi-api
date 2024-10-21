package br.com.mestradousp.gerenciadorformularios.repository;

import br.com.mestradousp.gerenciadorformularios.model.StudentCourse;
import br.com.mestradousp.gerenciadorformularios.model.StudentCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseKey> {
    List<StudentCourse> findByStudentId(Long studentId);
}
