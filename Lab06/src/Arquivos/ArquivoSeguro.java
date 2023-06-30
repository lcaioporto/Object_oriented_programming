package Arquivos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Administrador.Seguradora;
import Registro.Sinistro;
import Seguros.Condutor;
import Seguros.Seguro;

public class ArquivoSeguro implements I_Arquivo{

    public ArquivoSeguro() {
        //Inicializar o arquivo
        String seguroPath = "Lab06\\src\\Arquivos\\outputFiles\\Seguros.csv";
        File file = new File(seguroPath);
        String header = "ID,DATA_INICIO,DATA_FIM,SEGURADORA,LISTA_SINISTROS,LISTA_CONDUTORES,VALOR_MENSAL\n";
        if (!file.exists()) {
            //CRIAR O ARQUIVO
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("======================================");
                System.out.println("Ocorreu um erro inesperado!\nNão foi possivel o arquivo do seguro!");
                System.out.println("======================================");
            }
            FileWriter fw = null;
            try {
                fw = new FileWriter(file, true);
            } catch (IOException e) {
                System.out.println("======================================");
                System.out.println("Ocorreu um erro inesperado!\nNão foi possivel o arquivo do seguro!");
                System.out.println("======================================");
            }
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(header);
                bw.flush();
                bw.close();
                System.out.println("====================================================");
                System.out.println("O arquivo do seguro foi criado com sucesso!");
                System.out.println("====================================================");
            } catch (IOException e) {
                System.out.println("======================================");
                System.out.println("Ocorreu um erro inesperado!\nNão foi possivel criar o arquivo do seguro!");
                System.out.println("======================================");
            }
        }
    }

    @Override
    public boolean gravarArquivo(Seguradora seguradora) {
        String seguroPath = "Lab06\\src\\Arquivos\\outputFiles\\Seguros.csv";
        File file = new File(seguroPath);
        for (Seguro seguro : seguradora.getListaSeguros()) {
            //Lista de sinistros
            String listaSinistros = "'";
            for (Sinistro sinistro : (seguro).getListaSinistros()) {
                listaSinistros+= sinistro.getId() + ";";
            }
            listaSinistros+="'";
            //Lista condutores
            String listaCondutores = "'";
            for (Condutor condutor : (seguro).getListaCondutores()) {
                listaCondutores+= condutor.getCPF() + ";";
            }
            listaCondutores+="'";
            //Conteúdo
            String conteudo = ((Seguro) seguro).getID() + "," + ((Seguro) seguro).getDataInicio() + "," + 
                            ((Seguro) seguro).getDataFim() + "," + ((Seguro) seguro).getSeguradora().getNome() + ","
                            + listaSinistros + "," + listaCondutores + "," + ((Seguro) seguro).getValorMensal() + "\n";
            try {
                //arquivo já existe
                FileWriter fw = new FileWriter(file, true);
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(conteudo);
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    System.out.println("======================================");
                    System.out.println("Ocorreu um erro inesperado!\nNão foi possivel salvar os dados do seguro!");
                    System.out.println("======================================");
                    return false;
                }
            } catch (IOException e) {
                System.out.println("======================================");
                System.out.println("Ocorreu um erro inesperado!\nNão foi possivel salvar os dados do seguro!");
                System.out.println("======================================");
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<Object> lerArquivo(ArrayList<Object> l) {
        throw new UnsupportedOperationException("Unimplemented method 'lerArquivo'");
    }
}
