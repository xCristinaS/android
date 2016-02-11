package c.ejercicio39_firebase;

/**
 * Created by Cristina on 10/02/2016.
 */
public class Mensaje {

    private String clave;
    private String mensaje;

    public Mensaje(){

    }

    public Mensaje(String clave, String mensaje){
        this.clave = clave;
        this.mensaje = mensaje;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
