package lab02;
import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    public int generateRandomId () {
        Random rand = new Random();
        int randomId = rand.nextInt(99999); //gera um número inteiro aleatório entre 0 e 99999.
        return randomId;
    }

    public Sinistro (String data, String endereco) {
        this.id = generateRandomId();
        this.data = data;
        this.endereco = endereco;
    }

    public int getId () {
        return id;
    }

    public void setId (int newId) {
        id = newId;
    }

    public String getData () {
        return data;
    }

    public void setData (String newData) {
        data = newData;
    }

    public String getEndereco () {
        return endereco;
    }

    public void setEndereco (String newEndereco) {
        endereco = newEndereco;
    }
}
