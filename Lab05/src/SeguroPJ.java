import java.util.Date;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.cliente = cliente;
        this.frota = frota;
    }

    //Getters
    public Frota getFrota() {
        return frota;
    }

    @Override
    public ClientePJ getCliente() {
        return cliente;
    }

    //Setters
    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    @Override
    public void setCliente (Cliente cliente) {
        if (cliente instanceof ClientePJ) {
            this.cliente = (ClientePJ) cliente;
        } else {
            System.out.println("O cliente deve ser uma pessoa jurídica (PJ)");
        }
    }

    @Override
    public double calcularValor () {
        //QUANTIDADE DE VEÍCULOS
        int quantidadeVeiculos = 0;
        for (Frota f : cliente.getListaFrota()) {
            quantidadeVeiculos += f.getListaVeiculos().size();
        }
        //QUANTIDADE DE FUNCIONÁRIOS
        int quantidadeFuncionarios = super.getListaCondutores().size();
        //QUANTIDADE SINISTROS CLIENTE
        int quantidadeSinistrosCliente = 0;
        for(Sinistro s : super.getListaSinistros()) {
            if (s.getCondutor() == null) {
                quantidadeSinistrosCliente++;
            }
        }
        //ANOS PÓS CRIAÇÃO
        int anosPosFundacao = super.calcIdade(cliente.getDataFundacao());
        //QUANTIDADE SINISTROS CONDUTOR
        int quantidadeSinistrosCondutor = 0; 
        for (Condutor c : super.getListaCondutores()) {
            quantidadeSinistrosCondutor += c.getListaSinistros().size();
        }
        //Razões
        double r1 = (double) quantidadeFuncionarios/10;
        double r2 = (double) 1/(quantidadeVeiculos+2);
        double r3 = (double) 1/(anosPosFundacao+2);
        double r4 = (double) quantidadeSinistrosCliente/10;
        double r5 = (double) quantidadeSinistrosCondutor/10;
        //CALCULAR O VALOR
        double valor = CalcValor.VALOR_BASE.getValue() * (10 + r1) * (1 + r2) * (1 + r3) * (2 + r4) * (5 + r5);
        super.setValorMensal(valor);
        return valor;
    }

    @Override
    public String toString() {
        return "======== SEGURO PJ ========" + "\nData de início: " + super.getDataInicio() + "\nData do fim: " + super.getDataFim() + "\nSeguradora (nome): " + super.getSeguradora().getNome() + "\nFrota: " + frota.getCode() + "\nNome do cliente: " + cliente.getNome() + "\nCNPJ do cliente: " + cliente.getCNPJ() + "===========================";
    }
}
