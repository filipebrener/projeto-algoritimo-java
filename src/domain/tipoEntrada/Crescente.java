package domain.tipoEntrada;

public class Crescente extends TipoEntrada{
    public Crescente() {
        super("Crescente");
    }

    @Override
    public int[] gerarEntrada(Integer tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = i + 1;
        }
        return vetor;
    }
}
