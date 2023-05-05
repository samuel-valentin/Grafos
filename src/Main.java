import java.util.*;

public class Main {

    static class Nodo {
        String nombre;
        List<Arista> aristas;
        int distancia;

        public Nodo(String nombre) {
            this.nombre = nombre;
            this.aristas = new ArrayList<>();
            this.distancia = Integer.MAX_VALUE;
        }

        public void agregarArista(Nodo destino, int peso) {
            this.aristas.add(new Arista(destino, peso));
        }
    }

    static class Arista {
        Nodo destino;
        int peso;

        public Arista(Nodo destino, int peso) {
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
        Nodo[] nodosEnOrden = new Nodo[n]; // arreglo para mantener el orden de los nodos

        // Leer nombres de nodos y crear nodos
        for (int i = 0; i < n; i++) {
            String nombre = sc.next();
            Nodo nodo = new Nodo(nombre);
            nodos.put(nombre, nodo);
            nodosEnOrden[i] = nodo; // agregar nodo al arreglo en el orden en que se leyó
        }

        // Leer aristas y agregar arcos
        for (int i = 0; i < m; i++) {
            String origenNombre = sc.next();
            String destinoNombre = sc.next();
            int peso = sc.nextInt();

            Nodo origen = nodos.get(origenNombre);
            Nodo destino = nodos.get(destinoNombre);

            origen.agregarArista(destino, peso);
        }

        // Leer nodo fuente y ejecutar algoritmo de Dijkstra
        String fuenteNombre = sc.next();
        //sc.nextLine(); // consumir la línea extra
        Nodo fuente = nodos.get(fuenteNombre);
        fuente.distancia = 0;

        PriorityQueue<Nodo> pq = new PriorityQueue<>(Comparator.comparingInt(nodo -> nodo.distancia));
        pq.add(fuente);

        while (!pq.isEmpty()) {
            Nodo nodoActual = pq.poll();

            for (Arista arista : nodoActual.aristas) {
                Nodo vecino = arista.destino;
                int nuevaDistancia = nodoActual.distancia + arista.peso;
                if (nuevaDistancia < vecino.distancia) {
                    vecino.distancia = nuevaDistancia;
                    pq.remove(vecino);
                    pq.add(vecino);
                }
            }
        }

        // Imprimir distancias mínimas en el mismo orden en que se leyeron los nodos
        for (int i = 0; i < nodosEnOrden.length - 1; i++) {
            System.out.println(nodosEnOrden[i].nombre + ": " + nodosEnOrden[i].distancia);
        }
        System.out.print(nodosEnOrden[nodosEnOrden.length - 1].nombre + ": " + nodosEnOrden[nodosEnOrden.length - 1].distancia);
    }
}