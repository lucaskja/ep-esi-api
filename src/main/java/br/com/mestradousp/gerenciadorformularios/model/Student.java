package br.com.mestradousp.gerenciadorformularios.model;

import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.enums.Programs;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String uspNumber;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoginStatus loginStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private Exam exam;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_information_id", referencedColumnName = "id")
    private StudentInformation studentInformation;

    @JsonIgnoreProperties("students")
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @JsonIgnoreProperties("student")
    @OneToMany(mappedBy = "student")
    List<PerformanceReport> performanceReports;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.loginStatus.toString().equals("APPROVED");
    }

    public String getName() {
    return this.studentInformation.getName();
    }

    public LocalDate getDob() {
        return this.studentInformation.getDob();
    }

    public void setDob() {
        this.studentInformation.setDob(getDob());
    }

    public String getBirthPlace() {
        return this.studentInformation.getBirthPlace();
    }

    public String getNationality() {
        return this.studentInformation.getNationality();
    }

    public Programs getProgram() {
        return this.studentInformation.getProgram();
    }

    public void setProgram(Programs program) {
        this.studentInformation.setProgram(program);
    }

    public String getLattes() {
        return this.studentInformation.getLattes();
    }

    public void setLattes(String lattes) {
        this.studentInformation.setLattes(lattes);
    }
}
