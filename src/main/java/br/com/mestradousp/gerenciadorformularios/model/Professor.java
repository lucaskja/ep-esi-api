package br.com.mestradousp.gerenciadorformularios.model;

import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professors")
public class Professor {
    @Id
    private String uspNumber;

    @NotBlank
    private String Name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoginStatus loginStatus;

    @OneToMany(mappedBy = "professor")
    private Set<Student> students;

    @ManyToMany
    @JoinTable(
            name = "professors_courses",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    Set<Course> taughtCourses;
}
