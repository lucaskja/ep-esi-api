package br.com.mestradousp.gerenciadorformularios.enums;

import lombok.Getter;

@Getter
public enum StudentStatus {
    ENROLLED("enrolled"),
    FINISHED("finished"),
    BREAK("break");

    private final String status;

    private StudentStatus(String status) {
        this.status = status;
    }
}
