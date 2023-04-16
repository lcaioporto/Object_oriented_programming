import java.util.Random;
public class Sinistro {
    private final int ID; //garantir que o ID é único
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    public int generateRandomId () {
        //função que gera e retorna um id aleatório entre 0 e 999999.
        Random rand = new Random();
        int randomId = rand.nextInt(999999); //gera um número inteiro aleatório entre 0 e 999999.
        try {
            int n = (Seguradora.listaSinistro).size();
            for (int i = 0; i < n; i++) {
                if (((Seguradora.listaSinistro).get(i)).getId() == randomId) {
                    generateRandomId();
                    break;
                }
            }
            return randomId;
        }
        catch (Exception e) { //significa que a lista ainda não foi criada, ou seja, ainda não tem ID
            return randomId;
        }
    }

    //Construtor
    public Sinistro (String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.ID = generateRandomId();
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

    public String getData () {
        return data;
    }

    public void setData (String data) {
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
        return "-----SINISTRO-----" + "\nID: " + ID + "\nEndereco: " + endereco + "\nData: " + data + "\nSeguradora: " + seguradora.getNome() + "\nVeículo (Placa): " + veiculo.getPlaca() + "\nCliente: " + cliente.getNome() + "\n------------------";
    }
}
