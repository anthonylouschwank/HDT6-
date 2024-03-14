import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Esta clase contiene el método principal que maneja la interacción con el usuario para leer cartas,
 * agregarlas al maso de un jugador, buscar tipos de carta por nombre, mostrar las cartas del jugador,
 * y mostrar todas las cartas en diferentes ordenamientos.
 */
public class main {

    /**
     * Método principal que maneja la interacción con el usuario.
     * @param args Argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        boolean terminado = false;
        int ans;
        int ans1;
        ListaFactory factory = null;
        Map<String, String> mapa = null;
        List<String> MasoJugador = new ArrayList<>();
        String respuesta;
        Scanner sca = new Scanner(System.in);
        
        while (!terminado) {
            System.out.println("Bienvenido al sistema lector de cartas y variables, elige una opcion de todas");
            System.out.println("1. Leer en HashMap");
            System.out.println("2. Leer en TreeMap");
            System.out.println("3. Leer en linkedList");
            ans = sca.nextInt();
            
            if (ans == 1) {
                factory = new HashMapFactory();
            } else if (ans == 2) {
                factory = new TreeMapFactory();
            } else if (ans == 3) {
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
            
            if (ans1 == 1) {
                agregarCartaAlMaso(sca, mapa, MasoJugador);
            } else if (ans1 == 2) {
                buscarTipoCartaPorNombre(sca, mapa);
            } else if (ans1 == 3) {
                mostrarCartasDelJugador(MasoJugador, mapa);
            } else if (ans1 == 4) {
                mostrarCartasDelJugadorEnOrden(MasoJugador, mapa);
            } else if (ans1 == 5) {
                mostrarTodasLasCartas(mapa);
            } else if (ans1 == 6) {
                mostrarTodasLasCartasEnOrden(mapa);
            }
        }
    }

    /**
     * Agrega una carta al maso del jugador.
     * @param sca Objeto Scanner para entrada de usuario.
     * @param mapa Mapa de cartas.
     * @param MasoJugador Lista de cartas del jugador.
     */
    private static void agregarCartaAlMaso(Scanner sca, Map<String, String> mapa, List<String> MasoJugador) {
        boolean encontrado = true;
        System.out.println("Ingrese el nombre de la carta: ");
        sca.nextLine();
        String respuesta = sca.nextLine();
        
        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(respuesta)) {
                MasoJugador.add(respuesta);
            } else {
                encontrado = false;
            }
        }
        
        if (!encontrado) {
            System.out.println("La carta ingresada " + respuesta + " no está en la lista de cartas");
        }
    }

    /**
     * Busca el tipo de carta por nombre.
     * @param sca Objeto Scanner para entrada de usuario.
     * @param mapa Mapa de cartas.
     */
    private static void buscarTipoCartaPorNombre(Scanner sca, Map<String, String> mapa) {
        boolean encontrado = true;
        System.out.println("Ingrese el nombre de la carta: ");
        sca.nextLine();
        String respuesta = sca.nextLine();
        
        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(respuesta)) {
                System.out.println("El tipo de la carta " + respuesta + " es " + entry.getValue());
                return;
            } else {
                encontrado = false;
            }
        }
        
        if (!encontrado) {
            System.out.println("El tipo de la carta " + respuesta + " no se ha encontrado");
        }
    }

    /**
     * Muestra las cartas del jugador.
     * @param MasoJugador Lista de cartas del jugador.
     * @param mapa Mapa de cartas.
     */
    private static void mostrarCartasDelJugador(List<String> MasoJugador, Map<String, String> mapa) {
        Map<String, Integer> conteoCartas = new HashMap<>();
        
        // Contar cuántas veces se repite cada carta en el mazo del jugador
        for (String carta : MasoJugador) {
            conteoCartas.put(carta, conteoCartas.getOrDefault(carta, 0) + 1);
        }

        // Imprimir el conteo de cada carta junto con su tipo
        System.out.println("Conteo de cartas en el mazo del jugador:");
        for (Map.Entry<String, Integer> entry : conteoCartas.entrySet()) {
            String nombreCarta = entry.getKey();
            int cantidad = entry.getValue();
            String tipo = mapa.getOrDefault(nombreCarta, "Desconocido");
            System.out.println("Nombre: " + nombreCarta + ", Tipo: " + tipo + ", Cantidad: " + cantidad);
        }
    }

    /**
     * Muestra las cartas del jugador en orden.
     * @param MasoJugador Lista de cartas del jugador.
     * @param mapa Mapa de cartas.
     */
    private static void mostrarCartasDelJugadorEnOrden(List<String> MasoJugador, Map<String, String> mapa) {
        // Crear un mapa para agrupar las cartas por tipo
        Map<String, List<String>> cartasPorTipo = new HashMap<>();

        // Agrupar las cartas por tipo
        for (String carta : MasoJugador) {
            String tipo = mapa.getOrDefault(carta, "Desconocido");
            cartasPorTipo.putIfAbsent(tipo, new ArrayList<>());
            cartasPorTipo.get(tipo).add(carta);
        }

        // Imprimir el conteo de cada carta por tipo
        for (Map.Entry<String, List<String>> entry : cartasPorTipo.entrySet()) {
            String tipo = entry.getKey();
            List<String> cartasTipo = entry.getValue();
            System.out.println("Tipo: " + tipo);
            for (String carta : cartasTipo) {
                int cantidad = contarCarta(MasoJugador, carta);
                System.out.println("Nombre: " + carta + ", Cantidad: " + cantidad);
            }
        }
    }

    /**
     * Muestra todas las cartas.
     * @param mapa Mapa de cartas.
     */
    private static void mostrarTodasLasCartas(Map<String, String> mapa) {
        System.out.println("Contenido del mapa:");
        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            System.out.println("Tipo: " + entry.getKey() + ", Nombre: " + entry.getValue());
        }
    }

    /**
     * Muestra todas las cartas en orden.
     * @param mapa Mapa de cartas.
     */
    private static void mostrarTodasLasCartasEnOrden(Map<String, String> mapa) {
        System.out.println("MONSTRUO:");
        imprimirCartasPorTipo(mapa, "Monstruo");

        System.out.println("TRAMPA:");
        imprimirCartasPorTipo(mapa, "Trampa");

        System.out.println("HECHIZO:");
        imprimirCartasPorTipo(mapa, "Hechizo");
    }

    /**
     * Imprime las cartas por tipo.
     * @param mapa Mapa de cartas.
     * @param tipo Tipo de carta.
     */
    private static void imprimirCartasPorTipo(Map<String, String> mapa, String tipo) {
        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(tipo)) {
                System.out.println("Carta: " + entry.getKey() + " // Tipo: " + entry.getValue());
            }
        }
    }

    /**
     * Cuenta la cantidad de una carta en el maso del jugador.
     * @param MasoJugador Lista de cartas del jugador.
     * @param carta Carta a contar.
     * @return La cantidad de la carta en el maso del jugador.
     */
    private static int contarCarta(List<String> MasoJugador, String carta) {
        int contador = 0;
        for (String c : MasoJugador) {
            if (c.equals(carta)) {
                contador++;
            }
        }
        return contador;
    }
}
