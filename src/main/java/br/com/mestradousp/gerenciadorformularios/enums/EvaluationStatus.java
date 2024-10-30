package br.com.mestradousp.gerenciadorformularios.enums;

public enum EvaluationStatus {
    PENDING("Pending review by professor"),
    IN_REVIEW("Pending review by CCP"),
    APPROVED("Approved by CCP"),
    REJECTED("Rejected by CCP");

    private final String description;

    EvaluationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
