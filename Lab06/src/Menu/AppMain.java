package Menu;
//import java.util.Date;
//import java.util.Scanner;
//import java.text.SimpleDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Administrador.Seguradora;
import Clientes.ClientePF;
import Clientes.ClientePJ;
import Registro.Sinistro;
import Seguros.Condutor;
import Seguros.SeguroPF;
import Seguros.SeguroPJ;
import Veiculos.Frota;
import Veiculos.Veiculo;

public class AppMain {

    /*
        -Primeiramente trata-se uma série de testes implementando alguns métodos solicitados e instanciando objetos.

        -Ocorre a leitura dos dados de input (inputFiles) e instanciação dos objetos.
        -Todos os objetos instanciados pela leitura de arquivos são printados (toString()), menos aqueles que
        possuem dados errados (CPF ou CNPJ inválidos).

        -A parte de registro de dados é feita ao final do programa, ou seja, assim que o programa é fechado,
        as informações dos sinistros e dos seguros são registradas em dois arquivos (outputFiles).
    */

    public static void main(String[] args) {
        //Datas
        System.out.println("======== TESTE DE OBJETOS E MÉTODOS ========\n");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dataNascimento1 = new Date();
        Date dataNascimento2 = new Date();
        Date dataFundacao1 = new Date();
        Date dataFundacao2 = new Date();
        try {
            dataNascimento1 = formatter.parse("28-10-1998");
            dataNascimento2 = formatter.parse("20-02-1997");
            dataFundacao1 = formatter.parse("20-03-2010");
            dataFundacao2 = formatter.parse("10-08-2007");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Clientes
        ClientePF clientePF = new ClientePF("Caio", "Rua do Caio", "(19)998234567", "caio@gmail.com", "Ensino superior incompleto", "Masculino", "111.444.777-35", dataNascimento1);
        ClientePJ clientePJ = new ClientePJ("Lucas", "Rua do Lucas", "(19)99156789", "lucas@gmail.com", "11.222.333/0001-81", dataFundacao1);
        //Condutor
        Condutor condutor1 = new Condutor("529.982.247-25", "Pedro", "(12)12192381204", "Rua do Pedro", "pedro@gmail.com", dataNascimento2);
        Condutor condutor2 = new Condutor("299.578.200-01", "José", "(18)10291230", "Rua do José", "josé@gmail.com", dataNascimento1);
        //Veículo
        Veiculo veiculo1 = new Veiculo("placa1", "Ford", "SUV", 2015);
        Veiculo veiculo2 = new Veiculo("placa2", "Porshe", "Esportivo", 2022);
        clientePF.getListaVeiculos().add(veiculo2);
        clientePF.getListaVeiculos().add(veiculo1);
        //Frota
        Frota frota = new Frota("Nome da frota");
        frota.getListaVeiculos().add(veiculo1);
        frota.getListaVeiculos().add(veiculo2);
        clientePJ.getListaFrota().add(frota);
        //Seguradora
        Seguradora seguradora = new Seguradora("Nome da seguradora", "(19)927191298", "seguradora@gmail.com", "Rua da seguradora", "59.488.165/0001-05");
        seguradora.getListaClientes().add(clientePJ);
        seguradora.getListaClientes().add(clientePF);
        //LER DADOS
        seguradora.lerDados();
        //Seguro
        SeguroPF seguroPF = new SeguroPF(dataNascimento1, dataFundacao1, seguradora, veiculo1, clientePF);
        SeguroPJ seguroPJ = new SeguroPJ(dataNascimento2, dataFundacao2, seguradora, frota, clientePJ);
        seguradora.getListaSeguros().add(seguroPF);
        seguradora.getListaSeguros().add(seguroPJ);
        seguroPF.getListaCondutores().add(condutor1);
        seguroPF.getListaCondutores().add(condutor2);
        seguroPJ.getListaCondutores().add(condutor2);
        //Utilizando gerarSeguro() da seguradora (recebe inputs do usuário)
        Scanner sc = new Scanner(System.in);
        System.out.println("===== CADASTRO DE UM SEGURO =====\n");
        if (seguradora.gerarSeguro(sc)) {
            System.out.println("===============================");
            System.out.println("Segur ogerado com sucesso!");
            System.out.println("===============================\n");
        } else {
            System.out.println("===================================");
            System.out.println("Ocorreu um erro ao gerar o seguro");
            System.out.println("===================================\n");
        }
        //Sinistro
        Sinistro sinistro = new Sinistro(dataFundacao1, "Rua do acidente", seguroPF, condutor1);
        //Gerando sinistro utilizando gerarSinistro() do seguro (recebe inputs do usuário)
        System.out.println("\n===== GERAR UM SINISTRO =====\n");
        if (seguroPF.gerarSinistro(sc, seguradora)) {
            System.out.println("===============================");
            System.out.println("Sinistro gerado com sucesso!");
            System.out.println("===============================\n");
        } else {
            System.out.println("===================================");
            System.out.println("Ocorreu um erro ao gerar o sinistro");
            System.out.println("===================================\n");
        }

        //Teste toString() do seguroPF, seguroPJ e sinistro (objetos que não foram printados anteriormente)
        System.out.println(seguroPF);
        System.out.println(seguroPJ);
        System.out.println(sinistro);
        //Indo para o menu de operações
        System.out.println("========================================================");
        System.out.println("                  MENU INTERATIVO                       ");
        System.out.println("========================================================");
        
        MenuSeguradora.MenuInterativo(sc, seguradora);
        sc.close();
    }
}