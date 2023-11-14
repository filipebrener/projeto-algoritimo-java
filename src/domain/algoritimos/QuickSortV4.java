package domain.algoritimos;

import java.util.Random;

public class QuickSortV4 extends QuickSort {

    public QuickSortV4() {
        super("pivô aleatório");
    }

    @Override
    int escolhePivo(int[] vetor, int inicio, int fim) {
        return vetor[new Random().nextInt(inicio, fim + 1)];
    }
}
