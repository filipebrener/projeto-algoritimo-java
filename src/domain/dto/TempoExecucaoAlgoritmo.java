package domain.dto;

import java.util.List;
import java.util.stream.Collectors;

public class TempoExecucaoAlgoritmo {

    final String nomeAlgoritmo;

    final List<TemposPorEntrada> tempos;

    public TempoExecucaoAlgoritmo(String nomeAlgoritmo, List<TemposPorEntrada> tempos) {
        this.nomeAlgoritmo = nomeAlgoritmo;
        this.tempos = tempos;
    }

    @Override
    public String toString() {
        int lengthMaiorTempo = max(tempos.stream().flatMap(it -> it.getTempos().stream()).map( it -> it.getTempo().length()).collect(Collectors.toList()));
        return ( printTitulo() + tempos.stream().map(it -> it.toString(lengthMaiorTempo)).collect(Collectors.joining("\n")));
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

    private String printTitulo(){
        int repetir = 40 - (nomeAlgoritmo.length()/2);
        return "=".repeat(repetir) + nomeAlgoritmo + "=".repeat(repetir) + "\n";
    }
}
