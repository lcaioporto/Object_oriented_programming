import java.util.*;
public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<ClientePF> listaClientesPF;
    private ArrayList<ClientePJ> listaClientesPJ;
    public static ArrayList<Sinistro> listaSinistro;

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
        try {
            //CHECAR A VALIDAÇÃO DO CPF
            System.out.println("Insira o nome do cliente: ");
            String nome = sc.nextLine();
            System.out.println("Insira o endereço do cliente: ");
            String endereco_cliente = sc.nextLine();
            System.out.println("Cliente PJ (Pessoa Jurídica) ou PF (Pessoa Física)? ");
            String pj_pf = sc.nextLine();
            
            if (pj_pf.equals("PJ")) { //Cliente PJ
                System.out.println("Insira o CNPJ do cliente: ");

                String CNPJ = sc.nextLine();
                //Data de fundação
                System.out.println("Insira o ano de fundação: ");
                int ano = Integer.parseInt(sc.nextLine());
                System.out.println("Insira o mês de fundação: ");
                int mes = Integer.parseInt(sc.nextLine());
                System.out.println("Insira o dia de fundação: ");
                int dia = Integer.parseInt(sc.nextLine());
                Calendar dataFundacao = new GregorianCalendar(ano, mes, dia);
                //Criar objeto ClientePJ
                ClientePJ c = new ClientePJ(nome, endereco_cliente, CNPJ, dataFundacao);
                listaClientesPJ.add(c);
                listaClientes.add(c);
            }
            else if (pj_pf.equals("PF")) { //Cliente PF
                //educação
                System.out.println("Insira o grau de educação acadêmcia do cliente: ");
                String educacao = sc.nextLine();
                //gênero
                System.out.println("Insira o gênero do cliente: ");
                String genero = sc.nextLine();
                //classe econômica
                System.out.println("Insira a classe econômica do cliente: ");
                String classeEconomica = sc.nextLine();
                //CPF
                System.out.println("Insira o CPF do cliente: ");
                String CPF = sc.nextLine();
                //DATA NASCIMENTO
                System.out.println("Insira o ano de nascimento: ");
                int ano = Integer.parseInt(sc.nextLine());
                System.out.println("Insira o mês de nascimento: ");
                int mes = Integer.parseInt(sc.nextLine());
                System.out.println("Insira o dia de nascimento: ");
                int dia = Integer.parseInt(sc.nextLine());
                Calendar dataNascimento = new GregorianCalendar(ano, mes, dia);
                //DATA LICENCA
                System.out.println("Insira o ano de expedição da lincença: ");
                ano = Integer.parseInt(sc.nextLine());
                System.out.println("Insira o mês de expedição da lincença: ");
                mes = Integer.parseInt(sc.nextLine());
                System.out.println("Insira o dia de expedição da lincença: ");
                dia = Integer.parseInt(sc.nextLine());
                Calendar dataLicenca = new GregorianCalendar(ano, mes, dia);
                //Criar objeto ClientePF
                ClientePF c = new ClientePF(nome, endereco_cliente, educacao, genero, classeEconomica, CPF, dataNascimento, dataLicenca);
                listaClientesPF.add(c);
                listaClientes.add(c);
            }
            else {
                System.out.println("Responda apenas com 'PF' ou 'PJ'.");
            }
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean removerCliente(Scanner sc) {
        try {
            Cliente cliente = buscarCliente(sc);
            if (cliente == null) return false;
            String nome_cliente = cliente.getNome();
            listaClientesPJ.remove(listaClientesPF.indexOf(cliente));
            
            for (int i = 0; i < listaClientes.size(); i++) { //remover da lista geral de todos os clientes
                if (listaClientes.get(i).getNome().equals(nome_cliente)) {
                    listaClientes.remove(i);
                    break;
                }
            }
        return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean gerarSinistro(Scanner sc, Seguradora seguradora) {
        try {
            //Cliente
            Cliente cliente = buscarCliente(sc);
            if (cliente == null) { return false; }
            //Veículo
            Veiculo veiculo = buscaVeiculo(sc, cliente);
            if (veiculo == null) { return false; }
            //Data
            System.out.println("Insira a data do Sinistro: ");
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

    public void listarClientes() {
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println(listaClientes.get(i));
        }
    }

    public ArrayList<ClientePF> listarClientesPF () {
        return listaClientesPF;
    }

    public ArrayList<ClientePJ> listarClientesPJ () {
        return listaClientesPJ;
    }

    public void listarSinistro() {
        for (int i = 0; i < listaSinistro.size(); i++) {
            System.out.println(listaSinistro.get(i));
        }
    }

    public boolean visualizarSinistro(Scanner sc) {
        //dado um certo cliente, printa-se as informações de todos os sinsitros relacionados com este cliente, usando "toString()"
        try {
            System.out.println("Insira o nome do cliente relacionado com o Sinistro: ");
            String nome_cliente = sc.nextLine();
            for (int i = 0; i < listaSinistro.size(); i++) {
                if (listaSinistro.get(i).getCliente().getNome().equals(nome_cliente)) {
                    listaSinistro.get(i).toString();
                    break;
                }
            }
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean removerSinistro(int id) {
        //dado um certo ID, remove o sinistro que tem esse ID.
        try {
            int encontrou = 0;
            for (int i = 0; i < listaSinistro.size(); i++) {
                if (listaSinistro.get(i).getId() == id) {
                    listaSinistro.remove(i);
                    encontrou = 1;
                    break;
                }
            }
            if (encontrou == 0) { System.out.println("Sinistro não encontrado."); }
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean printInfoCliente(Scanner sc) {
        try {
            Cliente cliente = buscarCliente(sc);
            System.out.println(cliente);
            if (cliente == null) return false;
            return true;
        }
        catch (Exception e) { return false; }
    }

    public ClientePF buscaClientePF(Scanner sc) {
        //Pede o input do CPF do cliente e faz sua busca na lista de clientes PF.
        System.out.println("Insira o CPF do cliente: ");
        String cpf = sc.nextLine();
        cpf = cpf.replaceAll("[^0-9]", "");

        for (int i = 0; i < listaClientesPF.size(); i++) {
            String currCPF = listaClientesPF.get(i).getCPF().replaceAll("[^0-9]", "");
            if (currCPF.equals(cpf)) {
                return listaClientesPF.get(i);
            }
        }
        return null; //caso em que o cliente buscado não existe
    }

    public ClientePJ buscaClientePJ(Scanner sc) {
        //Pede o input do CPF do cliente e faz sua busca na lista de clientes PF.
        System.out.println("Insira o CNPJ do cliente: ");
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
        System.out.println("Insira a placa do veículo: ");
        String palca = sc.nextLine();
        for (int i = 0; i < c.getListaVeiculos().size(); i++) {
            if (c.getListaVeiculos().get(i).getPlaca().equals(palca)) return c.getListaVeiculos().get(i);
        }
        return null; //caso em que o veículo buscado não existe
    }

    public Cliente buscarCliente (Scanner sc) {
        System.out.println("Cliente é PF ou PJ?: ");
        String pf_pj = sc.nextLine().toUpperCase();
        if (pf_pj.equals("PF")) { return buscaClientePF(sc); }
        else if (pf_pj.equals("PJ")) return buscaClientePJ(sc);
        else {
            System.out.println("Deve-se responder apenas com 'PF' ou 'PJ'. Tente novamente.");
            return buscarCliente(sc);
        }
    }
}
