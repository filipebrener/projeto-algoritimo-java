package domain.algoritimos;

public class SelectionSort extends Algoritmo {

    public SelectionSort() {
        super("Selection Sort");
    }

    public int[] ordenar(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[j] < vetor[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = vetor[i];
            vetor[i] = vetor[minIndex];
            vetor[minIndex] = temp;
        }

        return vetor;
    }
}
