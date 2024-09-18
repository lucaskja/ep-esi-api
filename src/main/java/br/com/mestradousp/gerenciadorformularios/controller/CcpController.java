package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.UserResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseDto;
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

    @PostMapping("/user/update")
    public ResponseEntity<UserResponseUpdateDto> updateUserLogin(@RequestBody UpdateUserDto dto) {
        return ResponseEntity.ok(this.ccpService.updateUserLogin(dto));
    }

    @GetMapping("/user/student/status/{status}")
    public ResponseEntity<List<StudentResponseDto>> getAllPendingStudents(@PathVariable LoginStatus status) {
        return ResponseEntity.ok(this.ccpService.getAllPendingStudents(status));
    }
}
