package domain.algoritimos;


public class BubbleSortTradicional extends Algoritmo {

    public BubbleSortTradicional() {
        super("BubbleSort (tradicional)");
    }

    @Override
    protected int[] ordernar(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            for (int j = 0; j < vetor.length - i - 1; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
        return vetor;
    }
}
