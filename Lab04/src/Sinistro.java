import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
public class Sinistro {
    private final int ID;
    private Date data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    private static ArrayList<Integer> listaID; //lista com todos os ID's já criados; usado para não permitir nenhum ID repetido; 

    public int generateRandomId () {
        //função que gera e retorna um id aleatório entre 0 e 999999.
        Random rand = new Random();
        int randomId = rand.nextInt(999999); //gera um número inteiro aleatório entre 0 e 999999.
        try {
            int n = listaID.size();
            for (int i = 0; i < n; i++) {
                if (listaID.contains(randomId)) { //caso em que o ID gerado já existe
                    return generateRandomId(); //garante que o ID a ser criado será único
                }
            }
            return randomId;
        }
        catch (Exception e) { //significa que a lista ainda não foi criada, ou seja, ainda não tem ID
            return randomId;
        }
    }

    //Construtor
    public Sinistro (Date data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.ID = generateRandomId();
        ArrayList<Integer> listaID = new ArrayList<Integer>();
        listaID.add(this.ID);
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    //Getters and setters
    public int getId () {
        return ID;
    }

    public Date getData () {
        return data;
    }

    public void setData (Date data) {
        this.data = data;
    }

    public String getEndereco () {
        return endereco;
    }

    public void setEndereco (String endereco) {
        this.endereco = endereco;
    }

    public void setSeguradora (Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public String getSeguradora() {
        return seguradora.getNome();
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getVeiculo () {
        return veiculo.getPlaca();
    }

    public void setCliente (Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente () {
        return cliente;
    }

    @Override
    public String toString () {
        return "========SINISTRO========" + "\nID: " + ID + "\nEndereco: " + endereco + "\nData: " + data + "\nSeguradora: " + seguradora.getNome() + "\nVeículo (Placa): " + veiculo.getPlaca() + "\nCliente (Nome): " + cliente.getNome() + "\nCliente (CPF ou CNPJ): " + cliente.getId() + "\n========================";
    }
}
