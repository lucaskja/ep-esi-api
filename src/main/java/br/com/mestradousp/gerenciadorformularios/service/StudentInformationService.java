package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.model.StudentInformation;
import br.com.mestradousp.gerenciadorformularios.repository.StudentInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentInformationService {
    private final StudentInformationRepository studentInformationRepository;

    public StudentInformation createStudentInformation(StudentInformation studentInformation) {
        return this.studentInformationRepository.save(studentInformation);
    }
}
