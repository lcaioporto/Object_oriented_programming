import java.util.Date;
import java.util.ArrayList;
public class Cliente {
    private String nome;
    private String endereco;
    private final Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private ArrayList<Veiculo> listaVeiculos;

    //Construtor
    public Cliente (String nome, String endereco, String educacao, String genero, String classeEconomica) {
        this.nome = nome;
        this.endereco = endereco;
        dataLicenca = new Date();
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
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

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setEducacao(String newEducacao) {
        educacao = newEducacao;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setGenero(String newGenero) {
        genero = newGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setClasseEconomica(String newClasseEconomica) {
        genero = newClasseEconomica;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }


    @Override
    public String toString () {
        String s = "-----CLIENTE-----" + "\nNome: " + nome + "\nEndereco: " + endereco + "\nData de Licença: " + dataLicenca + "\nEducação: " + educacao + "\nGênero: " + genero + "\nClasse Econômica: " + classeEconomica + "\nLista de Veículos: " + listaVeiculos + "\n-----------------";
        return s;
    }

    public void addVeiculo(Veiculo v) {
        listaVeiculos.add(v);
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
}