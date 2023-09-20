package domain.dto;

import java.util.List;
import java.util.stream.Collectors;

public class TemposPorEntrada {

    final String tipoEntrada;

    final List<Tempo> tempos;

    public TemposPorEntrada(String tipoEntrada, List<Tempo> tempos) {
        this.tipoEntrada = tipoEntrada;
        this.tempos = tempos;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public String toString(int lengthMaiorTempo) {
        int maiorTamanho = max(tempos.stream().map(Tempo::getTamanho).collect(Collectors.toList()));
        return "\n" + tipoEntrada + "\n" + tempos.stream().map(it -> it.toString(maiorTamanho, lengthMaiorTempo)).collect(Collectors.joining("\n")) + "\n";
    }


    private static int max(List<Integer> lista) {
        int maior = lista.get(0);
        for (int elemento : lista) {
            if (elemento > maior) {
                maior = elemento;
            }
        }
        return maior;
    }

    public List<Tempo> getTempos() {
        return tempos;
    }
}
