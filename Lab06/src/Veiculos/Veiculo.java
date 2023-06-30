package Veiculos;
public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    //Construtor
    public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
    }

    //Getters and setters
    public String getPlaca () {
        return placa;
    }

    public void setPlaca (String newPlaca) {
        placa = newPlaca;
    }

    public String getMarca () {
        return marca;
    }

    public void setMarca (String newMarca) {
        marca = newMarca;
    }

    public String getModelo () {
        return modelo;
    }

    public void setModelo (String newModelo) {
        modelo = newModelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int newAnoFabricacao) {
        anoFabricacao = newAnoFabricacao;
    }

    @Override
    public String toString () {
        String s = "\n===== VEÍCULO =====" + "\nPlaca: " + placa + "\nMarca: " + marca + "\nModelo: " + modelo + "\nAno de Fabricação: " + anoFabricacao + "\n===================\n";
        return s;
    }
}