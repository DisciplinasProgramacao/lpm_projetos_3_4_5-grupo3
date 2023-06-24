import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlataformaStreaming {
    public static final String PLATAFORMA_NOME = "Plataforma Streaming";
    private String nome;
    private List<Serie> series;
    private List<Filme> filmes;
    private List<Cliente> clientes;
    private Cliente clienteAtual;
    private static PlataformaStreaming plataformaStreaming;
    private List<GeneroEnum> generos = new ArrayList<>();
    private List<IdiomaEnum> idiomas = new ArrayList<>();

    private PlataformaStreaming() {
    }

    /**
     * Inicia a plataforma de streaming, garantindo que apenas uma instância seja
     * criada.
     *
     * @return a instância única da plataforma de streaming
     */
    public static synchronized PlataformaStreaming iniciarPlataformaStreaming() {
        if (plataformaStreaming == null) {
            plataformaStreaming = new PlataformaStreaming();
        }
        return plataformaStreaming;
    }

    /**
     * Cadastra um novo cliente na plataforma.
     *
     * @param nomeUsuario o nome de usuário do cliente
     * @param senha       a senha do cliente
     */
    public void cadastrar(String nomeUsuario, String senha) {
        clientes.add(new Cliente(nomeUsuario, senha));
    }

    /**
     * Realiza o login de um cliente na plataforma.
     *
     * @param nomeUsuario o nome de usuário do cliente
     * @param senha       a senha do cliente
     * @return um Optional contendo o cliente logado, caso o login seja
     *         bem-sucedido, ou vazio caso contrário
     */
    public Optional<Cliente> login(String nomeUsuario, String senha) {
        var cliente = clientes.stream()
                .filter(usuario -> nomeUsuario.equals(usuario.getNomeDeUsuario()))
                .filter(usuario -> senha.equals(usuario.getSenha()))
                .findFirst();
        this.clienteAtual = cliente.orElse(null);
        return cliente;
    }

    /**
     * Realiza a assinatura do cliente atual como profissional.
     */
    public void realizarAssinatura() {
        clienteAtual.setClienteTipo(ClienteTipoEnum.PROFISSIONAL);
    }

    /**
     * Adiciona um cliente à plataforma.
     *
     * @param cliente o cliente a ser adicionado
     */
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Adiciona uma série à plataforma.
     *
     * @param serie a série a ser adicionada
     */
    public void adicionarSerie(Serie serie) {
        series.add(serie);
    }

    /**
     * Adiciona um filme à plataforma.
     *
     * @param filme o filme a ser adicionado
     */
    public void adicionarFilme(Filme filme) {
        filmes.add(filme);
    }

    /**
     * Filtra as séries da plataforma por gênero.
     *
     * @param genero o gênero a ser filtrado
     * @return uma lista de séries do gênero especificado
     */
    public List<Serie> filtrarSeriePorGenero(String genero) {
        return series.stream()
                .filter(g -> g.getGenero().nome().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }

    /**
     * Filtra as séries da plataforma por idioma.
     *
     * @param idioma o idioma a ser filtrado
     * @return uma lista de séries do idioma especificado
     */
    public List<Serie> filtrarSeriePorIdioma(String idioma) {
        return series.stream()
                .filter(s -> s.getIdioma().nome().equalsIgnoreCase(idioma))
                .collect(Collectors.toList());
    }

    /**
     * Filtra as séries da plataforma por quantidade de episódios.
     *
     * @param quantEpisodios a quantidade de episódios a ser filtrada
     * @return uma lista de séries com a quantidade de episódios especificada
     */
    public List<Serie> filtrarSeriePorQtdEpisodios(int quantEpisodios) {
        return series.stream()
                .filter(q -> q.getQuantidadeEpisodios() == quantEpisodios)
                .collect(Collectors.toList());
    }

    /**
     * Filtra os filmes da plataforma por gênero.
     *
     * @param genero o gênero a ser filtrado
     * @return uma lista de filmes do gênero especificado
     */
    public List<Filme> filtrarFilmePorGenero(String genero) {
        return filmes.stream()
                .filter(g -> g.getGenero().nome().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }

    /**
     * Filtra os filmes da plataforma por idioma.
     *
     * @param idioma o idioma a ser filtrado
     * @return uma lista de filmes do idioma especificado
     */
    public List<Filme> filtrarFilmePorIdioma(String idioma) {
        return filmes.stream()
                .filter(i -> i.getIdioma().nome().equalsIgnoreCase(idioma))
                .collect(Collectors.toList());
    }

    /**
     * Realiza o logoff do cliente atual.
     */
    public void logoff() {
        clienteAtual = null;
    }

    /**
     * Busca uma mídia (filme ou série) na plataforma pelo nome.
     *
     * @param nomeMidia o nome da mídia a ser buscada
     * @return a mídia encontrada, caso exista, ou null caso contrário
     */
    public Midia buscarMidia(String nomeMidia) {
        Optional<Filme> filme = buscarFilme(nomeMidia);
        if (filme.isPresent()) {
            return filme.get();
        }
        Optional<Serie> serie = buscarSerie(nomeMidia);
        return serie.orElse(null);
    }

    /**
     * Busca um filme na plataforma pelo nome.
     *
     * @param nomeFilme o nome do filme a ser buscado
     * @return um Optional contendo o filme encontrado, caso exista, ou vazio caso
     *         contrário
     */
    public Optional<Filme> buscarFilme(String nomeFilme) {
        return filmes.stream()
                .filter(midia -> midia.getNome().equals(nomeFilme))
                .findFirst();
    }

    /**
     * Busca uma série na plataforma pelo nome.
     *
     * @param nomeSerie o nome da série a ser buscada
     * @return um Optional contendo a série encontrada, caso exista, ou vazio caso
     *         contrário
     */
    public Optional<Serie> buscarSerie(String nomeSerie) {
        return series.stream()
                .filter(midia -> midia.getNome().equals(nomeSerie))
                .findFirst();
    }

    /**
     * Obtém o cliente que assistiu a maior quantidade de mídias.
     * Imprime o nome do cliente e a quantidade de mídias assistidas.
     */
    public Optional<Cliente> obterClienteComMaisMidiasAssistidas() {
        return clientes.stream()
                .max(Comparator.comparingInt(c -> c.getListaJaVistas().size()));
    }

    /**
     * Obtém o cliente que possui o maior número de avaliações.
     * Imprime o nome do cliente e a quantidade de mídias avaliadas.
     */
    public Optional<Cliente> obterClienteComMaisAvaliacoes() {
        return clientes.stream()
                .max(Comparator.comparingInt(Cliente::contarAvaliacoes));
    }

    /**
     * Calcula a porcentagem de clientes que possuem 15 ou mais avaliações em
     * relação ao total de clientes.
     * Imprime a porcentagem de clientes com mais de 15 avaliações.
     */
    public double porcentagemClientesQuinzeAvaliacoes() {
        long totalClientes = clientes.size();
        long clientesComAvaliacoes = clientes.stream()
                .filter(cliente -> cliente.contarAvaliacoes() >= 15)
                .count();

        return (double) clientesComAvaliacoes / totalClientes * 100;
    }

    /**
     * Obtém as 10 melhores mídias com base na avaliação, considerando apenas
     * aquelas que possuem pelo menos 100 avaliações.
     * Imprime o nome e a avaliação de cada mídia.
     */
    public List<Midia> melhoresMidias() {
        return clientes.stream()
                .flatMap(cliente -> cliente.getListaJaVistas().keySet().stream())
                .filter(midia -> midia.getAvaliacoes().size() >= 100)
                .distinct()
                .sorted(Comparator.comparingDouble(Midia::getAvaliacaoTotal).reversed())
                .limit(10)
                .toList();
    }

    /**
     * Imprime as 10 mídias com maior audiência.
     * Cada linha impressa contém o nome da mídia e sua audiência.
     */
    public List<Midia> midiasMaisVisualizadas() {
        return obterTop10MidiasPorAudiencia();
    }

    /**
     * Obtém as 10 mídias com maior audiência, combinando séries e filmes.
     *
     * @return A lista das 10 mídias com maior audiência.
     */
    public List<Midia> obterTop10MidiasPorAudiencia() {
        List<Midia> midias = new ArrayList<>();

        midias.addAll(series);
        midias.addAll(filmes);

        midias.sort(Comparator.comparing(Midia::getAudiencia).reversed());

        return midias.subList(0, Math.min(10, midias.size()));
    }

    /**
     * Obtém as 10 melhores mídias de um determinado gênero, com base na avaliação,
     * considerando apenas aquelas que possuem pelo menos 100 avaliações.
     * Imprime o nome e a avaliação de cada mídia.
     */
    public List<Midia> melhoresAvaliacoesPorGenero(GeneroEnum genero) {
        return clientes.stream()
                .flatMap(cliente -> cliente.getListaJaVistas().keySet().stream())
                .filter(midia -> midia.getAvaliacoes().size() >= 100 && genero.equals(midia.getGenero()))
                .distinct()
                .sorted(Comparator.comparingDouble(Midia::getAvaliacaoTotal).reversed())
                .limit(10)
                .toList();
    }

    /**
     * Obtém as 10 mídias com mais visualizações de um determinado gênero.
     * Imprime o nome da mídia e sua audiência.
     */
    public List<Midia> melhoresVisualizacoesPorGenero(GeneroEnum genero) {
        Map<Midia, Integer> visualizacoesPorMidia = clientes.stream()
                .flatMap(cliente -> cliente.getListaJaVistas().keySet().stream())
                .filter(midia -> midia.getGenero().equals(genero))
                .collect(Collectors.groupingBy(midia -> midia, Collectors.summingInt(Midia::getAudiencia)));

        return visualizacoesPorMidia.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .toList();

    }

    /**
     * Registra uma nova mídia na plataforma de streaming.
     *
     * @param midia A mídia a ser registrada.
     * @param linha A linha contendo os dados da mídia.
     */
    public void registrarMidia(Midia midia, String linha) {
        if (midia instanceof Serie) {
            Serie serie = new Serie();
            serie.setNome(getMidiaNome(linha));
            serie.setGenero(buildGenero());
            serie.setIdioma(buildIdioma());
            serie.setAudiencia(Integer.parseInt(getMidiaAudiencia(linha)));
            serie.setAvaliacoes(new ArrayList<>());
            serie.setAvaliacaoTotal(0.0);
            serie.setQuantidadeEpisodios((int) Math.floor(Math.random() * (50 + 1) + 1));
            plataformaStreaming.adicionarSerie(serie);
        }
        if (midia instanceof Filme) {
            Filme filme = new Filme();
            filme.setNome(getMidiaNome(linha));
            filme.setGenero(buildGenero());
            filme.setIdioma(buildIdioma());
            filme.setAudiencia(Integer.parseInt(getMidiaAudiencia(linha)));
            filme.setAvaliacoes(new ArrayList<>());
            filme.setAvaliacaoTotal(0.0);
            plataformaStreaming.adicionarFilme(filme);
        }
    }

    /**
     * Obtém o nome da mídia a partir da linha de dados.
     *
     * @param linha A linha contendo os dados da mídia.
     * @return O nome da mídia.
     */
    private String getMidiaNome(String linha) {
        String dados = linha.replaceAll(" ", "");
        return dados.split(";")[1];
    }

    /**
     * Obtém a audiência da mídia a partir da linha de dados.
     *
     * @param linha A linha contendo os dados da mídia.
     * @return A audiência da mídia.
     */
    private String getMidiaAudiencia(String linha) {
        String dados = linha.replaceAll(" ", "");
        return dados.split(";")[3];
    }

    /**
     * Constrói um idioma aleatório da lista de idiomas disponíveis.
     *
     * @return um idioma aleatório
     */
    public IdiomaEnum buildIdioma() {
        int size = idiomas.size();
        Random random = new Random();
        int randomInt = random.nextInt(size - 1 + 1);
        return idiomas.get(randomInt);
    }

    /**
     * Constrói um gênero aleatório da lista de gêneros disponíveis.
     *
     * @return um gênero aleatório
     */
    public GeneroEnum buildGenero() {
        int size = generos.size();
        Random random = new Random();
        int randomInt = random.nextInt(size - 1 + 1);
        return generos.get(randomInt);
    }

    /**
     * Inicializa a lista de gêneros e idiomas disponíveis.
     */
    public void inicializarGenerosIdiomas() {
        generos.add(GeneroEnum.ACAO);
        generos.add(GeneroEnum.ANIME);
        generos.add(GeneroEnum.AVENTURA);
        generos.add(GeneroEnum.COMEDIA);
        generos.add(GeneroEnum.DRAMA);
        generos.add(GeneroEnum.POLICIAL);
        generos.add(GeneroEnum.ROMANCE);
        idiomas.add(IdiomaEnum.PORTUGUES);
        idiomas.add(IdiomaEnum.ESPANHOL);
        idiomas.add(IdiomaEnum.INGLES);
    }

    /**
     * Valida se um idioma é válido.
     *
     * @param idioma o idioma a ser validado
     * @return true se o idioma é válido, caso contrário, false
     */
    public boolean validarIdioma(String idioma) {
        var midia = idiomas.stream()
                .filter(i -> idioma.equalsIgnoreCase(i.nome()))
                .findFirst();
        return midia.isPresent();
    }

    /**
     * Valida se um gênero é válido.
     *
     * @param genero o gênero a ser validado
     * @return true se o gênero é válido, caso contrário, false
     */
    public boolean validarGenero(String genero) {
        var midia = generos.stream()
                .filter(g -> genero.equalsIgnoreCase(g.nome()))
                .findFirst();
        return midia.isPresent();
    }

}
