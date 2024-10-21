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

    // Método para buscar as disciplinas aprovadas
    public List<String> getApprovedCourses(Long studentId) {
        List<StudentCourse> courses = studentCourseRepository.findByStudentId(studentId);
        // Supondo que uma nota maior ou igual a 5.0 seja considerada aprovada
        return courses.stream()
                .filter(course -> course.getGrade() >= 5.0)
                .map(course -> course.getCourse().getName()) // Assumindo que Course tenha um método getName()
                .collect(Collectors.toList());
    }

    // Método para buscar as disciplinas reprovadas
    public List<String> getFailedCourses(Long studentId) {
        List<StudentCourse> courses = studentCourseRepository.findByStudentId(studentId);
        // Supondo que uma nota menor que 5.0 seja considerada reprovada
        return courses.stream()
                .filter(course -> course.getGrade() < 5.0)
                .map(course -> course.getCourse().getName()) // Assumindo que Course tenha um método getName()
                .collect(Collectors.toList());
    }
}