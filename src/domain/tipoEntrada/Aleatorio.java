package domain.tipoEntrada;

import java.util.Random;

public class Aleatorio extends TipoEntrada{
    public Aleatorio() {
        super("Aleatorio");
    }

    @Override
    public int[] gerarEntrada(Integer tamanho) {
        int[] vetor = new int[tamanho];
        Random rand = new Random();
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = rand.nextInt(tamanho);
        }
        return vetor;
    }
}
