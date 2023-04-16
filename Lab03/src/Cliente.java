import java.util.ArrayList;
import java.util.Scanner;
public class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;

    //Construtor
    public Cliente (String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        listaVeiculos = new ArrayList<Veiculo>();
    }

    //Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome (String newName) {
        nome = newName;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco (String newEndereco) {
        endereco = newEndereco;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    @Override
    public String toString () {
        return "=========CLIENTE=========" + "\nNome: " + nome + "\nEndereco: " + endereco + "\nLista de Veículos: " + listaVeiculos + "\n=========================";
    }

    public boolean cadastrarVeiculo(Scanner sc) {
        try {
            //placa
            System.out.println("Insira a placa do veículo: ");
            String placa = sc.nextLine();
            //marca
            System.out.println("Insira a marca do veículo: ");
            String marca = sc.nextLine();
            //modelo
            System.out.println("Insira o modelo do veículo: ");
            String modelo = sc.nextLine();
            //ano de fabricação
            System.out.println("Insira o ano de fabricação do veículo: ");
            int anoFabricacao = Integer.parseInt(sc.nextLine());
            //criar objeto veículo
            Veiculo v = new Veiculo(placa, marca, modelo, anoFabricacao);
            listaVeiculos.add(v);
            return true;
        }
        catch (Exception e) { return false; }
    }
}