package Arquivos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Administrador.Seguradora;
import Clientes.ClientePJ;
import Utilidades.Validacao;
import Veiculos.Frota;

public class ArquivoClientePJ implements I_Arquivo {

    @Override
    public boolean gravarArquivo(Seguradora seguradora) {
        throw new UnsupportedOperationException("Unimplemented method 'gravarArquivo'");
    }

    @Override
    public ArrayList<Object> lerArquivo(ArrayList<Object> listaFrotas) {
        String clientePJPath = "Lab06\\src\\Arquivos\\inputFiles\\clientesPJ.csv";
        ArrayList<Object> listaClientePJ = new ArrayList<Object>();
        BufferedReader br = null;
        File fileFrota = new File(clientePJPath);
        String line = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dataFundacao;
        try {
            br = new BufferedReader(new FileReader(fileFrota));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                //iterar o arquivo linha a linha
                String[] data = line.split(","); //vetor da linha
                String CNPJ = data[0];
                if (!Validacao.validarCNPJ(CNPJ)) {
                    System.out.println("=================================================");
                    System.out.println("ERRO!\nHá um CNPJ inválido: " + CNPJ);
                    System.out.println("=================================================");
                    br.close();
                    return null;
                }
                else {
                    String nome = data[1];
                    String telefone = data[2];
                    String endereco = data[3];
                    String email = data[4];
                    String strDataFund = data[5];
                    try {
                        dataFundacao = formatter.parse(strDataFund);
                    } catch (Exception e) {
                        System.out.println("=================================================");
                        System.out.println("ERRO!\nHá uma data no formato inválido: " + strDataFund);
                        System.out.println("=================================================");
                        br.close();
                        return null;
                    }
                    String codeFrota = data[6];
                    //Instanciar o objeto ClientePJ
                    ClientePJ clientePJ = new ClientePJ(nome, endereco, telefone, email, CNPJ, dataFundacao);
                    //Adicionar veículos
                    for (Object frota : listaFrotas) {
                        String currCode = ((Frota) frota).getCode();
                        if (currCode.equals(codeFrota)) {
                            clientePJ.getListaFrota().add((Frota) frota);
                        }
                    }
                    listaClientePJ.add(clientePJ);
                    System.out.println(clientePJ);
                }
            }
            br.close();
            return listaClientePJ;
        } catch (IOException e) {
            System.out.println("=================================================");
            System.out.println("Ocorreu um erro inesperado ao ler o arquivo.\nTente novamente mais tarde.");
            System.out.println("==================================================");
            return null;
        }
    }
}