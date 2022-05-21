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

    public void setEhCiclo(boolean novoEhCiclo) {
        this.ehCiclo = novoEhCiclo;
    }

    public List<Integer> getCaminho() {
        return this.caminho;
    }

    public void setCaminho(List<Integer> novoCaminho) {
        this.caminho = novoCaminho;
    }

    ReturnWithDifferentTypes getTypes() {
        return new ReturnWithDifferentTypes(this.ehCiclo, this.caminho);
    }
}
