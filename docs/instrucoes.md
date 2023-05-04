# Instruções de uso

- O Maven está configurado dentro da pasta streaming, portanto, é necessário abrir esta pasta para rodar o projeto 
- O projeto usa java 17, não irá funcionar em outras versões.
- É necessário verificar se o SDK do projeto está configurado com o Java 17
- Para verificar o passo a cima, basta ir na engrenagem no canto superior direito, Project Structure 
- Na aba Project, confirmar que o SDK está com Java 17
- Na aba SDKs, confirmar que está com o Java 17
- Rodar o lifecycle do Maven (clean compile)
- Não é necessário estar com o Maven baixado na máquiana, mas caso esteja, basta executar o comando "mvn clean compile"
- Caso nao esteja, no canto superior direito tem uma aba Maven, basta abrir, ir em lifecycle executar o clean, depois executar o compile
- Nós utilizamos o lombok para facilitar na escrita de algumas classes, portanto, não temos alguns construtores, nem setter, nem getter, pois o lombok faz isso de forma automatica
