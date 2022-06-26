import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CFC {

    List<Integer> CFC(GrafoDirigido grafo) {
        ReturnOfDFS R = DFS(grafo);

        GrafoDirigido G_T = new GrafoDirigido();
        G_T.V = grafo.V;
        G_T.R = grafo.R;
        G_T.E = grafo.E;

        for (int i = 0; i < G_T.qtdArestas(); i++) {
            List<Float> listaVertice = new ArrayList<>();

            float value1 = grafo.E.get(i).get(0);
            float value2 = grafo.E.get(i).get(1);
            float value3 = grafo.E.get(i).get(2);

            listaVertice.add(value2);
            listaVertice.add(value1);
            listaVertice.add(value3);

            G_T.E.set(i, listaVertice);
        }

        ReturnOfDFS newReturn = DFSAdaptado(G_T);
        return newReturn.getA();

    }

    ReturnOfDFS DFS(GrafoDirigido grafo) {
        List<Boolean> C = new ArrayList<>();
        List<Integer> T = new ArrayList<>();
        List<Integer> F = new ArrayList<>();
        List<Integer> A = new ArrayList<>();

        int tempo = 0;
        int quantidadeVertices = grafo.qtdVertices();

        for (int i = 0; i < quantidadeVertices; i++) {
            C.add(false);
            T.add(Integer.MAX_VALUE);
            F.add(Integer.MAX_VALUE);
            A.add(null);
        }

        for (int u = 0; u < quantidadeVertices; u++) {
            if (!C.get(u)) {
                DFSVisit(grafo, u, C, T, F, A, tempo);
            }
        }

        return new ReturnOfDFS(C, T, F, A).getTypes();
    }

    ReturnOfDFS DFSAdaptado(GrafoDirigido grafo) {
        List<Boolean> C = new ArrayList<>();
        List<Integer> T = new ArrayList<>();
        List<Integer> F = new ArrayList<>();
        List<Integer> A = new ArrayList<>();

        int tempo = 0;
        int quantidadeVertices = grafo.qtdVertices();

        for (int i = 0; i < quantidadeVertices; i++) {
            C.add(false);
            T.add(Integer.MAX_VALUE);
            F.add(Integer.MAX_VALUE);
            A.add(null);
        }

        for (int u = quantidadeVertices-1; u >= 0 ; u--) {
            if (!C.get(u)) {
                DFSVisit(grafo, u, C, T, F, A, tempo);
            }
        }

        return new ReturnOfDFS(C, T, F, A).getTypes();
    }

    void DFSVisit(GrafoDirigido grafo, int v, List<Boolean> C, List<Integer> T, List<Integer> F, List<Integer> A, int tempo) {
        C.set(v, true);
        tempo = tempo + 1;
        T.set(v, tempo);

        float vFloat = v+1;
        List<Float> vizinhos = grafo.vizinhos(vFloat);

        for (Float u: vizinhos) {
            int uInt = Math.round(u) - 1;

            if (!C.get(uInt)) {
                A.set(uInt, v);
                DFSVisit(grafo, uInt, C, T, F, A, tempo);
            }
        }

        tempo = tempo + 1;
        F.set(v, tempo);

    }

    void DFSVisitAdaptado(GrafoDirigido grafo, int v, List<Boolean> C, List<Integer> T, List<Integer> F, List<Integer> A, int tempo) {
        C.set(v, true);
        tempo = tempo + 1;
        T.set(v, tempo);

        float vFloat = v+1;
        List<Float> vizinhos = grafo.vizinhos(vFloat);

        for (Float u: vizinhos) {
            int uInt = Math.round(u) - 1;

            if (!C.get(uInt)) {
                A.set(uInt, v);
                DFSVisit(grafo, uInt, C, T, F, A, tempo);
            }
        }

        tempo = tempo + 1;
        F.set(v, tempo);

    }

    void mostrarResposta(List<Integer> listaCFC, GrafoDirigido grafo) {
        List<Boolean> visitado = new ArrayList<>();
        for (int i = 0; i < listaCFC.size(); i++) {
            visitado.add(false);
        }

        List<List<Integer>> CFCs = new ArrayList<>();

        for (int i = 0; i < listaCFC.size(); i++) {
            if (!visitado.get(i)) {
                boolean foundBegin = false;
                boolean isAnswer = true;
                List<Integer> CFC = new ArrayList<>();
                Integer reference = i;

                List<Integer> copy = new ArrayList<>(listaCFC);

                while (!foundBegin && reference != null){
                    CFC.add(reference);
                    visitado.set(reference, true);
                    reference = getReference(listaCFC, reference);

                    copy.remove(reference);

                    if (reference == null) {
                        foundBegin = true;
                    }

                    if (CFC.stream().anyMatch(element -> copy.contains(element))) {
                        isAnswer = false;
                    }

                }
                if (isAnswer) {
                    CFCs.add(CFC);
                }
            }
        }

        List<List<Integer>> newCFCs = new ArrayList<>();
        for (int i = 0; i < listaCFC.size(); i++) {
            if (CFCs.isEmpty()) {
                List<Integer> newCFC = new ArrayList<>();
                newCFC.add(i);
                newCFCs.add(newCFC);
            } else {
                boolean inResult = false;
                for (int j = 0; j < CFCs.size(); j++) {
                    for (int k = 0; k < CFCs.get(j).size(); k++) {
                        if (CFCs.get(j).get(k) == i) {
                            inResult = true;
                        }
                    }
                }

                if (!inResult) {
                    List<Integer> newCFC = new ArrayList<>();
                    newCFC.add(i);
                    newCFCs.add(newCFC);
                }
            }
        }

        CFCs.addAll(newCFCs);

        for (int i = 0; i < CFCs.size(); i++) {
            for (int j = 0; j < CFCs.get(i).size(); j++) {
                Collections.sort(CFCs.get(i));
                System.out.print(grafo.rotulo(CFCs.get(i).get(j)+1));
                if (j < CFCs.get(i).size()-1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }

    Integer getReference(List<Integer> listaCFC, Integer reference) {
        if (reference != null) {
            return listaCFC.get(reference);
        }
        return null;
    }


    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        GrafoDirigido grafoDirigido = new GrafoDirigido();

        String separator = System.getProperty("file.separator");
        // Lendo arquivo de teste
        grafoDirigido.lerArquivo(separator+"testes"+separator+"dirigido2.txt");

        CFC algoritmo = new CFC();
        List<Integer> listaCFC = algoritmo.CFC(grafoDirigido);

        algoritmo.mostrarResposta(listaCFC, grafoDirigido);
    }

}
