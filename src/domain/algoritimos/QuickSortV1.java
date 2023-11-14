package domain.algoritimos;

public class QuickSortV1 extends QuickSort {

    public QuickSortV1() {
        super("pivô na primeira posição");
    }

    @Override
    int escolhePivo(int[] vetor, int inicio, int fim) {
        return vetor[inicio];
    }
}
