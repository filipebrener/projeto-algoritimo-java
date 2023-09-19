package domain;

import domain.algoritimos.Algoritmo;
import domain.tipoEntrada.TipoEntrada;
import domain.utils.Formats;

public class ProcessamentoAtual {

    Algoritmo algoritmo;

    TipoEntrada tipoEntrada;

    Integer tamanho;

    public ProcessamentoAtual(Algoritmo algoritmo, TipoEntrada tipoEntrada, Integer tamanho) {
        this.algoritmo = algoritmo;
        this.tipoEntrada = tipoEntrada;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return String.format("%nProcessando atualmente:%nAlgoritmo: %s%nOrdem inicial do vetor: %s%nTamanho %s%n", algoritmo.getNome(), tipoEntrada.getNome(), Formats.decimalFormat.format(tamanho));
    }
}
