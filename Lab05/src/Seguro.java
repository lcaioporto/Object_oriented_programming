import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public abstract class Seguro {
    private final int ID;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros; //contém apenas sinsitros do cliente do seguro
    private ArrayList<Condutor> listaCondutores;
    private double valorMensal;
    private static ArrayList<Integer> listaID;

    public int generateRandomId () {
        //função que gera e retorna um id aleatório entre 0 e 999999.
        Random rand = new Random();
        int randomId = rand.nextInt(999999); //gera um número inteiro aleatório entre 0 e 999999.
        try {
            if (listaID.contains(randomId)) { //caso em que o ID gerado já existe
                return generateRandomId(); //garante que o ID a ser criado será único
            }
            return randomId;
        }
        catch (Exception e) { //significa que a lista ainda não foi criada, ou seja, ainda não tem ID
            return randomId;
        }
    }

    //Construtor
    public Seguro (Date dataInicio, Date dataFim, Seguradora seguradora) {
        listaID = new ArrayList<Integer>();
        this.ID = generateRandomId();
        listaID.add(this.ID);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        listaCondutores = new ArrayList<Condutor>();
        listaSinistros = new ArrayList<Sinistro>();
    }

    //Getters
    public int getID() {
        return ID;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim () {
        return dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public Cliente getCliente() {
        return null;
    }

    //Setters

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim (Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public void setCliente (Cliente cliente) {}

    //Funções
    public double calcularValor () {
        return 0;
    }

    public int calcIdade (Date dataNascimento) {
        Date now = new Date();
        long diffInMillies = Math.abs(now.getTime() - dataNascimento.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int) diff/365;
    }

    protected boolean autorizarCondutor(Scanner sc) {
        //cria um novo objeto condutor e o adiciona na lista
        try {
            System.out.println("Insira as informações sobre o condutor.");
            System.out.println("Insira o nome do condutor: ");
            String nome = sc.nextLine();
            while (! Validacao.validaNome(nome)) {
                System.out.println("==============================================================");
                System.out.println("Nome inválido! O nome do condutor deve conter apenas letras!");
                System.out.println("Tente novamente.");
                System.out.println("==============================================================");
                System.out.println("Insira o nome do condutor: ");
                nome = sc.nextLine();
            }
            System.out.println("\nCPF do condutor: ");
            String CPF = sc.nextLine();
            while (! Validacao.validarCPF(CPF)) {
                System.out.println("===============================");
                System.out.println("CPF inválido. Tente novamente.");
                System.out.println("===============================");
                System.out.println("\nInsira o CPF do cliente: ");
                CPF = sc.nextLine();
            }
            System.out.println("\nInsira o telefone da seguradora: ");
            String telefone = sc.nextLine();
            System.out.println("\nInsira o e-mail da seguradora: ");
            String email = sc.nextLine();
            System.out.println("\nInsira o endereço da seguradora: ");
            String endereco = sc.nextLine();
            System.out.println("\nInsira a data de nascimento (Formato dd-MM-yyyy): ");
            String d = sc.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date dataNascimento = Validacao.validaFormatoDate(sc, d, formatter);
            while (! Validacao.validaDataNascimento(dataNascimento)) { 
                //verifica se o cliente tem mais de 18 anos
                System.out.println("===================================================");
                System.out.println("Data inválida! O condutor deve ter mais de 18 anos.");
                System.out.println("===================================================");
                return autorizarCondutor(sc);
            }
            Condutor c = new Condutor(CPF, nome, telefone, endereco, email, dataNascimento);
            getListaCondutores().add(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean desautorizarCondutor (Scanner sc) {
        //Busca um condutor na lista e o remove
        try {
            Condutor c = Buscar.buscaCondutor(sc, getListaCondutores());
            getListaCondutores().remove(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean gerarSinistro(Scanner sc, Seguradora seguradora) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date data;
            //Data
            System.out.println("\nInsira a data do Sinistro (Formato dd-MM-yyyy): ");
            String d = sc.nextLine();
            data = Validacao.validaFormatoDate(sc, d, formatter);
            //Endereço
            System.out.println("Insira o endereço do sinistro: ");
            String endereco_sinistro = sc.nextLine();
            //Condutor
            Condutor condutor = null;
            boolean ok = false;
            do {
                System.out.println("\nO condutor é o titular do seguro? Responda apenas com 'S'(sim) ou 'N' (não).");
                String yN = sc.nextLine().toLowerCase();
                if (yN.equals("s")) { 
                    condutor = null;
                    ok = true;
                    //objeto Sinistro
                    Sinistro s = new Sinistro(data, endereco_sinistro, this, condutor);
                    getListaSinistros().add(s);
                }
                else  if (yN.equals("n")) {
                    System.out.println("Então vamos buscar informações sobre o condutor relacionado ao sinistro.");
                    condutor = Buscar.buscaCondutor(sc, getListaCondutores());
                    ok = true;
                    //objeto Sinistro
                    Sinistro s = new Sinistro(data, endereco_sinistro, this, condutor);
                    condutor.getListaSinistros().add(s); //adiciona na lista de sinistros do condutor
                }
                else {
                    System.out.println("Responda apenas com 'S' ou 'N'");
                    System.out.println("Tente novamente...");
                }
            } while (!ok);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean removerSinistro(Scanner sc) {
        try {
            Sinistro s = Buscar.buscarSinistro(sc, listaSinistros);
            if (s == null) return false;
            listaSinistros.remove(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}