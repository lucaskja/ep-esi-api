package br.com.mestradousp.gerenciadorformularios.enums;

public enum EvaluationStatus {
    PENDING_REVIEW_BY_PROFESSOR("Pending review by professor"),
    REVIEWED_BY_PROFESSOR("Reviewed by professor"),
    PENDING_REVIEW_BY_CCP("Pending review by CCP"),
    REVIEWED_BY_CCP("Reviewed by CCP"),
    APPROVED_BY_CCP("Approved by CCP"),
    REJECTED_BY_CCP("Rejected by CCP");

    private final String description;

    EvaluationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
