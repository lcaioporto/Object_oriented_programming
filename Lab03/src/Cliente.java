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

    /*
    public boolean validarCPF () {
        String auxCpf = cpf.replaceAll("[^0-9]", ""); //retira caracteres que não são dígitos
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
    */
}