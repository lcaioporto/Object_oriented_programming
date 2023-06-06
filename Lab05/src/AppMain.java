import java.util.Date;
import java.text.SimpleDateFormat;
public class AppMain {
    public static void main(String[] args) {
        //Datas
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
        Condutor condutor = new Condutor("529.982.247-25", "Pedro", "(12)12192381204", "Rua do Pedro", "pedro@gmail.com", dataNascimento2);
        //Veículo
        Veiculo veiculo = new Veiculo("QNV-8709", "Ford", "SUV", 2015);
        //Frota
        Frota frota = new Frota("Nome da frota");
        frota.getListaVeiculos().add(veiculo);
        //Seguradora
        Seguradora seguradora = new Seguradora("Nome da seguradora", "(19)927191298", "seguradora@gmail.com", "Rua da seguradora", "59.488.165/0001-05");
        //Seguro
        SeguroPF seguroPF = new SeguroPF(dataNascimento1, dataFundacao1, seguradora, veiculo, clientePF);
        SeguroPJ seguroPJ = new SeguroPJ(dataNascimento2, dataFundacao2, seguradora, frota, clientePJ);
        //Sinistro
        Sinistro sinistro = new Sinistro(dataFundacao1, "Rua do acidente", seguroPF, condutor);
        //Teste toString()
        System.out.println(seguroPJ);
        System.out.println(sinistro);
        //Menu da Seguradora
        System.out.println("====================================================");
        MenuSeguradora.MenuInterativo();
    }
}