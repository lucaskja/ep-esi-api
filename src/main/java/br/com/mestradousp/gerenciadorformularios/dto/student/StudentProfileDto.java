package br.com.mestradousp.gerenciadorformularios.dto.student;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record StudentProfileDto(
        Long studentId,
        String uspNumber,
        String name,
        String email,
        LocalDate dob,
        String rg,
        String birthPlace,
        String nationality,
        String course,
        String professor,
        String lattes,
        LocalDate enrollmentDate,
        LocalDate qualificationExamApprovalDate,
        LocalDate languageProficiencyApprovalDate,
        LocalDate finalWorkDepositDeadline,
        List<String> approvedCourses,
        List<String> failedCourses
) {
}