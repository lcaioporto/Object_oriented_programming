import java.util.Scanner;
import java.util.ArrayList;
public class AppMain {
    /*
     * Abaixo segue-se o código que utiliza todos os métodos desenvolvidos pelas demais classes;
     * Esses métodos são acessados por meio de um Menu interativo baseado em uma Seguradora, que é criada por um método nesta classe Main.
     * Do modo como foi impletado, é possível lidar com várias seguradoras e alterar do menu de uma Seguradora para outra.
     */
    //TERMINAR TRANSFERIR SEGURO
    public static Seguradora criarSeguradora(Scanner sc) {
        //Recebe as informações necessárias e cria um objeto da classe Seguradora.
        print("Insira o nome da seguradora: ");
        String nome = sc.nextLine();
        print("\nInsira o telefone da seguradora: ");
        String telefone = sc.nextLine();
        print("\nInsira o e-mail da seguradora: ");
        String email = sc.nextLine();
        print("\nInsira o endereço da seguradora: ");
        String endereco = sc.nextLine();
        Seguradora s = new Seguradora(nome, telefone, email, endereco);
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input, input_cliente, input_sinistro, input_veiculo;
        //Obs.: Cada variavel de input recebe uma constante somada para se adequar aos valores do MenuOpercoes (enum)
        MenuOperacoes op, op_cliente, op_sinistro, op_veiculo;
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
            print("2) Sinistro");
            print("3) Consultar dados da seguradora");
            print("4) Cadastro de uma nova seguradora");
            print("5) Alterar para outra seguradora");
            print("6) Calcular a receita da seguradora");
            print("7) Transferência de seguro");
            print("8) Fechar Menu");
            print("=============================================");
            print("Entre com um número: ");
            input = Integer.parseInt(sc.nextLine());
            if (input == 8) break;
            op = getOperacaoByInput(input);

            switch (op) {
                case MENU_CLIENTES:
                while (true) {
                    print("===== CLIENTES =====");
                    print("O que você deseja fazer?");
                    print("1) Cadastrar um novo cliente");
                    print("2) Remover um cliente");
                    print("3) Listar todos os clientes");
                    print("4) Acessar dados de um determinado cliente");
                    print("5) Lidar com veículos");
                    print("6) Calcular o preço do seguro de determinado cliente");
                    print("7) Calcular o score de um Cliente");
                    print("8) Retornar ao menu principal");
                    print("==========================");
                    print("Entre com um número: ");
                    input_cliente = Integer.parseInt(sc.nextLine()) + 7;
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
                            print("3) Retornar ao Menu de Clientes");
                            print("=========================");
                            print("Entre com um número: ");
                            input_veiculo = Integer.parseInt(sc.nextLine()) + 14;
                            if (input_veiculo == 17) break;
                            op_veiculo = getOperacaoByInput(input_veiculo);

                            switch (op_veiculo) {
                                case CADASTRO_VEICULO:
                                    print("=== CADASTRO DE VEÍCULO ===");
                                    print("Abaixo insira os dados relacionados ao cliente ao qual deseja-se registar um veículo.");
                                    print("==========================");
                                    Cliente cliente = seguradoraAtual.buscarCliente(sc);
                                    if (cliente == null) {
                                        break;
                                    }
                                    check = cliente.cadastrarVeiculo(sc);
                                    print("==========================");
                                    if (check) print("Cadastro de veículo realizado com sucesso!");
                                    else print("Ocorreu um erro no cadastro do veículo. Tente novamente.");
                                    print("==========================");
                                    break;
                                
                                case REMOVER_VEICULO:
                                    print("=== REMOÇÃO DE VEÍCULO ===");
                                    print("Abaixo insira os dados relacionados ao cliente ao qual deseja-se remover o veículo.");
                                    print("==========================");
                                    cliente = seguradoraAtual.buscarCliente(sc);
                                    if (cliente == null) {
                                        break;
                                    }
                                    check = cliente.removeVeiculo(sc);
                                    print("==========================");
                                    if (check) print("Veículo removido com sucesso!");
                                    else print("Ocorreu um erro na remoção do veículo. Tente novamente.");
                                    print("==========================");
                                    break;

                                default:
                                    print("==============================================");
                                    print("Selecione apenas entre as opções apresentadas!");
                                    print("==============================================");
                            }
                        }
                        break;

                        case CALC_SEGURO:
                            print("===== CALCULAR SEGURO DO CLIENTE =====");
                            Cliente c = seguradoraAtual.buscarCliente(sc);
                            if (c == null) break;
                            double valor = seguradoraAtual.calcularPrecoSeguroCliente(c);
                            print("======================================");
                            print("Valor do seguro: " + valor + " reais.");
                            print("======================================");
                            break;

                        case CALC_SCORE:
                            print("=== CALCULAR SCORE CLIENTE ===");
                            c = seguradoraAtual.buscarCliente(sc);
                            if (c == null) break;
                            print("==============================");
                            print("Score: " + c.calculaScore());
                            print("==============================");
                            break;
                        
                        default:
                            print("==============================================");
                            print("Selecione apenas entre as opções apresentadas!");
                            print("==============================================");
                    }
                }
                break;

                case MENU_SINISTRO: // Menu do Sinistro
                while (true) {
                    print("====== SINISTRO ======");
                    print("O que você deseja fazer?");
                    print("1) Gerar um novo sinistro");
                    print("2) Listar todos os sinistros");
                    print("3) Visualizar um sinistro");
                    print("4) Remover um sinistro");
                    print("5) Retornar ao menu principal");
                    print("==========================");
                    print("Entre com um número: ");
                    input_sinistro = Integer.parseInt(sc.nextLine()) + 16;
                    if (input_sinistro == 21) break;
                    op_sinistro = getOperacaoByInput(input_sinistro);

                    switch (op_sinistro) {
                        case GERAR_SINISTRO:
                            print("===== Gerar um Sinistro =====");
                            check = seguradoraAtual.gerarSinistro(sc, seguradoraAtual);
                            print("==========================");
                            if (check) print("Sinistro gerado com sucesso!");
                            else print("Ocorreu um erro ao gerar o Sinistro. Tente novamente.");
                            print("==========================");
                            break;
                        case LISTAR_SINISTRO:
                            print("===== Listar todos os Sinistros =====");
                            check = seguradoraAtual.listarSinistro();
                            if (!check) {
                                print("========================================");
                                print("Ainda não há nenhum sinistro cadastrado!");
                            }
                            print("========================================");
                            break;
                        case ACESSAR_SINISTRO:
                            print("===== Visualizar um sinistro =====");
                            print("Insira o ID do sinistro: ");
                            int id = Integer.parseInt(sc.nextLine());
                            check = seguradoraAtual.visualizarSinistros(id);
                            if (!check) {
                                print("========================================");
                                print("O sinistro buscado não existe. Tente novamente.");
                            }
                            print("========================================");
                            break;
                        case REMOVER_SINISTRO:
                            print("===== Remover um sinistro =====");
                            print("Insira o ID do sinistro: ");
                            id = Integer.parseInt(sc.nextLine());
                            check = seguradoraAtual.removerSinistro(id);
                            print("========================================");
                            if (!check) {
                                print("O sinistro buscado não existe. Tente novamente.");
                            }
                            else print("Sinsitro removido com sucesso.");
                            print("========================================");
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
                    print("Valor da receita: " + seguradoraAtual.calcularReceita() + " reais.");
                    print("=======================================");
                    break;

                case TRANSF_SEGURO:
                    print("===== TRANSFERÊNCIA DE SEGURO =====");
                    print("Insira os dados referentes ao cliente de origem:");
                    Cliente cIni = seguradoraAtual.buscarCliente(sc);
                    print("=================================================");
                    print("Insira os dados referentes ao cliente final (que irá receber o seguro):");
                    Cliente cFinal = seguradoraAtual.buscarCliente(sc);
                    print("=================================================");
                    check = seguradoraAtual.transferirSeguro(cIni, cFinal);
                    if (check) {
                        print("Seguro transferido com sucesso!");
                    }
                    else {
                        print("Ocorreu um erro ao tentar realizar a transferência. Tente novamente.");
                    }
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