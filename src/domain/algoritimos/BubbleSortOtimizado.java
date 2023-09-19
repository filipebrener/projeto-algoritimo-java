package domain.algoritimos;

public class BubbleSortOtimizado extends Algoritmo {

    public BubbleSortOtimizado() {
        super("BubbleSort (otimizado)");
    }

    @Override
    protected int[] ordernar(int[] vetor) {
        boolean trocou;
        for (int i = 0; i < vetor.length - 1; i++) {
            trocou = false;
            for (int j = 0; j < vetor.length - i - 1; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    trocou = true;
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
            if(!trocou){
                break;
            }
        }
        return vetor;
    }
}
