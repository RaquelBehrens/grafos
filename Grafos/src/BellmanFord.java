import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

public class BellmanFord {
    public void doBellmanFord(Grafo grafo, Float vertice){
        HashMap<Float, Float> dvertice = new HashMap<Float, Float>();
        HashMap<Float, Float> avertice = new HashMap<Float, Float>();
        for (int i = 1; i < (grafo.qtdVertices()+1); i++ ){
            dvertice.put((float) i, Float.MAX_VALUE);
            avertice.put((float) i, null);
        }

        dvertice.put(vertice, (float) 0);

        for (int i = 0; i < grafo.qtdVertices()-1; i++){
            for (List<Float> e : (grafo.returnE())){
                float[] aresta = {e.get(0), e.get(1)};
                if (dvertice.get(e.get(1)) > dvertice.get(e.get(0)) + grafo.peso(aresta)){
                    dvertice.put(e.get(1), (dvertice.get(e.get(0)) + grafo.peso(aresta)));
                    avertice.put(e.get(1), e.get(0));
                }
            }
        }

        for (List<Float> e : (grafo.returnE())){
            float[] aresta = {e.get(0), e.get(1)};
            if (dvertice.get(e.get(1)) > dvertice.get(e.get(0)) + grafo.peso(aresta)){
                System.out.println("false, null, null");
                return;
            }
        }

        mostraResultados(avertice, dvertice);
    }

    public void mostraResultados(HashMap<Float, Float> caminhos, HashMap<Float, Float> custo){
        System.out.println(String.valueOf(custo));
        TreeMap<Float, String> aux = new TreeMap<Float, String>();
        for (int i = 1; i < caminhos.size()+1; i++ ) {

            String path = getCaminhoRecursiva(caminhos, (float) i);
            aux.put((float) i, (": "+path+", "+(float)i+"; d= "+(custo.get((float)i))));

        }
        System.out.println(getCaminhoRecursiva(caminhos, (float) 5));
        for (Map.Entry<Float,String> pair : aux.entrySet()) {

            System.out.println(pair.getKey()+pair.getValue().replace(" ,", " "));

        }
    }
    public String getCaminhoRecursiva(HashMap<Float, Float> caminhos, Float pos){
        if (caminhos.get(pos) == null){
            return "";
        }
        String c = String.valueOf(caminhos.get(pos));
        return (getCaminhoRecursiva(caminhos, caminhos.get(pos))+ ", " +c);
    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        Grafo grafo = new Grafo();
        String separator = System.getProperty("file.separator");

        // Lendo arquivo de teste
        grafo.lerArquivo(separator+"testes"+separator+"arvore_geradora_minima.txt");


        BellmanFord algoritmo = new BellmanFord();
        algoritmo.doBellmanFord(grafo, 1F);

    }
}
