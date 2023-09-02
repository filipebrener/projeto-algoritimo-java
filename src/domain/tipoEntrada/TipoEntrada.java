package domain.tipoEntrada;

public abstract class TipoEntrada {


    private final String nome;

    protected TipoEntrada(String nome) {
        this.nome = nome;
    }

    abstract public int[] gerarEntrada(Integer tamanho);

    public String getNome() {
        return nome;
    }
}
