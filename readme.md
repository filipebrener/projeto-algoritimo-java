# Guia para Executar Código Java

Este guia irá ensinar como executar um código Java em sua máquina. Certifique-se de ter o Java instalado antes de prosseguir.

## Pré-requisitos

- Java deve estar instalado em sua máquina. Você pode verificar a instalação executando o comando `java -version` no seu terminal. Se não estiver instalado, faça o download e a instalação a partir do [site oficial do Java](https://www.oracle.com/java/technologies/javase-downloads.html) se estiver no windows, se estiver no linux indico usar o Sdkman ou ASDF para realizar a instalação.

## Executar o Programa

1. Abra o terminal.

2. Navegue até o diretório do projeto onde seu código Java está localizado. Suponha que o seu código esteja em `/caminho/para/o/projeto`.


5. Agora você pode executar o programa Java usando o comando `java Main.class`. Isso executará o programa Java e mostrará a saída no terminal.

6. Para evitar problemas com a execução de algorítimos que usam bastante memória como QuickSort, vamos usar o argumento `-Xssm128m` que vai definir o tamanho da stack do processo, então para executar o programa o comando completo é `java -Xssm128 Main.class`


## Caso o programa não esteja compilado

3. Compile o programa usando o comando `javac -d ./out/production/projeto_algoritmo Main.java`. Certifique-se de estar dentro do diretório `/caminho/para/o/projeto/src` ao executar este comando. Isso irá compilar o código Java e gerar arquivos de classe na pasta `out/production/projeto_algoritmo`.

4. Após a compilação bem-sucedida, navegue até o diretório onde as classes foram geradas com o comando `cd ./out/production/projeto_algoritmo`.

Lembre-se de substituir `/caminho/para/o/projeto` pelo caminho real para o seu projeto.

Isso é tudo! Você agora sabe como compilar e executar um programa Java em sua máquina.
