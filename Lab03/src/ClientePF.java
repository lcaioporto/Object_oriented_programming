import java.util.Calendar;
import java.util.Date;
public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private Calendar dataLicenca;
    private String educacao;
    private final Calendar dataNascimento;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, String educacao, String genero, String classeEconomica, String CPF, Calendar dataNascimento, Calendar dataLicenca) {
        super(nome, endereco);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.classeEconomica = classeEconomica;
    }

    public Date getDataNascimento() {
        return dataNascimento.getTime();
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

    public void setDataLicenca(Calendar dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public Date getDataLicenca() {
        return dataLicenca.getTime();
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

    public boolean validarCPF () {
        String auxCpf = CPF.replaceAll("[^0-9]", ""); //retira caracteres que não são dígitos
        String digVerificadores = "", strVerificador1 = "", strVerificador2 = "";
        boolean allEqual = true; //auxiliar para verificar se todos os dígitos do CPF são iguais
        char charCurrDigit;
        int stringLength = auxCpf.length();
        int currDigit; //dígito atual - usado para iterar o cpf
        int multiplicador1 = 10, multiplicador2 = 11; //multiplicadores - usado para encontrar os digitos verificadores
        int sum1 = 0, sum2 = 0; //soma da multiplicação do dígito atual pelo multiplicador, para calcular os digitos verificadores
        int verificador1, verificador2, rest; //dígitos verificadores; "rest" é um auxiliar usado para guardar o resto de uma divisão

        if (stringLength != 11) return false; //verifica se o tamanho da string é igual a 11. Se não for, retorna falso.

        //verificar se todas os caracteres são iguais
        for (int i = 0; i < stringLength - 1; i++) {
            char c1 = auxCpf.charAt(i);
            char c2 = auxCpf.charAt(i + 1);
            if (c1 != c2) {
                allEqual = false;
                break; //caso em que os caracteres não são todos iguais
            }
        }

        if (allEqual) return false; //caso em que todos os caracteres são iguais

        //verificação do CPF por dígitos de validação
        for (int i = 0; i < stringLength; i++) {
            if (i < 9) {
                currDigit = Character.getNumericValue(auxCpf.charAt(i)); //valor numérico do dígito atual
                sum1 += currDigit*multiplicador1;
                sum2 += currDigit*multiplicador2;
                multiplicador1 -= 1;
                multiplicador2 -= 1;
            }
            else {
                charCurrDigit = auxCpf.charAt(i);
                digVerificadores += charCurrDigit; //dígitos verificadores do CPF
                
                if (i == 9) { //condição que serve apenas para garantir que o código abaixo rodará apenas 1 vez
                    //cálculo dígito verificador 1
                    rest = sum1 % 11;
                    if (rest < 2) verificador1 = 0;
                    else verificador1 = 11 - rest;
                    strVerificador1 = Integer.toString(verificador1);

                    //cálculo dígito verificador 2
                    sum2 += multiplicador2*verificador1;
                    rest = sum2 % 11;
                    if (rest < 2) verificador2 = 0;
                    else verificador2 = 11 - rest;
                    strVerificador2 = Integer.toString(verificador2);
                }
            }
        }
        if (digVerificadores.equals(strVerificador1 + strVerificador2)) return true;
        return false;
    }

    @Override
    public String toString () {
        return "=========CLIENTE=========" + "\nNome: " + super.getNome() + "\nEndereco: " + super.getEndereco() + "\nData de Licença: " + getDataLicenca() + "\nEducação: " + getEducacao() + "\nGênero: " + getGenero() + "\nClasse Econômica: " + getClasseEconomica() + "\nLista de Veículos: " + super.getListaVeiculos() + "\nCPF: " + getCPF() + "\nData de Nascimento: " + getDataNascimento() + "\n=========================";
    }
}