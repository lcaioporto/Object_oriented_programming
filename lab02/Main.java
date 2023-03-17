package lab02;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Caio", "111.444.777-35", "30/11/2004", 19, "Casa");
        System.out.println(cliente.validarCPF()); //true
        System.out.println(cliente.toString());

        Seguradora seguradora = new Seguradora("nomeSeguradora", "(19)99345-1097", "seguradora@gmail.com", "Unicamp");
        System.out.println(seguradora.getNome()); //nomeSeguradora

        Sinistro sinistro = new Sinistro("18/09/2007", "Unicamp");
        System.out.println(sinistro.getId()); //random Id

        Veiculo carro = new Veiculo("GFQ-7456", "Fiat", "SUV");
        System.out.println(carro.getMarca()); //Fiat
    }
}