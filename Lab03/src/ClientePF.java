import java.util.Date;
public class ClientePF extends Cliente {
    private final String CPF;
    private Date dataNascimento;

    public ClientePF(String nome, String endereco, String educacao, String genero, String classeEconomica, String CPF, Date dataNascimento) {
        super(nome, endereco, educacao, genero, classeEconomica);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date newDataNascimento) {
        dataNascimento = newDataNascimento;
    }

    public String getCPF() {
        return CPF;
    }
}