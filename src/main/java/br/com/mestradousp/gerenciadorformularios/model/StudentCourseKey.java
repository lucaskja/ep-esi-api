package br.com.mestradousp.gerenciadorformularios.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseKey implements Serializable {
    @Column(name = "student_id")
    String studentUspNumber;

    @Column(name = "course_id")
    String courseName;
}
