package domain.algoritimos;

public class InsertionSort extends Algoritmo {

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

}