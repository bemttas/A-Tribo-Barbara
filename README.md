# README

Este repositório contém três arquivos Java relacionados a um sistema de gerenciamento de guerreiros e terras. Abaixo está uma descrição detalhada de cada arquivo e suas funcionalidades:

## 1. Main.java

Este arquivo contém a classe `Main` com um método `main` que é o ponto de entrada do programa. No método `main`, um objeto da classe `App` é criado, passando o caminho de um arquivo de texto como argumento. Em seguida, são chamados os métodos `geraDot()` e `resultado()` do objeto `App`. O método `geraDot()` gera uma representação em formato DOT da árvore de guerreiros e suas conexões, enquanto o método `resultado()` retorna algumas estatísticas sobre a árvore de guerreiros, como o número total de guerreiros e o nome e quantidade de terras do maior dono de terras na última geração.

## 2. Guerreiro.java

Este arquivo contém a definição da classe `Guerreiro`, que representa um guerreiro no sistema de gerenciamento. A classe possui atributos como nome, quantidade de terras conquistadas, geração, pai e uma lista de filhos. Além disso, a classe possui métodos para adicionar filhos, transferir terras entre os guerreiros, encontrar guerreiros pelo nome, gerar representações em formato de string e gerar representações em formato de grafo usando a linguagem DOT.

## 3. App.java

Este arquivo contém a classe `App`, que é responsável por ler um arquivo de texto contendo informações sobre guerreiros e terras, criar uma árvore de guerreiros com base nessas informações e fornecer funcionalidades relacionadas à manipulação e análise dessa árvore. A classe possui métodos para ler o arquivo de texto, criar a árvore de guerreiros, identificar o maior dono de terras na última geração, gerar uma representação em formato DOT da árvore, escrever essa representação em um arquivo DOT e fornecer informações estatísticas sobre a árvore, como o número total de guerreiros e os dados do maior dono de terras na última geração.

## Como executar

1. Certifique-se de ter o JDK (Java Development Kit) instalado no seu sistema.
2. Abra um terminal ou prompt de comando na pasta onde os arquivos Java estão localizados.
3. Compile os arquivos Java digitando o seguinte comando: `javac Main.java Guerreiro.java App.java`
4. Execute o programa digitando o seguinte comando: `java Main`

Isso irá executar o programa, processar o arquivo de texto fornecido, gerar uma representação em formato DOT da árvore de guerreiros e exibir informações estatísticas sobre a árvore no console.

