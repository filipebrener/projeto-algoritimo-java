package domain.algoritimos;

import java.util.Random;

public class QuickSortV3 extends QuickSort {

    public QuickSortV3() {
        super("mediana - 15 elementos");
    }

    @Override
    int escolhePivo(int[] vetor, int inicio, int fim) {
        int numElementos = Math.min(15, fim - inicio + 1);
        int[] elementosAleatorios = new int[numElementos];
        Random random = new Random();

        // Preenche o array com 15 elementos aleatórios do intervalo [inicio, fim]
        for (int i = 0; i < numElementos; i++) {
            int indiceAleatorio = inicio + random.nextInt(fim - inicio + 1);
            elementosAleatorios[i] = vetor[indiceAleatorio];
        }

        // Ordena os elementos aleatórios
        new BubbleSortOtimizado().ordenar(elementosAleatorios);
        return elementosAleatorios[(elementosAleatorios.length - 1)/2];

    }
}
