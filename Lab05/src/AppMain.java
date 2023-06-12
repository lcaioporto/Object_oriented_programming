import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
public class AppMain {
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
            dataNascimento2 = formatter.parse("20-02-1997)");
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
        //clientePJ.getListaFrota().add(frota);
        //Seguradora
        Seguradora seguradora = new Seguradora("Nome da seguradora", "(19)927191298", "seguradora@gmail.com", "Rua da seguradora", "59.488.165/0001-05");
        seguradora.getListaClientes().add(clientePJ);
        seguradora.getListaClientes().add(clientePF);
        //Seguro
        SeguroPF seguroPF = new SeguroPF(dataNascimento1, dataFundacao1, seguradora, veiculo1, clientePF);
        SeguroPJ seguroPJ = new SeguroPJ(dataNascimento2, dataFundacao2, seguradora, frota, clientePJ);
        seguradora.getListaSeguros().add(seguroPF);
        seguradora.getListaSeguros().add(seguroPJ);
        seguroPF.getListaCondutores().add(condutor1);
        seguroPF.getListaCondutores().add(condutor2);
        seguroPJ.getListaCondutores().add(condutor2);
        //Testes toString() - parte 1
        System.out.println(clientePF);
        System.out.println(clientePJ);
        System.out.println(condutor1);
        System.out.println(frota);
        System.out.println(seguroPF);
        //Utilizando gerarSeguro() da seguradora (recebe inputs do usuário)
        Scanner sc = new Scanner(System.in);
        System.out.println("===== CADASTRO DE UM SEGURO =====\n");
        seguradora.gerarSeguro(sc);
        //Sinistro
        Sinistro sinistro = new Sinistro(dataFundacao1, "Rua do acidente", seguroPF, condutor1);
        //Gerando sinistro utilizando gerarSinistro() do seguro (recebe inputs do usuário)
        System.out.println("\n===== GERAR UM SINISTRO =====\n");
        if (seguroPF.gerarSinistro(sc, seguradora)) {
            System.out.println("Sinistro gerado com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao gerar o sinistro");
        }
        //Principais MÉTODOS
        //Calcular a receita da seguradora
        System.out.println("Receita da seguradora: " + String.format("%.2f", seguradora.calcularReceita()).replace(",", "."));
        //Calcular o valor dos seguros
        System.out.println("Valor do seguro PF: " + String.format("%.2f", seguroPF.calcularValor()).replace(",", "."));
        System.out.println("Valor do seguro PJ: " + String.format("%.2f", seguroPJ.calcularValor()).replace(",", "."));
        //Adicionar um veiculo a uma frota do ClientePJ
        System.out.println("\n===== ATUALIZAR UMA FROTA =====\n");
        clientePJ.atualizarFrota(sc, ClientePJ.ADICIONAR_VEICULOS_FROTA);
        //Listar todos os veículos de uma frota
        clientePJ.getVeiculosPorFrota(sc);
        //Pode-se cancelar um seguro utilizando o método abaixo
        System.out.println("\n===== CANCELAR UM SEGURO =====\n");
        seguradora.cancelarSeguro(sc);
        //Pode-se desautorizar um condutor usando o método abaixo
        System.out.println("\n===== DESAUTORIZAR UM CONDUTOR =====\n");
        seguroPF.desautorizarCondutor(sc);
        //Teste toString() - parte 2.
        System.out.println(clientePF);
        System.out.println(clientePJ);
        System.out.println(condutor1);
        System.out.println(veiculo1);
        System.out.println(frota);
        System.out.println(seguroPF);
        System.out.println(seguroPJ);
        System.out.println(sinistro);
        sc.close();
        //Menu da Seguradora
        System.out.println("========================================================");
        MenuSeguradora.MenuInterativo();
    }
}