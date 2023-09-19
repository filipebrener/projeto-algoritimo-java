package domain.algoritimos;

public class ShellSort extends Algoritmo {
    public ShellSort() {
        super("ShellSort");
    }

    @Override
    protected int[] ordernar(int[] vetor) {
        int n = vetor.length;

        // Inicializa o intervalo (gap) com metade do tamanho do array
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Realiza a inserção por intervalos semelhante ao insertion sort
            for (int i = gap; i < n; i++) {
                int temp = vetor[i];
                int j;

                // Mover elementos do subarray arr[0..i-gap] que são maiores que temp
                for (j = i; j >= gap && vetor[j - gap] > temp; j -= gap) {
                    vetor[j] = vetor[j - gap];
                }

                // Insere o elemento temporário na posição correta
                vetor[j] = temp;
            }
        }
        return vetor;
    }
}
