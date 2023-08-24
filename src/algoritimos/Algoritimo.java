package algoritimos;

public abstract class Algoritimo {

    protected final String nome;

    protected Algoritimo(String nome) {
        this.nome = nome;
    }

    public int[] ordernar(int[] vetor){
        throw new RuntimeException("Método não implementado");
    };

    public void ordernarPrintando(int[] vetor){
        throw new RuntimeException("Método não implementado");
    };

    public String getNome(){
        return this.nome;
    }

}
