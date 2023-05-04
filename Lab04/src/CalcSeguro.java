public enum CalcSeguro {
    //constantes para os cálculos do seguro
    VALOR_BASE (100.0),
    FATOR_18_30 (1.2),
    FATOR_30_60 (1.0),
    FATOR_60_90 (1.5);

    private double value;
    private CalcSeguro (double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
