import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestorEstudiantes {

    // Calcula la nota media de un estudiante
    public static double calcularNotaMedia(Estudiante estudiante) {
        double suma = 0;
        for (int i = 0; i < estudiante.getNotas().length; i++) { // Error: índice fuera de rango
            suma += estudiante.getNotas()[i];
        }
        if(estudiante.getNotas().length == 0)
        {
            return -1;
        }else {
            return suma / estudiante.getNotas().length;
        }
        // Error si el array está vacío
    }

    // Encuentra al estudiante con la mejor nota media
    public static Estudiante encontrarMejorEstudiante(Estudiante[] estudiantes) {
        estudiantes = null;
        Estudiante mejor = null;
        double mejorNota = -1;
        try{
            for (Estudiante estudiante : estudiantes) {
                double media = calcularNotaMedia(estudiante); // Posible fallo aquí
                if (media > mejorNota) {
                    mejorNota = media;
                    mejor = estudiante;
                }
            }
            return mejor;
        }catch (NullPointerException e)
        {
            System.out.println("La lista de estudiantes esta vacia o nula.");
            return null;
        }
    }

    // Guarda los resultados en un fichero
    public static void guardarResultados(Estudiante[] estudiantes, String rutaFichero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaFichero))) {
            for (Estudiante estudiante : estudiantes) {

                if(calcularNotaMedia(estudiante) == -1)
                {
                    writer.write("Nombre: " + estudiante.getNombre() + " // SIN NOTAS" );
                }
                else {
                    writer.write("Nombre: " + estudiante.getNombre() + ", Nota Media: " +
                            calcularNotaMedia(estudiante));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero: " + e.getMessage());
        }
    }
}
