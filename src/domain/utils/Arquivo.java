package domain.utils;

import domain.algoritimos.Algoritmo;
import domain.tipoEntrada.TipoEntrada;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static domain.utils.EnumUtils.PASTA_RAIZ;

public class Arquivo {

    private static void criarPastas(File arquivo){
        if (!arquivo.getParentFile().exists()) {
            if (arquivo.getParentFile().mkdirs()) {
                System.out.println("Pasta criada: " + arquivo.getParent());
            } else {
                System.err.println("Erro ao criar a pasta: " + arquivo.getParent());
                throw new RuntimeException("Não foi possível criar as pastas para os arquivos!");
            }
        }
    }

    public static void salvarArquivo(String caminhoDoArquivo, int[] conteudo) {
        File arquivo = new File(caminhoDoArquivo);
        criarPastas(arquivo);
        try (FileWriter escritor = new FileWriter(arquivo);
             BufferedWriter buffer = new BufferedWriter(escritor)) {

            buffer.write(String.valueOf(conteudo.length));
            buffer.newLine();
            for (int valor : conteudo) {
                buffer.write(String.valueOf(valor));
                buffer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public static void salvarArquivo(String tipo, Algoritmo algoritmo, Integer tamanho, TipoEntrada tipoEntrada, int[] conteudo){
        String caminhoArquivo = montarCaminhoArquivo(tipo, algoritmo, tamanho, tipoEntrada);
        salvarArquivo(caminhoArquivo, conteudo);
    }

    public static void salvarArquivo(String tipo, Algoritmo algoritmo, Integer tamanho, TipoEntrada tipoEntrada, String conteudo){
        String caminhoArquivo = montarCaminhoArquivo(tipo, algoritmo, tamanho, tipoEntrada);
        salvarArquivo(caminhoArquivo, conteudo);
    }
    private static String montarCaminhoArquivo(String tipo, Algoritmo algoritmo, Integer tamanho, TipoEntrada tipoEntrada){
        return String.format("%s/%s/Arquivos de %s/%s/%s%s%s.txt",
                PASTA_RAIZ,
                algoritmo.getNome(),
                tipo,
                tipoEntrada.getNome(),
                tipo,
                tipoEntrada.getNome(),
                tamanho);
    }

    public static void salvarArquivo(String caminhoDoArquivo, String conteudo) {
        File arquivo = new File(caminhoDoArquivo);
        criarPastas(arquivo);
        try (FileWriter escritor = new FileWriter(arquivo);
            BufferedWriter buffer = new BufferedWriter(escritor)) {
            buffer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public static void apagarDiretorio(String caminhoDoDiretorio) {
        Path diretorio = Paths.get(caminhoDoDiretorio);

        try {
            Files.walkFileTree(diretorio, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE,
                    new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            Files.delete(file);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                            Files.delete(dir);
                            System.out.printf("Pasta %s deletada com sucesso!\n", dir);
                            return FileVisitResult.CONTINUE;
                        }
                    });
        } catch (IOException e) {
            System.err.println("Erro ao apagar o diretório: " + e.getMessage());
        }
    }

    public static List<String> listarPastasCriadas(String caminhoDaPasta) {
        List<String> pastasEncontradas = new ArrayList<>();
        File pasta = new File(caminhoDaPasta);

        if (pasta.exists() && pasta.isDirectory()) {
            File[] subdiretorios = pasta.listFiles(File::isDirectory);

            if (subdiretorios != null) {
                for (File subdiretorio : subdiretorios) {
                    pastasEncontradas.add(subdiretorio.getName());
                }
            }
        }

        return pastasEncontradas;
    }

    public static String lerTempo(String tipo, Algoritmo algoritmo, Integer tamanho, TipoEntrada tipoEntrada) {
        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader(montarCaminhoArquivo(tipo, algoritmo, tamanho, tipoEntrada));
            bufferedReader = new BufferedReader(fileReader);
            return bufferedReader.readLine();
        } catch (IOException ignored) {
            return "(sem tempo)";
        }
    }



}
