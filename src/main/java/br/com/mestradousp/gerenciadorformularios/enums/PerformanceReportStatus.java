package br.com.mestradousp.gerenciadorformularios.enums;

import lombok.Getter;

@Getter
public enum PerformanceReportStatus {
    SEND("send"),
    PROFESSOR_ANALYZING("professorAnalyzing"),
    CCP_ANALYZING("ccpAnalyzing"),
    REVISED("revised");

    private final String status;

    private PerformanceReportStatus(String status) {
        this.status = status;
    }
}
