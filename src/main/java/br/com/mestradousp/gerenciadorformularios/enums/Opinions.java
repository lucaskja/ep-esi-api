package br.com.mestradousp.gerenciadorformularios.enums;

public enum Opinions {
    ADEQUATE("adequate"),
    ADEQUATE_WITH_CAVEATS("adequateWithCaveats"),
    UNSATISFYING("unsatisfying");

    private final String status;

    private Opinions(String status) {
        this.status = status;
    }
}
