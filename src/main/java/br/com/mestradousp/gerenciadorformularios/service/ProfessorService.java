package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.util.ProfessorMapper;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.repository.ProfessorRepository;
import br.com.mestradousp.gerenciadorformularios.utils.EntityValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor validateIfProfessorExists(String professorUspNumber) {
        return new EntityValidator<>(
                professorRepository,
                () -> new NotFoundException("Professor not found"),
                () -> new ConflictException("Professor already exists")
        ).validateIfEntityExists(professorUspNumber);
    }

    public Optional<Professor> findProfessorById(String id) {
        return this.professorRepository.findById(id);
    }

    public ProfessorResponseUpdateDto updateLogin(Professor professor, LoginStatus status) {
        professor.setLoginStatus(status);

        return ProfessorMapper.toResponseUpdateDto(this.professorRepository.save(professor));
    }
}
