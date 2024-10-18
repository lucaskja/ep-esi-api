package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.ccp.CcpCreateOpinionDto;
import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorRequestCreateDto;
import br.com.mestradousp.gerenciadorformularios.model.Ccp;
import br.com.mestradousp.gerenciadorformularios.repository.CcpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CcpService {
    private final StudentService studentService;
    private final ProfessorService professorService;
    private final CcpRepository ccpRepository;

    public Optional<Ccp> findCcpByEmail(String email) {
        return ccpRepository.findByEmail(email);
    }

    public Optional<Ccp> findCcpById(Long id) {
        return ccpRepository.findById(id);
    }
}
