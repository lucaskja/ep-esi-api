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

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentResponseDto {
    private String name;

    private String email;

    private LocalDate dob;

    private String documentNumber;

    private String birthPlace;

    private String nationality;

    private Programs program;

    private String lattes;

    private LocalDate registrationDate;

    private StudentStatus status;

    private LoginStatus loginStatus;

    private ProfessorResponseDto professor;

    private Exam exam;

    List<PerformanceReport> performanceReports;
}
