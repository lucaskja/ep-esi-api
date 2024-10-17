package br.com.mestradousp.gerenciadorformularios.security;

import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Ccp;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.service.CcpService;
import br.com.mestradousp.gerenciadorformularios.service.JwtTokenService;
import br.com.mestradousp.gerenciadorformularios.service.ProfessorService;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final JwtTokenService tokenService;
    private final StudentService studentService;
    private final ProfessorService professorService;
    private final CcpService ccpService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = this.recoverToken(request);

        if (token != null) {
            String login = tokenService.validateToken(token);
            Student student = studentService.findStudentByEmail(login).orElse(null);
            Professor professor = professorService.findByEmail(login).orElse(null);
            Ccp ccp = ccpService.findCcpByEmail(login).orElse(null);

            if (student == null && professor == null && ccp == null) {
                throw new NotFoundException("User not found");
            }

            UserDetails user = student != null ? student : professor != null ? professor : ccp;

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        return authHeader == null ? null : authHeader.replace("Bearer ", "");
    }
}
