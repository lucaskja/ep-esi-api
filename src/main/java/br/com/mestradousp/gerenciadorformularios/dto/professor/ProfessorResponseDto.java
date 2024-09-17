package br.com.mestradousp.gerenciadorformularios.dto.professor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class ProfessorResponseDto {
    private String name;
    private String upsNumber;
}
