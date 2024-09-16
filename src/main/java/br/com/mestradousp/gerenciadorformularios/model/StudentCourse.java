package br.com.mestradousp.gerenciadorformularios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students_courses")
public class StudentCourse {
    @EmbeddedId
    StudentCourseKey id;

    @ManyToOne
    @MapsId("studentUspNumber")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId("courseName")
    @JoinColumn(name = "course_id")
    Course course;

    private Double grade;
}
