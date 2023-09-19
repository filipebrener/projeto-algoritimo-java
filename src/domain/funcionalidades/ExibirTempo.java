package domain.funcionalidades;

import domain.algoritimos.Algoritmo;
import domain.tipoEntrada.TipoEntrada;
import domain.utils.Arquivo;
import domain.utils.Contexto;
import domain.utils.Formats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExibirTempo extends Funcionalidade {

    public ExibirTempo(Contexto contexto){
        super(contexto);
    }

    // TODO: Melhorar a leitura no arquivo, para não precisar de ler duas vezes
    public void executar(){

        Map<String, List<String>> temposMap = new HashMap<>();
        for (Algoritmo algoritmo : algoritmos) {
            List<String> tempos = new ArrayList<>();
            for (TipoEntrada tipoEntrada : tipoEntradas) {
                for (Integer tamanho : tamanhos) {
                    tempos.add(Arquivo.lerTempo("Tempo", algoritmo, tamanho, tipoEntrada));
                }
            }
            temposMap.put(algoritmo.getNome(), tempos);
        }

        for (Algoritmo algoritmo : algoritmos) {
            printSeparator(algoritmo.getNome());
            for (TipoEntrada tipoEntrada : tipoEntradas) {
                System.out.println("\n" + tipoEntrada.getNome());
                for (Integer tamanho : tamanhos) {
                    String tempoAtual = Arquivo.lerTempo("Tempo", algoritmo, tamanho, tipoEntrada);
                    String praFicarBonitinho = " ".repeat(log(max(tamanhos)) - log(tamanho) + 1);
                    int tamanhoMaiorTempo = max(temposMap.get(algoritmo.getNome()).stream().map(String::length).collect(Collectors.toList()));
                    String espacos = " ".repeat(tamanhoMaiorTempo - tempoAtual.length() + 1);
                    System.out.printf("%s%s:%s%s\n", Formats.decimalFormat.format(tamanho), praFicarBonitinho, espacos, tempoAtual);
                }
            }
            System.out.println("\n");
        }
    }

    private static int log(int maiorElemento) {
        int log = (int) (Math.log10(maiorElemento));
        return  log + (log/3);
    }

    private static int max(List<Integer> lista) {
        int maior = lista.get(0);
        for (int elemento : lista) {
            if (elemento > maior) {
                maior = elemento;
            }
        }
        return maior;
    }

    private static void printSeparator(String titulo){
        int repetir = 40 - (titulo.length()/2);
        System.out.println("=".repeat(repetir) + titulo + "=".repeat(repetir));
    }

}
