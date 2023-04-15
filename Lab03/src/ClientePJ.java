import java.util.Date;
public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, String CNPJ, Date dataFundacao) {
        super(nome, endereco);
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
        return "-----CLIENTE-----" + "\nNome: " + super.getNome() + "\nEndereco: " + super.getEndereco() +  "\nLista de Veículos: " + super.getListaVeiculos() + "\nCNPJ: " + CNPJ + "\nData de Fundação: " + dataFundacao + "\n-----------------";
    }

    /*
    public boolean validarCNPJ() {
        ...
    }
    */
}