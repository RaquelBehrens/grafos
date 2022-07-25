import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class EdmondsKarp {

    int EdmondsKarp(GrafoDirigido grafo, int source, int sink) {
        int n = grafo.V.size();
        List<List<Integer>> F = new ArrayList<>();
        List<List<Integer>> C = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            List<Integer> F_intermediario = new ArrayList<>();
            List<Integer> C_intermediario = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                F_intermediario.add(0);
                C_intermediario.add(0);
            }
            F.add(F_intermediario);
            C.add(C_intermediario);
        }

        int m = grafo.E.size();
        for (int i = 0; i < m; i++) {
            C.get(Math.round(grafo.E.get(i).get(0))).set(Math.round(grafo.E.get(i).get(1)), Math.round(grafo.E.get(i).get(2)));
        }


        while(true) {
            List<Integer> path = BFS(C, F, source, sink);
            if(path == null) {
                break;
            }

            int u = path.get(0);
            int v = path.get(1);
            int flow = C.get(u).get(v) - F.get(u).get(v);

            for(int i = 0; i < path.size() - 2; i++) {
                u = path.get(i+1);
                v = path.get(i+2);
                flow = Math.min(flow, C.get(u).get(v) - F.get(u).get(v));
            }

            for(int i = 0; i < path.size() - 1; i++) {
                u = path.get(i);
                v = path.get(i+1);
                F.get(u).set(v, (F.get(u).get(v))+flow);
                F.get(v).set(u, (F.get(u).get(v))-flow);
            }
            int j = 0;
        }

        int resultado = 0;

        for (int i = 0; i < n; i++) {
            resultado = resultado + F.get(source).get(i);
        }

        return resultado;
    }

    List<Integer> BFS(List<List<Integer>> C, List<List<Integer>> F, int source, int sink) {
        List<Integer> P = new ArrayList<>();
        Stack<Integer> Q = new Stack<>();

        for (int i = 0; i < C.size(); i++) {
            P.add(-1);
        }

        P.set(source, source);
        Q.add(source);

        while (Q.size() != 0) {
            int u = Q.pop();

            for(int v = 0; v < C.size(); v++) {
                if ((C.get(u).get(v) - F.get(u).get(v) > 0) && (P.get(v) == -1)) {
                    P.set(v, u);
                    Q.push(v);

                    if (v == sink) {
                        List<Integer> path = new ArrayList<>();

                        while (true) {
                            path.add(0,v);
                            if (v == source) {
                                break;
                            }
                            v = P.get(v);
                        }
                        return path;
                    }
                }
            }
        }
        return null;
    }



    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        GrafoDirigido grafoDirigido = new GrafoDirigido();

        String separator = System.getProperty("file.separator");

        // Lendo arquivo de teste
        grafoDirigido.lerArquivo(separator+"testes"+separator+"fluxo_maximo.txt");

        EdmondsKarp algoritmo = new EdmondsKarp();
        int resultado = algoritmo.EdmondsKarp(grafoDirigido, 1, 6);

        System.out.println("Fluxo maximo: " + resultado);
    }

}
