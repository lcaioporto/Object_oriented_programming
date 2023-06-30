package Arquivos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Administrador.Seguradora;
import Registro.Sinistro;
import Seguros.Seguro;

public class ArquivoSinistro implements I_Arquivo {

    public ArquivoSinistro() {
        //Inicializar o arquivo
        String seguroPath = "Lab06\\src\\Arquivos\\outputFiles\\Sinistros.csv";
        File file = new File(seguroPath);
        String header = "ID,DATA,ENDERECO,CONDUTOR,SEGURO\n";
        if (!file.exists()) {
            //CRIAR O ARQUIVO
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("======================================");
                System.out.println("Ocorreu um erro inesperado!\nNão foi possivel o arquivo do sinistro!");
                System.out.println("======================================");
            }
            FileWriter fw = null;
            try {
                fw = new FileWriter(file, true);
            } catch (IOException e) {
                System.out.println("======================================");
                System.out.println("Ocorreu um erro inesperado!\nNão foi possivel o arquivo do sinistro!");
                System.out.println("======================================");
            }
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(header);
                bw.flush();
                bw.close();
                System.out.println("====================================================");
                System.out.println("O arquivo do sinistro foi criado com sucesso!");
                System.out.println("====================================================");
            } catch (IOException e) {
                System.out.println("======================================");
                System.out.println("Ocorreu um erro inesperado!\nNão foi possivel criar o arquivo do sinistro!");
                System.out.println("======================================");
            }
        }
    }

    @Override
    public boolean gravarArquivo(Seguradora seguradora) {
        String sinistroPath = "Lab06\\src\\Arquivos\\outputFiles\\Sinistros.csv";
        File file = new File(sinistroPath);
        String conteudo = "";
        for (Seguro seguro : seguradora.getListaSeguros()) {
            for (Sinistro sinistro : seguro.getListaSinistros()) {
                //Conteúdo
                if ((sinistro).getCondutor() != null) {
                    conteudo = (sinistro).getId() + "," + (sinistro).getData() + "," + 
                                (sinistro).getEndereco() + "," + (sinistro).getCondutor().getCPF() + ","
                                + (sinistro).getSeguro().getID() + "\n";
                } else { //caso no qual o cliente responável pelo seguro é o indivíduo associado ao sinistro
                    conteudo = (sinistro).getId() + "," + (sinistro).getData() + "," + 
                                (sinistro).getEndereco() + "," + (sinistro).getSeguro().getCliente().getId() + ","
                                + (sinistro).getSeguro().getID() + "\n";
                }
                try {
                    //arquivo já existe
                    FileWriter fw = new FileWriter(file, true);
                    try (BufferedWriter bw = new BufferedWriter(fw)) {
                        bw.write(conteudo);
                        bw.flush();
                        bw.close();
                    } catch (IOException e) {
                        System.out.println("======================================");
                        System.out.println("Ocorreu um erro inesperado!\nNão foi possivel salvar os dados do sinistro!");
                        System.out.println("======================================");
                        return false;
                    }
                } catch (IOException e) {
                    System.out.println("======================================");
                    System.out.println("Ocorreu um erro inesperado!\nNão foi possivel salvar os dados do sinistro!");
                    System.out.println("======================================");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ArrayList<Object> lerArquivo(ArrayList<Object> l) {
        throw new UnsupportedOperationException("Unimplemented method 'lerArquivo'");
    }
}
