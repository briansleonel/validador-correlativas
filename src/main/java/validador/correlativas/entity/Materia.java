package validador.correlativas.entity;

import java.util.ArrayList;
import java.util.List;

public class Materia {
    private String nombre;
    private List<Materia> correlativas;

    public Materia(String nombre) {
        this.nombre = nombre;
        this.correlativas = new ArrayList<Materia>();
    }

    public boolean puedeCursar(Alumno alumno) {
        /**
         * El alumno puede cursar si cumple con todas las correlativas necesarias
         */
        return this.correlativas.stream().allMatch(materiaCorrelativa -> alumno.tenesCorrelativa(materiaCorrelativa));
    }

    public void agregarCorrelativa(Materia correlativa) {
        correlativas.add(correlativa);
    }

    public boolean tenesCorrelativas() {
        return !this.correlativas.isEmpty();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Materia> getCorrelativas() {
        return correlativas;
    }

    public void setCorrelativas(List<Materia> correlativas) {
        this.correlativas = correlativas;
    }


}
