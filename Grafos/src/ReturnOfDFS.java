import java.util.ArrayList;
import java.util.List;

public class ReturnOfDFS {

    private List<Boolean> C = new ArrayList<>();
    private List<Integer> T = new ArrayList<>();
    private List<Integer> F = new ArrayList<>();
    private List<Integer> A = new ArrayList<>();

    public ReturnOfDFS(List<Boolean> C, List<Integer> T, List<Integer> F, List<Integer> A) {
        this.C = C;
        this.T = T;
        this.F = F;
        this.A = A;
    }

    public List<Boolean> getC() {
        return this.C;
    }

    public List<Integer> getT() {
        return this.T;
    }

    public List<Integer> getF() {
        return this.F;
    }

    public List<Integer> getA() {
        return this.A;
    }

    ReturnOfDFS getTypes() {
        return new ReturnOfDFS(this.C, this.T, this.F, this.A);
    }

}
