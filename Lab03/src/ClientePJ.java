import java.util.Date;
public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, String educacao, String genero, String classeEconomica, String CNPJ, Date dataFundacao) {
        super(nome, endereco, educacao, genero, classeEconomica);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date newDataFundacao) {
        dataFundacao = newDataFundacao;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    @Override
    public String toString () {
        String s = "-----CLIENTE-----" + "\nNome: " + super.getNome() + "\nEndereco: " + super.getEndereco() + "\nData de Licença: " + super.getDataLicenca() + "\nEducação: " + super.getEducacao() + "\nGênero: " + super.getGenero() + "\nClasse Econômica: " + super.getClasseEconomica() + "\nLista de Veículos: " + super.getListaVeiculos() + "\nCNPJ: " + CNPJ + "\nData de Fundação: " + dataFundacao + "\n-----------------";
        return s;
    }

    /*
    public boolean validarCNPJ() {
        ...
    }
    */
}