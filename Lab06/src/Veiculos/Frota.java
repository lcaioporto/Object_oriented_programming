package Veiculos;
import java.util.ArrayList;
import java.util.Scanner;

import Utilidades.Buscar;

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculos;
    private static ArrayList<String> listaCode;

    public Frota (String code) {
        listaCode = new ArrayList<String>();
        this.code = code;
        listaCode.add(code);
        listaVeiculos = new ArrayList<Veiculo>();
    }

    //Getters
    public String getCode() {
        return code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public static ArrayList<String> getListaCode() {
        return listaCode;
    }

    //Setters
    public void setCode(String code) {
        this.code = code;
    }

    //Funções

    public boolean addVeiculo (Scanner sc) {
        //Recebe todas as informações necessárias para criar um objeto Veículo.
        //O objeto criado é associado ao cliente, adicionando-o na lista de veiculos.
        //Caso o método funcione adequadamente, retrona true; c.c retorna false.
        try {
            int anoFabricacao = 0;
            //placa
            System.out.println("\nInsira a placa do veículo: ");
            String placa = sc.nextLine();
            //marca
            System.out.println("\nInsira a marca do veículo: ");
            String marca = sc.nextLine();
            //modelo
            System.out.println("\nInsira o modelo do veículo: ");
            String modelo = sc.nextLine();
            //ano de fabricação
            System.out.println("\nInsira o ano de fabricação do veículo: ");
            while (true) {
                try {
                    anoFabricacao = Integer.parseInt(sc.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("==================================================");
                    System.out.println("O ano de fabricação do veículo deve ser um número!");
                    System.out.println("Tente novamente.");
                    System.out.println("==================================================");
                }
            }
            //criar objeto veículo
            Veiculo v = new Veiculo(placa, marca, modelo, anoFabricacao);
            listaVeiculos.add(v);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean removerVeiculo (Scanner sc) {
        //Remove um veículo a partir de sua placa
        Veiculo v = Buscar.buscarVeiculo(sc, listaVeiculos);
        if (v != null) {
            listaVeiculos.remove(v);
            return true;
        }
        return false; //caso em que o veículo buscado não existe
    }

    @Override
    public String toString() {
        return "========== FROTA ==========" + "\nCode: " + this.code + "\nLista de veículos: " + this.listaVeiculos + "\n===========================\n";
    }
}
