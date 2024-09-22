package br.com.mestradousp.gerenciadorformularios.dto.util;

import br.com.mestradousp.gerenciadorformularios.dto.student.StudentCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;

import java.util.List;

public abstract class StudentMapper {
    public static Student toModel(StudentCreateDto dto, Professor professor) {
        return new Student(
                dto.uspNumber(),
                dto.name(),
                dto.email(),
                dto.dob(),
                dto.documentNumber(),
                dto.birthPlace(),
                dto.nationality(),
                dto.program(),
                dto.lattes(),
                dto.registrationDate(),
                dto.status(),
                dto.loginStatus(),
                professor,
                null,
                null
        );
    }
    public static Student toModel(StudentResponseDto dto, Professor professor) {
        return new Student(
                null,
                dto.name(),
                dto.email(),
                dto.dob(),
                dto.documentNumber(),
                dto.birthPlace(),
                dto.nationality(),
                dto.program(),
                dto.lattes(),
                dto.registrationDate(),
                dto.status(),
                dto.loginStatus(),
                professor,
                null,
                null
        );
    };

    public static StudentResponseDto toResponseDto(Student model) {
        return new StudentResponseDto(
                model.getName(),
                model.getEmail(),
                model.getDob(),
                model.getDocumentNumber(),
                model.getBirthPlace(),
                model.getNationality(),
                model.getProgram(),
                model.getLattes(),
                model.getRegistrationDate(),
                model.getStatus(),
                model.getLoginStatus(),
                ProfessorMapper.toResponseDto(model.getProfessor()),
                model.getExam(),
                model.getPerformanceReports()
        );
    };

    public static List<StudentResponseDto> toResponseDto(List<Student> models) {
        return models.stream()
                .map(student -> new StudentResponseDto(
                    student.getName(),
                    student.getEmail(),
                    student.getDob(),
                    student.getDocumentNumber(),
                    student.getBirthPlace(),
                    student.getNationality(),
                    student.getProgram(),
                    student.getLattes(),
                    student.getRegistrationDate(),
                    student.getStatus(),
                    student.getLoginStatus(),
                    ProfessorMapper.toResponseDto(student.getProfessor()),
                    student.getExam(),
                    student.getPerformanceReports())
                )
                .toList();
    };

    public static StudentCreateDto toCreateDto(Student model) {
        return new StudentCreateDto(
                model.getUspNumber(),
                model.getName(),
                model.getEmail(),
                model.getDob(),
                model.getDocumentNumber(),
                model.getBirthPlace(),
                model.getNationality(),
                model.getProgram(),
                model.getLattes(),
                model.getRegistrationDate(),
                model.getStatus(),
                model.getLoginStatus(),
                model.getProfessor().getUspNumber()
        );
    };

    public static StudentResponseUpdateDto toResponseUpdateDto(Student model) {
        return new StudentResponseUpdateDto(
                model.getUspNumber(),
                model.getName(),
                ProfessorMapper.toResponseDto(model.getProfessor()),
                model.getLoginStatus()
        );
    };
}
