package validador.correlativas;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import validador.correlativas.entity.Alumno;
import validador.correlativas.entity.Inscripcion;
import validador.correlativas.entity.Materia;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) {

        List<Alumno> alumnos = getAlumnos();
        List<Materia> materias = getMaterias();

        List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();

        String pathCSVFile = "./inscripciones.csv";
        try {
                Reader reader = Files.newBufferedReader(Paths.get(pathCSVFile));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord csvRecord : csvParser) {
                // Accediendo a los valores por el indice de la columna
                String nombreAlumno = csvRecord.get(0);
                String nombreMateria = csvRecord.get(1);

                // Busco el alumno en el array de alumnos
                Alumno alumnoEncontrado = alumnos.stream().filter(alu -> alu.getNombre().equalsIgnoreCase(nombreAlumno)).findAny()
                        .orElse(null);

                // Busco la materia en el array de materias
                Materia materiaEncontrada = materias.stream().filter(mat -> mat.getNombre().equalsIgnoreCase(nombreMateria)).findAny()
                        .orElse(null);

                Inscripcion ins = new Inscripcion(alumnoEncontrado, materiaEncontrada);
                inscripciones.add(ins);
            }

            inscripciones.forEach(inscripcion -> {
                System.out.println("Inscripción");
                System.out.println("Alumno : " + inscripcion.getAlumno().getNombre());
                System.out.println("Materia : " + inscripcion.getMateria().getNombre());
                System.out.println("Fecha : " + inscripcion.getFecha());
                System.out.println("---------------\n");
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Alumno> getAlumnos() {
        // Crear Alumnos
        Alumno alumnoJose = new Alumno("José Rodríguez", "7283788");
        Alumno alumnaVanesa = new Alumno("Vanesa Sosa", "7283788");
        Alumno alumnaLucia = new Alumno("Lucia Pérez", "7283788");

        // Listado de alumnos
        List<Alumno> alumnos = new ArrayList<Alumno>();
        alumnos.add(alumnoJose);
        alumnos.add(alumnaVanesa);
        alumnos.add(alumnaLucia);

        return  alumnos;
    }

    public static List<Materia> getMaterias() {
        // Crear Materias
        Materia materiaProgramacionI = new Materia("Programación I");
        Materia materiaProgramacionII = new Materia("Programación II");
        Materia materiaBaseDatosI = new Materia("Bases de datos I");

        // Agregar correlativa a materia
        materiaProgramacionII.agregarCorrelativa(materiaProgramacionI);

        // Listado de materias
        List<Materia> materias = new ArrayList<Materia>();
        materias.add(materiaProgramacionI);
        materias.add(materiaProgramacionII);
        materias.add(materiaBaseDatosI);

        return  materias;
    }
}
