package lab02;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;

    public Veiculo(String placa, String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

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
}