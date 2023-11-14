package domain.algoritimos;

import java.util.Scanner;

public abstract class QuickSort extends Algoritmo {

    protected QuickSort(String descricao) {
        super("QuickSort (" + descricao + ")");
    }

    @Override
    protected int[] ordenar(int[] vetor) {
        quickSort(vetor, 0, vetor.length - 1);
        return vetor;
    }

    protected void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    private int separar(int[] vetor, int inicio, int fim) {
        int pivo = escolhePivo(vetor, inicio, fim);
        int i = inicio + 1;
        while (i <= fim) {
            if (vetor[i] <= pivo)
                i++;
            else if (pivo < vetor[fim])
                fim--;
            else {
                int troca = vetor[i];
                vetor[i] = vetor[fim];
                vetor[fim] = troca;
                i++;
                fim--;
            }
        }
        vetor[inicio] = vetor[fim];
        vetor[fim] = pivo;
        return fim;
    }

    abstract int escolhePivo(int[] vetor, int inicio, int fim);

}
