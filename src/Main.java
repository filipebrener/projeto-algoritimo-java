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
        String escolhaUsuario = scanner.nextLine();

        while(!escolhaUsuario.equals("1") && !escolhaUsuario.equals("2")){
            System.out.print("Número digitado é inválido! Por favor, digite novamente: ");
            escolhaUsuario = scanner.nextLine();
        }
        return escolhaUsuario.equals("1");
    }

    public static String getCaminhoArquivo(String tipo, Algoritimo algoritimo, Integer tamanho, TipoEntrada tipoEntrada){
        return String.format("%s/aquivos_de_%s/%s_%s%s.txt",
                algoritimo.getNome().replace(" ", "_").toLowerCase(),
                tipo, tipo,
                tipoEntrada.getNome().toLowerCase(),
                tamanho);
    }

    // Método para formatar a duração no formato "HH:mm:ss:mm"
    public static String formatDuration(Duration duration) {
        long minutos = duration.toMinutesPart();
        long segundos = duration.toSecondsPart();
        long milissegundos = duration.toMillisPart();

        return String.format("%02d:%02d:%10d", minutos, segundos, milissegundos);
    }

    public static void main(String[] args) {
        Contexto contexto = new Contexto();
        Arquivo arquivo = new Arquivo();

        do {
            contexto.escolherAlgoritimo();
            contexto.escolherEntrada();
            contexto.escolherTamanho();
            for(Algoritimo algoritimo: contexto.getAlgoritimosEscolhidos()){
                for(TipoEntrada tipoEntrada: contexto.getTipoEntradaEscolhidos()){
                    for (Integer tamanho: contexto.getTamanhosEscolhidos()){
                        int[] vetorEntrada = tipoEntrada.gerarEntrada(tamanho);
                        int[] vetorSaida = algoritimo.executar(vetorEntrada);
                        Duration tempoExecucao = algoritimo.getTempoExecucao();
                        arquivo.salvarArquivo(getCaminhoArquivo("entrada", algoritimo, tamanho, tipoEntrada), vetorEntrada);
                        arquivo.salvarArquivo(getCaminhoArquivo("saida",algoritimo, tamanho, tipoEntrada), vetorSaida);
                        arquivo.salvarArquivo(getCaminhoArquivo("tempo",algoritimo, tamanho, tipoEntrada), formatDuration(tempoExecucao));
                    }
                }
            }
            contexto.finalizar();
        }while(continuar());

    }

}
