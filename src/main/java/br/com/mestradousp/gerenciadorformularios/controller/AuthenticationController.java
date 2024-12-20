package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.login.LoginRequestDto;
import br.com.mestradousp.gerenciadorformularios.dto.login.LoginResponseDto;
import br.com.mestradousp.gerenciadorformularios.dto.login.RegisterRequestDto;
import br.com.mestradousp.gerenciadorformularios.exception.BadRequestException;
import br.com.mestradousp.gerenciadorformularios.model.Ccp;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.service.JwtTokenService;
import br.com.mestradousp.gerenciadorformularios.service.ProfessorService;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import br.com.mestradousp.gerenciadorformularios.util.PasswordEncoder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtTokenService tokenService;
    private final AuthenticationManager authentication;
    private final StudentService studentService;
    private final ProfessorService professorService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto) {


        return ResponseEntity.ok(this.generateToken(dto));
    }

    @GetMapping("/crypto")
    public ResponseEntity<String> cryto() {
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        return ResponseEntity.ok(passwordEncoder.encode("abcd12345"));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        if (!dto.role().toString().equals("STUDENT")) {
           throw new BadRequestException("User is not a student");
        }

        this.studentService.createStudent(dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private LoginResponseDto generateToken(LoginRequestDto dto) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        Authentication auth = this.authentication.authenticate(usernamePassword);

        String email = usernamePassword.getPrincipal().toString();

        Student student = this.studentService.findStudentByEmail(email).orElse(null);
        Professor professor = professorService.findByEmail(email).orElse(null);

        if (student != null ) {
            return tokenService.generateToken(
                    (Student) auth.getPrincipal(),
                    ((Student) auth.getPrincipal()).getId(),
                    ((Student) auth.getPrincipal()).getRole()
            );
        }

        if (professor != null) {
            return tokenService.generateToken(
                    (Professor) auth.getPrincipal(),
                    ((Professor) auth.getPrincipal()).getId(),
                    ((Professor) auth.getPrincipal()).getRole()
            );
        }

        return tokenService.generateToken(
                (Ccp) auth.getPrincipal(),
                ((Ccp) auth.getPrincipal()).getId(),
                ((Ccp) auth.getPrincipal()).getRole()
        );
    }
}
