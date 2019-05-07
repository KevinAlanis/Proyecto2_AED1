package Json;

import java.io.Serializable;

public class Carro implements Serializable {
    public String marca;
    public int modelo;
    public int placa;

    public Carro(String marca, int modelo, int placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "{" + "marca='" + marca + '\''
                + ", modelo='" + modelo + '\''
                + ", placa=" + placa + '}';
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }
}
