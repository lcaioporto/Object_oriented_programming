public abstract class Cliente {
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    //private ArrayList<Veiculo> listaVeiculos; //contém todos os veículos de um certo cliente
    //private double valorSeguro;

    //Construtor
    public Cliente (String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

    //Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome (String newName) {
        nome = newName;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco (String newEndereco) {
        endereco = newEndereco;
    }

    public double calculaScore() {
        return 0;
    }

    public String getId () {
        //retorna seu CPF ou seu CNPJ, dependendo do seu tipo.
        if (this instanceof ClientePF) {
            return ((ClientePF) this).getCPF().replaceAll("[^0-9]", "");
        }
        return ((ClientePJ) this).getCNPJ().replaceAll("[^0-9]", "");
    }

    @Override
    public String toString () {
        return "========= CLIENTE =========" + "\nNome: " + this.nome + "\nEndereco: " + this.endereco + "\nTelefone: " + this.telefone + "\nEmail: " + this.email + "\n=========================";
    }
}