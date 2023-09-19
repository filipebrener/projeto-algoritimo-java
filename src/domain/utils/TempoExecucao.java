package domain.utils;

import java.time.LocalDateTime;
import java.time.Duration;

public class TempoExecucao {

    private LocalDateTime tempoInicio;
    private LocalDateTime tempoFim;
    private Duration tempoExecucao;

    public void iniciar() {
        tempoInicio = LocalDateTime.now();
        tempoFim = null;
        tempoExecucao = null;
    }

    public void finalizar() {
        tempoFim = LocalDateTime.now();
        if (tempoInicio != null) {
            tempoExecucao = Duration.between(tempoInicio, tempoFim);
        } else {
            throw new IllegalStateException("O tempo de início e não foi definido corretamente.");
        }
    }

    public Duration getTempoExecucao() {
        return tempoExecucao;
    }
}
