package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.professor.GetProfessorDto;
import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorRequestCreateDto;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.repository.ProfessorRepository;
import br.com.mestradousp.gerenciadorformularios.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder = new PasswordEncoder();

    public Optional<Professor> findProfessorById(Long id) {
        return this.professorRepository.findById(id);
    }

    public Optional<Professor> findByEmail(String email) {
        return this.professorRepository.findByEmail(email);
    }

    public void createProfessor(ProfessorRequestCreateDto dto) {
        this.findByEmail(dto.email()).ifPresent(professor -> {throw new ConflictException("Email already registered");});

        this.professorRepository.save(
                Professor.builder()
                        .name(dto.name())
                        .email(dto.email())
                        .password(this.passwordEncoder.encode(dto.password()))
                        .uspNumber(dto.uspNumber())
                        .role(dto.role())
                        .build()
        );
    }

    public List<GetProfessorDto> getAllProfessors() {
        return this.professorRepository.findAll().stream()
                .map(professor -> GetProfessorDto.builder()
                        .professorId(professor.getId())
                        .professorName(professor.getName())
                        .build()
                )
                .toList();
    }
}
