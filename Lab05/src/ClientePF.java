import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeUnit;
public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private String educacao;
    private final Date dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;

    public ClientePF(String nome, String endereco, String telefone, String email, String educacao, String genero, String CPF, Date dataNascimento) {
        super(nome, endereco, telefone, email);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.educacao = educacao;
        listaVeiculos = new ArrayList<Veiculo>();
    }

    //Getters
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public String getGenero() {
        return genero;
    }

    public String getEducacao () {
        return educacao;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    //Setters
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEducacao (String educacao) {
        this.educacao = educacao;
    }

    //Funções

    public boolean cadastrarVeiculo(Scanner sc) {
        //Recebe todas as informações necessárias para criar um objeto Veículo.
        //O objeto criado é associado ao cliente, adicionando-o na lista de veiculos.
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            int anoFabricacao = 0;
            //placa
            System.out.println("\nInsira a placa do veículo: ");
            String placa = sc.nextLine();
            //marca
            System.out.println("\nInsira a marca do veículo: ");
            String marca = sc.nextLine();
            //modelo
            System.out.println("\nInsira o modelo do veículo: ");
            String modelo = sc.nextLine();
            //ano de fabricação
            System.out.println("\nInsira o ano de fabricação do veículo: ");
            while (true) {
                try {
                    anoFabricacao = Integer.parseInt(sc.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("==================================================");
                    System.out.println("O ano de fabricação do veículo deve ser um número!");
                    System.out.println("Tente novamente.");
                    System.out.println("==================================================");
                }
            }
            //criar objeto veículo
            Veiculo v = new Veiculo(placa, marca, modelo, anoFabricacao);
            listaVeiculos.add(v);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean removeVeiculo (Scanner sc) {
        //Remove um veículo a partir de sua placa
        System.out.println("Insira a placa do veículo: ");
        String palca = sc.nextLine();
        for (Veiculo v : listaVeiculos) {
            if (v.getPlaca().equals(palca)) {
                listaVeiculos.remove(listaVeiculos.indexOf(v));
                return true;
            }
        }
        return false; //caso em que o veículo buscado não existe
    }
    
    public int calcIdade () {
        Date now = new Date();
        long diffInMillies = Math.abs(now.getTime() - dataNascimento.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int) diff/365;
    }
    @Override
    public String toString () {
        return super.toString() + "\nEducação: " + getEducacao() + "\nGênero: " + getGenero() + "\nLista de Veículos: " + getListaVeiculos() + "\nCPF: " + getCPF() + "\nData de Nascimento: " + getDataNascimento() + "\n=========================\n";
    }
}