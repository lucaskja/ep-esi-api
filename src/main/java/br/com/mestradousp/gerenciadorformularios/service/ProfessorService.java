package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.util.ProfessorMapper;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public void validateIfProfessorExists(String id) {
        this.professorRepository.findById(id).orElseThrow(() -> new NotFoundException("Professor not found"));
    }

    public Professor findProfessorById(String id) {
        return this.professorRepository.findById(id).orElse(null);
    }

    public ProfessorResponseUpdateDto updateLogin(Professor professor, LoginStatus status) {
        professor.setLoginStatus(status);

        return ProfessorMapper.toResponseUpdateDto(this.professorRepository.save(professor));
    }
}
