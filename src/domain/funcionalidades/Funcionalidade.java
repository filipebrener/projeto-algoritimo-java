package domain.funcionalidades;

import domain.algoritimos.Algoritmo;
import domain.tipoEntrada.TipoEntrada;
import domain.Contexto;

import java.util.List;

public abstract class Funcionalidade {

    protected static List<Algoritmo> algoritmos;
    protected static List<TipoEntrada> tipoEntradas;
    protected static List<Integer> tamanhos;

    public Funcionalidade(Contexto contexto){
        algoritmos = contexto.getAlgoritmosEscolhidos();
        tipoEntradas = contexto.getTipoEntradaEscolhidos();
        tamanhos = contexto.getTamanhosEscolhidos();
    }

    public abstract void executar();

}
