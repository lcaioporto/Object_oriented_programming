package Enum;

public enum LerDados {
    CLIENTEPF(1),
    CLIENTEPJ(2),
    VEICULOS(3),
    FROTA(4),
    CONDUTOR(5),
    SEGURO(6),
    SINISTRO(7);

    private final int value;

    private LerDados (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
