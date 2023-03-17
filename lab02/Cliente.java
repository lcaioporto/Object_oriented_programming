package lab02;

public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    //Construtor
    public Cliente (String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    //Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome (String newName) {
        nome = newName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf (String newCpf) {
        cpf = newCpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento (String newDataNascimento) {
        dataNascimento = newDataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade (int newIdade) {
        idade = newIdade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco (String newEndereco) {
        endereco = newEndereco;
    }

    @Override
    public String toString () {
        String s = "-----CLIENTE-----" + "\nNome: " + nome + "\nIdade: " + idade + "\nCPF: " + cpf + "\nData de Nascimento: " + dataNascimento + "\nEndereco: " + endereco + "\n-----------------";
        return s;
    }

    public boolean validarCPF () {

        String auxCpf = cpf.replaceAll("[^0-9]", ""); //retira caracteres que não são dígitos
        int stringLength = auxCpf.length();
        boolean allEqual = true;
        String digVerificadores = "", strVerificador1 = "", strVerificador2 = "";
        char charCurrDigit;
        int currDigit; //dígito atual - usado para iterar o cpf
        int multiplicador1 = 10, multiplicador2 = 11; //multiplicadores - usado para encontrar os digitos verificadores
        int sum1 = 0, sum2 = 0; //soma da multiplicação do dígito atual pelo multiplicador, para calcular os digitos verificadores
        int verificador1, verificador2, rest;

        if (stringLength != 11) {
            return false; //verifica se o tamanho da string é igual a 11.
        }

        //verificar se todas os caracteres são iguais
        for (int i = 0; i < stringLength - 1; i++) {
            char c1 = auxCpf.charAt(i);
            char c2 = auxCpf.charAt(i + 1);
            if (c1 != c2) {
                allEqual = false;
                break; //caso em que os caracteres não são todos iguais
            }
        }
        if (allEqual) {
            return false; //caso em que todos os caracteres são iguais
        }

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
}