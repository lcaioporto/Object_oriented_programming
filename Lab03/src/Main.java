import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Seguradora seguradoraX = new Seguradora("Nome Seguradora", "3344559977", "seguradora@gmail.com", "Endereço seguradora");
        seguradoraX.cadastrarCliente(sc); //recebe input de informações para gerar o cliente - para o exemplo, cria-se um PF
        seguradoraX.cadastrarCliente(sc); //para o exemplo, cria-se um PJ

        ClientePF c1 = seguradoraX.listarClientesPF().get(0);
        System.out.println(c1.validarCPF());

        ClientePJ c2 = seguradoraX.listarClientesPJ().get(0); 
        System.out.println(c2.validarCNPJ());

        seguradoraX.removerCliente(sc); //o método recebe todos os inputs necessários para remover um determinado cliente.
        /*
        //Cliente cliente = new Cliente("Caio", "Casa");
        //System.out.println(cliente.toString()); //imprime informações do cliente

        Seguradora seguradora = new Seguradora("nomeSeguradora", "(19)99345-1097", "seguradora@gmail.com", "Unicamp");
        System.out.println(seguradora.getNome()); //nomeSeguradora

        Sinistro sinistro = new Sinistro("18/09/2007", "Unicamp");
        System.out.println(sinistro.getId()); //random Id

        Veiculo carro = new Veiculo("GFQ-7456", "Fiat", "SUV", 2002);
        System.out.println(carro.getMarca()); //Fiat
        System.out.println(carro);

        @Deprecated
        Date date = new Date();
        ClientePJ c = new ClientePJ("Caio", "Unicamp", "11.222.333/0001-81", date);
        System.out.println(c.validarCNPJ()); //true
        */
    }
}