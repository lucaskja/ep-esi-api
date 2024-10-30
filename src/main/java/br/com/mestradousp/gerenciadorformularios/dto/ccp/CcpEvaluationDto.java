package br.com.mestradousp.gerenciadorformularios.dto.ccp;

import br.com.mestradousp.gerenciadorformularios.enums.Opinions;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class CcpEvaluationDto {
    private Long formId;
    private String reviewerName;
    private Roles roles;
    private String feedback;
    private Opinions opinions;
}
