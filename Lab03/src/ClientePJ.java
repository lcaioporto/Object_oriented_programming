import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Calendar dataFundacao;

    public ClientePJ(String nome, String endereco, String CNPJ, Calendar dataFundacao) {
        super(nome, endereco);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
    }

    public Date getDataFundacao() {
        return dataFundacao.getTime();
    }

    public void setDataFundacao(Calendar newDataFundacao) {
        dataFundacao = newDataFundacao;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    @Override
    public String toString () {
        return "=========CLIENTE=========" + "\nNome: " + super.getNome() + "\nEndereco: " + super.getEndereco() +  "\nLista de Veículos: " + super.getListaVeiculos() + "\nCNPJ: " + CNPJ + "\nData de Fundação: " + dataFundacao + "\n=========================";
    }

    public boolean validarCNPJ() {
        String auxCNPJ = CNPJ.replaceAll("[^0-9]", ""); //retira caracteres que não são dígitos
        String digVerificadores = "", strVerificador1 = "", strVerificador2 = "";
        boolean allEqual = true; //auxiliar para verificar se todos os dígitos do CPF são iguais
        char charCurrDigit;
        int strLength = auxCNPJ.length();
        int currDigit; //dígito atual - usado para iterar o cpf
        int sum1 = 0, sum2 = 0; //soma da multiplicação do dígito atual pelo multiplicador, para calcular os digitos verificadores
        int verificador1, verificador2, rest; //dígitos verificadores; "rest" é um auxiliar usado para guardar o resto de uma divisão

        if (strLength != 14) return false; //verifica se o tamanho da string é igual a 11. Se não for, retorna falso.

        //verificar se todas os caracteres são iguais
        for (int i = 0; i < strLength - 1; i++) {
            char c1 = auxCNPJ.charAt(i);
            char c2 = auxCNPJ.charAt(i + 1);
            if (c1 != c2) {
                allEqual = false;
                break; //caso em que os caracteres não são todos iguais
            }
        }

        if (allEqual) return false; //caso em que todos os caracteres são iguais

        ArrayList<Integer> list1;
        list1 = new ArrayList<Integer>();
        for (int i = 5; i > 1; i--) list1.add(i);
        for (int i = 9; i > 1; i--) list1.add(i);

        ArrayList<Integer> list2;
        list2 = new ArrayList<Integer>();
        for (int i = 6; i > 1; i--) list2.add(i);
        for (int i = 9; i > 1; i--) list2.add(i);
        //verificação do CNPJ por dígitos de validação
        for (int i = 0; i < strLength; i++) {
            if (i < 12) {
                currDigit = Character.getNumericValue(auxCNPJ.charAt(i)); //valor numérico do dígito atual
                sum1 += currDigit*list1.get(i);
                sum2 += currDigit*list2.get(i);
            }
            else {
                charCurrDigit = auxCNPJ.charAt(i);
                digVerificadores += charCurrDigit; //dígitos verificadores do CNPJ
                
                if (i == 12) { //condição que serve apenas para garantir que o código abaixo rodará apenas 1 vez
                    //cálculo dígito verificador 1
                    rest = sum1 % 11;
                    if (rest < 2) verificador1 = 0;
                    else verificador1 = 11 - rest;
                    strVerificador1 = Integer.toString(verificador1);
                    if (strVerificador1.charAt(0) != digVerificadores.charAt(0)) return false; //verifica se o primeiro digito verificador é válido
                    //cálculo dígito verificador 2
                    sum2 += (Character.getNumericValue(charCurrDigit))*list2.get(i);
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
}