import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

public class Validacao {

    public static boolean validarCPF (String CPF) {
        //Algoritimo de validação de CPF.
        //Caso o CPF seja válido, retorna true; c.c. retorna false.
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

    public static boolean validarCNPJ(String CNPJ) {
        //Algoritimo de validação de CNPJ.
        //Caso o CPF seja válido, retorna true; c.c. retorna false.
        String auxCNPJ = CNPJ.replaceAll("[^0-9]", ""); //retira caracteres que não são dígitos
        String digVerificadores = "", strVerificador1 = "", strVerificador2 = "";
        boolean allEqual = true; //auxiliar para verificar se todos os dígitos do CNPJ são iguais
        char charCurrDigit;
        int strLength = auxCNPJ.length();
        int currDigit; //dígito atual - usado para iterar o CNPJ
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

    public static boolean validaNome (String nome) {
        return nome.matches("^[a-zA-ZÀ-ę]*$");
    }

    public static boolean validaDataNascimento (Date data) {
        //verificar se o cliente tem pelo menos 18 anos.
        Date now = new Date();
        long diffInMillies = now.getTime() - data.getTime();
        if (diffInMillies < 0) {
            return false; //data inserida é mais nova que a data atual
        }
        diffInMillies = Math.abs(diffInMillies);
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        int idade = (int) diff/365;
        if (idade >= 18) return true;
        return false;
    }

    public static boolean validaDataLinceca (Date dataAntes, Date dataDepois) {
        //retorna true se dataDepois é realmente depois de dataAntes.
        //retorna false, caso contrário
        long diffInMillies = dataDepois.getTime() - dataAntes.getTime();
        if (diffInMillies > 0) return true;
        return false;
    }

    public static int validainput (Scanner sc, int min, int max) {
        //valida o input no menu operações, garatindo que ele é um número, é inteiro, e está entre as opções do menu (entre min e máx, inclusos).
        int input;
        System.out.println("Entre com um número: ");
        while (true) {
            try {
                input = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("======================");
                System.out.println("Insira apenas números!");
                System.out.println("======================");
                System.out.println("Entre com um número: ");
            }
        }
        while ((input > max || input < min)) {
            System.out.println("==============================================");
            System.out.println("Selecione apenas entre as opções apresentadas!");
            System.out.println("==============================================");
            System.out.println("Entre com um número: ");
            while (true) {
                try {
                    input = Integer.parseInt(sc.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("======================");
                    System.out.println("Insira apenas números!");
                    System.out.println("======================");
                    System.out.println("Entre com um número: ");
                }
            }
        }
        return input;
    }

    public static Date validaFormatoDate (Scanner sc, String d, SimpleDateFormat formatter) {
        Date output;
        while (true) {
            try {
                output = formatter.parse(d);
                break;
            } catch (Exception e) {
                System.out.println("==========================================");
                System.out.println("Formato de data inválido! Tente novamente.");
                System.out.println("==========================================");
                System.out.println("\nInsira a data novamente (Formato dd-MM-yyyy): ");
                d = sc.nextLine();
            }
        }
        return output;
    }

    public static boolean isNull (Object o) {
        //Se verificar se o Objeto é null, printa que ele não existe
        if (o == null) {
            System.out.println("=============================");
            System.out.println("O item buscado não existe!");
            System.out.println("=============================");
            return true;
        } 
        return false;
    }
}