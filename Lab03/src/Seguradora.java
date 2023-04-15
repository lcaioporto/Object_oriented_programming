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
            System.out.println("Insira o nome do cliente: ");
            String nome = sc.nextLine();
            System.out.println("Insira o endereço do cliente: ");
            String endereco = sc.nextLine();
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
                ClientePJ c = new ClientePJ(nome, endereco, CNPJ, dataFundacao);
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
                ClientePF c = new ClientePF(nome, endereco, educacao, genero, classeEconomica, CPF, dataNascimento, dataLicenca);
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
            int encontrou = 0;
            String nome = "";
            System.out.println("Deseja-se remover um cliente PF ou PJ?");
            String pj_pf = sc.nextLine();

            if (pj_pf.equals("PJ")) { //Cliente PJ

                System.out.println("Insira o CNPJ do cliente: ");
                String cnpj = sc.nextLine();
                cnpj = cnpj.replaceAll("[^0-9]", "");

                for (int i = 0; i < listaClientesPJ.size(); i++) { //itera a lista buscando o CNPJ que deseja-se remover.
                    String currCNPJ = listaClientesPJ.get(i).getCNPJ().replaceAll("[^0-9]", "");
                    if (currCNPJ.equals(cnpj)) {
                        nome = listaClientesPJ.get(i).getNome();
                        listaClientesPJ.remove(i);
                        encontrou = 1;
                        break;
                    }
                }
            }

            else if (pj_pf.equals("PF")) { //Cliente PF

                System.out.println("Insira o CPF do cliente: ");
                String cpf = sc.nextLine();
                cpf = cpf.replaceAll("[^0-9]", "");

                for (int i = 0; i < listaClientesPF.size(); i++) {
                    String currCPF = listaClientesPF.get(i).getCPF().replaceAll("[^0-9]", "");
                    if (currCPF.equals(cpf)) {
                        nome = listaClientesPF.get(i).getNome();
                        listaClientesPF.remove(i);
                        encontrou = 1;
                        break;
                    }
                }
            }
            if (encontrou == 0) return false;
            else {
                for (int i = 0; i < listaClientes.size(); i++) { //remover da lista geral de todos os clientes
                    if (listaClientes.get(i).getNome().equals(nome)) {
                        listaClientes.remove(i);
                        break;
                    }
                }
            }
        System.out.println("Cliente removido com sucesso!");
        return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean gerarSinistro(Sinistro s) {
        try {
            listaSinistro.add(s);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public ArrayList<Cliente> listarClientes() {
        return listaClientes;
    }

    public ArrayList<ClientePF> listarClientesPF () {
        return listaClientesPF;
    }

    public ArrayList<ClientePJ> listarClientesPJ () {
        return listaClientesPJ;
    }

    public ArrayList<Sinistro> listarSinistro() {
        return listaSinistro;
    }

    public boolean visualizarSinistro(Cliente c) {
        try {
            for (int i = 0; i < listaSinistro.size(); i++) {
                if ((((listaSinistro.get(i)).getCliente()).getNome()).equals(c.getNome())) {
                    listaSinistro.get(i).toString();
                    break;
                }
            }
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean removerSinistro(int id) {
        try {
            int encontrou = 0;
            for (int i = 0; i < listaSinistro.size(); i++) {
                if (((listaSinistro.get(i)).getId()) == id) {
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
}