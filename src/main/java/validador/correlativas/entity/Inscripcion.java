package validador.correlativas.entity;

import validador.correlativas.entity.Alumno;
import validador.correlativas.entity.Materia;

import java.time.LocalDate;

public class Inscripcion {
    private Alumno alumno;
    private Materia materia;
    private LocalDate fecha;

    public Inscripcion(Alumno alumno, Materia materia) {
        this.alumno = alumno;
        this.materia = materia;
        this.fecha = LocalDate.now();
    }

    public boolean aprobada() {
        /**
         *  ¿Como sabemos si la inscripción esta aceptada?
         *  - Si la materia no tiene correlativas
         *  - Si la materia tiene correlativas y el alumno cumple
         */
        return !this.materia.tenesCorrelativas() || this.materia.puedeCursar(this.alumno);
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "alumno=" + alumno.getNombre() +
                ", materia=" + materia.getNombre() +
                ", fecha=" + fecha +
                '}';
    }
}
