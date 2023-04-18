import java.util.ArrayList;
import java.util.Scanner;
public class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos; //contém todos os veículos de um certo cliente

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
        //Recebe todas as informações necessárias para criar um objeto Veículo.
        //O objeto criado é associado ao cliente, adicionando-o na lista de veiculos.
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
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
            int anoFabricacao = Integer.parseInt(sc.nextLine());
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
        for (int i = 0; i < listaVeiculos.size(); i++) {
            if (listaVeiculos.get(i).getPlaca().equals(palca)) {
                listaVeiculos.remove(i);
                return true;
            }
        }
        return false; //caso em que o veículo buscado não existe
    }
}