package domain;

import algoritimos.Algoritimo;
import domain.tipoEntrada.TipoEntrada;

import java.text.DecimalFormat;

public class Pipeline {

    Algoritimo algoritimo;

    TipoEntrada tipoEntrada;

    Integer tamanho;

    public Pipeline(Algoritimo algoritimo, TipoEntrada tipoEntrada, Integer tamanho) {
        this.algoritimo = algoritimo;
        this.tipoEntrada = tipoEntrada;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###");
        return String.format("%nProcessando atualmente:%nAlgorítimo: %s%nOrdem inicial do vetor: %s%nTamanho %s%n", algoritimo.getNome(), tipoEntrada.getNome(), df.format(tamanho));
    }
}
