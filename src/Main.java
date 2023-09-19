import domain.utils.Arquivo;
import domain.utils.Contexto;
import domain.algoritimos.Algoritmo;
import domain.tipoEntrada.TipoEntrada;
import domain.utils.Terminal;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.*;

public class Main {

    static final Scanner scanner = new Scanner(System.in);

    final static String PASTA_RAIZ = "Algoritmos/";

    static Contexto contexto = new Contexto();

    static Runnable funcionalidade;

    static String escolhaUsuario;

    static Boolean continuar = true;

    static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public static String formatDuration(Duration duration) {
        long minutos = (duration.toHoursPart() * 60L)  + duration.toMinutesPart();
        long segundos = duration.toSecondsPart();
        long milissegundos = duration.toMillisPart();

        return String.format("%d:%02d:%06d", minutos, segundos, milissegundos);
    }

    public static void exibirMenu(Map<String, Runnable> funcionalidades) {
        Terminal.clearConsole();
        String[] opcoesMenu =  funcionalidades.keySet().toArray(new String[0]);
        System.out.println("Escolha uma das opções:");
        for(int i = 0; i < opcoesMenu.length; i++){
            System.out.printf("%s: %s\n",i + 1, opcoesMenu[i]);
        }
        boolean escolhaValida;
        System.out.print("Sua escolha: ");
        do{
            escolhaUsuario = scanner.nextLine();
            escolhaValida = funcionalidades.containsKey(opcoesMenu[Integer.parseInt(escolhaUsuario) - 1]);
            if(!escolhaValida){
                System.out.print("Número digitado é inválido! Por favor, digite novamente: ");
            }
        }while(!escolhaValida);
        funcionalidade = funcionalidades.get(opcoesMenu[Integer.parseInt(escolhaUsuario) - 1]);
    }

    static Runnable apagarPastas(){
        List<String> pastas = Arquivo.listarPastasCriadas(PASTA_RAIZ);
        if(pastas.isEmpty()){
            System.out.println("\nNenhuma pasta encontrada, experimente executar um algoritmo!");
            System.out.print("Pressione enter para continuar... ");
            scanner.nextLine();
            return null;
        }
        contexto.escolherAlgoritmo("apagar a pasta", pastas);
        for (Algoritmo algoritmo : contexto.getAlgoritmosEscolhidos()) {
            Arquivo.apagarDiretorio(PASTA_RAIZ + algoritmo.getNome());
        }
        contexto.finalizar();
        return null;
    }

    static Runnable executarAlgoritmos(){
        contexto.escolherAlgoritmo("executar", null);
        contexto.escolherEntrada();
        contexto.escolherTamanho();
        contexto.iniciarBarraProgresso();
        for (Algoritmo algoritmo : contexto.getAlgoritmosEscolhidos()) {
            for (TipoEntrada tipoEntrada : contexto.getTipoEntradaEscolhidos()) {
                for (Integer tamanho : contexto.getTamanhosEscolhidos()) {
                    contexto.setProcessamentoAtual(algoritmo, tipoEntrada, tamanho);
                    int[] vetorEntrada = tipoEntrada.gerarEntrada(tamanho);
                    contexto.atualizarProgresso();
                    Arquivo.salvarArquivo("Entrada", algoritmo, tamanho, tipoEntrada, vetorEntrada);
                    contexto.atualizarProgresso();
                    int[] vetorSaida = algoritmo.executar(vetorEntrada);
                    contexto.atualizarProgresso();
                    Duration tempoExecucao = algoritmo.getTempoExecucao();
                    contexto.atualizarProgresso();
                    Arquivo.salvarArquivo("Saida", algoritmo, tamanho, tipoEntrada, vetorSaida);
                    contexto.atualizarProgresso();
                    Arquivo.salvarArquivo("Tempo", algoritmo, tamanho, tipoEntrada, formatDuration(tempoExecucao));
                    contexto.atualizarProgresso();
                }
            }
        }
        contexto.finalizar();
        return null;
    }

    static Runnable visualizarTemposDeExecucao(){
        Terminal.clearConsole();
        List<String> pastas = Arquivo.listarPastasCriadas(PASTA_RAIZ);
        if(pastas.isEmpty()){
            System.out.println("\nNenhuma pasta encontrada, experimente executar um algoritmo!");
            System.out.print("Pressione enter para continuar... ");
            scanner.nextLine();
            return null;
        }
        contexto.escolherAlgoritmo("consultar o tempo", pastas);
        contexto.escolherEntrada();
        contexto.escolherTamanho();
        for (Algoritmo algoritmo : contexto.getAlgoritmosEscolhidos()) {
            printSeparator(algoritmo.getNome());
            for (TipoEntrada tipoEntrada : contexto.getTipoEntradaEscolhidos()) {
                System.out.println("\n" + tipoEntrada.getNome());
                for (Integer tamanho : contexto.getTamanhosEscolhidos()) {
                    String tempo = Arquivo.lerTempo("Tempo", algoritmo, tamanho, tipoEntrada);
                    String praFicarBonitinho = " ".repeat(log(max(contexto.getTamanhosEscolhidos())) - log(tamanho) + 1);
                    System.out.printf("%s%s: %s\n", decimalFormat.format(tamanho), praFicarBonitinho, tempo);
                }
            }
            System.out.println("\n");
        }
        contexto.finalizar();
        return null;
    }

    public static void printSeparator(String titulo){
        int repetir = 40 - (titulo.length()/2);
        System.out.println("=".repeat(repetir) + titulo + "=".repeat(repetir));
    }

    public static int log(int maiorElemento) {
        int log = (int) (Math.log10(maiorElemento));
        return  log + (log/3);
    }

    public static int max(List<Integer> lista) {
        int maior = lista.get(0);
        for (int elemento : lista) {
            if (elemento > maior) {
                maior = elemento;
            }
        }
        return maior;
    }

    public static void main(String[] args) {
        Map<String, Runnable> funcionalidades = new LinkedHashMap<>();
        funcionalidades.put("Executar algoritmos", Main::executarAlgoritmos);
        funcionalidades.put("Visualizar tempos de execução", Main::visualizarTemposDeExecucao);
        funcionalidades.put("Apagar pastas", Main::apagarPastas);
        funcionalidades.put("Fechar programa", () -> continuar = false);
        do{
            exibirMenu(funcionalidades);
            funcionalidade.run();
        }while(continuar);
        scanner.close();
    }

}
