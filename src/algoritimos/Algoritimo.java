package algoritimos;

import domain.TempoExecucao;

import java.time.Duration;

public abstract class Algoritimo {

    protected final String nome;

    private final TempoExecucao execucao;

    protected Algoritimo(String nome) {
        this.nome = nome;
        this.execucao = new TempoExecucao();
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
            throw new RuntimeException("Não foi possível determinar o tempo de execução do algorítimo: " + this.nome);
        }
        return tempoExecucao;
    }

    protected abstract int[] ordernar(int[] vetor);

    public String getNome(){
        return this.nome;
    }

}
