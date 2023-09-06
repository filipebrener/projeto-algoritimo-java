import algoritimos.Algoritimo;
import domain.Arquivo;
import domain.Contexto;
import domain.tipoEntrada.TipoEntrada;

import java.time.Duration;
import java.util.Scanner;

public class Main {

    public static boolean continuar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voltar ao menu principal ?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        System.out.print("Escolha: ");
        String escolhaUsuario = scanner.nextLine();

        while(!escolhaUsuario.equals("1") && !escolhaUsuario.equals("2")){
            System.out.print("Número digitado é inválido! Por favor, digite novamente: ");
            escolhaUsuario = scanner.nextLine();
        }
        return escolhaUsuario.equals("1");
    }

    public static String getCaminhoArquivo(String tipo, Algoritimo algoritimo, Integer tamanho, TipoEntrada tipoEntrada){
        return String.format("%s/Arquivos de %s/%s/%s%s%s.txt",
                algoritimo.getNome(),
                tipo,
                tipoEntrada.getNome(),
                tipo,
                tipoEntrada.getNome(),
                tamanho);
    }

    public static String formatDuration(Duration duration) {
        long minutos = (duration.toHoursPart() * 60L)  + duration.toMinutesPart();
        long segundos = duration.toSecondsPart();
        long milissegundos = duration.toMillisPart();

        return String.format("%d:%02d:%06d", minutos, segundos, milissegundos);
    }

    public static void main(String[] args) {
        Contexto contexto = new Contexto();
        Arquivo arquivo = new Arquivo();

        do {
            contexto.escolherAlgoritimo();
            contexto.escolherEntrada();
            contexto.escolherTamanho();
            contexto.iniciar();
            for(Algoritimo algoritimo: contexto.getAlgoritimosEscolhidos()){
                for(TipoEntrada tipoEntrada: contexto.getTipoEntradaEscolhidos()){
                    for(Integer tamanho: contexto.getTamanhosEscolhidos()){
                        contexto.setProcessamentoAtual(algoritimo, tipoEntrada, tamanho);
                        int[] vetorEntrada = tipoEntrada.gerarEntrada(tamanho);
                        contexto.atualizarProgresso();
                        arquivo.salvarArquivo(getCaminhoArquivo("Entrada", algoritimo, tamanho, tipoEntrada), vetorEntrada);
                        contexto.atualizarProgresso();
                        int[] vetorSaida = algoritimo.executar(vetorEntrada);
                        contexto.atualizarProgresso();
                        Duration tempoExecucao = algoritimo.getTempoExecucao();
                        contexto.atualizarProgresso();
                        arquivo.salvarArquivo(getCaminhoArquivo("Saida",algoritimo, tamanho, tipoEntrada), vetorSaida);
                        contexto.atualizarProgresso();
                        arquivo.salvarArquivo(getCaminhoArquivo("Tempo",algoritimo, tamanho, tipoEntrada), formatDuration(tempoExecucao));
                        contexto.atualizarProgresso();
                    }
                }
            }
            contexto.finalizar();
        }while(continuar());

    }

}
