import java.util.*;
public class Seguradora {

    //DEIXAR UMA LISTA APENAS - listaClientes
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Cliente> listaClientes; //lista de todos os clientes da seguradora
    private ArrayList<ClientePF> listaClientesPF; //lista dos clientes do tipo PF (Pessoa Física)
    private ArrayList<ClientePJ> listaClientesPJ; //lista dos clientes do tipo PJ (Pessoa Jurídica)
    private static ArrayList<Sinistro> listaSinistro; //lista com todos os sinistros da seguradora;

    //Construtor da Seguradora
    public Seguradora (String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        listaClientes = new ArrayList<Cliente>();
        listaClientesPF = new ArrayList<ClientePF>();
        listaClientesPJ = new ArrayList<ClientePJ>();
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

    public boolean cadastrarCliente(Scanner sc) {
        //Realiza todo o cadastro de um cliente, seja ele PF ou PJ, recebendo inputs de todas as informações necessárias
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            //Informações padrões de todos os clientes
            System.out.println("Insira o nome do cliente: ");
            String nome_cliente = sc.nextLine();
            System.out.println("\nInsira o endereço do cliente: ");
            String endereco_cliente = sc.nextLine();
            System.out.println("\nCliente PJ (Pessoa Jurídica) ou PF (Pessoa Física)? ");
            String pj_pf = sc.nextLine().toUpperCase();
            
            if (pj_pf.equals("PJ")) { //Cliente PJ
                //Data de fundação
                System.out.println("\nInsira o ano de fundação: ");
                int ano = Integer.parseInt(sc.nextLine());
                System.out.println("\nInsira o mês de fundação: ");
                int mes = Integer.parseInt(sc.nextLine()) - 1;
                System.out.println("\nInsira o dia de fundação: ");
                int dia = Integer.parseInt(sc.nextLine());
                Calendar dataFundacao = new GregorianCalendar(ano, mes, dia);
                //CNPJ
                System.out.println("\nInsira o CNPJ do cliente: ");
                String CNPJ = sc.nextLine();
                //Criar objeto ClientePJ
                ClientePJ c = new ClientePJ(nome_cliente, endereco_cliente, CNPJ, dataFundacao);
                //Validação do CNPJ
                if (!c.validarCNPJ()) {
                    System.out.println("===============================");
                    System.out.println("CNPJ inválido. Tente novamente.");
                    System.out.println("===============================");
                    return cadastrarCliente(sc);
                }
                else {
                    System.out.println("===============================");
                    System.out.println("CNPJ válido!");
                    listaClientesPJ.add(c);
                    listaClientes.add(c);
                }
            }
            else if (pj_pf.equals("PF")) { //Cliente PF
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
                System.out.println("\nInsira o ano de nascimento: ");
                int ano = Integer.parseInt(sc.nextLine());
                System.out.println("\nInsira o mês de nascimento: ");
                int mes = Integer.parseInt(sc.nextLine()) - 1;
                System.out.println("\nInsira o dia de nascimento: ");
                int dia = Integer.parseInt(sc.nextLine());
                Calendar dataNascimento = new GregorianCalendar(ano, mes, dia);
                //DATA LICENCA
                System.out.println("\nInsira o ano de expedição da lincença: ");
                ano = Integer.parseInt(sc.nextLine());
                System.out.println("\nInsira o mês de expedição da lincença: ");
                mes = Integer.parseInt(sc.nextLine());
                System.out.println("\nInsira o dia de expedição da lincença: ");
                dia = Integer.parseInt(sc.nextLine());
                Calendar dataLicenca = new GregorianCalendar(ano, mes, dia);
                //CPF
                System.out.println("\nInsira o CPF do cliente: ");
                String CPF = sc.nextLine();
                //Criar objeto ClientePF
                ClientePF c = new ClientePF(nome_cliente, endereco_cliente, educacao, genero, classeEconomica, CPF, dataNascimento, dataLicenca);
                //Validação do CPF
                if (!c.validarCPF()) {
                    System.out.println("===============================");
                    System.out.println("CPF inválido. Tente novamente.");
                    System.out.println("===============================");
                    return cadastrarCliente(sc);
                }
                else {
                    System.out.println("===============================");
                    System.out.println("CPF válido!");
                    listaClientesPF.add(c);
                    listaClientes.add(c);
                }
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
        try {
            Cliente cliente = buscarCliente(sc);
            if (cliente == null) return false;
            try { //Cliente PF
            listaClientesPF.remove(listaClientesPF.indexOf(cliente));
            }
            catch (Exception e) { //Cliente PJ
                listaClientesPJ.remove(listaClientesPJ.indexOf(cliente)); 
            }
            listaClientes.remove(listaClientes.indexOf(cliente)); //remover da lista geral de todos os clientes
        return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean gerarSinistro(Scanner sc, Seguradora seguradora) {
        //Recebe todas as informações necessárias para gerar um objeto sinistro.
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            //Cliente
            Cliente cliente = buscarCliente(sc);
            if (cliente == null) { return false; }
            //Veículo
            System.out.println("\n");
            Veiculo veiculo = buscaVeiculo(sc, cliente);
            if (veiculo == null) { return false; }
            //Data
            System.out.println("\nInsira a data do Sinistro: ");
            String data = sc.nextLine();
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
        //ArrayList<Cliente> listaClientes = new ArrayList<>();
        if (listaClientes.size() == 0) {
            return false;
        }
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println("\n" + listaClientes.get(i));
        }
        return true;
    }

    public ArrayList<ClientePF> listarClientesPF () {
        return listaClientesPF;
    }

    public ArrayList<ClientePJ> listarClientesPJ () {
        return listaClientesPJ;
    }

    public boolean listarSinistro() {
        //Printa informações de todos os Sinistros da Seguradora
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        if (listaSinistro.size() == 0) {
            return false;
        }
        for (int i = 0; i < listaSinistro.size(); i++) {
            System.out.println(listaSinistro.get(i));
        }
        return true;
    }

    public boolean removerSinistro(int id) {
        //dado um certo ID, remove o sinistro que tem esse ID da lista de Sinistros da seguradora.
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            int encontrou = 0;
            for (int i = 0; i < listaSinistro.size(); i++) {
                if (listaSinistro.get(i).getId() == id) {
                    listaSinistro.remove(i);
                    encontrou = 1;
                    break;
                }
            }
            if (encontrou == 0) { return false; }
            return true;
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

    public ClientePF buscaClientePF(Scanner sc) {
        //Pede o input do CPF do cliente e faz sua busca na lista de clientes PF.
        System.out.println("\nInsira o CPF do cliente: ");
        String cpf = sc.nextLine();
        String currCPF;
        cpf = cpf.replaceAll("[^0-9]", "");

        for (int i = 0; i < listaClientesPF.size(); i++) {
            currCPF = listaClientesPF.get(i).getCPF().replaceAll("[^0-9]", "");
            if (currCPF.equals(cpf)) {
                return listaClientesPF.get(i);
            }
        }
        return null; //caso em que o cliente buscado não existe
    }

    public ClientePJ buscaClientePJ(Scanner sc) {
        //Pede o input do CNPJ do cliente e faz sua busca na lista de clientes PJ.
        System.out.println("\nInsira o CNPJ do cliente: ");
        String CNPJ = sc.nextLine();
        CNPJ = CNPJ.replaceAll("[^0-9]", "");

        for (int i = 0; i < listaClientesPJ.size(); i++) {
            String currCNPJ = listaClientesPJ.get(i).getCNPJ().replaceAll("[^0-9]", "");
            if (currCNPJ.equals(CNPJ)) {
                return listaClientesPJ.get(i);
            }
        }
        return null; //caso em que o cliente buscado não existe
    }

    public Veiculo buscaVeiculo (Scanner sc, Cliente c) {
        //Busca um veículo na lista de veículos de um dado Cliente c, recebendo a informação da placa
        //Usada para gerar um sinistro
        System.out.println("Insira a placa do veículo: ");
        String palca = sc.nextLine();
        for (int i = 0; i < c.getListaVeiculos().size(); i++) {
            if (c.getListaVeiculos().get(i).getPlaca().equals(palca)) return c.getListaVeiculos().get(i);
        }
        return null; //caso em que o veículo buscado não existe
    }

    public Cliente buscarCliente (Scanner sc) {
        //Utiliza as funções "buscaClientePF()" e "buscarClientePJ()" para buscar qualquer cliente
        System.out.println("Cliente é PF ou PJ?: ");
        String pf_pj = sc.nextLine().toUpperCase();
        if (pf_pj.equals("PF")) { return buscaClientePF(sc); }
        else if (pf_pj.equals("PJ")) return buscaClientePJ(sc);
        else {
            System.out.println("==================================================");
            System.out.println("Responda apenas com 'PF' ou 'PJ'. Tente novamente.");
            System.out.println("==================================================");
            return buscarCliente(sc);
        }
    }

    public boolean visualizarSinistros(int id) {
        //dado um certo id, printa-se as informações do sinsitro que tem esse id"
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            int encontrou = 0;
            for (int i = 0; i < listaSinistro.size(); i++) {
                if (listaSinistro.get(i).getId() == id) {
                    System.out.println(listaSinistro.get(i));
                    encontrou = 1;
                    break;
                }
            }
            if (encontrou == 1) return true;
            else return false;
        }
        catch (Exception e) { return false; }
    }

    @Override
    public String toString() {
        return "======== DADOS SEGURADORA ========\n" + "Nome: " + getNome() + "\nEmail: " + getEmail() + "\nEndereço: " + getEndereco() + "\nTelefone: " + getTelefone() + "\n==================================";
    }
}