package algoritimos;


import java.util.Arrays;

public class InsertionSort extends Algoritimo{

    public InsertionSort(){
        super("Insertion Sort");
    }

    public int[] ordernar(int[] vetor) {

        for (int j = 1; j < vetor.length; j++){
            int valorAtual = vetor[j];
            int indice = j - 1;
            while (indice >= 0 && vetor[indice] > valorAtual){
                vetor[indice + 1] = vetor[indice];
                indice--;
            }
            vetor[indice + 1] = valorAtual;
        }

        return vetor;
    }

    public void ordernarPrintando(int[] vetor) {
        System.out.printf("Algorítimo: %s\nTamanho: %s%n", this.getNome(), vetor.length);

        System.out.println("Passo 0: " + Arrays.toString(vetor));
        for (int j = 1; j < vetor.length; j++){
            int valorAtual = vetor[j];
            int indice = j - 1;
            while (indice >= 0 && vetor[indice] > valorAtual){
                vetor[indice + 1] = vetor[indice];
                indice--;
            }
            vetor[indice + 1] = valorAtual;
            System.out.println("Passo " + j + ": " + Arrays.toString(vetor));
        }

    }

}