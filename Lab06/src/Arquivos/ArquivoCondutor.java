package Arquivos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Administrador.Seguradora;
import Seguros.Condutor;
import Utilidades.Validacao;

public class ArquivoCondutor implements I_Arquivo{

    @Override
    public boolean gravarArquivo(Seguradora seguradora) {
        throw new UnsupportedOperationException("Unimplemented method 'gravarArquivo'");
    }

    @Override
    public ArrayList<Object> lerArquivo(ArrayList<Object> l) {
        String condutorPath = "Lab06/src/Arquivos/inputFiles/condutores.csv";
        ArrayList<Object> listaCondutores = new ArrayList<Object>();
        BufferedReader br = null;
        File fileCondutor = new File(condutorPath);
        String line = "";
        Date dataNascimento;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            br = new BufferedReader(new FileReader(fileCondutor));
            line = br.readLine(); //ler o header
            while ((line = br.readLine()) != null) {
                //iterar o arquivo linha a linha
                String[] data = line.split(","); //vetor da linha
                String CPF = data[0];
                if (!Validacao.validarCPF(CPF)) {
                    System.out.println("=================================================");
                    System.out.println("ERRO!\nHá um CPF inválido: " + CPF);
                    System.out.println("=================================================");
                }
                else {
                    String nome = data[1];
                    String telefone = data[2];
                    String endereco = data[3];
                    String email = data[4];
                    String strDataNascimento = data[5];
                    //Verificar se a data é válida
                    try {
                        dataNascimento = formatter.parse(strDataNascimento);
                    } catch (Exception e) {
                        System.out.println("=================================================");
                        System.out.println("ERRO!\nHá uma data no formato inválido: " + strDataNascimento);
                        System.out.println("=================================================");
                        br.close();
                        return null;
                    }
                    //Instanciar o objeto condutor
                    Condutor condutor = new Condutor(CPF, nome, telefone, endereco, email, dataNascimento);
                    listaCondutores.add(condutor);
                    System.out.println(condutor);
                }
            }
            br.close();
            return listaCondutores;
        } catch (IOException e) {
            System.out.println("=================================================");
            System.out.println("Ocorreu um erro inesperado ao ler o arquivo.\nTente novamente mais tarde.");
            System.out.println("==================================================");
            return null;
        }
    }
}
