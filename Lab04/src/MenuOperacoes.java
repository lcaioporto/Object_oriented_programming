public enum MenuOperacoes {
    //Menu Principal
    MENU_CLIENTES(1), 
    MENU_SINISTRO(2),
    CONSULTAR_SEGURADORA(3), 
    NOVA_SEGURADORA(4),
    ALTERAR_SEGURADORA(5),
    CALC_RECEITA(6),
    TRANSF_SEGURO (7),
    //Menu Clientes (input + 7)
    CADASTRO_CLIENTE(8),
    REMOVER_CLIENTE(9),
    LISTAR_CLIENTES(10),
    ACESSAR_CLIENTE(11),
    MENU_VEICULOS (12),
    CALC_SEGURO(13),
    CALC_SCORE (14),
    //Menu Veículos (input + 14)
    CADASTRO_VEICULO (15),
    REMOVER_VEICULO (16),
    //Menu Sinistro (input + 16)
    GERAR_SINISTRO (17),
    LISTAR_SINISTRO (18),
    ACESSAR_SINISTRO (19),
    REMOVER_SINISTRO (20),
    ;

    private final int value;

    private MenuOperacoes (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
