package br.com.mestradousp.gerenciadorformularios.enums;

import lombok.Getter;

@Getter
public enum Programs {
    MASTER("master"),
    PHD("phd");

    private final String program;

    private Programs(String program) {
        this.program = program;
    }
}
