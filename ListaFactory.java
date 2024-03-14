import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;

interface ListaFactory {
    Map<String, String> crearLista();
}

// Clase HashMapFactory
class HashMapFactory implements ListaFactory {
    @Override
    public Map<String, String> crearLista() {
        return new HashMap<>();
    }
}

// Clase TreeMapFactory
class TreeMapFactory implements ListaFactory {
    @Override
    public Map<String, String> crearLista() {
        return new TreeMap<>();
    }
}

// Clase LinkedHashMapFactory
class LinkedHashMapFactory implements ListaFactory {
    @Override
    public Map<String, String> crearLista() {
        return new LinkedHashMap<>();
    }
}

