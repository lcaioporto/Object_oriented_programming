package Clientes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Utilidades.Buscar;
import Veiculos.Frota;
import Veiculos.Veiculo;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;
    private ArrayList<Frota> listaFrota;
    //valores para utilização do método atualizarFrota.
    public static int ADICIONAR_VEICULOS_FROTA = 1;
    public static int REMOVER_VEICULOS_FROTA = 2;
    public static int REMOVER_FROTA = 3;

    public ClientePJ(String nome, String endereco, String telefone, String email, String CNPJ, Date dataFundacao) {
        super(nome, endereco, telefone, email);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        listaFrota = new ArrayList<Frota>();
    }

    //Getters
    public Date getDataFundacao() {
        return dataFundacao;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public ArrayList<Frota> getListaFrota() {
        return listaFrota;
    }

    //Setters
    public void setDataFundacao(Date newDataFundacao) {
        dataFundacao = newDataFundacao;
    }

    //Métodos
    private void adicionarVeiculosFrota (Scanner sc, Frota frota) {
        String input = "";
        System.out.println("Cadastre o novo veículo inserindo as informações abaixo.");
        do {
            boolean check = frota.addVeiculo(sc);
            if (check) {
                System.out.println("===================");
                System.out.println("Veículo cadastrado!");
                System.out.println("===================");
            }
            else {
                System.out.println("=======================================");
                System.out.println("Ocorreu um erro ao cadastrar o veículo.");
                System.out.println("=======================================");
            }
            System.out.println("=========================================================");
            System.out.println("Se deseja prosseguir inserindo um novo veículo, digite 1.");
            System.out.println("Caso contrário, digite 2.");
            System.out.println("=========================================================");
            input = sc.nextLine();
        } while (input.equals("1"));
    }

    private void removerVeiculosFrota (Scanner sc, Frota frota) {
        String input = "";
        System.out.println("Entre com os veículos da Frota.");
        do {
            boolean check = frota.removerVeiculo(sc);
            if (check) {
                System.out.println("===================");
                System.out.println("Veículo removido!");
                System.out.println("===================");
            }
            else {
                System.out.println("=======================================");
                System.out.println("Ocorreu um erro ao remover o veículo.");
                System.out.println("=======================================");
            }
            System.out.println("Se deseja prosseguir removendo um outro veículo, digite 1.");
            System.out.println("Caso contrário, digite 2.");
            input = sc.nextLine();
        } while (!input.equals("2"));
    }

    public boolean cadastrarFrota (Scanner sc) {
        try {
            System.out.println("Insira o code da frota.");
            String nome = sc.nextLine();
            while (Frota.getListaCode().contains(nome)) {
                System.out.println("O code informado já existe");
                System.out.println("Tente novamente...");
                nome = sc.nextLine();
            }
            Frota frota = new Frota(nome);
            this.adicionarVeiculosFrota(sc, frota);
            listaFrota.add(frota);
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean atualizarFrota(Scanner sc, int tipo) {
        System.out.println("Com qual frota deseja-se lidar?");
        Frota f = Buscar.buscarFrota(sc, listaFrota);
        if (f != null) {
            if (tipo == 1) { //adicionar veículos na frota
                adicionarVeiculosFrota(sc, f);
            } else if (tipo == 2) { //remover veículos da frota
                removerVeiculosFrota(sc, f);
            } else if (tipo == 3) { //remover a frota toda
                f.getListaVeiculos().clear();
                listaFrota.remove(f);
            }
            return true;
        }
        return false;
    }

    public boolean getVeiculosPorFrota (Scanner sc) {
        try {
            Frota frota = Buscar.buscarFrota(sc, listaFrota);
            System.out.println("===== LISTA DE VEÍCULOS DA FROTA " + frota.getCode() + " =====");
            for (Veiculo veiculo : frota.getListaVeiculos()) {
                System.out.println(veiculo);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString () {
        return super.toString() + "\nCNPJ: " + this.CNPJ + "\nData de Fundação: " + this.dataFundacao + "\n=========================\n";
    }
}