public enum MenuOperacoes {
    //Menu Principal
    MENU_CLIENTES(1), 
    MENU_SEGURO(2),
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
    MENU_FROTA(13),
    CALC_SEGURO_CLIENTE(14),
    //Menu Veículos (input + 14)
    CADASTRO_VEICULO (15),
    REMOVER_VEICULO (16),
    LISTAR_VEICULO_CLIENTE (17),
    LISTAR_VEICULO_SEGURADORA (18),
    //Menu Frota (input + 18)
    CADASTRO_FROTA (19),
    REMOVER_FROTA (20),
    ADICIONAR_VEICULO_FROTA (21),
    REMOVER_VEICULO_FROTA (22),
    LISTAR_VEICULO_FROTA (23),
    //Menu Seguro (input + 23)
    GERAR_SEGURO(24),
    CANCELAR_SEGURO (25),
    AUTORIZAR_CONDUTOR(26), //NOVO
    DESAUTORIZAR_CONDUTOR(27), //NOVO
    VISUALIZAR_SEGURO (28),
    LISTAR_SEGURO_CLIENTE (29),
    LISTAR_SEGURO_SEGURADORA (30),
    MENU_SINISTRO (31),
    //Menu Sinistro (input + 31)
    GERAR_SINISTRO (32),
    LISTAR_SINISTRO_SEGURO (33),
    ACESSAR_SINISTRO_SEGURO (34),
    REMOVER_SINISTRO_SEGURO (35),
    LISTAR_SINISTRO_CONDUTOR (36),
    ACESSAR_SINISTRO_CONDUTOR (37),
    REMOVER_SINISTRO_CONDUTOR (38),
    LISTA_SINISTRO_CLIENTE (39),
    ;

    private final int value;

    private MenuOperacoes (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
