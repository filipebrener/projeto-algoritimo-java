package domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {

    private void criarPastas(File arquivo){
        if (!arquivo.getParentFile().exists()) {
            if (arquivo.getParentFile().mkdirs()) {
                System.out.println("Pasta criada: " + arquivo.getParent());
            } else {
                System.err.println("Erro ao criar a pasta: " + arquivo.getParent());
                throw new RuntimeException("Não foi possível criar as pastas para os arquivos!");
            }
        }
    }

    public void salvarArquivo(String caminhoDoArquivo, int[] conteudo) {
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

//            System.out.println("Arquivo salvo com sucesso em: " + caminhoDoArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public void salvarArquivo(String caminhoDoArquivo, String conteudo) {
        File arquivo = new File(caminhoDoArquivo);
        criarPastas(arquivo);
        try (FileWriter escritor = new FileWriter(arquivo);
            BufferedWriter buffer = new BufferedWriter(escritor)) {
            buffer.write(conteudo);
//            System.out.println("Arquivo salvo com sucesso em: " + caminhoDoArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

}
