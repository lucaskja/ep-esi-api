package br.com.mestradousp.gerenciadorformularios.dto.student;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class StudentProfileDto {
    private Long studentId;
    private String uspNumber;
    private String name;
    private String email;
    private LocalDate dob;
    private String rg;
    private String birthPlace;
    private String nationality;
    private String course;
    private String professor;
    private String lattes;
    private LocalDate enrollmentDate;
    private LocalDate qualificationExamApprovalDate;
    private LocalDate languageProficiencyApprovalDate;
    private LocalDate finalWorkDepositDeadline;
    private List<String> approvedCourses;
    private List<String> failedCourses;
}