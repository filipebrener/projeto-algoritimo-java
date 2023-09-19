import domain.funcionalidades.ExecutarAlgoritmos;
import domain.utils.Arquivo;
import domain.utils.Contexto;
import domain.algoritimos.Algoritmo;
import domain.funcionalidades.ExibirTempo;
import domain.utils.Terminal;

import java.util.*;

public class Main {

    static final Scanner scanner = new Scanner(System.in);

    final static String PASTA_RAIZ = "Algoritmos/";

    final static Contexto contexto = new Contexto();

    private static boolean continuar = true;

    public static Runnable escolherFuncionalidade(Map<String, Runnable> funcionalidades) {
        Terminal.clearConsole();
        String escolhaUsuario;
        String[] opcoesMenu =  funcionalidades.keySet().toArray(new String[0]);
        System.out.println("Escolha uma das opções:");
        for(int i = 0; i < opcoesMenu.length; i++){
            System.out.printf("%s: %s\n",i + 1, opcoesMenu[i]);
        }
        boolean escolhaValida = true;
        System.out.print("Sua escolha: ");
        do{
            escolhaUsuario = scanner.nextLine();
            try {
                if(!escolhaValida){
                    System.err.print("Número digitado é inválido! Por favor, digite novamente: ");
                }
                escolhaValida = funcionalidades.containsKey(opcoesMenu[Integer.parseInt(escolhaUsuario) - 1]);
            } catch (Exception ex){
                escolhaValida = false;
            }
        }while(!escolhaValida);
        return funcionalidades.get(opcoesMenu[Integer.parseInt(escolhaUsuario) - 1]);
    }

    static Runnable apagarPastas(){
        List<String> pastas = Arquivo.listarPastasCriadas(PASTA_RAIZ);
        if(pastas.isEmpty()){
            System.out.println("\nNenhuma pasta encontrada, experimente executar um algoritmo!");
            System.out.print("Pressione enter para continuar... ");
            scanner.nextLine();
            return null;
        }
        if(!contexto.escolherAlgoritmo("apagar a pasta", pastas)) return null;
        for (Algoritmo algoritmo : contexto.getAlgoritmosEscolhidos()) {
            Arquivo.apagarDiretorio(PASTA_RAIZ + algoritmo.getNome());
        }
        contexto.finalizar();
        return null;
    }

    static Runnable executarAlgoritmos(){
        if(!contexto.setContexto("executar")) return null;
        ExecutarAlgoritmos executarAlgoritmos = new ExecutarAlgoritmos(contexto);
        executarAlgoritmos.executar();
        contexto.finalizar();
        return null;
    }

    static Runnable visualizarTemposDeExecucao(){
        List<String> pastas = Arquivo.listarPastasCriadas(PASTA_RAIZ);
        if(pastas.isEmpty()){
            System.out.println("\nNenhuma pasta encontrada, experimente executar um algoritmo!");
            System.out.print("Pressione enter para continuar... ");
            scanner.nextLine();
            return null;
        }
        if(!contexto.setContexto("consultar o tempo", pastas)) return null;
        ExibirTempo exibirTempo = new ExibirTempo(contexto);
        exibirTempo.executar();
        contexto.finalizar();
        return null;
    }

    public static void main(String[] args) {
        Map<String, Runnable> funcionalidades = new LinkedHashMap<>();
        funcionalidades.put("Executar algoritmos", Main::executarAlgoritmos);
        funcionalidades.put("Visualizar tempos de execução", Main::visualizarTemposDeExecucao);
        funcionalidades.put("Apagar pastas", Main::apagarPastas);
        funcionalidades.put("Fechar programa", () -> continuar = false);
        do{
            escolherFuncionalidade(funcionalidades).run();
        }while(continuar);
        scanner.close();
    }

}
