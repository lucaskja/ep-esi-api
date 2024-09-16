package br.com.mestradousp.gerenciadorformularios.repository;

import br.com.mestradousp.gerenciadorformularios.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
