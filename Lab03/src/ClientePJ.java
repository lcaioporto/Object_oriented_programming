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
}