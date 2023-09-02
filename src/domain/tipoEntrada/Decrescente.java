package domain.tipoEntrada;

public class Decrescente extends TipoEntrada{
    public Decrescente() {
        super("Decrescente");
    }

    @Override
    public int[] gerarEntrada(Integer tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = tamanho - i;
        }
        return vetor;
    }
}
