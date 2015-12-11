package cristina.examen;

/**
 * Created by Cristina on 11/12/2015.
 */
public class Libro {

    private String titulo, autor, anioPublicacion, sinopsis, urlPortada;

    public Libro (String titulo, String autor, String anioPublicacion, String sinopsis, String urlPortada){
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.sinopsis = sinopsis;
        this.urlPortada = urlPortada;
    }

    public String getAutor() {
        return autor;
    }

    public String getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }
}
