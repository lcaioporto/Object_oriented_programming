import java.util.*;
public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Cliente> listaClientes;
    public static ArrayList<Sinistro> listaSinistro;

    //Construtor da Seguradora
    public Seguradora (String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        listaClientes = new ArrayList<Cliente>();
        listaSinistro = new ArrayList<Sinistro>();
    }

    //Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome (String newName) {
        nome = newName;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String newTelefone) {
        telefone = newTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String newEmail) {
        email = newEmail;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String newEndereco) {
        endereco = newEndereco;
    }

    public boolean cadastrarCliente(Cliente c) {
        try {
            listaClientes.add(c);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean removerCliente(String clienteNome) {
        try {
            int encontrou = 0;
            for (int i = 0; i < listaClientes.size(); i++) {
                if (((listaClientes.get(i)).getNome()).equals(clienteNome)) {
                    listaClientes.remove(i);
                    encontrou = 1;
                    break;
                }
            }
            if (encontrou == 0) { System.out.println("Cliente não encontrado."); }
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean gerarSinistro(Sinistro s) {
        try {
            listaSinistro.add(s);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public ArrayList<Cliente> listarClientes() {
        return listaClientes;
    }

    public ArrayList<Sinistro> listarSinistro() {
        return listaSinistro;
    }

    public boolean visualizarSinistro(Cliente c) {
        try {
            for (int i = 0; i < listaSinistro.size(); i++) {
                if ((((listaSinistro.get(i)).getCliente()).getNome()).equals(c.getNome())) {
                    listaSinistro.get(i).toString();
                    break;
                }
            }
            return true;
        }
        catch (Exception e) { return false; }
    }

    public boolean removerSinistro(int id) {
        try {
            int encontrou = 0;
            for (int i = 0; i < listaSinistro.size(); i++) {
                if (((listaSinistro.get(i)).getId()) == id) {
                    listaSinistro.remove(i);
                    encontrou = 1;
                    break;
                }
            }
            if (encontrou == 0) { System.out.println("Sinistro não encontrado."); }
            return true;
        }
        catch (Exception e) { return false; }
    }
}