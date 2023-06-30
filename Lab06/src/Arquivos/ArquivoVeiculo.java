package Arquivos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Administrador.Seguradora;
import Veiculos.Veiculo;

public class ArquivoVeiculo implements I_Arquivo {

    @Override
    public boolean gravarArquivo(Seguradora seguradora) {
        throw new UnsupportedOperationException("Unimplemented method 'gravarArquivo'");
    }

    @Override
    public ArrayList<Object> lerArquivo(ArrayList<Object> l) {
        String veiculoPath = "Lab06\\src\\Arquivos\\inputFiles\\veiculos.csv";
        ArrayList<Object> listaVeiculos = new ArrayList<Object>();
        BufferedReader br = null;
        File fileVeiculo = new File(veiculoPath);
        String line = "";
        int ano;
        try {
            br = new BufferedReader(new FileReader(fileVeiculo));
            line = br.readLine(); //pular o header
            while ((line = br.readLine()) != null) {
                //iterar o arquivo linha a linha
                String[] data = line.split(","); //vetor da linha
                String placa = data[0];
                
                String marca = data[1];
                String modelo = data[2];
                String anoFab = data[3];
                //Verificar se o ano é de fato um inteiro
                try {
                    ano = Integer.parseInt(anoFab);
                } catch (Exception e) {
                    System.out.println("=================================================");
                    System.out.println("ERRO!\nHá um ano no formato inválido: " + anoFab);
                    System.out.println("=================================================");
                    br.close();
                    return null;
                }
                //Instanciar o objeto veiculo
                Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
                listaVeiculos.add(veiculo);
                System.out.println(veiculo);
            }
            br.close();
            return listaVeiculos;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("=================================================");
            System.out.println("Ocorreu um erro inesperado ao ler o arquivo.\nTente novamente mais tarde.");
            System.out.println("==================================================");
            return null;
        }
    }
}
