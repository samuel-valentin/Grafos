import java.util.*;

public class Main {

    static class Nodo {
        String nombre;
        List<Arco> arcos;
        int distancia;

        public Nodo(String nombre) {
            this.nombre = nombre;
            this.arcos = new ArrayList<>();
            this.distancia = Integer.MAX_VALUE;
        }

        public void agregarArco(Nodo destino, int peso) {
            this.arcos.add(new Arco(destino, peso));
        }
    }

    static class Arco {
        Nodo destino;
        int peso;

        public Arco(Nodo destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Leer entrada
        int n = sc.nextInt(); // número de nodos
        int m = sc.nextInt(); // número de aristas

        Map<String, Nodo> nodos = new HashMap<>();

        // Leer nombres de nodos
        for (int i = 0; i < n; i++) {
            String nombre = sc.next();
            nodos.put(nombre, new Nodo(nombre));
        }

        // Leer aristas
        for (int i = 0; i < m; i++) {
            String origenNombre = sc.next();
            String destinoNombre = sc.next();
            int peso = sc.nextInt();

            Nodo origen = nodos.get(origenNombre);
            Nodo destino = nodos.get(destinoNombre);

            origen.agregarArco(destino, peso);
        }

        // Leer nodo fuente
        String fuenteNombre = sc.next();
        Nodo fuente = nodos.get(fuenteNombre);
        fuente.distancia = 0;

        // Ejecutar algoritmo de Dijkstra
        PriorityQueue<Nodo> pq = new PriorityQueue<>(Comparator.comparingInt(nodo -> nodo.distancia));
        pq.add(fuente);

        while (!pq.isEmpty()) {
            Nodo nodoActual = pq.poll();

            for (Arco arco : nodoActual.arcos) {
                Nodo vecino = arco.destino;
                int nuevaDistancia = nodoActual.distancia + arco.peso;
                if (nuevaDistancia < vecino.distancia) {
                    vecino.distancia = nuevaDistancia;
                    pq.remove(vecino); // Eliminar y volver a agregar para actualizar la cola de prioridad
                    pq.add(vecino);
                }
            }
        }

        // Imprimir distancias mínimas
        for (Nodo nodo : nodos.values()) {
            System.out.println(nodo.nombre + ": " + nodo.distancia);
        }
    }
}
