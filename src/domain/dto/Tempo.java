package domain.dto;

import domain.utils.Formats;

public class Tempo {

    final int tamanho;

    final String tempo;

    public Tempo(int tamanho, String tempo) {
        this.tamanho = tamanho;
        this.tempo = tempo;
    }

    public String toString(int maiorTamanho, int lengthMaiorTempo) {
        String espacos = " ".repeat(lengthMaiorTempo - tempo.length() + 1);
        String praFicarBonitinho = " ".repeat(log(maiorTamanho) - log(tamanho) + 1);
        return String.format("%s%s:%s%s", Formats.decimalFormat.format(tamanho), praFicarBonitinho, espacos, tempo);
    }

    private static int log(int maiorElemento) {
        int log = (int) (Math.log10(maiorElemento));
        return  log + (log/3);
    }

    public int getTamanho() {
        return tamanho;
    }

    public String getTempo() {
        return tempo;
    }
}
