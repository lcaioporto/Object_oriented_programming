import java.util.Scanner;
import java.util.ArrayList;

public class MenuSeguradora {

    /*
     * Abaixo segue-se o código que utiliza todos os métodos desenvolvidos pelas demais classes;
     * Esses métodos são acessados por meio de um Menu interativo baseado em uma Seguradora, que é criada por um método nesta classe Main.
     * Do modo como foi impletado, é possível lidar com várias seguradoras e alterar do menu de uma Seguradora para outra.
     */
    public static Seguradora criarSeguradora(Scanner sc) {
        //Recebe as informações necessárias e cria um objeto da classe Seguradora.
        print("Insira o nome da seguradora: ");
        String nome = sc.nextLine();
        while (! Validacao.validaNome(nome)) {
            print("==============================================================");
            print("Nome inválido! O nome da seguradora deve conter apenas letras!");
            print("Tente novamente.");
            print("==============================================================");
            print("Insira o nome da seguradora: ");
            nome = sc.nextLine();
        }
        print("\nInsira o telefone da seguradora: ");
        String telefone = sc.nextLine();
        print("\nInsira o e-mail da seguradora: ");
        String email = sc.nextLine();
        print("\nInsira o endereço da seguradora: ");
        String endereco = sc.nextLine();
        print("\nInsira o CNPJ da seguradora: ");
        String CNPJ = sc.nextLine();
        while (! Validacao.validarCNPJ(CNPJ)) {
            System.out.println("===============================");
            System.out.println("CNPJ inválido. Tente novamente.");
            System.out.println("===============================");
            System.out.println("\nInsira o CNPJ da seguradora: ");
            CNPJ = sc.nextLine();
        }
        Seguradora s = new Seguradora(nome, telefone, email, endereco, CNPJ);
        return s;
    }

    public static MenuOperacoes getOperacaoByInput (int input) {
        //dado um input, retorna a operação associada.
        for (MenuOperacoes op : MenuOperacoes.values()) {
            if (op.getValue() == input) return op;
        }
        return null; //operacao não existe
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void MenuInterativo (Scanner sc) {
        int input, input_cliente, input_sinistro, input_veiculo, input_seguro, input_frota;
        //Obs.: Cada variavel de input recebe uma constante somada para se adequar aos valores do MenuOpercoes (enum)
        MenuOperacoes op, op_cliente, op_sinistro, op_veiculo, op_seguro, op_frota;
        boolean check;
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>(); //lista que contém todas as seguradoras criadas.
        print("===== CADASTRO DA SEGURADORA =====");
        Seguradora seguradoraAtual = criarSeguradora(sc);
        listaSeguradoras.add(seguradoraAtual); //lista que contém todas as seguradoras criadas
        print("=============================================");
        while (true) { //Menu principal
            print("=== MENU DA SEGURADORA '" + seguradoraAtual.getNome() + "' ===");
            print("=============================================");
            print("Com o que deseja-se lidar?");
            print("1) Clientes");
            print("2) Seguro");
            print("3) Consultar dados da seguradora");
            print("4) Calcular a receita da seguradora");
            print("5) Transferência de seguro");
            print("6) Cadastro de uma nova seguradora");
            print("7) Alterar para outra seguradora");
            print("8) Fechar Menu");
            print("=============================================");
            input = Validacao.validainput(sc, 1, 8);
            if (input == 8) break;
            op = getOperacaoByInput(input);

            switch (op) {
                case MENU_CLIENTES:
                    while (true) {
                        print("===== CLIENTES =====");
                        print("O que você deseja fazer?");
                        print("1) Cadastrar um novo cliente");
                        print("2) Remover um cliente");
                        print("3) Listar todos os clientes da seguradora");
                        print("4) Acessar dados de um determinado cliente");
                        print("5) Lidar com veículos (SOMENTE para pessoa física - PF)");
                        print("6) Lidar com frotas (SOMENTE para pessoa jurídica - PJ)");
                        print("7) Calcular o preço do seguro de determinado cliente");
                        print("8) Retornar ao menu principal");
                        print("==========================");
                        input_cliente = Validacao.validainput(sc, 1, 8) + 7;
                        if (input_cliente == 15) break;
                        op_cliente = getOperacaoByInput(input_cliente);

                        switch (op_cliente) {
                            case CADASTRO_CLIENTE:
                                print("===== Iniciando um novo Cadastro ======");
                                check = seguradoraAtual.cadastrarCliente(sc);
                                print("==================================");
                                if (check) print("Cadastro realizado com sucesso!");
                                else print("Ocorreu um erro ao tentar cadastrar o cliente! Tente novamente.");
                                print("==================================");
                                break;
                            case REMOVER_CLIENTE:
                                print("===== Remoção de clientes ======");
                                check = seguradoraAtual.removerCliente(sc);
                                print("==========================");
                                if (check) print("Cliente removido com sucesso!");
                                print("==========================");
                                break;
                            case LISTAR_CLIENTES:
                                print("===== Listando todos os clientes =====");
                                check = seguradoraAtual.listarClientes();
                                if (!check) {
                                    print("=======================================");
                                    print("Ainda não há nenhum cliente cadastrado!");
                                }
                                print("=======================================");
                                break;
                            case ACESSAR_CLIENTE:
                                check = seguradoraAtual.printInfoCliente(sc);
                                print("==========================");
                                break;
                            
                            case MENU_VEICULOS: //Menu Veículos
                                while (true) {
                                    print("======= VEÍCULOS =======");
                                    print("O que você deseja fazer?");
                                    print("1) Cadastrar um novo veículo");
                                    print("2) Remover um veículo");
                                    print("3) Listar veículos de um cliente");
                                    print("4) Listar veículos da seguradora");
                                    print("5) Retornar ao Menu de Clientes");
                                    print("=========================");
                                    input_veiculo = Validacao.validainput(sc, 1, 5) + 14;
                                    if (input_veiculo == 19) break;
                                    op_veiculo = getOperacaoByInput(input_veiculo);

                                    switch (op_veiculo) {
                                        case CADASTRO_VEICULO:
                                            print("=== CADASTRO DE VEÍCULO ===");
                                            print("Abaixo insira os dados relacionados ao cliente ao qual deseja-se registar um veículo.");
                                            print("Lembre-se que o cliente deve ser uma pessoa física (PF)");
                                            print("======================================================================================");
                                            Cliente cliente = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                                            if (cliente == null) {
                                                break;
                                            }
                                            try {
                                                check = ((ClientePF) cliente).cadastrarVeiculo(sc);
                                            } catch (Exception e) {
                                                print("============================================================");
                                                print("PARA LIDAR COM VEÍCULOS, UTILIZE APENAS PESSOA FÍSICA (PF)!");
                                                print("============================================================");
                                                break;
                                            }
                                            print("============================================================");
                                            if (check) print("Cadastro de veículo realizado com sucesso!");
                                            else print("Ocorreu um erro no cadastro do veículo. Tente novamente.");
                                            print("============================================================");
                                            break;
                                        
                                        case REMOVER_VEICULO:
                                            print("=== REMOÇÃO DE VEÍCULO ===");
                                            print("Abaixo insira os dados relacionados ao cliente ao qual deseja-se remover o veículo.");
                                            print("==========================");
                                            cliente = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                                            if (cliente == null) {
                                                break;
                                            }
                                            try {
                                                check = ((ClientePF) cliente).removeVeiculo(sc);
                                            } catch (Exception e) {
                                                print("============================================================");
                                                print("PARA LIDAR COM VEÍCULOS, UTILIZE APENAS PESSOA FÍSICA (PF)!");
                                                print("============================================================");
                                                break;
                                            }
                                            print("==========================");
                                            if (check) print("Veículo removido com sucesso!");
                                            else print("Ocorreu um erro na remoção do veículo. Tente novamente.");
                                            print("==========================");
                                            break;

                                        case LISTAR_VEICULO_CLIENTE:
                                            print("============================================================");
                                            print("Abaixo insira as informações do cliente (dono dos veículos)!");
                                            print("============================================================");
                                            cliente = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                                            try {
                                                for (Veiculo veiculo : ((ClientePF) cliente).getListaVeiculos()) {
                                                    System.out.println(veiculo);
                                                }
                                            } catch (Exception e) {
                                                print("============================================================");
                                                print("PARA LIDAR COM VEÍCULOS, UTILIZE APENAS PESSOA FÍSICA (PF)!");
                                                print("============================================================");
                                                break;
                                            }
                                            break;
                                        case LISTAR_VEICULO_SEGURADORA:
                                            
                                            print("=========================================");
                                            print("Listando todos os veículos da seguradora!");
                                            print("=========================================");
                                            check = seguradoraAtual.listarVeiculoSeguradora(sc);
                                            if (!check) {
                                                print("=======================================");
                                                print("Ocorreu um erro ao listar todos os veículos.");
                                            }
                                            print("=========================================");
                                            break;

                                        default:
                                            print("==============================================");
                                            print("Selecione apenas entre as opções apresentadas!");
                                            print("==============================================");
                                    }
                                }
                                break;

                            case MENU_FROTA: //Menu Frota
                                while (true) {
                                    print("======= FROTA =======");
                                    print("O que você deseja fazer?");
                                    print("1) Cadastrar uma nova frota");
                                    print("2) Remover uma frota inteira");
                                    print("3) Adicionar veículos à uma frota");
                                    print("4) Remover veículos de uma frota");
                                    print("5) Listar veículos de uma frota");
                                    print("6) Retornar ao Menu de Clientes");
                                    print("=========================");
                                    input_frota = Validacao.validainput(sc, 1, 6) + 18;
                                    if (input_frota == 24) break;
                                    op_frota = getOperacaoByInput(input_frota);

                                    switch (op_frota) {
                                        case CADASTRO_FROTA:
                                            print("===== CADASTRO DE FROTA =====");
                                            print("Abaixo insira os dados relacionados ao cliente ao qual deseja-se registar a frota.");
                                            print("Lembre-se que o cliente deve ser uma pessoa jurídica (PJ).");
                                            print("======================================================================================");
                                            Cliente cliente = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                                            if (Validacao.isNull(cliente)) {
                                                break;
                                            }
                                            try {
                                                check = ((ClientePJ) cliente).cadastrarFrota(sc);
                                            } catch (Exception e) {
                                                print("==============================================================");
                                                print("PARA LIDAR COM VEÍCULOS, UTILIZE APENAS PESSOA JURÍDICA (PJ)!");
                                                print("==============================================================");
                                                break;
                                            }
                                            print("============================================================");
                                            if (check) { 
                                                print("Cadastro da frota realizado com sucesso!");
                                            }
                                            else {
                                                print("Ocorreu um erro no cadastro da frota. Tente novamente.");
                                            }
                                            print("============================================================");
                                            break;
                                        
                                        case REMOVER_FROTA:
                                            print("=== REMOÇÃO DE UMA FROTA ===");
                                            print("Abaixo insira os dados relacionados ao cliente ao qual deseja-se remover a frota.");
                                            print("==========================");
                                            cliente = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                                            if (Validacao.isNull(cliente)) {
                                                break;
                                            }
                                            check = ((ClientePJ) cliente).atualizarFrota(sc, ClientePJ.REMOVER_FROTA);
                                            print("==========================");
                                            if (check) { 
                                                print("Frota removida com sucesso!");
                                            }
                                            else {
                                                print("Ocorreu um erro na remoção da frota. Tente novamente.");
                                            }
                                            print("==========================");
                                            break;

                                        case REMOVER_VEICULO_FROTA:
                                            print("===== REMOÇÃO DE UM VEÍCULO DE UMA FROTA =====");
                                            print("Abaixo insira os dados relacionados ao cliente ao qual deseja-se remover o veículo.");
                                            print("=====================================================================================");
                                            cliente = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                                            if (Validacao.isNull(cliente)) {
                                                break;
                                            }
                                            check = ((ClientePJ) cliente).atualizarFrota(sc, ClientePJ.REMOVER_VEICULOS_FROTA);
                                            print("==========================");
                                            if (check) { 
                                                print("Veículo removido com sucesso!");
                                            }
                                            else {
                                                print("Ocorreu um erro na remoção do veículo. Tente novamente.");
                                            }
                                            print("==========================");
                                            break;

                                        case ADICIONAR_VEICULO_FROTA:
                                            print("===== ADIÇÃO DE UM VEÍCULO À UMA FROTA =====");
                                            print("Abaixo insira os dados relacionados ao cliente ao qual deseja-se adicionar o veículo.");
                                            print("=====================================================================================");
                                            cliente = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                                            if (Validacao.isNull(cliente)) {
                                                break;
                                            }
                                            check = ((ClientePJ) cliente).atualizarFrota(sc, ClientePJ.ADICIONAR_VEICULOS_FROTA);
                                            print("==========================");
                                            if (check) { 
                                                print("Veículo adicionado com sucesso!");
                                            }
                                            else {
                                                print("Ocorreu um erro na adição do veículo. Tente novamente.");
                                            }
                                            print("==========================");
                                            break;

                                        case LISTAR_VEICULO_FROTA:
                                            print("====================== LISTANDO TODOS OS VEÍCULOS DE UMA FROTA ====================================");
                                            print("Abaixo insira os dados relacionados ao cliente ao qual deseja-se listar a frota.");
                                            cliente = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                                            check = ((ClientePJ) cliente).getVeiculosPorFrota(sc);
                                            if (!check) {
                                                print("Ocorreu um erro ao tentar listar os veículos.");
                                                print("Tente novamente...");
                                                print("=======================================================");
                                            }
                                            break;

                                        default:
                                            print("==============================================");
                                            print("Selecione apenas entre as opções apresentadas!");
                                            print("==============================================");
                                    }
                                }
                                break;

                            case CALC_SEGURO_CLIENTE:
                                print("===== CALCULAR SEGURO DO CLIENTE =====");
                                Cliente cliente = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                                if (cliente == null) break;
                                double valor = 0;
                                for (Seguro currSeguro : seguradoraAtual.getListaSeguros()) {
                                    if (currSeguro.getCliente().equals(cliente)) {
                                        valor = currSeguro.calcularValor();
                                        break;
                                    }
                                }
                                if (valor != 0) {
                                    print("======================================");
                                    print("Valor do seguro: " + String.format("%.2f", valor).replace(",", ".") + " reais.");
                                    print("======================================");
                                } else {
                                    print("Não há nenhum seguro associado a este cliente.");
                                }
                                break;
                            
                            default:
                                print("==============================================");
                                print("Selecione apenas entre as opções apresentadas!");
                                print("==============================================");
                        }
                    }
                    break;

                case MENU_SEGURO: // Menu do Sinistro
                    while (true) {
                        print("====== SEGURO ======");
                        print("O que você deseja fazer?");
                        print("1) Gerar um novo seguro");
                        print("2) Cancelar um seguro");
                        print("3) Autorizar um condutor");
                        print("4) Desautorizar um condutor");
                        print("5) Visualizar um seguro");
                        print("6) Listar todos os seguros de um cliente");
                        print("7) Listar todos os seguros da seguradora");
                        print("8) Lidar com sinistros");
                        print("9) Retornar ao menu principal");
                        print("==========================");
                        input_seguro = Validacao.validainput(sc, 1, 9) + 23;
                        if (input_seguro == 32) break;
                        op_seguro = getOperacaoByInput(input_seguro);

                        switch (op_seguro) {
                            case GERAR_SEGURO:
                                print("===== Gerar um seguro =====");
                                check = false;
                                check = seguradoraAtual.gerarSeguro(sc);
                                print("===========================================================");
                                if (check) print("Seguro gerado com sucesso!");
                                else print("Ocorreu um erro ao gerar o seguro. Tente novamente.");
                                print("===========================================================");
                                break;
                            case CANCELAR_SEGURO:
                                print("===== Remover um seguro =====");
                                check = seguradoraAtual.cancelarSeguro(sc);
                                print("========================================");
                                if (!check) {
                                    print("O seguro buscado não existe. Tente novamente.");
                                }
                                else print("Seguro cancelado com sucesso.");
                                print("========================================");
                                break;
                            case AUTORIZAR_CONDUTOR:
                                print("===== AUTORIZAR_CONDUTOR =====");
                                Seguro seg = Buscar.buscarSeguro(sc, seguradoraAtual.getListaSeguros());
                                seg.autorizarCondutor(sc);
                                break;
                            case DESAUTORIZAR_CONDUTOR:
                                print("===== DESAUTORIZAR CONDUTOR =====");
                                seg = Buscar.buscarSeguro(sc, seguradoraAtual.getListaSeguros());
                                seg.desautorizarCondutor(sc);
                                break;
                            case VISUALIZAR_SEGURO:
                                print("===== Visualizar um seguro =====");
                                Seguro s = Buscar.buscarSeguro(sc, seguradoraAtual.getListaSeguros());
                                System.out.println(s);
                                print("========================================");
                                break;
                            case LISTAR_SEGURO_CLIENTE:
                                print("===== Listando um sinistro =====");
                                ArrayList<Seguro> listaSegurosCliente = seguradoraAtual.getSegurosPorCliente(sc);
                                for (Seguro seguro : listaSegurosCliente) {
                                    System.out.println(seguro);
                                }
                                print("========================================");
                                break;
                            case LISTAR_SEGURO_SEGURADORA:
                                print("======= Listando todos os seguros da seguradora =======");
                                for (Seguro seguro : seguradoraAtual.getListaSeguros()) {
                                    System.out.println(seguro);
                                }
                                print("=======================================================");
                                break;
                            case MENU_SINISTRO:
                                while (true) {
                                    print("====== SINISTRO ======");
                                    print("O que você deseja fazer?");
                                    print("1) Gerar um novo sinistro");
                                    print("2) Listar todos os sinistros de um seguro");
                                    print("3) Visualizar um sinistro de um seguro");
                                    print("4) Remover um sinistro de um seguro");
                                    print("5) Listar todos os sinistros de um condutor");
                                    print("6) Visualizar um sinistro de um condutor");
                                    print("7) Remover um sinistro de um condutor");
                                    print("8) Listar todos os sinistros de um cliente");
                                    print("9) Retornar ao menu do seguro");
                                    print("==========================");
                                    input_sinistro = Validacao.validainput(sc, 1, 9) + 31;
                                    if (input_sinistro == 40) break;
                                    op_sinistro = getOperacaoByInput(input_sinistro);
                
                                    switch (op_sinistro) {
                                        case GERAR_SINISTRO:
                                            print("===== Gerar um Sinistro =====");
                                            Seguro seguro = Buscar.buscarSeguro(sc, seguradoraAtual.getListaSeguros());
                                            if (Validacao.isNull(seguro)) {
                                                break;
                                            }
                                            check = seguro.gerarSinistro(sc, seguradoraAtual);
                                            if (check) { 
                                                print("Sinistro gerado com sucesso!");
                                            }
                                            else { 
                                                print("Ocorreu um erro ao gerar o sinistro. Tente novamente.");
                                            }
                                            print("===========================================================");
                                            break;
                                        case LISTAR_SINISTRO_SEGURO:
                                            print("===== Listar todos os sinistros de um seguro =====");
                                            seguro = Buscar.buscarSeguro(sc, seguradoraAtual.getListaSeguros());
                                            if (seguro == null || seguro.getListaSinistros().size() == 0) {
                                                print("=====================================================");
                                                print("Ainda não há nenhum sinistro cadastrado neste seguro!");
                                                print("=====================================================");
                                            }
                                            else {
                                                for (Sinistro sinsitro : seguro.getListaSinistros()) {
                                                    System.out.println(sinsitro);
                                                }
                                                print("========================================");
                                            }
                                            break;
                                        case ACESSAR_SINISTRO_SEGURO:
                                            print("===== Visualizar um sinistro =====");
                                            seguro = Buscar.buscarSeguro(sc, seguradoraAtual.getListaSeguros());
                                            if (Validacao.isNull(seguro)) {
                                                break;
                                            }
                                            else {
                                                Sinistro sinistro = Buscar.buscarSinistro(sc, seguro.getListaSinistros());
                                                if (Validacao.isNull(sinistro)) {
                                                    break;
                                                }
                                                else {
                                                    System.out.println(sinistro);
                                                    print("========================================");
                                                }
                                            }
                                            break;
                                        case REMOVER_SINISTRO_SEGURO:
                                            print("===== Remover um sinistro de um seguro =====");
                                            seguro = Buscar.buscarSeguro(sc, seguradoraAtual.getListaSeguros());
                                            if (Validacao.isNull(seguro)) {
                                                break;
                                            }
                                            check = seguro.removerSinistro(sc);
                                            if (!check) {
                                                print("O sinistro buscado não existe. Tente novamente.");
                                            }
                                            else { 
                                                print("Sinsitro removido com sucesso.");
                                            }
                                            print("========================================");
                                            break;
                                        case LISTAR_SINISTRO_CONDUTOR:
                                            print("===== Listar todos os sinistros de um condutor =====");
                                            seguro = Buscar.buscarSeguro(sc, seguradoraAtual.getListaSeguros());
                                            if (Validacao.isNull(seguro)) {
                                                break;
                                            }
                                            Condutor condutor = Buscar.buscaCondutor(sc, seguro.getListaCondutores());
                                            if (Validacao.isNull(condutor)) {
                                                break;
                                            }
                                            if (condutor.getListaSinistros().size() == 0) {
                                                print("========================================================");
                                                print("Ainda não há nenhum sinistro cadastrado neste condutor!");
                                                print("========================================================");
                                            }
                                            else {
                                                for (Sinistro sinsitro : condutor.getListaSinistros()) {
                                                    System.out.println(sinsitro);
                                                }
                                                print("========================================");
                                            }
                                            break;
                                        case ACESSAR_SINISTRO_CONDUTOR:
                                            print("===== Visualizar um sinistro de um condutor =====");
                                            seguro = Buscar.buscarSeguro(sc, seguradoraAtual.getListaSeguros());
                                            if (Validacao.isNull(seguro)) {
                                                break;
                                            }
                                            else {
                                                condutor = Buscar.buscaCondutor(sc, seguro.getListaCondutores());
                                                if (Validacao.isNull(condutor)) {
                                                    break;
                                                }
                                                Sinistro sinistro = Buscar.buscarSinistro(sc, condutor.getListaSinistros());
                                                if (Validacao.isNull(sinistro)) {
                                                    break;
                                                }
                                                else {
                                                    System.out.println(sinistro);
                                                    print("========================================");
                                                }
                                            }
                                            break;
                                        case REMOVER_SINISTRO_CONDUTOR:
                                            print("===== Remover um sinistro de um condutor =====");
                                            seguro = Buscar.buscarSeguro(sc, seguradoraAtual.getListaSeguros());
                                            if (Validacao.isNull(seguro)) {
                                                break;
                                            }
                                            condutor = Buscar.buscaCondutor(sc, seguro.getListaCondutores());
                                            if (Validacao.isNull(condutor)) {
                                                break;
                                            }
                                            check = condutor.removerSinistro(sc);
                                            if (!check) {
                                                print("O sinistro buscado não existe. Tente novamente.");
                                            }
                                            else { 
                                                print("Sinsitro removido com sucesso.");
                                            }
                                            print("========================================");
                                            break;
                                        case LISTA_SINISTRO_CLIENTE:
                                            print("=======================================");
                                            ArrayList<Sinistro> listaSinistroCliente = seguradoraAtual.getSinistrosPorCliente(sc);
                                            if (listaSinistroCliente.size() == 0) {
                                                print("============================================");
                                                print("O cliente em questão não possui sinistros!");
                                            }
                                            else {
                                                for (Sinistro sinistro : listaSinistroCliente) {
                                                    System.out.println(sinistro);
                                                }
                                            }
                                            print("============================================");
                                            break;
                                        default:
                                            print("==============================================");
                                            print("Selecione apenas entre as opções apresentadas!");
                                            print("==============================================");
                                    }
                                }
                                break;
                            default:
                                print("==============================================");
                                print("Selecione apenas entre as opções apresentadas!");
                                print("==============================================");
                        }
                    }
                    break;

            case CONSULTAR_SEGURADORA:
                System.out.println(seguradoraAtual);
                break;
            
            case NOVA_SEGURADORA:
                print("===== CADASTRO DA NOVA SEGURADORA ===");
                Seguradora seguradoraAux = criarSeguradora(sc);
                listaSeguradoras.add(seguradoraAux);
                seguradoraAtual = seguradoraAux;
                print("========================================================================================");
                print("Novo cadastro realizado com sucesso! Agora você retornará ao Menu desta nova seguradora!");
                print("========================================================================================");
                break;
            
            case ALTERAR_SEGURADORA:
                if (listaSeguradoras.size() == 1) {
                    print("=========================================");
                    print("Ainda não há outra seguradora cadastrada!");
                    print("=========================================");
                }
                else {
                    print("===== SELECIONAR SEGURADORA =====");
                    for (int i = 0; i < listaSeguradoras.size(); i++) {
                        print((i + 1) + ") " + listaSeguradoras.get(i).getNome());
                    }
                    print("==================================");
                    print("Entre com um número: ");
                    int n = Integer.parseInt(sc.nextLine());
                    if (n < listaSeguradoras.size() + 2) {
                        seguradoraAtual = listaSeguradoras.get(n - 1);
                        print("==================================");
                        print("Seguradora alterada com sucesso!");
                        print("==================================");
                    }
                    else {
                        print("===========================================");
                        print("O número digitado não está entre as opções!");
                        print("===========================================");
                    }
                }
                break;
                
                case CALC_RECEITA:
                    print("======== RECEITA DA SEGURADORA ========");
                    print("Valor da receita: " + String.format("%.2f", seguradoraAtual.calcularReceita()).replace(",", ".") + " reais.");
                    print("=======================================");
                    break;

                case TRANSF_SEGURO:
                    print("===== TRANSFERÊNCIA DE SEGURO =====");
                    print("Insira os dados referentes ao cliente de origem:");
                    Cliente cIni = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                    if (cIni != null) {
                        print("=================================================");
                        print("Insira os dados referentes ao cliente final (que irá receber o seguro):");
                        Cliente cFinal = Buscar.buscarCliente(sc, seguradoraAtual.getListaClientes());
                        check = false;
                        check = seguradoraAtual.transferirSeguro(cIni, cFinal);
                        print("=================================================");
                        if (check) {
                            print("Seguro transferido com sucesso!");
                        }
                        else {
                            print("Ocorreu um erro e a transferência não foi realizada.");
                        }
                    } else {
                        print("Ocorreu um erro e a transferência não foi realizada.");
                    }
                    print("=================================================");
                    break;
                default:
                    print("==============================================");
                    print("Selecione apenas entre as opções apresentadas!");
                    print("==============================================");
            }
        }
        print("===== MENU ENCERRADO =====");
        sc.close();
    }
}