import java.util.Scanner;
public class Main {
    public static Seguradora criarSeguradora(Scanner sc) {
        System.out.println("Insira o nome da seguradora: ");
        String nome = sc.nextLine();
        System.out.println("Insira o telefone da seguradora: ");
        String telefone = sc.nextLine();
        System.out.println("Insira o e-mail da seguradora: ");
        String email = sc.nextLine();
        System.out.println("Insira o endereço da seguradora: ");
        String endereco = sc.nextLine();
        Seguradora s = new Seguradora(nome, telefone, email, endereco);
        return s;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input, input_cliente, input_sinistro;
        boolean check;
        System.out.println("===== CADASTRO DA SEGURADORA ===");
        Seguradora seguradoraAtual = criarSeguradora(sc);
        System.out.println("==========================");
        do {
            System.out.println("=== MENU DA SEGURADORA '" + seguradoraAtual.getNome() + "' ===");
            System.out.println("==========================");
            System.out.println("Com o que deseja-se lidar?");
            System.out.println("1) Clientes");
            System.out.println("2) Sinistro");
            System.out.println("3) Cadastro de veículos");
            System.out.println("4) Cadastro de uma nova seguradora");
            System.out.println("5) Fechar Menu");
            System.out.println("==========================");
            System.out.println("Entre com um número: ");
            input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                do {
                    System.out.println("===== CLIENTES =====");
                    System.out.println("O que você deseja fazer?");
                    System.out.println("1) Cadastrar um novo cliente");
                    System.out.println("2) Remover um cliente");
                    System.out.println("3) Listar todos os clientes");
                    System.out.println("4) Acessar dados de um determinado cliente");
                    System.out.println("5) Retornar ao menu principal");
                    System.out.println("==========================");
                    System.out.println("Entre com um número: ");
                    input_cliente = Integer.parseInt(sc.nextLine());

                    switch (input_cliente) {
                        case 1:
                            System.out.println("===== Iniciando um novo Cadastro ======");
                            check = seguradoraAtual.cadastrarCliente(sc);
                            System.out.println("==========================");
                            if (check) System.out.println("Cadastro realizado com sucesso!");
                            else System.out.println("Ocorreu um erro ao tentar cadastrar o cliente! Tente novamente.");
                            System.out.println("==========================");
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
                            seguradoraAtual.listarClientes();
                            break;
                        case 4:
                            check = seguradoraAtual.printInfoCliente(sc);
                            if (!check) System.out.println("Cliente não encontrado.");
                            System.out.println("==========================");
                            break;
                    }
                } while (input_cliente != 5);
                break;

                case 2:
                do {
                    System.out.println("====== SINISTRO ======");
                    System.out.println("O que você deseja fazer?");
                    System.out.println("1) Gerar um novo sinistro");
                    System.out.println("2) Visualizar um sinsitro");
                    System.out.println("3) Listar todos os sinistros");
                    System.out.println("4) Retornar ao menu principal");
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
                            System.out.println("===== Visualizar um Sinistro =====");
                            check = seguradoraAtual.visualizarSinistro(sc);
                            if(!check) System.out.println("O Sinistro buscado não existe.");
                            System.out.println("==========================");
                            break;
                        case 3:
                            System.out.println("===== Listar todos os Sinistros =====");
                            seguradoraAtual.listarSinistro();
                            System.out.println("==========================");
                            break;
                    }
                } while (input_sinistro != 4);
                break;

            case 3:
                System.out.println("=== CADASTRO DE VEÍCULO ===");
                System.out.println("Abaixo insira os dados relacionados ao cliente ao qual deseja-se registar um veículo.");
                System.out.println("==========================");
                Cliente cliente = seguradoraAtual.buscarCliente(sc);
                check = cliente.cadastrarVeiculo(sc);
                System.out.println("==========================");
                if (check) System.out.println("Cadastro de veículo realizado com sucesso!");
                else System.out.println("Ocorreu um erro no cadastro do veículo. Tente novamente.");
                System.out.println("==========================");
                break;
            case 4:
                System.out.println("===== CADASTRO DA NOVA SEGURADORA ===");
                seguradoraAtual = criarSeguradora(sc);
                System.out.println("==========================");
                System.out.println("Novo cadastro realizado com sucesso! Agora você retornará ao Menu desta nova seguradora!");
                System.out.println("==========================");
                break;
        }
    } while (input != 5);

        /*
        //Criação da seguradora e cadastro de clientes
        Seguradora seguradoraX = new Seguradora("Nome Seguradora", "3344559977", "seguradora@gmail.com", "Endereço seguradora");
        seguradoraX.cadastrarCliente(sc); //recebe input de informações para gerar o cliente 1 - para o exemplo, cria-se um PF
        seguradoraX.cadastrarCliente(sc); //cliente 2 - para o exemplo, assume-se que é criado um PJ

        //cliente 1: no exemplo, é PF
        ClientePF c1 = seguradoraX.listarClientesPF().get(0);
        System.out.println(c1.validarCPF());

        //cliente 2: no exemplo, é PJ
        ClientePJ c2 = seguradoraX.listarClientesPJ().get(0); 
        System.out.println(c2.validarCNPJ());

        //cadastro de veículos para cada cliente - o próprio método recebe input das variáveis e cria o objeto veículo.
        c1.cadastrarVeiculo(sc);
        c2.cadastrarVeiculo(sc);

        //remoção de clientes
        seguradoraX.removerCliente(sc); //o método recebe todos os inputs necessários para remover um determinado cliente.

        //Gerar Sinistro
        seguradoraX.gerarSinistro(sc, seguradoraX, c2.getListaVeiculos().get(0), c2); //recebe a seguradora, o veículo e o cliente como parâmetros; e recebe input da data e endereço internamente
        seguradoraX.gerarSinistro(sc, seguradoraX, c1.getListaVeiculos().get(0), c1);

        //Visualizar sinistro do cliente c2
        seguradoraX.visualizarSinistro(c2);

        //Listas sinistros
        seguradoraX.listarSinistro();

        //toString()
        System.out.println
        */
        System.out.println("===== MENU ENCERRADO =====");
        sc.close();
    }
}