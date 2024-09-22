package br.com.mestradousp.gerenciadorformularios.dto.student;

import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorResponseDto;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.enums.Programs;
import br.com.mestradousp.gerenciadorformularios.enums.StudentStatus;
import br.com.mestradousp.gerenciadorformularios.model.Exam;
import br.com.mestradousp.gerenciadorformularios.model.PerformanceReport;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public record StudentResponseDto(
        String name,
        String email,
        LocalDate dob,
        String documentNumber,
        String birthPlace,
        String nationality,
        Programs program,
        String lattes,
        LocalDate registrationDate,
        StudentStatus status,
        LoginStatus loginStatus,
        ProfessorResponseDto professor,
        Exam exam,
        List<PerformanceReport> performanceReports
) {}
