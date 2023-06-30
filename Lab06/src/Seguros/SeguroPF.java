package Seguros;
import java.util.Date;

import Administrador.Seguradora;
import Clientes.Cliente;
import Clientes.ClientePF;
import Enum.CalcValor;
import Registro.Sinistro;
import Veiculos.Veiculo;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    //Construtor
    public SeguroPF (Date dataInicio, Date dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
        super.setValorMensal(calcularValor());
    }
    //Getters
    public Veiculo getVeiculo() {
        return veiculo;
    }

    @Override
    public ClientePF getCliente() {
        return cliente;
    }

    //Setters
    @Override
    public void setCliente(Cliente cliente) {
        if (cliente instanceof ClientePF) {
            this.cliente = (ClientePF) cliente;
        } else {
            System.out.println("O cliente deve ser uma pessoa física (PF)");
        }
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    //Funções
    @Override
    public double calcularValor() {
        int idade = super.calcIdade(cliente.getDataNascimento());
        double fator_idade;
        //FATOR IDADE
        if (idade >= 18 && idade < 30) fator_idade = CalcValor.FATOR_18_30.getValue();
        else if (idade < 60) fator_idade = CalcValor.FATOR_30_60.getValue();
        else fator_idade = CalcValor.FATOR_60_90.getValue();
        //QUANTIDADE DE VEÍCULOS
        int quantidadeVeiculos = cliente.getListaVeiculos().size();
        //QUANTIDADE SINISTROS CLIENTE
        int quantidadeSinistrosCliente = 0;
        for(Sinistro s : super.getListaSinistros()) {
            if (s.getCondutor() == null) { 
                quantidadeSinistrosCliente++;
            }
        }
        //QUANTIDADE SINISTROS CONDUTOR
        int quantidadeSinistrosCondutor = 0; 
        for (Condutor c : super.getListaCondutores()) {
            for (Sinistro sinistro : c.getListaSinistros()) {
                if (sinistro.getSeguro().equals(this)) {
                    quantidadeSinistrosCondutor++;
                }
            }
        }
        //Razões
        double r1 = (double) 1/(quantidadeVeiculos + 2);
        double r2 = (double) quantidadeSinistrosCliente/10;
        double r3 = (double) quantidadeSinistrosCondutor/10;
        //CALCULAR O VALOR
        double valor = CalcValor.VALOR_BASE.getValue() * fator_idade * (1 + r1) * (2 + r2) + (5 + r3);
        super.setValorMensal(valor);
        return valor;
    }

    @Override
    public String toString() {
        return super.toString()+ "\nValor mensal: " + this.getValorMensal() + "\nPlaca do veículo: " + veiculo.getPlaca() + "\nMarca do veículo: " + veiculo.getMarca() + "\nNome do cliente: " + cliente.getNome() + "\nCPF do cliente: " + cliente.getCPF() + "\n===========================\n";
    }
}
