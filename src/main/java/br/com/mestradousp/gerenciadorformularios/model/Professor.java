package br.com.mestradousp.gerenciadorformularios.model;

import jakarta.persistence.*;
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

    private String Name;

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
