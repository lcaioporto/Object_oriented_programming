package Arquivos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Clientes.ClientePF;
import Utilidades.Validacao;
import Veiculos.Veiculo;
import Administrador.Seguradora;

public class ArquivoClientePF implements I_Arquivo {

    @Override
    public boolean gravarArquivo (Seguradora seguradora) {
        throw new UnsupportedOperationException("Unimplemented method 'gravarArquivo'");
    }

    @Override
    public ArrayList<Object> lerArquivo(ArrayList<Object> listaVeiculos) {
        ArrayList<Object> listaClientesPF = new ArrayList<Object>(); //lista com clientesPF
        BufferedReader br = null;
        String clientePFPath = "Lab06\\src\\Arquivos\\inputFiles\\clientesPF.csv";
        File fileClientePF = new File(clientePFPath);
        String line = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento;
        try {
            br = new BufferedReader(new FileReader(fileClientePF));
            line = br.readLine(); //pular o header
            while ((line = br.readLine()) != null) {
                //iterar o arquivo linha a linha
                String[] data = line.split(","); //vetor da linha
                String CPF = data[0];
                //Verificar a validade do CPF
                if (!Validacao.validarCPF(CPF)) {
                    System.out.println("=================================================");
                    System.out.println("Há um CPF inválido: " + CPF);
                    System.out.println("=================================================");
                }
                else {
                    String nome = data[1];
                    String telefone = data[2];
                    String endereco = data[3];
                    String email = data[4];
                    String sexo = data[5];
                    String ensino = data[6];
                    String strDataNascimento = data[7];
                    //Verificar se o formato de data está adequado
                    try {
                        dataNascimento = formatter.parse(strDataNascimento);
                    } catch (Exception e) {
                        System.out.println("=================================================");
                        System.out.println("ERRO!\nHá uma data no formato inválido: " + strDataNascimento);
                        System.out.println("=================================================");
                        br.close();
                        return null;
                    }
                    String placaVeiculo = data[8];
                    //Instanciar o objeto clientePF
                    ClientePF clientePF = new ClientePF(nome, endereco, telefone, email, ensino, sexo, CPF, dataNascimento);
                    //Adicionar o respectivo veículo à lista de veículos do cliente
                    for (Object veiculo : listaVeiculos) {
                        if (((Veiculo) veiculo).getPlaca().equals(placaVeiculo)) {
                            clientePF.getListaVeiculos().add((Veiculo) veiculo);
                        }
                    }
                    //Adicionar na lista de clientesPF
                    listaClientesPF.add(clientePF);
                    System.out.println(clientePF);
                }
            }
            br.close();
            return listaClientesPF;
        } catch (IOException e) {
            System.out.println("=================================================");
            System.out.println("Ocorreu um erro inesperado ao ler o arquivo.\nTente novamente mais tarde.");
            System.out.println("==================================================");
            return null;
        }
    }
}
