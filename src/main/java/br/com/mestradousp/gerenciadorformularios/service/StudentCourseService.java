package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.model.StudentCourse;
import br.com.mestradousp.gerenciadorformularios.repository.StudentCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;

    public List<String> getApprovedCourses(Long studentId) {
        List<StudentCourse> courses = studentCourseRepository.findByStudentId(studentId);
        return courses.stream()
                .filter(course -> course.getGrade() >= 5.0)
                .map(course -> course.getCourse().getName())
                .collect(Collectors.toList());
    }

    public List<String> getFailedCourses(Long studentId) {
        List<StudentCourse> courses = studentCourseRepository.findByStudentId(studentId);
        return courses.stream()
                .filter(course -> course.getGrade() < 5.0)
                .map(course -> course.getCourse().getName())
                .collect(Collectors.toList());
    }
}