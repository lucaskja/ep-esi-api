package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Ccp;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {
    private final CcpService ccpService;
    private final ProfessorService professorService;
    private final StudentService studentService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentService.findStudentByEmail(username).orElse(null);
        Professor professor = professorService.findByEmail(username).orElse(null);
        Ccp ccp = ccpService.findCcpByEmail(username).orElse(null);

        if (student == null && professor == null && ccp == null) {
            throw new NotFoundException("User not found");
        }

        return student != null ? student : professor != null ? professor : ccp;
    }
}
