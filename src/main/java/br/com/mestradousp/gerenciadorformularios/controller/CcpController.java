package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.UserResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.user.UpdateUserDto;
import br.com.mestradousp.gerenciadorformularios.service.CcpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
