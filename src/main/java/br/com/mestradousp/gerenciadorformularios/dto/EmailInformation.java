package br.com.mestradousp.gerenciadorformularios.dto;

import java.time.LocalDate;

public record EmailInformation(
        String email,
        String name,

        LocalDate dateToSendDeadline
) {
}
