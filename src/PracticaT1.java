import java.util.ArrayList;
import java.util.Scanner;

public class PracticaT1 {
    //Variables
    static int numeroAmigos;
    static Scanner lectorConsola = new Scanner(System.in);
    static ArrayList<String> listadoAmigos = new ArrayList<>();

    //Método ejecución Main
    public static void main(String[] args){

        //Nº amigos
        do{
            System.out.println("¿Cuántos amigos entran en el juego?");
            String entrada = lectorConsola.nextLine();

            //Controlar si no se introduce un número
            try {
                numeroAmigos = Integer.parseInt(entrada);
            } catch (NumberFormatException _) {
                System.out.println("Error: introduce un número válido.");
                numeroAmigos = 0;
                continue;
            }

            //Controlar si se introduce como nº de amigos 1
            if(numeroAmigos <= 1) {
                System.out.println("El número de amigos debe ser mayor que 1");
            }
        } while(numeroAmigos <= 1);

        //Nombres de amigos
        boolean correcto;
        do{
            //Limpiamos la lista para cuando reiniciemos
            listadoAmigos.clear();
            correcto = introducirNombreAmigos(numeroAmigos);

            if(!correcto){
                System.out.println("\nReiniciando la apliación del Amigo Invisible:\n");
            }
        }while(!correcto);

        //Asignación de listados
        ArrayList<String> listadoAmigoInvisible= calcularAsignadosAmigoInvisible(listadoAmigos);

        // Mostrar resultados del Amigo Invisible
         System.out.println("\nLas parejas obtenidas son:\n");
            for (int i = 0; i < listadoAmigos.size(); i++) {
                System.out.println(listadoAmigos.get(i) + " -> " + listadoAmigoInvisible.get(i));
            }

        System.out.println("\nSorteo realizado con éxito\n");
        return;
    }

    //Método introducir nombres
    public static boolean introducirNombreAmigos(int amigos){

        for(int i = 1; i <= amigos; i++){
        System.out.println("Introduce el nombre del amigo " + i);
        //Para que funcione con nombres compuestos o nombre con apellidos
        String nombreAmigo = lectorConsola.nextLine();

        //Nombre eliminando los espacios de inicio y final. Mayúsculas para poder comparar
        String nombreAmigoMayusculas = nombreAmigo.trim().toUpperCase();

        //Comprobación nombres repetidos.
            if(listadoAmigos.contains(nombreAmigoMayusculas)){
                System.out.println("Por favor, para que pueda funcionar adecuadamente la aplicación inserta nombres distintos");
                return false;
            }
            listadoAmigos.add(nombreAmigoMayusculas);
        }

        return true;
    }

    //Método asignar amigos aleatoreamente
    public static ArrayList<String> calcularAsignadosAmigoInvisible(ArrayList<String> listaAmigosOriginal){

        ArrayList<String> listaAmigosAsignados = new ArrayList<String>();

        // Evitar que alguien se asigne a sí mismo
        boolean hayRepetidos;
        do {
            hayRepetidos = false;
            listaAmigosAsignados.clear(); // Reiniciamos antes de cada intento

            // Lista de los amigosOriginal
            ArrayList<String> amigosDisponibles = new ArrayList<>(listaAmigosOriginal);

            // Asignación aleatoria
            for (int i = 0; i < listaAmigosOriginal.size(); i++) {
                String amigoOriginal = listaAmigosOriginal.get(i);
                String asignado;
                int indiceAleatorio;

                do {
                    indiceAleatorio = (int) (Math.random() * amigosDisponibles.size());
                    asignado = amigosDisponibles.get(indiceAleatorio);
                }
                while (asignado.equals(amigoOriginal) && amigosDisponibles.size() > 1);

                listaAmigosAsignados.add(asignado);
                amigosDisponibles.remove(indiceAleatorio);
            }

            // Comprobamos que nadie se haya asignado a sí mismo
            for (int i = 0; i < listaAmigosOriginal.size(); i++) {
                if (listaAmigosOriginal.get(i).equals(listaAmigosAsignados.get(i))) {
                    hayRepetidos = true;
                    break;
                }
            }
        } while (hayRepetidos);

        return listaAmigosAsignados;
    }
}
