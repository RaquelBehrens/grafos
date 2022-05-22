import java.util.ArrayList;
import java.util.List;

public class ReturnWithDifferentTypes {

    private boolean ehCiclo;
    private List<Integer> caminho = new ArrayList<>();

    public ReturnWithDifferentTypes(boolean ehCiclo, List<Integer> caminho) {
        this.ehCiclo = ehCiclo;
        this.caminho = caminho;
    }

    public boolean getEhCiclo() {
        return this.ehCiclo;
    }

    public List<Integer> getCaminho() {
        return this.caminho;
    }

    ReturnWithDifferentTypes getTypes() {
        return new ReturnWithDifferentTypes(this.ehCiclo, this.caminho);
    }
}
