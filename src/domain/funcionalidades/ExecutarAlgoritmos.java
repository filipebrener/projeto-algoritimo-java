package domain.funcionalidades;

import domain.*;
import domain.algoritimos.Algoritmo;
import domain.tipoEntrada.TipoEntrada;
import domain.utils.Formats;
import domain.utils.Terminal;

import java.time.Duration;
import java.util.stream.Collectors;

public class ExecutarAlgoritmos extends Funcionalidade{

    private int quantidadePassos;

    private int passosExecutados;

    private ProcessamentoAtual processamentoAtualAtual;

    public ExecutarAlgoritmos(Contexto contexto) {
        super(contexto);
    }

    public void executar() {
        iniciarBarraProgresso();
        for (Algoritmo algoritmo : algoritmos) {
            for (TipoEntrada tipoEntrada : tipoEntradas) {
                for (Integer tamanho : tamanhos) {
                    setProcessamentoAtual(algoritmo, tipoEntrada, tamanho);
                    int[] vetorEntrada = tipoEntrada.gerarEntrada(tamanho);
                    atualizarProgresso();
                    Arquivo.salvarArquivo("Entrada", algoritmo, tamanho, tipoEntrada, vetorEntrada);
                    atualizarProgresso();
                    int[] vetorSaida = algoritmo.executar(vetorEntrada);
                    atualizarProgresso();
                    Duration tempoExecucao = algoritmo.getTempoExecucao();
                    atualizarProgresso();
                    Arquivo.salvarArquivo("Saida", algoritmo, tamanho, tipoEntrada, vetorSaida);
                    atualizarProgresso();
                    Arquivo.salvarArquivo("Tempo", algoritmo, tamanho, tipoEntrada, formatDuration(tempoExecucao));
                    atualizarProgresso();
                    verificarOrdenacao(vetorSaida);
                    atualizarProgresso();
                }
            }
        }
    }

    private void iniciarBarraProgresso() {
        quantidadePassos = 7 * algoritmos.toArray().length * tipoEntradas.toArray().length * tamanhos.toArray().length;
        passosExecutados = 0;
    }

    private void setProcessamentoAtual(Algoritmo algoritmo, TipoEntrada tipoEntrada, Integer tamanho) {
        processamentoAtualAtual = new ProcessamentoAtual(algoritmo, tipoEntrada, tamanho);
    }

    private void atualizarProgresso() {
        exibirInformacoesGerais();
        exibirInformacoesAtuais();
        passosExecutados++;
        exibirBarraProgresso();
    }

    public void exibirInformacoesGerais(){
        Terminal.clearConsole();
        System.out.println("Informações gerais:");
        System.out.printf("Algorítmos: %s%n",
                algoritmos.stream()
                        .map(Algoritmo::getNome)
                        .collect(Collectors.joining(", "))
        );
        System.out.printf("Tipos de entrada: %s%n",
                tipoEntradas.stream()
                        .map(TipoEntrada::getNome)
                        .collect(Collectors.joining(", "))
        );
        System.out.printf("Tamanhos: [%s]%n%n",
                tamanhos.stream()
                        .map(Formats.decimalFormat::format)
                        .collect(Collectors.joining(", "))
        );
    }

    private void exibirInformacoesAtuais() {
        System.out.println(processamentoAtualAtual);
    }

    private void exibirBarraProgresso(){
        int porcentagem = (int) ((double) passosExecutados / quantidadePassos * 100);
        int barras = porcentagem / 2;
        System.out.print("Progresso: " + porcentagem + "% [");
        for (int i = 0; i < 50; i++) {
            System.out.print(i < barras ? "#" : " ");
        }
        System.out.println("]");
    }

    public static String formatDuration(Duration duration) {
        long minutos = (duration.toHoursPart() * 60L)  + duration.toMinutesPart();
        long segundos = duration.toSecondsPart();
        long milissegundos = duration.toMillisPart();

        return String.format("%d:%02d:%06d", minutos, segundos, milissegundos);
    }

    private void verificarOrdenacao(int [] vetor){
        for(int i = 1; i < vetor.length; i++){
            if(vetor[i - 1] > vetor[i]){
                throw new RuntimeException(String.format("Erro na ordenação do algorítimo %s com tamanho %s de ordem %s!!!\n%s",
                        processamentoAtualAtual.getAlgoritmo().getNome(), processamentoAtualAtual.getTamanho(),
                        processamentoAtualAtual.getTipoEntrada().getNome(),
                        "Erro encontrado na posição " + (i + 1) + " do vetor, confira se esta tudo em ordem no arquivo de saída!"));
            }
        }
    }

}
