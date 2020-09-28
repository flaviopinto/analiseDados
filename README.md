# Análise Dados (Spring Boot + Docker)

## Descrição
Análise Dados é uma aplicação desenvolvida com Spring Boot e Maven que monitora os arquivos dentro de um diretório e processa as informações de Clientes, Vendedores e Vendas contidas nesses arquivos.

Exemplo Conteúdo do Arquivo
```
001ç1234567891234çPedroç50000
002ç2345675434544345çJose da SilvaçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
```
Obs: [ç][,][-] são separadores configuráveis na classe Constantes.java

## Executar o Projeto Local 

Executar a aplicação com uma IDE

### Pré-requisitos

* Java 8
* Maven
* git
* IDE (Eclipse, STS, IntelliJ, ...)

### Sem Docker:

1) Clonar o Projeto
    ```
    git clone https://github.com/flaviopinto/analiseDados.git
    ```
2) No Eclipse ou STS
    ```
    File -> Import -> Maven -> Existing Maven project
    ```
3) Após Importar o Projeto
    ```
    Raiz do Projeto -> Run As -> Spring Boot App 
    ```
4) Adicionar Arquivo
    ```
    Adicionar os arquivos que serão processados na pasta de entrada (Configurada na classe Constantes.java)
    ```

### Com Docker:

1) Clonar o Projeto
    ```
    git clone https://github.com/flaviopinto/analiseDados.git
    ```
2) Buildar o Projeto
    ```
    mvn clean
    mvn install
    ```
3) Criar e Executar um Container
    ```
    docker build -t analisedados .
    docker run --name testeapi analisedados 
    ```
4) Adicionar Arquivo (Em outro terminal)
    ```
    ##Adicionar arquivo da máquina local para o container (/home/data/in -> Configurado na classe Constantes.java)
    docker cp C:\meu_local_entrada\nome_arquivo.txt testeapi:/home/data/in 
    ##Copiar o arquivo processado pelo app para máquina local (/home/data/out -> Configurado na classe Constantes.java)
    docker cp testeapi:/home/data/out/nome_arquivo.txt C:\meu_local_saida 
    ```
    

## Principais Arquivos do Projeto

|Configurações Spring Boot | Classe Java  |
|--------------------------|---|
|Classe Main | [AnaliseDadosApplication](https://github.com/flaviopinto/analiseDados/blob/master/src/main/java/br/com/fp/AnaliseDadosApplication.java) |
|Configurações | [Constantes](https://github.com/flaviopinto/analiseDados/blob/master/src/main/java/br/com/fp/constantes/Constantes.java) |


