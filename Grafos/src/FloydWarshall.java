import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FloydWarshall {

    ArrayList<Float>[] setMatrizAdjacencia(Grafo grafo) {

        ArrayList<Float>[] matrizAdjacencia = (ArrayList<Float>[])new ArrayList[grafo.qtdArestas()];

        for (int i = 0; i < grafo.qtdArestas(); i++) {
            matrizAdjacencia[i] = new ArrayList<Float>();
            for (int j = 0; j < grafo.qtdArestas(); j++) {
                matrizAdjacencia[i].add(2147483647.0f);
            }
        }

        for (int i = 0; i < grafo.qtdArestas(); i++) {
            for (int j = 0; j < grafo.qtdArestas(); j++) {
                float[] aresta = {(float)i, (float)j};
                if (i == j) {
                    matrizAdjacencia[i].set(j, 0.0f);
                } else if (grafo.haAresta(aresta)) {
                    matrizAdjacencia[i].set(j, grafo.peso(aresta));
                } else {
                    matrizAdjacencia[i].set(j, 2147483647.0f);
                }
            }
        }
        return matrizAdjacencia;
    }

    ArrayList<Float>[] floydWarshall(Grafo grafo) {
        ArrayList<Float>[] matrizD = this.setMatrizAdjacencia(grafo);

        for (int k = 0; k < grafo.qtdArestas(); k++) {
            for (int u = 0; u < grafo.qtdArestas(); u++) {
                for (int v = 0; v < grafo.qtdArestas(); v++) {
                    float duv = matrizD[u].get(v);
                    float duk = matrizD[u].get(k);
                    float dkv = matrizD[k].get(v);

                    matrizD[u].set(v, Math.min(duv, duk + dkv));
                }
            }
        }

        return matrizD;
    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        Grafo grafo = new Grafo();
        String separator = System.getProperty("file.separator");

        // Lendo arquivo de teste
        grafo.lerArquivo(separator+"testes"+separator+"arvore_geradora_minima.txt");


        FloydWarshall algoritmo = new FloydWarshall();
        ArrayList<Float>[] matrizD = algoritmo.floydWarshall(grafo);

        for (int i = 0; i < grafo.qtdArestas(); i++) {
            System.out.print(i + ":");
            for (int j = 0; j < grafo.qtdArestas() - 1; j++) {
                System.out.print( matrizD[i].get(j) + ",");
            }
            System.out.println(matrizD[i].get(grafo.qtdArestas()-1));
        }

    }
}
