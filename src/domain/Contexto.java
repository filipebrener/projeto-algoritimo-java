package domain;

import domain.algoritimos.*;
import domain.tipoEntrada.Aleatorio;
import domain.tipoEntrada.Crescente;
import domain.tipoEntrada.Decrescente;
import domain.tipoEntrada.TipoEntrada;
import domain.utils.Formats;
import domain.utils.Terminal;

import java.util.*;

public class Contexto {

    private static final String VOLTAR = "0";
    private static final int PROXIMO = 1;
    private static final int ANTERIOR = -1;

    final Scanner scanner = new Scanner(System.in);

    private final List<Algoritmo> todosAlgoritmos;
    private List<Algoritmo> algoritmosEscolhidos;

    private final List<TipoEntrada> todosOsTiposEntrada;
    private List<TipoEntrada> tipoEntradaEscolhidos;

    private final List<Integer> todosOsTamanhos;
    private List<Integer> tamanhosEscolhidos;


    public Contexto() {
        this.todosAlgoritmos = new ArrayList<>();
//        todosAlgoritmos.add(new InsertionSort());
//        todosAlgoritmos.add(new BubbleSortTradicional());
//        todosAlgoritmos.add(new BubbleSortOtimizado());
//        todosAlgoritmos.add(new SelectionSort());
        todosAlgoritmos.add(new ShellSort());
        todosAlgoritmos.add(new MergeSort());
        todosAlgoritmos.add(new QuickSortV1()); // pivô primeira posição
        todosAlgoritmos.add(new QuickSortV2()); // média
        todosAlgoritmos.add(new QuickSortV3()); // mediada
        todosAlgoritmos.add(new QuickSortV4()); // aleatório

        this.todosOsTamanhos = List.of(10, 100, 1000, 10000, 100000, 1000000);

        this.todosOsTiposEntrada = new ArrayList<>();
        todosOsTiposEntrada.add(new Crescente());
        todosOsTiposEntrada.add(new Decrescente());
        todosOsTiposEntrada.add(new Aleatorio());

    }

    public boolean setContexto(String acao){
        return setContexto(acao, null, 0);
    }

    public boolean setContexto(String acao, List<String> filtroAlgoritmo){
        return setContexto(acao, filtroAlgoritmo, 0);
    }

    public boolean setContexto(String acao, List<String> filtroAlgoritmo, int passo) {
        if (passo < 0) return false;

        switch (passo) {
            case 0:
                passo += escolherAlgoritmo(acao, filtroAlgoritmo) ? PROXIMO : ANTERIOR;
                break;
            case 1:
                passo += escolherEntrada() ? PROXIMO : ANTERIOR;
                break;
            case 2:
                passo += escolherTamanho() ? PROXIMO : ANTERIOR;
                break;
        }

        boolean fim = passo > 2;
        return  fim || setContexto(acao, filtroAlgoritmo, passo);
    }


    public void finalizar() {
        System.out.println("Pressione enter para continuar... ");
        scanner.nextLine();
        this.algoritmosEscolhidos = null;
        this.tipoEntradaEscolhidos = null;
        this.tamanhosEscolhidos = null;
    }

    public boolean escolherAlgoritmo(String acao, List<String> filtroAlgoritmo) {
        Terminal.clearConsole();
        Map<String, Algoritmo> algoritmoMap = new HashMap<>();
        System.out.printf("Selecione um algoritmo para %s: %n\n", acao);
        List<Algoritmo> algoritmosValidos = filtroAlgoritmo == null ? todosAlgoritmos : todosAlgoritmos.stream()
                .filter(algoritmo -> filtroAlgoritmo.contains(algoritmo.getNome()))
                .toList();
        int index = 1;
        System.out.printf("%s: Voltar\n", VOLTAR);
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
            if(escolhaUsuario.equals(VOLTAR)){
                return false;
            }
            Algoritmo algoritmoEscolhido = algoritmoMap.get(escolhaUsuario);
            if (algoritmoEscolhido != null || escolhaUsuario.equals(indiceTodosAlgoritimos)) {
                this.algoritmosEscolhidos = escolhaUsuario.equals(indiceTodosAlgoritimos) ? algoritmosValidos : List.of(algoritmoEscolhido);
                break;
            }
            System.out.print("Número digitado é inválido! Por favor, digite novamente: ");
        } while (true);
        return true;
    }

    public boolean escolherEntrada(){
        Terminal.clearConsole();
        Map<String, TipoEntrada> tipoEntradaMap = new HashMap<>();
        System.out.println("Escolha um tipo de entrada para o algoritmo: ");
        int index = 1;
        System.out.printf("%s: Voltar\n", VOLTAR);
        for(TipoEntrada entrada: todosOsTiposEntrada){
            tipoEntradaMap.put(String.valueOf(index), entrada);
            System.out.printf("%s: %s\n",index++, entrada.getNome());
        }
        String todasEntradas = String.valueOf(index);
        System.out.println(todasEntradas + ": Todas");

        System.out.print("\nEscolha uma entrada: ");
        String escolhaUsuario = scanner.nextLine();
        if(escolhaUsuario.equals(VOLTAR)){
            return false;
        }
        TipoEntrada entradaEscolhida = tipoEntradaMap.get(escolhaUsuario);
        while(entradaEscolhida == null && !escolhaUsuario.equals(todasEntradas)){
            System.out.print("Número digitado é inválido! Por favor, digite novamente: ");
            escolhaUsuario = scanner.nextLine();
            entradaEscolhida = tipoEntradaMap.get(escolhaUsuario);
        }
        this.tipoEntradaEscolhidos = escolhaUsuario.equals(todasEntradas) ? todosOsTiposEntrada : List.of(entradaEscolhida);
        return true;
    }

    public boolean escolherTamanho() {
        Terminal.clearConsole();
        Map<String, Integer> tamanhosMap = new HashMap<>();
        System.out.println("Escolha um tamanho para a entrada do algoritmo: ");
        int index = 1;
        System.out.printf("%s: Voltar\n", VOLTAR);
        for(Integer tamanho: todosOsTamanhos){
            tamanhosMap.put(String.valueOf(index), tamanho);
            System.out.printf("%s: %s\n",index++, Formats.decimalFormat.format(tamanho));
        }
        String todosTamanhos = String.valueOf(index);
        System.out.println(todosTamanhos + ": Todos");

        System.out.print("\nEscolha um tamanho: ");
        String escolhaUsuario = scanner.nextLine();
        if(escolhaUsuario.equals(VOLTAR)){
            return false;
        }

        Integer tamanhoEscolhido = tamanhosMap.get(escolhaUsuario);
        while(tamanhoEscolhido == null && !escolhaUsuario.equals(todosTamanhos)){
            System.out.print("Número digitado é inválido! Por favor, digite novamente: ");
            escolhaUsuario = scanner.nextLine();
            tamanhoEscolhido = tamanhosMap.get(escolhaUsuario);
        }

        this.tamanhosEscolhidos = escolhaUsuario.equals(todosTamanhos) ? this.todosOsTamanhos : List.of(tamanhoEscolhido);
        return true;
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

}
