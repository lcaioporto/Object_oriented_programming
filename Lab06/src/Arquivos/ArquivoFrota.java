package Arquivos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Administrador.Seguradora;
import Veiculos.Frota;
import Veiculos.Veiculo;

public class ArquivoFrota implements I_Arquivo{

    @Override
    public boolean gravarArquivo(Seguradora seguradora) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gravarArquivo'");
    }

    @Override
    public ArrayList<Object> lerArquivo(ArrayList<Object> listaVeiculos) {
        String frotaPath = "Lab06\\src\\Arquivos\\inputFiles\\frotas.csv";
        ArrayList<Object> listaFrotas = new ArrayList<Object>();
        BufferedReader br = null;
        File fileFrota = new File(frotaPath);
        String line = "";
        try {
            br = new BufferedReader(new FileReader(fileFrota));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                //iterar o arquivo linha a linha
                String[] data = line.split(","); //vetor da linha
                String id = data[0];
                
                String placa1 = data[1];
                String placa2 = data[2];
                String placa3 = data[3];
                //Instanciar o objeto frota
                Frota frota = new Frota(id);
                //Adicionar veículos
                for (Object veiculo : listaVeiculos) {
                    String currPlaca = ((Veiculo) veiculo).getPlaca();
                    if (currPlaca.equals(placa1) || currPlaca.equals(placa2) || currPlaca.equals(placa3)) {
                        frota.getListaVeiculos().add((Veiculo) veiculo);
                    }
                }
                listaFrotas.add(frota);
                System.out.println(frota);
            }
            br.close();
            return listaFrotas;
        } catch (IOException e) {
            System.out.println("=================================================");
            System.out.println("Ocorreu um erro inesperado ao ler o arquivo.\nTente novamente mais tarde.");
            System.out.println("==================================================");
            return null;
        }
    }
}
