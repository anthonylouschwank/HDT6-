import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class main {
    public static void main(String[] args) {
        boolean terminado = false;
        int ans;
        int ans1;
        ListaFactory factory = null;
        Map<String, String> mapa = null;
        while(!terminado){
            System.out.println("Bienvenido al sistema lector de cartas y variables, elige una opcion de todas");
            System.out.println("1. Leer en HashMap");
            System.out.println("2. Leer en TreeMap");
            System.out.println("3. Leer en linkedList");
            Scanner sca = new Scanner(System.in);
            ans = sca.nextInt();
            if(ans == 1){
                factory = new HashMapFactory();
            } else if(ans == 2){
                factory = new TreeMapFactory();
            } else if(ans == 3){
                factory = new LinkedHashMapFactory();
            }
            mapa = CartaReader.leerCartas(factory);
            
            terminado = true;
            System.out.println("Entrando al sistema de modificacion de masos del jugador");
            System.out.println("1. Agregar cartas al maso");
            System.out.println("2. Buscar tipo de carta por Nombre");
            System.out.println("3. Mostrar cartas del Jugador");
            System.out.println("4. Mostrar cartas del Jugador en Orden");
            System.out.println("5. Mostrar todas las cartas");
            System.out.println("6. Mostrar todas las cartas en Orden");
            ans1 = sca.nextInt();
            if(ans1 == 1){

            } else if(ans1 == 2){

            } else if(ans1 == 3){

            } else if(ans1 == 4){

            } else if(ans1 == 5){
                System.out.println("Contenido del mapa:");
                for (Map.Entry<String, String> entry : mapa.entrySet()) {
                    System.out.println("Tipo: " + entry.getKey() + ", Nombre: " + entry.getValue());
                }
            } else if(ans1 == 6){
                System.out.println("MONSTRUO:");
                imprimirCartasPorTipo(mapa, "Monstruo");

                System.out.println("TRAMPA:");
                imprimirCartasPorTipo(mapa, "Trampa");

                System.out.println("HECHIZO:");
                imprimirCartasPorTipo(mapa, "Hechizo");

            }
        }

    } 
    private static void imprimirCartasPorTipo(Map<String, String> mapa, String tipo) {
        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(tipo)) {
                System.out.println("Carta: " + entry.getKey() + " // Tipo: " + entry.getValue());
            }
        }
    }
}
