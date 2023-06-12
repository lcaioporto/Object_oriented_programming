import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Condutor {
    private final String CPF;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Date dataNascimento;
    private ArrayList<Sinistro> listaSinistros; //contém apenas sinsitros relacionados a este condutor

    public Condutor(String CPF, String nome, String telefone, String endereco, String email, Date dataNascimento) {
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        listaSinistros = new ArrayList<Sinistro>();
    }

    //Getters

    public String getCPF() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    //Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    //Funções

    public boolean adicionarSinistro(Scanner sc) {
        try {
            Sinistro s = Buscar.buscarSinistro(sc, listaSinistros);
            if (s == null) return false;
            listaSinistros.add(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removerSinistro(Scanner sc) {
        try {
            Sinistro s = Buscar.buscarSinistro(sc, listaSinistros);
            if (s == null) return false;
            listaSinistros.remove(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "======== CONDUTOR ========" + "\nCPF: " + this.CPF + "\nNome: " + this.nome + "\nTelefone: " + this.telefone + "\nEndereço: " + this.endereco + "\nE-mail: " + this.email + "\nData de nascimento: " + this.dataNascimento + "\n==========================\n";
    }
}