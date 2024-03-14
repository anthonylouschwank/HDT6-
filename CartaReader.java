import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CartaReader {
    private static final String PATH = "cards_desc.txt";

    public static Map<String, String> leerCartas(ListaFactory factory) {
        Map<String, String> cartas = factory.crearLista();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    cartas.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cartas;
    }
}
