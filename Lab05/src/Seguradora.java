import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private final String CNPJ;
    private ArrayList<Cliente> listaClientes; //lista de todos os clientes da seguradora
    private ArrayList<Seguro> listaSeguros; //lista com todos os seguros da seguradora;

    //Construtor da Seguradora
    public Seguradora (String nome, String telefone, String email, String endereco, String CNPJ) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.CNPJ = CNPJ;
        listaClientes = new ArrayList<Cliente>();
        listaSeguros = new ArrayList<Seguro>();
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public ArrayList<Cliente> getListaClientes () {
        return listaClientes;
    }

    public ArrayList<Seguro> getListaSeguros () {
        return listaSeguros;
    }

    public String getCNPJ () {
        return CNPJ;
    }

    //Setters
    public void setNome (String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    //Funções

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
            System.out.println("\nInsira o telefone do cliente: ");
            String telefone_cliente = sc.nextLine();
            System.out.println("\nInsira o e-mail do cliente: ");
            String email_cliente = sc.nextLine();
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
                //Criar objeto ClientePJ
                ClientePJ c = new ClientePJ(nome_cliente, endereco_cliente, telefone_cliente, email_cliente, CNPJ, dataFundacao);
                listaClientes.add(c);
            }
            else if (pj_pf.equals("PF")) { //Cliente PF
                //Inicializando datas
                Date dataNascimento;
                //educação
                System.out.println("\nInsira o grau de educação acadêmcia do cliente: ");
                String educacao = sc.nextLine();
                //gênero
                System.out.println("\nInsira o gênero do cliente. Responda apenas com 'M' (masculino), 'F' (feminino) ou 'NI' (prefiro não informar).");
                String genero = sc.nextLine();
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
                ClientePF c = new ClientePF(nome_cliente, endereco_cliente, telefone_cliente, email_cliente, educacao, genero, CPF, dataNascimento);
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
            Cliente cliente = Buscar.buscarCliente(sc, listaClientes);
            if (cliente == null) return false;
            listaClientes.remove(cliente); //remover da lista geral de todos os clientes
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

    public ArrayList<Sinistro> getSinistrosPorCliente(Scanner sc) {
        //Printa as informações de todos os sinistros de um determinado cliente
        ArrayList<Sinistro> listaSinistrosCliente = new ArrayList<Sinistro>();
        Cliente cliente = Buscar.buscarCliente(sc, listaClientes);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return null;
        }
        else {
            System.out.println("Serão mostradas informações dos sinistros relacionados ao seguro que o cliente possui.");
            for (Seguro seguro : listaSeguros) {
                if (seguro.getCliente().equals(cliente)) {
                    for (Sinistro sinistro : seguro.getListaSinistros()) {
                        if (sinistro.getCondutor() == null) {
                            listaSinistrosCliente.add(sinistro);
                        }
                    }
                }
            }
            return listaSinistrosCliente;
        }
    }

    public boolean removerSinistroSeguro(Scanner sc) {
        //dado um certo ID, remove o sinistro que tem esse ID da lista de Sinistros de um seguro.
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            Seguro seguro = Buscar.buscarSeguro(sc, listaSeguros);
            if (seguro == null) return false;
            boolean check = seguro.removerSinistro(sc);
            if (!check) return false;
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean removerSinistroCondutor(Scanner sc) {
        //dado um certo ID, remove o sinistro que tem esse ID da lista de Sinistros de um seguro.
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            Seguro seguro = Buscar.buscarSeguro(sc, listaSeguros);
            if (seguro == null) return false;
            Condutor condutor = Buscar.buscaCondutor(sc, seguro.getListaCondutores());
            if (condutor == null) return false;
            boolean check = condutor.removerSinistro(sc);
            if (!check) return false;
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean printInfoCliente(Scanner sc) {
        //Printa as informações de um cliente específco da seguradora
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            Cliente cliente = Buscar.buscarCliente(sc, listaClientes);
            if (cliente == null) return false;
            System.out.println(cliente);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean visualizarSinistroSeguro(Scanner sc) {
        //dado um certo id, printa-se as informações do sinsitro que tem esse id"
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            Seguro seguro = Buscar.buscarSeguro(sc, listaSeguros);
            if (seguro == null) return false;
            Sinistro sinistro = Buscar.buscarSinistro(sc, seguro.getListaSinistros());
            if (sinistro == null) return false;
            System.out.println(sinistro);
            return false;
        }
        catch (Exception e) { return false; }
    }

    public double calcularReceita () {
        double receita = 0;
        for (Seguro seguro : listaSeguros) {
            receita += seguro.calcularValor();
        }
        return receita;
    }

    public boolean transferirSeguro(Cliente cIni, Cliente cFinal) {
        //Transfere o seguro do cliente cIni para cFinal
        try {
            Seguro seguroIni = null, seguroFinal = null;
            for (Seguro seguro : listaSeguros) {
                if (seguro.getCliente().equals(cIni)) {
                    seguroIni = seguro;
                    break;
                }
            }
            for (Seguro seguro : listaSeguros) {
                if (seguro.getCliente().equals(cFinal)) {
                    seguroFinal = seguro;
                    break;
                }
            }
            if (seguroIni == null || seguroFinal == null) return false;
            //garantir que os dois clientes são do mesmo tipo
            if (((cIni instanceof ClientePF) && (cFinal instanceof ClientePF)) || ((cIni instanceof Cliente PJ) || (cFinal instanceof ClientePJ))) {
                seguroIni.setCliente(cFinal);
            }
            else {
                System.out.println("Os dois clientes devem ser ambos PF ou ambos PJ.");
                return false;
            }
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean listarVeiculoCliente (Scanner sc, Cliente c) {
        //dado um cliente, printa os carros dele
        if (c == null) return false;
        if (c instanceof ClientePF) {
            if (((ClientePF) c).getListaVeiculos().size() == 0) {
                System.out.println("=======================================");
                System.out.println("Não há veículo cadastrado neste cliente");
                System.out.println("=======================================");
                return false;
            }
            for (Veiculo v : ((ClientePF) c).getListaVeiculos()) {
                System.out.println(v);
            }
            return true;
        }
        else {
            for (Frota frota : ((ClientePJ) c).getListaFrota()) {
                System.out.println(frota.getListaVeiculos());
            }
            return true;
        }
    }

    public boolean listarVeiculoSeguradora (Scanner sc) {
        //itera a lista de clientes pritando todos os carros de todos os clientes da seguradora
        if (listaClientes.size() == 0) return false;
        for (Cliente c : listaClientes) {
            listarVeiculoCliente(sc, c);
            }
        return true;
    }

    public boolean gerarSeguro (Scanner sc) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //DATA INÍCIO
        System.out.println("Qual a data de início do seguro (Formato dd-MM-yyyy)?");
        String d = sc.nextLine();
        Date dataInicio = Validacao.validaFormatoDate(sc, d, formatter);
        //DATA FIM
        System.out.println("\nQual a data de fim do seguro (Formato dd-MM-yyyy)?");
        d = sc.nextLine();
        Date dataFim = Validacao.validaFormatoDate(sc, d, formatter);
        while (!Validacao.validaDataLinceca(dataInicio, dataFim)) {
            System.out.println("A data final deve ser após a data de início!");
            System.out.println("Tente novamente...");
            System.out.println("Qual a data de fim do seguro (Formato dd-MM-yyyy)?");
            d = sc.nextLine();
            dataFim = Validacao.validaFormatoDate(sc, d, formatter);
        }
        //SEGURADORA
        Seguradora seguradora = this;
        //BUSCAR CLIENTE
        System.out.println("\nInsira informações sobre o titular do seguro.");
        Cliente cliente = Buscar.buscarCliente(sc, listaClientes);
        if (cliente == null) return false;
        System.out.print("\n");
        //SEGURO PF
        if (cliente instanceof ClientePF) {
            Veiculo veiculo = Buscar.buscarVeiculo(sc, ((ClientePF) cliente).getListaVeiculos());
            if (veiculo == null) return false;
            SeguroPF seguro = new SeguroPF(dataInicio, dataFim, seguradora, veiculo, (ClientePF) cliente);
            listaSeguros.add(seguro);
        } else { //SEGURO PJ
            Frota frota = Buscar.buscarFrota(sc, ((ClientePJ) cliente).getListaFrota());
            if (frota == null) return false;
            SeguroPJ seguro = new SeguroPJ(dataInicio, dataFim, seguradora, frota, (ClientePJ) cliente);
            listaSeguros.add(seguro);
        }
        return true;
    }

    public ArrayList<Seguro> getSegurosPorCliente(Scanner sc) {
        ArrayList<Seguro> listaSegurosCliente = new ArrayList<Seguro>();
        Cliente cliente = Buscar.buscarCliente(sc, listaClientes);
        for (Seguro seguro : listaSeguros) {
            if (seguro.getCliente().equals(cliente)) {
                listaSegurosCliente.add(seguro);
            }
        }
        return listaSegurosCliente;
    }

    public boolean cancelarSeguro (Scanner sc) {
        Seguro seguro = Buscar.buscarSeguro(sc, listaSeguros);
        if (seguro == null) return false;
        listaSeguros.remove(seguro);
        return true;
    }

    @Override
    public String toString() {
        return "======== DADOS SEGURADORA ========\n" + "Nome: " + getNome() + "\nEmail: " + getEmail() + "\nEndereço: " + getEndereco() + "\nTelefone: " + getTelefone() + "\nCNPJ: " + getCNPJ() + "\n==================================";
    }
}