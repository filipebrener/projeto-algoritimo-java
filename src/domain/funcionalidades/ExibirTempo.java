package domain.funcionalidades;

import domain.algoritimos.Algoritmo;
import domain.dto.TempoExecucaoAlgoritmo;
import domain.dto.Tempo;
import domain.dto.TemposPorEntrada;
import domain.tipoEntrada.TipoEntrada;
import domain.Contexto;

import java.util.ArrayList;
import java.util.List;

public class ExibirTempo extends Funcionalidade {

    public ExibirTempo(Contexto contexto){
        super(contexto);
    }

    public void executar(){

        List<TempoExecucaoAlgoritmo> temposDeExecuxao = new ArrayList<>();
        for (Algoritmo algoritmo : algoritmos) {
            List<TemposPorEntrada> temposPorEntradas = new ArrayList<>();
            for (TipoEntrada tipoEntrada : tipoEntradas) {
                List<Tempo> tempos = new ArrayList<>();
                for (Integer tamanho : tamanhos) {
                    tempos.add(new Tempo(tamanho, Arquivo.lerTempo("Tempo", algoritmo, tamanho, tipoEntrada)));
                }
                temposPorEntradas.add(new TemposPorEntrada(tipoEntrada.getNome(), tempos));
            }
            temposDeExecuxao.add(new TempoExecucaoAlgoritmo(algoritmo.getNome(), temposPorEntradas));
        }

        for(TempoExecucaoAlgoritmo temposExecuxao: temposDeExecuxao){
            System.out.println(temposExecuxao);
        }
    }

}
