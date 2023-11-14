package domain.algoritimos;

public class QuickSortV2 extends QuickSort {

    public QuickSortV2() {
        super("média");
    }

    @Override
    int escolhePivo(int[] vetor, int inicio, int fim) {
        return vetor[(inicio + fim)/2];
    }

}
