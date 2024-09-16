package br.com.mestradousp.gerenciadorformularios.enums;

import lombok.Getter;

@Getter
public enum LoginStatus {
    PENDENT("pendent"),
    APPROVED("approved"),
    DISAPPROVED("disapproved");

    private final String status;

    private LoginStatus(String status) {
        this.status = status;
    }
}