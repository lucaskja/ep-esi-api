package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.student.StudentCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseDto;
import br.com.mestradousp.gerenciadorformularios.dto.util.StudentMapper;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ProfessorService professorService;

    @Autowired
    public StudentService(
            StudentRepository studentRepository,
            ProfessorService professorService

    ) {
        this.studentRepository = studentRepository;
        this.professorService = professorService;
    }

    public StudentResponseDto createStudent(StudentCreateDto studentDto) {
        Professor advisor = professorService.findProfessorById(studentDto.getProfessorUspNumber());

        Student studentToSave = StudentMapper.toModel(studentDto, advisor);

        return StudentMapper.toResponseDto(studentRepository.save(studentToSave));
    }
}
