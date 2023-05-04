import java.util.Date;
public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, String CNPJ, Date dataFundacao, int qtdeFuncionarios) {
        super(nome, endereco);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
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

    public double CalcScore() {
        return CalcSeguro.VALOR_BASE.getValue() * (1 + (qtdeFuncionarios)/100) * super.getListaVeiculos().size();
    }

    @Override
    public String toString () {
        return "=========CLIENTE=========" + "\nNome: " + super.getNome() + "\nEndereco: " + super.getEndereco() +  "\nLista de Veículos: " + super.getListaVeiculos() + "\nCNPJ: " + CNPJ + "\nData de Fundação: " + dataFundacao + "\n=========================";
    }
}