
import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    public int generateRandomId () {
        //função que gera e retorna um id aleatório entre 0 e 999999.
        Random rand = new Random();
        int randomId = rand.nextInt(999999); //gera um número inteiro aleatório entre 0 e 999999.
        return randomId;
    }

    //Construtor
    public Sinistro (String data, String endereco) {
        this.id = generateRandomId();
        this.data = data;
        this.endereco = endereco;
    }

    //Getters and setters
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
