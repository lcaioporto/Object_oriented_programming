import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Cliente> listaClientes; //lista de todos os clientes da seguradora
    private ArrayList<Sinistro> listaSinistro; //lista com todos os sinistros da seguradora;

    //Construtor da Seguradora
    public Seguradora (String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        listaClientes = new ArrayList<Cliente>();
        listaSinistro = new ArrayList<Sinistro>();
    }

    //Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome (String newName) {
        nome = newName;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String newTelefone) {
        telefone = newTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String newEmail) {
        email = newEmail;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String newEndereco) {
        endereco = newEndereco;
    }

    public ArrayList<Cliente> getListaClientes () {
        return listaClientes;
    }

    public ArrayList<Sinistro> getListaSinistro () {
        return listaSinistro;
    }

    public boolean cadastrarCliente(Scanner sc) {
        //Realiza todo o cadastro de um cliente, seja ele PF ou PJ, recebendo inputs de todas as informações necessárias
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            //Informações padrões de todos os clientes
            System.out.println("Insira o nome do cliente: ");
            String nome_cliente = sc.nextLine();
            while (! Validacao.validaNome(nome_cliente)) {
                System.out.println("===========================================================");
                System.out.println("Nome inválido! \nO nome do cliente deve conter apenas letras! \nTente novamente.");
                System.out.println("===========================================================");
                System.out.println("Insira o nome do cliente: ");
                nome_cliente = sc.nextLine();
            }
            System.out.println("\nInsira o endereço do cliente: ");
            String endereco_cliente = sc.nextLine();
            System.out.println("\nCliente PJ (Pessoa Jurídica) ou PF (Pessoa Física)? ");
            String pj_pf = sc.nextLine().toUpperCase();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            if (pj_pf.equals("PJ")) { //Cliente PJ
                //Inicializando a data
                Date dataFundacao;
                //Data de fundação
                System.out.println("\nInsira a data de fundação (Formato dd-MM-yyyy): ");
                String d = sc.nextLine();
                dataFundacao = Validacao.validaFormatoDate(sc, d, formatter);
                //CNPJ
                System.out.println("\nInsira o CNPJ do cliente: ");
                String CNPJ = sc.nextLine();
                //Validação do CNPJ
                while (! Validacao.validarCNPJ(CNPJ)) {
                    System.out.println("===============================");
                    System.out.println("CNPJ inválido. Tente novamente.");
                    System.out.println("===============================");
                    System.out.println("\nInsira o CNPJ do cliente: ");
                    CNPJ = sc.nextLine();
                }
                System.out.println("===============================");
                System.out.println("CNPJ válido!");
                //Quantidade de Funcionários
                System.out.println("\nInsira a quantidade de funcionários da empresa: ");
                int qntdeFuncionarios = 0;
                while (true) {
                    try {
                        qntdeFuncionarios = Integer.parseInt(sc.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("============================");
                        System.out.println("Responda apenas com números!");
                        System.out.println("============================");
                    }
                }
                //Criar objeto ClientePJ
                ClientePJ c = new ClientePJ(nome_cliente, endereco_cliente, CNPJ, dataFundacao, qntdeFuncionarios);
                listaClientes.add(c);
            }
            else if (pj_pf.equals("PF")) { //Cliente PF
                //Inicializando datas
                Date dataLicenca;
                Date dataNascimento;
                //educação
                System.out.println("\nInsira o grau de educação acadêmcia do cliente: ");
                String educacao = sc.nextLine();
                //gênero
                System.out.println("\nInsira o gênero do cliente: ");
                String genero = sc.nextLine();
                //classe econômica
                System.out.println("\nInsira a classe econômica do cliente: ");
                String classeEconomica = sc.nextLine();
                //DATA NASCIMENTO
                System.out.println("\nInsira a data de nascimento (Formato dd-MM-yyyy): ");
                String d = sc.nextLine();
                dataNascimento = Validacao.validaFormatoDate(sc, d, formatter);
                while (! Validacao.validaDataNascimento(dataNascimento)) { 
                    //verifica se o cliente tem mais de 18 anos
                    System.out.println("==================================================");
                    System.out.println("Data inválida! O cliente deve ter mais de 18 anos.");
                    System.out.println("==================================================");
                    return cadastrarCliente(sc);
                }
                //DATA LICENCA
                System.out.println("\nInsira a data de expedição da lincença (Formato dd-MM-yyyy): ");
                d = sc.nextLine();
                dataLicenca = Validacao.validaFormatoDate(sc, d, formatter);
                while (! Validacao.validaDataLinceca(dataNascimento, dataLicenca)) {
                    //avalia se a data de lincença dada ocorreu depois da data de nascimento
                    System.out.println("==========================================");
                    System.out.println("Data inválida! Tente novamente.");
                    System.out.println("==========================================");
                    return cadastrarCliente(sc);
                }
                //CPF
                System.out.println("\nInsira o CPF do cliente: ");
                String CPF = sc.nextLine();
                while (! Validacao.validarCPF(CPF)) {
                    System.out.println("===============================");
                    System.out.println("CPF inválido. Tente novamente.");
                    System.out.println("===============================");
                    System.out.println("\nInsira o CPF do cliente: ");
                    CPF = sc.nextLine();
                }
                System.out.println("===============================");
                System.out.println("CPF válido!");
                //Criar objeto ClientePJ
                ClientePF c = new ClientePF(nome_cliente, endereco_cliente, educacao, genero, classeEconomica, CPF, dataNascimento, dataLicenca);
                listaClientes.add(c);
            }
            else {
                System.out.println("==================================================");
                System.out.println("Responda apenas com 'PF' ou 'PJ'. Tente novamente.");
                System.out.println("==================================================");
                return cadastrarCliente(sc);
            }
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean removerCliente(Scanner sc) {
        //Remove um determinado cliente da lista de clientes de uma Seguradora
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        //Os sinistros relacionados ao cliente não são removidos porque a lista de sinistros é pensada como um registro de sinistros.
        try {
            Cliente cliente = buscarCliente(sc);
            if (cliente == null) return false;
            listaClientes.remove(listaClientes.indexOf(cliente)); //remover da lista geral de todos os clientes
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean gerarSinistro(Scanner sc, Seguradora seguradora) {
        //Recebe todas as informações necessárias para gerar um objeto sinistro.
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date data;
            //Cliente
            Cliente cliente = buscarCliente(sc);
            if (cliente == null) { 
                return false; 
            } //cliente não encontrado
            //Veículo
            Veiculo veiculo = buscarVeiculo(sc, cliente);
            if (veiculo == null) { return false; } //veiculo não encontrado
            //Data
            System.out.println("\nInsira a data do Sinistro (Formato dd-MM-yyyy): ");
            String d = sc.nextLine();
            data = Validacao.validaFormatoDate(sc, d, formatter);
            //Endereço
            System.out.println("Insira o endereço do sinistro: ");
            String endereco_sinistro = sc.nextLine();
            //objeto Sinistro
            Sinistro s = new Sinistro(data, endereco_sinistro, seguradora, veiculo, cliente);
            listaSinistro.add(s);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean listarClientes() {
        //Printa informações de todos os clientes da Seguradora.
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        if (listaClientes.size() == 0) {
            return false;
        }
        for (Cliente c : listaClientes) {
            System.out.println("\n" + c);
        }
        return true;
    }

    public boolean listarSinistros() {
        //Printa informações de todos os Sinistros da Seguradora
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        if (listaSinistro.size() == 0) {
            return false;
        }
        for (Sinistro s : listaSinistro) {
            System.out.println("\n" + s);
        }
        return true;
    }

    public boolean listarSinistroCliente(Scanner sc) {
        Cliente c = buscarCliente(sc);
        if (c == null) return false;
        for (Sinistro s : listaSinistro) {
            if (s.getCliente().equals(c)) {
                System.out.println("\n" + s);
            }
        }
        return true;
    }

    public boolean removerSinistro(int id) {
        //dado um certo ID, remove o sinistro que tem esse ID da lista de Sinistros da seguradora.
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            for (Sinistro s : listaSinistro) {
                if (s.getId() == id) {
                    listaSinistro.remove(listaSinistro.indexOf(s));
                    return true;
                }
            }
            return false;
        }
        catch (Exception e) { return false; }
    }

    public boolean printInfoCliente(Scanner sc) {
        //Printa as informações de um cliente específco da seguradora
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            Cliente cliente = buscarCliente(sc);
            if (cliente == null) return false;
            System.out.println(cliente);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public Veiculo buscarVeiculo (Scanner sc, Cliente c) {
        //Busca um veículo na lista de veículos de um dado Cliente c, recebendo a informação da placa
        //Usada para gerar um sinistro
        System.out.println("Insira a placa do veículo: ");
        String palca = sc.nextLine();
        for (Veiculo v : c.getListaVeiculos()) {
            if (v.getPlaca().equals(palca)) return v;
        }
        System.out.println("Veículo não encontrado!");
        return null; //caso em que o veículo buscado não existe
    }

    public Cliente buscarCliente (Scanner sc) {
        //Recebe inputs e a partir das informações retorna um cliente específico.
        String aux = "";
        if (aux.equals("2")) return null;
        System.out.println("Cliente é PF ou PJ?: ");
        String pf_pj = sc.nextLine().toUpperCase();
        String id;

        if (pf_pj.equals("PF")) {
            System.out.println("\nInsira o CPF do cliente: ");
            id = sc.nextLine().replaceAll("[^0-9]", "");
        }
        else if (pf_pj.equals("PJ")) {
            System.out.println("\nInsira o CNPJ do cliente: ");
            id = sc.nextLine().replaceAll("[^0-9]", "");
        }
        else {
            System.out.println("==================================================");
            System.out.println("Responda apenas com 'PF' ou 'PJ'. Tente novamente.");
            System.out.println("==================================================");
            return buscarCliente(sc);
        }
        for (Cliente currCliente : listaClientes) {
            if (currCliente.getId().equals(id)) {
                return currCliente;
            }
        }
        System.out.println("==================================================================");
        System.out.println("O cliente buscado não existe.");
        System.out.println("Digite 1 caso queira tentar novamente.");
        System.out.println("Caso contrário, digite 2 (ou qualquer outro dígito distinto de 1).");
        System.out.println("==================================================================");
        aux = sc.nextLine();
        if (aux.equals("1")) return buscarCliente(sc); //cliente não encontrado
        return null;
    }

    public boolean visualizarSinistro(int id) {
        //dado um certo id, printa-se as informações do sinsitro que tem esse id"
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            for (Sinistro s : listaSinistro) {
                if (s.getId() == id) {
                    System.out.println(s);
                    return true;
                }
            }
            return false;
        }
        catch (Exception e) { return false; }
    }

    public int calcQtdeSinistros (Cliente c) {
        //dado um cliente C, calcula a quantidade de sinistros associado a este cliente
        int n = 0;
        for (Sinistro s : listaSinistro) {
            if (s.getCliente().equals(c)) n++;
        }
        return n;
    }

    public double calcularPrecoSeguroCliente(Cliente c) {
        double valor = c.calculaScore() * (1 + calcQtdeSinistros(c));
        c.setValorSeguro(valor);
        return valor;
    }

    public double calcularReceita () {
        double receita = 0;
        for (Cliente c : listaClientes) {
            receita += calcularPrecoSeguroCliente(c);
        }
        return receita;
    }

    public boolean transferirSeguro (Cliente cIni, Cliente cFinal) {
        //Transfere o seguro do cliente cIni para cFinal
        try {
            cFinal.getListaVeiculos().addAll(cIni.getListaVeiculos()); //adiciona os veiculos de cIni aos de cFinal
            cIni.getListaVeiculos().clear(); //remove os veiculos de cIni
            //atualizar o valor dos seguros
            calcularPrecoSeguroCliente(cIni);
            calcularPrecoSeguroCliente(cFinal);
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean listarVeiculoCliente (Scanner sc) {
        Cliente c = buscarCliente(sc);
        if (c == null) return false;
        if (c.getListaVeiculos().size() == 0) {
            System.out.println("=======================================");
            System.out.println("Não há veículo cadastrado neste cliente");
            System.out.println("=======================================");
            return false;
        }
        for (Veiculo v : c.getListaVeiculos()) {
            System.out.println(v);
        }
        return true;
    }

    public boolean listarVeiculoSeguradora (Scanner sc) {
        if (listaClientes.size() == 0) return false;
        for (Cliente c : listaClientes) {
            for (Veiculo v : c.getListaVeiculos()) {
                System.out.println(v);
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "======== DADOS SEGURADORA ========\n" + "Nome: " + getNome() + "\nEmail: " + getEmail() + "\nEndereço: " + getEndereco() + "\nTelefone: " + getTelefone() + "\n==================================";
    }
}