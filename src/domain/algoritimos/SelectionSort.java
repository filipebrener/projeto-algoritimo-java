package domain.algoritimos;

public class SelectionSort extends Algoritmo {


    public SelectionSort() {
        super("Selection Sort");
    }

    @Override
    protected int[] ordernar(int[] vetor) {
        int n = vetor.length;
        for (int i = 0; i < n - 1; i++) {
            // Encontra o índice do menor elemento no subarray não ordenado
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (vetor[j] < vetor[minIndex]) {
                    minIndex = j;
                }
            }

            // Troca o menor elemento com o primeiro elemento não ordenado
            int temp = vetor[i];
            vetor[i] = vetor[minIndex];
            vetor[minIndex] = temp;
        }

        return vetor;
    }
}
