import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
public class Sinistro {
    private final int ID;
    private Date data;
    private String endereco;
    private Seguro seguro;
    private Condutor condutor;
    private static ArrayList<Integer> listaID; //lista com todos os ID's já criados; usado para não permitir nenhum ID repetido; 

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
    public Sinistro (Date data, String endereco, Seguro seguro, Condutor condutor) {
        this.ID = generateRandomId();
        ArrayList<Integer> listaID = new ArrayList<Integer>();
        listaID.add(this.ID);
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
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

    public void setCondutor (Condutor condutor) {
        this.condutor = condutor;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public Seguro getSeguro () {
        return this.seguro;
    }

    @Override
    public String toString () {
        return "========SINISTRO========" + "\nID: " + ID + "\nEndereco: " + endereco + "\nData: " + data + "\nSeguro (ID): " + seguro.getID() + "\nCondutor (Nome): " + condutor.getNome() + "\nCondutor (CPF): " + condutor.getCPF() + "\n========================";
    }
}
