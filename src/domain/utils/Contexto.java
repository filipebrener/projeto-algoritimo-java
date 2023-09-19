package domain.utils;

import domain.algoritimos.*;
import domain.tipoEntrada.Aleatorio;
import domain.tipoEntrada.Crescente;
import domain.tipoEntrada.Decrescente;
import domain.tipoEntrada.TipoEntrada;

import java.text.DecimalFormat;

import java.util.*;
import java.util.stream.Collectors;

public class Contexto {

    final Scanner scanner = new Scanner(System.in);

    private final List<Algoritmo> todosAlgoritmos;

    private List<Algoritmo> algoritmosEscolhidos;

    private final List<TipoEntrada> todosOsTiposEntrada;

    private List<TipoEntrada> tipoEntradaEscolhidos;

    private final  List<Integer> todosOsTamanhos;

    private List<Integer> tamanhosEscolhidos;

    private final DecimalFormat df = new DecimalFormat("#,###");

    private int quantidadePassos;
    private int passosExecutados;

    private Pipeline pipelineAtual;

    public Contexto() {
        this.todosAlgoritmos = new ArrayList<>();
        todosAlgoritmos.add(new InsertionSort());
        todosAlgoritmos.add(new BubbleSortTradicional());
        todosAlgoritmos.add(new BubbleSortOtimizado());
        todosAlgoritmos.add(new SelectionSort());
        todosAlgoritmos.add(new ShellSort());

        this.todosOsTamanhos = List.of(10, 100, 1000, 10000, 100000, 1000000);

        this.todosOsTiposEntrada = new ArrayList<>();
        todosOsTiposEntrada.add(new Crescente());
        todosOsTiposEntrada.add(new Decrescente());
        todosOsTiposEntrada.add(new Aleatorio());

    }

    public void setContexto(String acao, List<String> filtroAlgoritmo){
        escolherAlgoritmo(acao, filtroAlgoritmo);
        escolherEntrada();
        escolherTamanho();
    }

    public void exibirInformacoesGerais(){
        Terminal.clearConsole();
        System.out.println("Informações gerais:");
        System.out.printf("Algorítmos: %s%n",
                algoritmosEscolhidos.stream()
                        .map(Algoritmo::getNome)
                        .collect(Collectors.joining(", "))
        );
        System.out.printf("Tipos de entrada: %s%n",
                tipoEntradaEscolhidos.stream()
                        .map(TipoEntrada::getNome)
                        .collect(Collectors.joining(", "))
        );
        System.out.printf("Tamanhos: [%s]%n%n",
                tamanhosEscolhidos.stream()
                        .map(df::format)
                        .collect(Collectors.joining(", "))
        );
    }

    public void finalizar() {
        System.out.println("Pressione enter para continuar... ");
        scanner.nextLine();
        this.algoritmosEscolhidos = new ArrayList<>();
        this.tipoEntradaEscolhidos = new ArrayList<>();
        this.tamanhosEscolhidos = new ArrayList<>();
    }

    public void escolherAlgoritmo(String acao, List<String> filtroAlgoritmo) {
        Terminal.clearConsole();
        Map<String, Algoritmo> algoritmoMap = new HashMap<>();
        System.out.printf("Selecione um algoritmo para %s: %n\n", acao);
        List<Algoritmo> algoritmosValidos = filtroAlgoritmo == null ? todosAlgoritmos : todosAlgoritmos.stream()
                .filter(algoritmo -> filtroAlgoritmo.contains(algoritmo.getNome()))
                .toList();
        int index = 1;
        for (Algoritmo algoritmo : algoritmosValidos) {
            algoritmoMap.put(String.valueOf(index), algoritmo);
            System.out.printf("%s: %s\n", index++, algoritmo.getNome());
        }
        String indiceTodosAlgoritimos = index > 2 ? String.valueOf(index) : null;
        if(indiceTodosAlgoritimos != null){
            System.out.println(indiceTodosAlgoritimos + ": Todos");
        }
        System.out.print("\nEscolha um algoritmo: ");
        String escolhaUsuario;
        do {
            escolhaUsuario = scanner.nextLine();
            Algoritmo algoritmoEscolhido = algoritmoMap.get(escolhaUsuario);

            if (algoritmoEscolhido != null || escolhaUsuario.equals(indiceTodosAlgoritimos)) {
                this.algoritmosEscolhidos = escolhaUsuario.equals(indiceTodosAlgoritimos) ? algoritmosValidos : List.of(algoritmoEscolhido);
                break;
            }
            System.out.print("Número digitado é inválido! Por favor, digite novamente: ");
        } while (true);
    }



    public void escolherEntrada(){
        Terminal.clearConsole();
        Map<String, TipoEntrada> tipoEntradaMap = new HashMap<>();
        System.out.println("Escolha um tipo de entrada para o algoritmo: ");
        int index = 1;
        for(TipoEntrada entrada: todosOsTiposEntrada){
            tipoEntradaMap.put(String.valueOf(index), entrada);
            System.out.printf("%s: %s\n",index++, entrada.getNome());
        }
        String todasEntradas = String.valueOf(index);
        System.out.println(todasEntradas + ": Todas");

        System.out.print("\nEscolha uma entrada: ");
        String escolhaUsuario = scanner.nextLine();
        TipoEntrada entradaEscolhida = tipoEntradaMap.get(escolhaUsuario);

        while(entradaEscolhida == null && !escolhaUsuario.equals(todasEntradas)){
            System.out.print("Número digitado é inválido! Por favor, digite novamente: ");
            escolhaUsuario = scanner.nextLine();
            entradaEscolhida = tipoEntradaMap.get(escolhaUsuario);
        }
        this.tipoEntradaEscolhidos = escolhaUsuario.equals(todasEntradas) ? todosOsTiposEntrada : List.of(entradaEscolhida);
    }

    public void escolherTamanho() {
        Terminal.clearConsole();
        Map<String, Integer> tamanhosMap = new HashMap<>();
        System.out.println("Escolha um tamanho para a entrada do algoritmo: ");
        int index = 1;
        for(Integer tamanho: todosOsTamanhos){
            tamanhosMap.put(String.valueOf(index), tamanho);
            System.out.printf("%s: %s\n",index++, df.format(tamanho));
        }
        String todosTamanhos = String.valueOf(index);
        System.out.println(todosTamanhos + ": Todos");

        System.out.print("\nEscolha um tamanho: ");
        String escolhaUsuario = scanner.nextLine();
        Integer tamanhoEscolhido = tamanhosMap.get(escolhaUsuario);

        while(tamanhoEscolhido == null && !escolhaUsuario.equals(todosTamanhos)){
            System.out.print("Número digitado é inválido! Por favor, digite novamente: ");
            escolhaUsuario = scanner.nextLine();
            tamanhoEscolhido = tamanhosMap.get(escolhaUsuario);
        }

        this.tamanhosEscolhidos = escolhaUsuario.equals(todosTamanhos) ? this.todosOsTamanhos : List.of(tamanhoEscolhido);
    }

    public void iniciarBarraProgresso() {
        quantidadePassos = 6 * getAlgoritmosEscolhidos().toArray().length * getTipoEntradaEscolhidos().toArray().length * getTamanhosEscolhidos().toArray().length;
        passosExecutados = 0;
    }

    public void setProcessamentoAtual(Algoritmo algoritmo, TipoEntrada tipoEntrada, Integer tamanho) {
        pipelineAtual = new Pipeline(algoritmo, tipoEntrada, tamanho);
    }

    public void atualizarProgresso() {
        exibirInformacoesGerais();
        exibirInformacoesAtuais();
        passosExecutados++;
        exibirBarraProgresso();
    }

    private void exibirInformacoesAtuais() {
        System.out.println(pipelineAtual);
    }

    private void exibirBarraProgresso(){
        int porcentagem = (int) ((double) passosExecutados / quantidadePassos * 100);
        int barras = porcentagem / 2; // Para criar as barras "#" conforme a porcentagem

        System.out.print("Progresso: " + porcentagem + "% [");

        for (int i = 0; i < barras; i++) {
            System.out.print("#");
        }

        for (int i = barras; i < 50; i++) {
            System.out.print(" ");
        }

        System.out.println("]");
    }

    public List<Algoritmo> getAlgoritmosEscolhidos() {
        return algoritmosEscolhidos;
    }

    public List<TipoEntrada> getTipoEntradaEscolhidos() {
        return tipoEntradaEscolhidos;
    }

    public List<Integer> getTamanhosEscolhidos() {
        return tamanhosEscolhidos;
    }

    public List<Integer> getTodosOsTamanhos() {
        return todosOsTamanhos;
    }
}
