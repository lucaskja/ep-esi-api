package br.com.mestradousp.gerenciadorformularios.dto.ccp;

import br.com.mestradousp.gerenciadorformularios.enums.Opinions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CcpCreateOpinionDto(
        @NotNull
        Long ccpId,

        @NotBlank
        String ccpOpinion,

        @NotNull
        Opinions ccpFinalOpinion
) {
}
