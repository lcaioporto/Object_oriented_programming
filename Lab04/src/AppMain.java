import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class AppMain {
    public static void main(String[] args) {
        //Inicia-se realizando as instanciações solicitadas e finaliza-se com a chamada do Menu
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //2 objetos da classe Veiculo
        Veiculo v1 = new Veiculo("QNV-4563", "Ford", "Ford Ka", 2003);
        Veiculo v2 = new Veiculo("WQX-2367", "Porshe", "SUV", 2015);
        //Datas arbitrárias para exemplificação
        Date data1 = new Date();
        Date data2 = new Date();
        try {
            data1 = formatter.parse("23-11-1997");
            data2 = formatter.parse("20-02-2015");
        } catch (ParseException e) {
            System.out.println("Data inválida");
        }
        //1 objeto da classe ClientePF, 1 objeto da classe ClientePJ
        ClientePF cPF = new ClientePF("Lucas", "Rua X", "Ensino Superior Incompleto", "Masculino", "Classe média", "111.444.777-35", data1 , data2);
        ClientePJ cPJ = new ClientePJ("Empresa Y", "Rua Y", "11.222.333/0001-81", data1, 50);
        //1 objeto da classe Seguradora;
        Seguradora seguradora = new Seguradora("Seguradora CAIO", "(19)33674712", "seguradora.caio@gmail.com", "Rua da seguradora");
        //Adicionar pelo menos 1 Veiculo em cada Cliente instanciado;
        cPF.getListaVeiculos().add(v1);
        cPJ.getListaVeiculos().add(v2);
        //Cadastrar pelo menos 1 ClientePF e 1 ClientePJ na Seguradora;
        seguradora.getListaClientes().add(cPF);
        seguradora.getListaClientes().add(cPJ);
        //Gerar pelo menos 2 objetos Sinistro; (e adicioná-los na lista de sinistros da seguradora)
        Sinistro s1 = new Sinistro(data1, "Rua do acidente 1", seguradora, v1, cPF);
        Sinistro s2 = new Sinistro(data2, "Rua do acidente 2", seguradora, v2, cPJ);
        seguradora.getListaSinistro().add(s1);
        seguradora.getListaSinistro().add(s2);
        //Chamar os métodos da classe Seguradora: listarClientes(); visualizarSinistro(); listarSinistros(); e calcularReceita()
        seguradora.listarClientes();
        seguradora.visualizarSinistro(s1.getId()); //acessa os dados do sinistro s1
        seguradora.listarSinistros();
        seguradora.calcularReceita();
        //Atualizar o atributo valorSeguro de cada cliente cadastrado na seguradora utilizando o método calcularPrecoSeguroCliente() da classe Seguradora;
        seguradora.calcularPrecoSeguroCliente(cPJ);
        seguradora.calcularPrecoSeguroCliente(cPF);
        //Valores do seguro
        System.out.println("Seguro do(a) " + cPF.getNome() + ": " + cPF.getValorSeguro());
        System.out.println("Seguro do(a) " + cPJ.getNome() + ": " + cPJ.getValorSeguro());
        //Mostrar na tela a receita total da seguradora utilizando o método calcularReceita();
        System.out.println("Valor da receita da seguradora: " + seguradora.calcularReceita());
        //Menu da Seguradora
        System.out.println("====================================================");
        MenuSeguradora.MenuInterativo();
    }
}