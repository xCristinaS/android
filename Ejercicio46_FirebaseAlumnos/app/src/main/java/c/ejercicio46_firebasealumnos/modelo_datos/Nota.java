package c.ejercicio46_firebasealumnos.modelo_datos;

/**
 * Created by Cristina on 22/02/2016.
 */
public class Nota {

    private int ingles, lengua, mates, sociales;

    public Nota(){

    }

    public Nota(int ingles, int lengua, int mates, int sociales){
        this.ingles = ingles;
        this.lengua = lengua;
        this.mates = mates;
        this.sociales = sociales;
    }

    public int getIngles() {
        return ingles;
    }

    public void setIngles(int ingles) {
        this.ingles = ingles;
    }

    public int getLengua() {
        return lengua;
    }

    public void setLengua(int lengua) {
        this.lengua = lengua;
    }

    public int getMates() {
        return mates;
    }

    public void setMates(int mates) {
        this.mates = mates;
    }

    public int getSociales() {
        return sociales;
    }

    public void setSociales(int sociales) {
        this.sociales = sociales;
    }
}
