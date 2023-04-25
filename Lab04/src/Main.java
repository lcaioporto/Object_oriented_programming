import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    /*
     * Abaixo segue-se o código que utiliza todos os métodos desenvolvidos pelas demais classes;
     * Esses métodos são acessados por meio de um Menu interativo baseado em uma Seguradora, que é criada por um método nesta classe Main.
     * Do modo como foi impletado, é possível lidar com várias seguradoras e alterar do menu de uma Seguradora para outra.
     */
    //USAR ENUM!!!
    public static Seguradora criarSeguradora(Scanner sc) {
        //Recebe as informações necessárias e cria um objeto da classe Seguradora.
        System.out.println("Insira o nome da seguradora: ");
        String nome = sc.nextLine();
        System.out.println("\nInsira o telefone da seguradora: ");
        String telefone = sc.nextLine();
        System.out.println("\nInsira o e-mail da seguradora: ");
        String email = sc.nextLine();
        System.out.println("\nInsira o endereço da seguradora: ");
        String endereco = sc.nextLine();
        Seguradora s = new Seguradora(nome, telefone, email, endereco);
        return s;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input, input_cliente, input_sinistro, input_veiculo;
        boolean check;
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>(); //lista que contém todas as seguradoras criadas.
        System.out.println("===== CADASTRO DA SEGURADORA =====");
        Seguradora seguradoraAtual = criarSeguradora(sc);
        listaSeguradoras.add(seguradoraAtual);
        System.out.println("=============================================");
        do { //Menu principal
            System.out.println("=== MENU DA SEGURADORA '" + seguradoraAtual.getNome() + "' ===");
            System.out.println("=============================================");
            System.out.println("Com o que deseja-se lidar?");
            System.out.println("1) Clientes");
            System.out.println("2) Sinistro");
            System.out.println("3) Consultar dados da seguradora");
            System.out.println("4) Cadastro de uma nova seguradora");
            System.out.println("5) Alterar para outra seguradora");
            System.out.println("6) Fechar Menu");
            System.out.println("=============================================");
            System.out.println("Entre com um número: ");
            input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1: // Menu dos clientes
                do {
                    System.out.println("===== CLIENTES =====");
                    System.out.println("O que você deseja fazer?");
                    System.out.println("1) Cadastrar um novo cliente");
                    System.out.println("2) Remover um cliente");
                    System.out.println("3) Listar todos os clientes");
                    System.out.println("4) Acessar dados de um determinado cliente");
                    System.out.println("5) Lidar com veículos");
                    System.out.println("6) Retornar ao menu principal");
                    System.out.println("==========================");
                    System.out.println("Entre com um número: ");
                    input_cliente = Integer.parseInt(sc.nextLine());

                    switch (input_cliente) {
                        case 1:
                            System.out.println("===== Iniciando um novo Cadastro ======");
                            check = seguradoraAtual.cadastrarCliente(sc);
                            System.out.println("==================================");
                            if (check) System.out.println("Cadastro realizado com sucesso!");
                            else System.out.println("Ocorreu um erro ao tentar cadastrar o cliente! Tente novamente.");
                            System.out.println("==================================");
                            break;
                        case 2:
                            System.out.println("===== Remoção de clientes ======");
                            check = seguradoraAtual.removerCliente(sc);
                            System.out.println("==========================");
                            if (check) System.out.println("Cliente removido com sucesso!");
                            else System.out.println("Cliente não encontrado.");
                            System.out.println("==========================");
                            break;
                        case 3:
                            System.out.println("===== Listando todos os clientes =====");
                            check = seguradoraAtual.listarClientes();
                            if (!check) {
                                System.out.println("=======================================");
                                System.out.println("Ainda não há nenhum cliente cadastrado!");
                            }
                            System.out.println("=======================================");
                            break;
                        case 4:
                            check = seguradoraAtual.printInfoCliente(sc);
                            if (!check) {
                                System.out.println("==========================");
                                System.out.println("Cliente não encontrado.");
                            }
                            System.out.println("==========================");
                            break;
                        case 5: //Menu Veículos
                        do {
                            System.out.println("======= VEÍCULOS =======");
                            System.out.println("O que você deseja fazer?");
                            System.out.println("1) Cadastrar um novo veículo");
                            System.out.println("2) Remover um veículo");
                            System.out.println("3) Retornar ao Menu de Clientes");
                            System.out.println("=========================");
                            System.out.println("Entre com um número: ");
                            input_veiculo = Integer.parseInt(sc.nextLine());

                            switch (input_veiculo) {
                                case 1:
                                    System.out.println("=== CADASTRO DE VEÍCULO ===");
                                    System.out.println("Abaixo insira os dados relacionados ao cliente ao qual deseja-se registar um veículo.");
                                    System.out.println("==========================");
                                    Cliente cliente = seguradoraAtual.buscarCliente(sc);
                                    if (cliente == null) {
                                        System.out.println("==============================================");
                                        System.out.println("O cliente buscado não existe. Tente novamente.");
                                        System.out.println("==============================================");
                                        break;
                                    }
                                    check = cliente.cadastrarVeiculo(sc);
                                    System.out.println("==========================");
                                    if (check) System.out.println("Cadastro de veículo realizado com sucesso!");
                                    else System.out.println("Ocorreu um erro no cadastro do veículo. Tente novamente.");
                                    System.out.println("==========================");
                                    break;
                                
                                case 2:
                                    System.out.println("=== REMOÇÃO DE VEÍCULO ===");
                                    System.out.println("Abaixo insira os dados relacionados ao cliente ao qual deseja-se remover o veículo.");
                                    System.out.println("==========================");
                                    cliente = seguradoraAtual.buscarCliente(sc);
                                    if (cliente == null) {
                                        System.out.println("==============================================");
                                        System.out.println("O cliente buscado não existe. Tente novamente.");
                                        System.out.println("==============================================");
                                        break;
                                    }
                                    check = cliente.removeVeiculo(sc);
                                    System.out.println("==========================");
                                    if (check) System.out.println("Veículo removido com sucesso!");
                                    else System.out.println("Ocorreu um erro na remoção do veículo. Tente novamente.");
                                    System.out.println("==========================");
                                    break;
                            }
                        } while (input_veiculo != 3);
                    }
                } while (input_cliente != 6);
                break;

                case 2: // Menu do Sinistro
                do {
                    System.out.println("====== SINISTRO ======");
                    System.out.println("O que você deseja fazer?");
                    System.out.println("1) Gerar um novo sinistro");
                    System.out.println("2) Listar todos os sinistros");
                    System.out.println("3) Visualizar um sinistro");
                    System.out.println("4) Remover um sinistro");
                    System.out.println("5) Retornar ao menu principal");
                    System.out.println("==========================");
                    System.out.println("Entre com um número: ");
                    input_sinistro = Integer.parseInt(sc.nextLine());
                    switch (input_sinistro) {
                        case 1:
                            System.out.println("===== Gerar um Sinistro =====");
                            check = seguradoraAtual.gerarSinistro(sc, seguradoraAtual);
                            System.out.println("==========================");
                            if (check) System.out.println("Sinistro gerado com sucesso!");
                            else System.out.println("Ocorreu um erro ao gerar o Sinistro. Tente novamente.");
                            System.out.println("==========================");
                            break;
                        case 2:
                            System.out.println("===== Listar todos os Sinistros =====");
                            check = seguradoraAtual.listarSinistro();
                            if (!check) {
                                System.out.println("========================================");
                                System.out.println("Ainda não há nenhum sinistro cadastrado!");
                            }
                            System.out.println("========================================");
                            break;
                        case 3:
                            System.out.println("===== Visualizar um sinistro =====");
                            System.out.println("Insira o ID do sinistro: ");
                            int id = Integer.parseInt(sc.nextLine());
                            check = seguradoraAtual.visualizarSinistros(id);
                            if (!check) {
                                System.out.println("========================================");
                                System.out.println("O sinistro buscado não existe. Tente novamente.");
                            }
                            System.out.println("========================================");
                            break;
                        case 4:
                            System.out.println("===== Remover um sinistro =====");
                            System.out.println("Insira o ID do sinistro: ");
                            id = Integer.parseInt(sc.nextLine());
                            check = seguradoraAtual.removerSinistro(id);
                            System.out.println("========================================");
                            if (!check) {
                                System.out.println("O sinistro buscado não existe. Tente novamente.");
                            }
                            else System.out.println("Sinsitro removido com sucesso.");
                            System.out.println("========================================");
                            break;
                    }
                } while (input_sinistro != 5);
                break;

            case 3:
                System.out.println(seguradoraAtual);
                break;
            
            case 4:
                System.out.println("===== CADASTRO DA NOVA SEGURADORA ===");
                Seguradora seguradoraAux = criarSeguradora(sc);
                listaSeguradoras.add(seguradoraAux);
                seguradoraAtual = seguradoraAux;
                System.out.println("========================================================================================");
                System.out.println("Novo cadastro realizado com sucesso! Agora você retornará ao Menu desta nova seguradora!");
                System.out.println("========================================================================================");
                break;
            
            case 5:
                if (listaSeguradoras.size() == 1) {
                    System.out.println("=========================================");
                    System.out.println("Ainda não há outra seguradora cadastrada!");
                    System.out.println("=========================================");
                }
                else {
                    System.out.println("===== SELECIONAR SEGURADORA =====");
                    for (int i = 0; i < listaSeguradoras.size(); i++) {
                        System.out.println((i + 1) + ") " + listaSeguradoras.get(i).getNome());
                    }
                    System.out.println("==================================");
                    System.out.println("Entre com um número: ");
                    int n = Integer.parseInt(sc.nextLine());
                    if (n < listaSeguradoras.size() + 2) {
                        seguradoraAtual = listaSeguradoras.get(n - 1);
                        System.out.println("==================================");
                        System.out.println("Seguradora alterada com sucesso!");
                        System.out.println("==================================");
                    }
                    else {
                        System.out.println("===========================================");
                        System.out.println("O número digitado não está entre as opções!");
                        System.out.println("===========================================");
                    }
                }
                break;
        }
    } while (input != 6);
        System.out.println("===== MENU ENCERRADO =====");
        sc.close();
    }
}