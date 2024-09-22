package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.user.UpdateUserDto;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.service.CcpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ccp")
public class CcpController {
    private final CcpService ccpService;

    @Autowired
    public CcpController(CcpService ccpService) {
        this.ccpService = ccpService;
    }

    @GetMapping("/user/student/status/{status}")
    public ResponseEntity<List<StudentResponseDto>> getAllPendingStudents(@PathVariable LoginStatus status) {
        return ResponseEntity.ok(this.ccpService.getAllPendingStudents(status));
    }

    @PatchMapping("/user/student/update")
    public ResponseEntity<StudentResponseUpdateDto> updateStudentLogin(@RequestBody UpdateUserDto dto) {
        return ResponseEntity.ok(this.ccpService.updateStudentLogin(dto));
    }

    @PatchMapping("/user/professor/update")
    public ResponseEntity<ProfessorResponseUpdateDto> updateProfessorLogin(@RequestBody UpdateUserDto dto) {
        return ResponseEntity.ok(this.ccpService.updateProfessorLogin(dto));
    }
}
