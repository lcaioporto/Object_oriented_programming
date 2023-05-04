import java.util.Date;
import java.util.concurrent.TimeUnit;
public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private final Date dataNascimento;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, String educacao, String genero, String classeEconomica, String CPF, Date dataNascimento, Date dataLicenca) {
        super(nome, endereco);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.classeEconomica = classeEconomica;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setEducacao (String educacao) {
        this.educacao = educacao;
    }

    public String getEducacao () {
        return educacao;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    public String getClasseEconomica () {
        return classeEconomica;
    }

    private int calcIdade () {
        Date now = new Date();
        long diffInMillies = Math.abs(now.getTime() - dataNascimento.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int) diff/365;
    }

    public double calculaScore () {
        int idade = calcIdade();
        double fator_idade = 0;
        //Definição do fator idade
        if (idade >= 18 && idade < 30) fator_idade = CalcSeguro.FATOR_18_30.getValue();
        else if (idade < 60) fator_idade = CalcSeguro.FATOR_30_60.getValue();
        else fator_idade = CalcSeguro.FATOR_60_90.getValue();
        //Conta do Score
        return CalcSeguro.VALOR_BASE.getValue() * super.getListaVeiculos().size() * fator_idade;
    }

    @Override
    public String toString () {
        return "=========CLIENTE=========" + "\nNome: " + super.getNome() + "\nEndereco: " + super.getEndereco() + "\nData de Licença: " + getDataLicenca() + "\nEducação: " + getEducacao() + "\nGênero: " + getGenero() + "\nClasse Econômica: " + getClasseEconomica() + "\nLista de Veículos: " + super.getListaVeiculos() + "\nCPF: " + getCPF() + "\nData de Nascimento: " + getDataNascimento() + "\n=========================";
    }
}