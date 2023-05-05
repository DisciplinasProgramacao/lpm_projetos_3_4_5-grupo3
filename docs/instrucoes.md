# Instruções de uso

- As configuraçoes foram feitas usando a IDE Intellij
- O Maven está configurado dentro da pasta streaming, portanto, é necessário abrir esta pasta para rodar o projeto 
- O projeto usa java 17, não irá funcionar em outras versões.
- É necessário verificar se o SDK do projeto está configurado com o Java 17
- Para verificar o passo a cima, basta ir na engrenagem no canto superior direito, Project Structure 
- Na aba Project, confirmar que o SDK está com Java 17
- Na aba SDKs, confirmar que está com o Java 17
- Nós utilizamos o lombok para facilitar na escrita de algumas classes, portanto, não temos alguns construtores, nem setter, nem getter, pois o lombok faz isso de forma automatica
- Para rodar a aplicação, deve-se rodar o main da classe App, com as opçoes de 1 para fazer login, 0 para fazer logoff, 2 para cadastrar filme, 3 pra cadastrar serie, 4 para pesquisar filme ou serie, 5 para assistir a midia e cadastrar uma avaliacao
- Por questoes de erros de limpeza do Buffer na leitura de dados do teclado usando a classe Scanner, o projeto só ira funcionar com entradas de dados sem utilizar espaçamentos nas leituras
