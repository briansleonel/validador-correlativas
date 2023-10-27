package validador.correlativas.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import validador.correlativas.entity.Alumno;
import validador.correlativas.entity.Inscripcion;
import validador.correlativas.entity.Materia;

public class InscripcionTest {

    @Test
    public void materiaSinCorrelativas() {
        Alumno alumno = new Alumno("Marcos", "7283788");
        Materia materiaProgramacionI = new Materia("Programación I");
        Inscripcion inscripcion = new Inscripcion(alumno, materiaProgramacionI);

        Assertions.assertTrue(inscripcion.aprobada());
    }

    @Test
    public void materiaConCorrelativasNoAprobadas() {
        Alumno alumno = new Alumno("Marcos", "7283788");
        Materia materiaProgramacionI = new Materia("Programación I");
        Materia materiaProgramacionII = new Materia("Programación II");

        materiaProgramacionII.agregarCorrelativa(materiaProgramacionI);

        Inscripcion inscripcion = new Inscripcion(alumno, materiaProgramacionII);

        Assertions.assertFalse(inscripcion.aprobada());
    }

    @Test
    public void materiaConCorrelativasAprobadas() {
        Alumno alumno = new Alumno("Marcos", "7283788");
        Materia materiaProgramacionI = new Materia("Programación I");
        Materia materiaProgramacionII = new Materia("Programación II");

        materiaProgramacionII.agregarCorrelativa(materiaProgramacionI);
        alumno.agregarMateriaAprobada(materiaProgramacionI);

        Inscripcion inscripcion = new Inscripcion(alumno, materiaProgramacionII);

        Assertions.assertTrue(inscripcion.aprobada());
    }
}
