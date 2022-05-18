import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileReader;

public class Grafo {

    // Vértices
    List<List<String>> V = new ArrayList<>();

    // Arestas
    List<List<String>> E = new ArrayList<>();

    // Função que mapeia o peso de cada aresta
    String w(String[] vertice) {
        for (int i = 0; i < E.size(); i++) {
            if (E.get(i).get(0).equals(vertice[0]) && E.get(i).get(1).equals(vertice[1])) {
                return E.get(i).get(2);
            }
        }
        return "2147483647";
    }

    int qtdVertices() {
        int tamanho = V.size();
        return tamanho;
    }

    int qtdArestas() {
        int tamanho = E.size();
        return tamanho;
    }

    int grau(String v) {
        int quantidade = 0;

        for (int i = 0; i < E.size(); i++) {
            for (int j = 0; j < E.get(i).size(); j++)
                if (E.get(i).get(j).equals(v)) {
                    quantidade++;
                }
        }

        return quantidade;
    }

    String rotulo(String v) {
        for (int i = 0; i < V.size(); i++) {
            if (V.get(i).get(0).equals(v)) {
                return V.get(i).get(1);
            }
        }
        return "";
    }

    void lerArquivo() throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("src/arquivo.txt"));
        boolean lendoVertices = false;

        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.contains("vertice")) {
                lendoVertices = true;
                continue;
            } else if (line.contains("edges")) {
                lendoVertices = false;
                continue;
            }

            String[] textoSeparado = line.split(" ");

            if (lendoVertices) {

                List<String> listaVertices = new ArrayList<String>();
                listaVertices.add(textoSeparado[0]);
                listaVertices.add(textoSeparado[1]);
                V.add(listaVertices);

            } else if (lendoVertices == false) {
                List<String> listaVertices = new ArrayList<String>();
                listaVertices.add(textoSeparado[0]);
                listaVertices.add(textoSeparado[1]);
                listaVertices.add(textoSeparado[2]);
                E.add(listaVertices);
            }
        }
        System.out.println(V);
        System.out.println(E);
    }

    public static void main(String[] args) throws FileNotFoundException {

        Grafo grafo = new Grafo();

        // Lendo arquivo de teste
        grafo.lerArquivo();

        String[] vertice = { "1", "2"};
        String valor = grafo.w(vertice);
        System.out.println(valor);

        System.out.println(grafo.qtdVertices());

        System.out.println(grafo.qtdArestas());

        System.out.println(grafo.grau("4"));

        System.out.println(grafo.rotulo("4"));

    }

}
