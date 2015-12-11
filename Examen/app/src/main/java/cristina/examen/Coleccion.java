package cristina.examen;

import java.util.ArrayList;

/**
 * Created by Cristina on 11/12/2015.
 */
public class Coleccion {
    private static ArrayList<Libro> bdd  = new ArrayList<>();

    static {
        String sinopsis = "GANAR SIGNIFICA FAMA Y RIQUEZA. PERDER SIGNIFICA UNA MUERTE SEGURA. En una oscura versión del futuro próximo, doce chicos y doce chicas se ven obligados a participar en un reality show llamado Los juegos del hambre. Solo hay una regla: matar o morir. Cuando Katniss Everdeen, una joven de dieciséis años se presenta voluntaria para ocupar el lugar de su hermana en los juegos, lo entiende como una condena a muerte. Sin embargo Katniss ya ha visto la muerte de cerca y la supervivencia forma parte de su naturaleza. ¡Que empiecen los septuagésimo cuartos juegos del hambre!";
        bdd.add(new Libro("Los juegos del hambre","Suzanne Collins", "2008", sinopsis, "http://www.blogjuegosdelhambre.com/wp-content/uploads/LJDH2.jpg"));
        sinopsis = "El arma más poderosa creada por el hombre, una organización secreta sedienta de venganza... y apenas unas horas para evitar el desastre. La eterna pugna entre ciencia y religión se ha convertido en una guerra muy real. En un laboratorio de máxima seguridad, aparece asesinado un científico con un extraño símbolo grabado a fuego en su pecho. Para el profesor Robert Langdon no hay duda: los Illuminati, los hombres enfrentados a la Iglesia desde los tiempos de Galileo, han regresado. Y esta vez disponen de la más mortífera arma que ha creado la humanidad, un artefacto con el que pueden ganar la batalla final contra su eterno enemigo. Acompañado de una joven científica y un audaz capitán de la Guardia Suiza, Langdon comienza una carrera contra reloj, en una búsqueda desesperada por los rincones más secretos de El Vaticano.";
        bdd.add(new Libro("Ángeles y demonios","Dan Brown", "2000", sinopsis, "http://s1.trrsf.com/blogs/331/files/image/angelesdemonios2.jpg"));
        sinopsis = "Cuando la estudiante de Literatura Anastasia Steele recibe el encargo de entrevistar al exitoso y joven empresario Christian Grey, queda impresionada al encontrarse ante un hombre atractivo, seductor y también muy intimidante.\n" +
                "\n" +
                "La inexperta e inocente Ana intenta olvidarle, pero pronto comprende cuánto le desea. Cuando la pareja por fin inicia una apasionada relación, Ana se sorprende por las peculiares prácticas eróticas de Grey, al tiempo que descubre los límites de sus propios y más oscuros deseos…\n";
        bdd.add(new Libro("50 sombras de Grey","E.L. James", "2011", sinopsis, "http://s.libertaddigital.com/fotos/noticias/cincuentasombras.jpg"));





        sinopsis = "GANAR SIGNIFICA FAMA Y RIQUEZA. PERDER SIGNIFICA UNA MUERTE SEGURA. En una oscura versión del futuro próximo, doce chicos y doce chicas se ven obligados a participar en un reality show llamado Los juegos del hambre. Solo hay una regla: matar o morir. Cuando Katniss Everdeen, una joven de dieciséis años se presenta voluntaria para ocupar el lugar de su hermana en los juegos, lo entiende como una condena a muerte. Sin embargo Katniss ya ha visto la muerte de cerca y la supervivencia forma parte de su naturaleza. ¡Que empiecen los septuagésimo cuartos juegos del hambre!";
        bdd.add(new Libro("Los juegos del hambre","Suzanne Collins", "2008", sinopsis, "http://www.blogjuegosdelhambre.com/wp-content/uploads/LJDH2.jpg"));
        sinopsis = "El arma más poderosa creada por el hombre, una organización secreta sedienta de venganza... y apenas unas horas para evitar el desastre. La eterna pugna entre ciencia y religión se ha convertido en una guerra muy real. En un laboratorio de máxima seguridad, aparece asesinado un científico con un extraño símbolo grabado a fuego en su pecho. Para el profesor Robert Langdon no hay duda: los Illuminati, los hombres enfrentados a la Iglesia desde los tiempos de Galileo, han regresado. Y esta vez disponen de la más mortífera arma que ha creado la humanidad, un artefacto con el que pueden ganar la batalla final contra su eterno enemigo. Acompañado de una joven científica y un audaz capitán de la Guardia Suiza, Langdon comienza una carrera contra reloj, en una búsqueda desesperada por los rincones más secretos de El Vaticano.";
        bdd.add(new Libro("Ángeles y demonios","Dan Brown", "2000", sinopsis, "http://s1.trrsf.com/blogs/331/files/image/angelesdemonios2.jpg"));
        sinopsis = "Cuando la estudiante de Literatura Anastasia Steele recibe el encargo de entrevistar al exitoso y joven empresario Christian Grey, queda impresionada al encontrarse ante un hombre atractivo, seductor y también muy intimidante.\n" +
                "\n" +
                "La inexperta e inocente Ana intenta olvidarle, pero pronto comprende cuánto le desea. Cuando la pareja por fin inicia una apasionada relación, Ana se sorprende por las peculiares prácticas eróticas de Grey, al tiempo que descubre los límites de sus propios y más oscuros deseos…\n";
        bdd.add(new Libro("50 sombras de Grey","E.L. James", "2011", sinopsis, "http://s.libertaddigital.com/fotos/noticias/cincuentasombras.jpg"));

        sinopsis = "GANAR SIGNIFICA FAMA Y RIQUEZA. PERDER SIGNIFICA UNA MUERTE SEGURA. En una oscura versión del futuro próximo, doce chicos y doce chicas se ven obligados a participar en un reality show llamado Los juegos del hambre. Solo hay una regla: matar o morir. Cuando Katniss Everdeen, una joven de dieciséis años se presenta voluntaria para ocupar el lugar de su hermana en los juegos, lo entiende como una condena a muerte. Sin embargo Katniss ya ha visto la muerte de cerca y la supervivencia forma parte de su naturaleza. ¡Que empiecen los septuagésimo cuartos juegos del hambre!";
        bdd.add(new Libro("Los juegos del hambre","Suzanne Collins", "2008", sinopsis, "http://www.blogjuegosdelhambre.com/wp-content/uploads/LJDH2.jpg"));
        sinopsis = "El arma más poderosa creada por el hombre, una organización secreta sedienta de venganza... y apenas unas horas para evitar el desastre. La eterna pugna entre ciencia y religión se ha convertido en una guerra muy real. En un laboratorio de máxima seguridad, aparece asesinado un científico con un extraño símbolo grabado a fuego en su pecho. Para el profesor Robert Langdon no hay duda: los Illuminati, los hombres enfrentados a la Iglesia desde los tiempos de Galileo, han regresado. Y esta vez disponen de la más mortífera arma que ha creado la humanidad, un artefacto con el que pueden ganar la batalla final contra su eterno enemigo. Acompañado de una joven científica y un audaz capitán de la Guardia Suiza, Langdon comienza una carrera contra reloj, en una búsqueda desesperada por los rincones más secretos de El Vaticano.";
        bdd.add(new Libro("Ángeles y demonios","Dan Brown", "2000", sinopsis, "http://s1.trrsf.com/blogs/331/files/image/angelesdemonios2.jpg"));
        sinopsis = "Cuando la estudiante de Literatura Anastasia Steele recibe el encargo de entrevistar al exitoso y joven empresario Christian Grey, queda impresionada al encontrarse ante un hombre atractivo, seductor y también muy intimidante.\n" +
                "\n" +
                "La inexperta e inocente Ana intenta olvidarle, pero pronto comprende cuánto le desea. Cuando la pareja por fin inicia una apasionada relación, Ana se sorprende por las peculiares prácticas eróticas de Grey, al tiempo que descubre los límites de sus propios y más oscuros deseos…\n";
        bdd.add(new Libro("50 sombras de Grey","E.L. James", "2011", sinopsis, "http://s.libertaddigital.com/fotos/noticias/cincuentasombras.jpg"));

        sinopsis = "GANAR SIGNIFICA FAMA Y RIQUEZA. PERDER SIGNIFICA UNA MUERTE SEGURA. En una oscura versión del futuro próximo, doce chicos y doce chicas se ven obligados a participar en un reality show llamado Los juegos del hambre. Solo hay una regla: matar o morir. Cuando Katniss Everdeen, una joven de dieciséis años se presenta voluntaria para ocupar el lugar de su hermana en los juegos, lo entiende como una condena a muerte. Sin embargo Katniss ya ha visto la muerte de cerca y la supervivencia forma parte de su naturaleza. ¡Que empiecen los septuagésimo cuartos juegos del hambre!";
        bdd.add(new Libro("Los juegos del hambre","Suzanne Collins", "2008", sinopsis, "http://www.blogjuegosdelhambre.com/wp-content/uploads/LJDH2.jpg"));
        sinopsis = "El arma más poderosa creada por el hombre, una organización secreta sedienta de venganza... y apenas unas horas para evitar el desastre. La eterna pugna entre ciencia y religión se ha convertido en una guerra muy real. En un laboratorio de máxima seguridad, aparece asesinado un científico con un extraño símbolo grabado a fuego en su pecho. Para el profesor Robert Langdon no hay duda: los Illuminati, los hombres enfrentados a la Iglesia desde los tiempos de Galileo, han regresado. Y esta vez disponen de la más mortífera arma que ha creado la humanidad, un artefacto con el que pueden ganar la batalla final contra su eterno enemigo. Acompañado de una joven científica y un audaz capitán de la Guardia Suiza, Langdon comienza una carrera contra reloj, en una búsqueda desesperada por los rincones más secretos de El Vaticano.";
        bdd.add(new Libro("Ángeles y demonios","Dan Brown", "2000", sinopsis, "http://s1.trrsf.com/blogs/331/files/image/angelesdemonios2.jpg"));
        sinopsis = "Cuando la estudiante de Literatura Anastasia Steele recibe el encargo de entrevistar al exitoso y joven empresario Christian Grey, queda impresionada al encontrarse ante un hombre atractivo, seductor y también muy intimidante.\n" +
                "\n" +
                "La inexperta e inocente Ana intenta olvidarle, pero pronto comprende cuánto le desea. Cuando la pareja por fin inicia una apasionada relación, Ana se sorprende por las peculiares prácticas eróticas de Grey, al tiempo que descubre los límites de sus propios y más oscuros deseos…\n";
        bdd.add(new Libro("50 sombras de Grey","E.L. James", "2011", sinopsis, "http://s.libertaddigital.com/fotos/noticias/cincuentasombras.jpg"));
    }

    public static void agregarLibro(Libro libro){
        bdd.add(libro);
    }

    public static void eliminarLibro(Libro libro){
        bdd.remove(libro);
    }

    public static Libro getLibroAtIndex(int position){
        return bdd.get(position);
    }

    public static ArrayList<Libro> getLibros(){
        return bdd;
    }
}
