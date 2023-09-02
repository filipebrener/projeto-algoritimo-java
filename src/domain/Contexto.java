package domain;

import algoritimos.Algoritimo;
import algoritimos.InsertionSort;
import domain.tipoEntrada.Aleatorio;
import domain.tipoEntrada.Crescente;
import domain.tipoEntrada.Decrescente;
import domain.tipoEntrada.TipoEntrada;

import java.util.*;

public class Contexto {

    private final Scanner scanner = new Scanner(System.in);

    private final List<Algoritimo> todosAlgoritimos;

    private List<Algoritimo> algoritimosEscolhidos;

    private final List<TipoEntrada> todosOsTiposEntrada;

    private List<TipoEntrada> tipoEntradaEscolhidos;

    private final  List<Integer> todosOsTamanhos;

    private List<Integer> tamanhosEscolhidos;

    public Contexto() {
        this.todosAlgoritimos = new ArrayList<>();
        todosAlgoritimos.add(new InsertionSort());


        this.todosOsTamanhos = List.of(10, 100, 1000, 10000, 100000, 1000000);

        this.todosOsTiposEntrada = new ArrayList<>();
        todosOsTiposEntrada.add(new Crescente());
        todosOsTiposEntrada.add(new Decrescente());
        todosOsTiposEntrada.add(new Aleatorio());

    }

    public void finalizar() {
        this.algoritimosEscolhidos = new ArrayList<>();
        this.tipoEntradaEscolhidos = new ArrayList<>();
        this.tamanhosEscolhidos = new ArrayList<>();
    }

    public void escolherAlgoritimo() {
        Map<String, Algoritimo> algoritimoMap = new HashMap<>();
        System.out.println("Selecione um algorítimo para executar: ");
        int index = 1;
        for(Algoritimo algoritimo: todosAlgoritimos){
            algoritimoMap.put(String.valueOf(index), algoritimo);
            System.out.printf("%s: %s\n",index++, algoritimo.getNome());
        }
        String todosAlgoritimos = String.valueOf(index);
        System.out.println(todosAlgoritimos + ": Todos");

        System.out.print("\nEscolha um algorítimo: ");
        String escolhaUsuario = scanner.nextLine();
        Algoritimo algoritimoEscolhido = algoritimoMap.get(escolhaUsuario);

        while(algoritimoEscolhido == null && !escolhaUsuario.equals(todosAlgoritimos)){
            System.out.print("Número digitado é inválido! Por favor, digite novamente: ");
            escolhaUsuario = scanner.nextLine();
            algoritimoEscolhido = algoritimoMap.get(escolhaUsuario);
        }

        this.algoritimosEscolhidos = escolhaUsuario.equals(todosAlgoritimos) ? this.todosAlgoritimos : List.of(algoritimoEscolhido);

    }


    public void escolherEntrada(){
        Map<String, TipoEntrada> tipoEntradaMap = new HashMap<>();
        System.out.println("Escolha um tipo de entrada para o algorítimo: ");
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
        Map<String, Integer> tamanhosMap = new HashMap<>();
        System.out.println("Escolha um tamanho para a entrada do algorítimo: ");
        int index = 1;
        for(Integer tamanho: todosOsTamanhos){
            tamanhosMap.put(String.valueOf(index), tamanho);
            System.out.printf("%s: %s\n",index++, tamanho);
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

    public List<Algoritimo> getAlgoritimosEscolhidos() {
        return algoritimosEscolhidos;
    }

    public List<TipoEntrada> getTipoEntradaEscolhidos() {
        return tipoEntradaEscolhidos;
    }

    public List<Integer> getTamanhosEscolhidos() {
        return tamanhosEscolhidos;
    }

}
