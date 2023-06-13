import java.util.ArrayList;
import java.util.Scanner;

public class Buscar {
    public static Veiculo buscarVeiculo (Scanner sc, ArrayList<Veiculo> listaVeiculos) {
        //Busca um veículo na lista de veículos de um dado Cliente c, recebendo a informação da placa
        //Usada para gerar um sinistro
        String aux = "1";
        do {
            System.out.println("Insira a placa do veículo: ");
            String palca = sc.nextLine();
            for (Veiculo v : listaVeiculos) {
                if (v.getPlaca().equals(palca)) {
                    return v;
                }
            }
            System.out.println("==================================================================");
            System.out.println("O veículo buscado não existe.");
            System.out.println("Digite 1 caso queira tentar novamente.");
            System.out.println("Caso contrário, digite 2 (ou qualquer outro dígito distinto de 1).");
            System.out.println("==================================================================");
            aux = sc.nextLine();
        } while(aux.equals("1")); 
        return null; //caso em que o veículo buscado não existe
    }

    public static Cliente buscarCliente (Scanner sc, ArrayList<Cliente> listaClientes) {
        //Recebe inputs e a partir das informações retorna um cliente específico.
        String aux = "1";
        String pf_pj = "";
        String id = "";
        do {
            System.out.println("Cliente é PF ou PJ?: ");
            pf_pj = sc.nextLine().toUpperCase();
            do {
                if (pf_pj.equals("PF")) {
                    System.out.println("\nInsira o CPF do cliente: ");
                    id = sc.nextLine().replaceAll("[^0-9]", "");
                }
                else if (pf_pj.equals("PJ")) {
                    System.out.println("\nInsira o CNPJ do cliente: ");
                    id = sc.nextLine().replaceAll("[^0-9]", "");
                }
                else {
                    System.out.println("==================================================");
                    System.out.println("Responda apenas com 'PF' ou 'PJ'. Tente novamente.");
                    System.out.println("==================================================");
                    pf_pj = "0";
                }
            } while (pf_pj.equals("0"));
            for (Cliente currCliente : listaClientes) {
                if (currCliente.getId().equals(id)) {
                    return currCliente;
                }
            }
            System.out.println("==================================================================");
            System.out.println("O cliente buscado não existe.");
            System.out.println("Digite 1 caso queira tentar novamente.");
            System.out.println("Caso contrário, digite 2 (ou qualquer outro dígito distinto de 1).");
            System.out.println("==================================================================");
            aux = sc.nextLine();
        } while (aux.equals("1"));
        return null;
    }

    public static Condutor buscaCondutor (Scanner sc, ArrayList<Condutor> listaCondutores) {
        //recebe os inputs necessários para retornar um condutor específico dentro de uma lista
        String aux = "1";
        do {
            System.out.println("\nInsira o CPF do condutor: ");
            String cpf = sc.nextLine().replaceAll("[^0-9]", "");
            for (Condutor currCondutor : listaCondutores) {
                if ((currCondutor.getCPF().replaceAll("[^0-9]", "")).equals(cpf)) {
                    return currCondutor;
                }
            }
            System.out.println("==================================================================");
            System.out.println("O condutor buscado não existe.");
            System.out.println("Digite 1 caso queira tentar novamente.");
            System.out.println("Caso contrário, digite 2 (ou qualquer outro dígito distinto de 1).");
            System.out.println("==================================================================");
            aux = sc.nextLine();
        } while(aux.equals("1"));
        return null;
    }

    public static Sinistro buscarSinistro (Scanner sc, ArrayList<Sinistro> listaSinistros) {
        String aux = "1";
        do {
            System.out.println("\nInsira o ID do sinistro: ");
            int id = Integer.parseInt(sc.nextLine().replaceAll("[^0-9]", ""));
            for (Sinistro currSinistro : listaSinistros) {
                if (currSinistro.getId() == id) {
                    return currSinistro;
                }
            }
            System.out.println("==================================================================");
            System.out.println("O sinistro buscado não existe.");
            System.out.println("Digite 1 caso queira tentar novamente.");
            System.out.println("Caso contrário, digite 2 (ou qualquer outro dígito distinto de 1).");
            System.out.println("==================================================================");
            aux = sc.nextLine();
        } while (aux.equals("1"));
        return null;
    }

    public static Seguro buscarSeguro (Scanner sc, ArrayList<Seguro> listaSeguros) {
        String aux = "1";
        do {
            System.out.println("\nInsira o ID do seguro: ");
            int id = Integer.parseInt(sc.nextLine());
            for (Seguro seguro : listaSeguros) {
                if (seguro.getID() == id) {
                    return seguro;
                }
            }
            System.out.println("==================================================================");
            System.out.println("O seguro buscado não existe.");
            System.out.println("Digite 1 caso queira tentar novamente.");
            System.out.println("Caso contrário, digite 2 (ou qualquer outro dígito distinto de 1).");
            System.out.println("==================================================================");
            aux = sc.nextLine();
        } while (aux.equals("1"));
        return null;
    }

    public static Frota buscarFrota (Scanner sc, ArrayList<Frota> listaFrota) {
        String aux = "1";
        do {
            System.out.println("Insira o código da frota: ");
            String codigo = sc.nextLine();
            for (Frota f : listaFrota) {
                if (f.getCode().equals(codigo)) {
                    System.out.println("===================");
                    System.out.println("Frota encontrada!");
                    System.out.println("===================");
                    return f;
                }
            }
            System.out.println("==================================================================");
            System.out.println("A frota buscada não existe.");
            System.out.println("Digite 1 caso queira tentar novamente.");
            System.out.println("Caso contrário, digite 2 (ou qualquer outro dígito distinto de 1).");
            System.out.println("==================================================================");
            aux = sc.nextLine();
        } while (aux.equals("1"));
        return null;
    }
}