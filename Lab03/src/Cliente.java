import java.util.ArrayList;
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
        return"-----CLIENTE-----" + "\nNome: " + nome + "\nEndereco: " + endereco + "\nLista de Veículos: " + listaVeiculos + "\n-----------------";
    }

    public boolean cadastrarVeiculo(Veiculo v) {
        try {
            listaVeiculos.add(v);
            return true;
        }
        catch (Exception e) { return false; }
    }
}