package br.com.mestradousp.gerenciadorformularios.repository;

import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAllByLoginStatusEquals(LoginStatus status);
}