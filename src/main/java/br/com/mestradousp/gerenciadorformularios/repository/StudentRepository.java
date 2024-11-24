package br.com.mestradousp.gerenciadorformularios.repository;

import br.com.mestradousp.gerenciadorformularios.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s LEFT JOIN s.studentInformation si WHERE si.status = 'ENROLLED'")
    List<Student> findStudentsWithZeroPerformanceReport();

    @Query("SELECT s FROM Student s LEFT JOIN s.studentInformation si LEFT JOIN s.performanceReports WHERE si.status = 'ENROLLED' AND s.professor.id = :professorId")
    List<Student> findByProfessorId(@Param("professorId") Long professorId);

    @Query("SELECT s FROM Student s LEFT JOIN s.studentInformation si LEFT JOIN s.performanceReports pr WHERE si.status = 'ENROLLED' AND s.id = :studentId AND s.professor.id = :professorId")
    Student findByProfessorIdAndStudentId(@Param("professorId") Long professorId, @Param("studentId") Long studentId);

    Optional<Student> findByEmail(String email);
}
