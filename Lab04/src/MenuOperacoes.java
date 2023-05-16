public enum MenuOperacoes {
    //Menu Principal
    MENU_CLIENTES(1), 
    MENU_SINISTRO(2),
    CONSULTAR_SEGURADORA(3),
    CALC_RECEITA(4),
    TRANSF_SEGURO (5),
    NOVA_SEGURADORA(6),
    ALTERAR_SEGURADORA(7),
    //Menu Clientes (input + 7)
    CADASTRO_CLIENTE(8),
    REMOVER_CLIENTE(9),
    LISTAR_CLIENTES(10),
    ACESSAR_CLIENTE(11),
    MENU_VEICULOS (12),
    CALC_SEGURO_CLIENTE(13),
    CALC_SCORE_CLIENTE (14),
    //Menu Veículos (input + 14)
    CADASTRO_VEICULO (15),
    REMOVER_VEICULO (16),
    LISTAR_VEICULO_CLIENTE (17),
    LISTAR_VEICULO_SEGURADORA (18),
    //Menu Sinistro (input + 18)
    GERAR_SINISTRO (19),
    LISTAR_SINISTRO (20),
    ACESSAR_SINISTRO (21),
    REMOVER_SINISTRO (22),
    LISTA_SINISTRO_CLIENTE (23),
    ;

    private final int value;

    private MenuOperacoes (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
