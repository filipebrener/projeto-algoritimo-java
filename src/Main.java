import algoritimos.Algoritimo;
import algoritimos.InsertionSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Main {

    public static int[] entradaCrescente(int N) {
        int[] vetor = new int[N];
        for (int i = 0; i < N; i++) {
            vetor[i] = i + 1;
        }
        return vetor;
    }

    public static int[] entradaDecrescente(int N) {
        int[] vetor = new int[N];
        for (int i = 0; i < N; i++) {
            vetor[i] = N - i;
        }
        return vetor;
    }

    public static int[] entradaAleatoria(int N) {
        int[] vetor = new int[N];
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            vetor[i] = rand.nextInt(N); // Gera números aleatórios de 0 a 99, você pode ajustar o intervalo conforme necessário.
        }
        return vetor;
    }

    public static int[] getEntrada(int n, TipoEntrada tipoEntrada){
        switch (tipoEntrada){
            case ALEATORIO -> {
                return entradaAleatoria(n);
            }
            case CRESCENTE ->{
                return entradaCrescente(n);
            }
            case DECRESCENTE ->{
                return entradaDecrescente(n);
            }
            default -> {
                throw new RuntimeException("Tipo de entrada desconhecido: " + tipoEntrada.name());
            }
        }

    }


    public static void main(String[] args) {
        List<Algoritimo> algoritimos = new ArrayList<>();
        algoritimos.add(new InsertionSort());
        int tamanhoEntrada = 10;
        int[] entrada = getEntrada(tamanhoEntrada, TipoEntrada.ALEATORIO);
        for(Algoritimo algoritimo: algoritimos){
            algoritimo.ordernarPrintando(entrada);
        }
    }

}
