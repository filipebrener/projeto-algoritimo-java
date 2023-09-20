package domain.algoritimos;

import domain.TempoExecucaoCalc;

import java.time.Duration;

public abstract class Algoritmo {

    protected final String nome;

    private final TempoExecucaoCalc execucao;

    protected Algoritmo(String nome) {
        this.nome = nome;
        this.execucao = new TempoExecucaoCalc();
    }

    public final int[] executar(int[] vetor){
        execucao.iniciar();
        int[] vetorOrdenado = this.ordernar(vetor);
        execucao.finalizar();
        return vetorOrdenado;
    }

    public Duration getTempoExecucao(){
        Duration tempoExecucao = this.execucao.getTempoExecucao();
        if(tempoExecucao == null){
            throw new RuntimeException("Não foi possível determinar o tempo de execução do algoritmo: " + this.nome);
        }
        return tempoExecucao;
    }

    protected abstract int[] ordernar(int[] vetor);

    public String getNome(){
        return this.nome;
    }

}
