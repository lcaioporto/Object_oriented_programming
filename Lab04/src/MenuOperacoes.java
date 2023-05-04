public enum MenuOperacoes {
    //Menu Principal
    MENU_CLIENTES(1), 
    MENU_SINISTRO(2),
    CONSULTAR_SEGURADORA(3), 
    NOVA_SEGURADORA(4),
    ALTERAR_SEGURADORA(5),
    CALC_RECEITA(6), //NOVO
    //Menu Clientes (input + 6)
    CADASTRO_CLIENTE(7),
    REMOVER_CLIENTE(8),
    LISTAR_CLIENTES(9),
    ACESSAR_CLIENTE(10),
    MENU_VEICULOS (11),
    CALC_SEGURO(12), //NOVO
    CALC_SCORE (13), //NOVO
    //Menu Veículos (input + 13)
    CADASTRO_VEICULO (14),
    REMOVER_VEICULO (15),
    //Menu Sinistro (input + 15)
    GERAR_SINISTRO (16),
    LISTAR_SINISTRO (17),
    ACESSAR_SINISTRO (18),
    REMOVER_SINISTRO (19),
    ;

    private final int value;

    private MenuOperacoes (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
