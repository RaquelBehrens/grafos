import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

public class BuscaNivel {


    public void doBuscaNivel(Grafo grafo, Float vertice){
        HashMap<Float, Boolean> cvertice = new HashMap<Float, Boolean>();
        HashMap<Float, Float> dvertice = new HashMap<Float, Float>();
        HashMap<Float, Float> avertice = new HashMap<Float, Float>();
        for (int i = 1; i < (grafo.qtdVertices()+1); i++ ){
            cvertice.put((float) i, false);
            dvertice.put((float) i, Float.MAX_VALUE);
            avertice.put((float) i, null);
        }
        cvertice.put(vertice, true);
        dvertice.put(vertice, (float) 0);
        PriorityQueue<Float> fila = new PriorityQueue<Float>();
        fila.add(vertice);

        while (fila.size() > 0){
            Float u = fila.remove();
            List<Float> vizinhos = grafo.vizinhos(u);
            for (Float v : vizinhos){
                if(cvertice.get(v) == false){
                    cvertice.put(v, true);
                    dvertice.put(v, (dvertice.get(u)+1));
                    avertice.put(v, u);
                    fila.add(v);
                }
            }
        }
        mostraResultados(dvertice);
    }

    public void mostraResultados(HashMap<Float, Float> niveis){
        TreeMap<Float, String> aux = new TreeMap<Float, String>();
        for (Map.Entry<Float,Float> pair : niveis.entrySet()) {
            if(aux.get(pair.getValue()) == null){
                aux.put(pair.getValue(), ": "+pair.getKey());
            } else {
                aux.put(pair.getValue(), aux.get(pair.getValue())+" "+pair.getKey());
            }
        }
        for (Map.Entry<Float,String> pair : aux.entrySet()) {

            System.out.println(pair.getKey()+pair.getValue());

        }
    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        Grafo grafo = new Grafo();
        String separator = System.getProperty("file.separator");

        // Lendo arquivo de teste
        grafo.lerArquivo(separator+"testes"+separator+"arvore_geradora_minima.txt");


        BuscaNivel algoritmo = new BuscaNivel();
        algoritmo.doBuscaNivel(grafo, 5F);

    }
}
