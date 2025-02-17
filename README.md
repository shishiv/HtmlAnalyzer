# HtmlAnalyzer

HtmlAnalyzer é uma aplicação Java que busca o conteúdo HTML de uma URL fornecida, analisa a estrutura HTML e extrai o nó de texto mais profundo.

## Uso

Para executar o HtmlAnalyzer, você precisa fornecer uma URL como argumento. A aplicação buscará o conteúdo HTML da URL fornecida e o analisará.

### Exemplo

```sh
java -cp /path/to/HtmlAnalyzer.jar HtmlAnalyzer <url>
```

Substitua `<url>` pela URL que você deseja analisar.

### Exemplo de Saída

Se uma URL não for fornecida, a seguinte mensagem será exibida:

```sh
Please provide a URL as argument
```

Se uma URL for fornecida e o conteúdo HTML for buscado e analisado com sucesso, o nó de texto mais profundo será exibido no console.

### Tratamento de Erros

Se houver um erro ao conectar à URL, a seguinte mensagem será exibida:

```sh
URL connection error
```

Se o conteúdo HTML estiver malformado, a seguinte mensagem será exibida:

```sh
malformed HTML
```

## Compilação

Para compilar o HtmlAnalyzer, use o seguinte comando:

```bash
javac HtmlAnalyzer.java
```

## Execução

Para executar o HtmlAnalyzer, use o seguinte comando:

```bash
java HtmlAnalyzer <url>
```

Substitua `<url>` pela URL que você deseja analisar.

## Informações Adicionais

O desafio consiste em analisar um código HTML seguindo algumas premissas específicas e desenvolver um programa em Java, utilizando apenas o JDK 17, sem bibliotecas externas ou classes nativas relacionadas à manipulação de HTML/XML/DOM. Também há um critério bônus para identificar HTML malformado.

Se possível, gostaria muito da sua ajuda para entender melhor a melhor abordagem para resolver o problema. Para facilitar, posso lhe enviar os arquivos de teste e o código-fonte para compilação.

Agradeço desde já pela atenção e qualquer orientação que puder me passar!

Abraços,  
Myke

## Licença

Este projeto está licenciado sob a Licença MIT.
