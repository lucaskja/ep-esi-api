package br.com.mestradousp.gerenciadorformularios.repository;

import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
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
    Optional<Student> findByEmail(String email);
}
