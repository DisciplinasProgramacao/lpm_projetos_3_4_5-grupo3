import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class App {
	private static final Scanner ler = new Scanner(System.in);
	private static final List<GeneroEnum> generos = new ArrayList<>();
	private static final List<IdiomaEnum> idiomas = new ArrayList<>();

	private static final PlataformaStreaming plataformaStreaming = PlataformaStreaming.iniciarPlataformaStreaming();

	public static void main(String[] args) {
		inicializarGenerosIdiomas();
		int x;

		do {
			if (isNull(plataformaStreaming.getClienteAtual())) {
				System.out.println("1 - Fazer cadastro");
				System.out.println("2 - LogIn");
				System.out.println("100 - Sair");
				System.out.println("Informe um numero:");
				x = ler.nextInt();
			} else {
				System.out.println("3 - Cadastrar filme");
				System.out.println("4 - Cadastrar serie");
				System.out.println("5 - Buscar Midia");
				System.out.println("6 - Buscar Filme");
				System.out.println("7 - Buscar Serie");
				System.out.println("8 - Assistir midia");
				System.out.println("9 - Assistir filme");
				System.out.println("10 - Assistir serie");
				System.out.println("11 - Filtrar serie por genero");
				System.out.println("12 - Filtrar serie por idioma");
				System.out.println("13 - Filtrar serie por qtd de episodios");
				System.out.println("14 - Filtrar filme por genero");
				System.out.println("15 - Filtrar filme por idioma");
				System.out.println("16 - Cadastrar filme pelo arquivo");
				System.out.println("17 - Cadastrar serie pelo arquivo");
				System.out.println("18 - Salvar dados em Json");
				System.out.println("19 - Obter cliente que mais assistiu midias");
				System.out.println("20 - Obter cliente que mais tem avaliacoes");
				System.out.println("21 - Porcentagem de clientes com mais de 15 avaliacoes");
				System.out.println("22 - 10 midias de melhor avaliacao com pelo menos 100 avaliacoes");
				System.out.println("23 - 10 mídias com mais visualizações");
				System.out.println("24 - 10 midias de melhor avaliacao com pelo menos 100 avaliacoes, por genero");
				System.out.println("25 - 10 mídias com mais visualizações, por genero");
				System.out.println("26 - Realizar assinatura para cliente Profissional");
				System.out.println("0 - LogOff");
				System.out.println("100 - Sair");
				System.out.println("Informe um numero:");
				x = ler.nextInt();
			}

			// OBS: Essa nomeclatura do switch case só funciona no Java 17
			switch (x) {
				case 0 -> logoff();
				case 1 -> cadastrar();
				case 2 -> login();
				case 3 -> registrarFilme();
				case 4 -> registrarSerie();
				case 5 -> buscarMidia();
				case 6 -> buscarFilme();
				case 7 -> buscarSerie();
				case 8 -> assistirMidia();
				case 9 -> assistirFilme();
				case 10 -> assistirSerie();
				case 11 -> filtrarSerieGenero();
				case 12 -> filtrarSerieIdioma();
				case 13 -> filtrarSerieQtdEpisodio();
				case 14 -> filtrarFilmeGenero();
				case 15 -> filtrarFilmeIdioma();
				case 16 -> cadastrarFilmeArquivo();
				case 17 -> cadastrarSerieArquivo();
				case 18 -> salvarDadosEmJson();
				case 19 -> obterClienteComMaisMidiasAssistidas();
				case 20 -> obterClienteComMaisAvaliacoes();
				case 21 -> porcentagemClientesQuinzeAvaliacoes();
				case 22 -> melhoresMidias();
				case 23 -> midiasMaisVisualizadas();
				case 24 -> melhoresAvaliacoesPorGenero();
				case 25 -> melhoresVisualizacoesPorGenero();
				case 26 -> realizarAssinatura();
			}
		} while (x != 100);
		salvarDadosEmJson();
		ler.close();
	}

	/**
	 * Realiza a assinatura do cliente na plataforma de streaming.
	 * Utiliza a instância da classe PlataformaStreaming para realizar a assinatura.
	 */
	private static void realizarAssinatura() {
		plataformaStreaming.realizarAssinatura();
	}

	/**
	 * Cadastra um filme a partir de um arquivo na plataforma de streaming.
	 * Cria uma instância da classe Filme e lê os dados do arquivo especificado.
	 */
	private static void cadastrarFilmeArquivo() {
		Filme filme = new Filme();
		lerArquivo(filme, "src/main/data/POO_Filmes.csv");
	}

	/**
	 * Cadastra uma série a partir de um arquivo na plataforma de streaming.
	 * Cria uma instância da classe Serie e lê os dados do arquivo especificado.
	 */
	private static void cadastrarSerieArquivo() {
		Serie serie = new Serie();
		lerArquivo(serie, "src/main/data/POO_Series.csv");
	}


	/**
	 * Lê um arquivo e registra as mídias encontradas.
	 *
	 * @param midia       Objeto do tipo Midia usado para registrar as mídias encontradas.
	 * @param nomeArquivo O nome do arquivo a ser lido.
	 */
	private static void lerArquivo(Midia midia, String nomeArquivo) {
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(nomeArquivo));

			String linha = buffRead.readLine();

			while (linha != null) {
				linha = buffRead.readLine();
				if (nonNull(linha)) {
					registrarMidia(midia, linha);
				}
			}
			buffRead.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}
	}

	/**
	 * Obtém o cliente que assistiu a maior quantidade de mídias.
	 * Imprime o nome do cliente e a quantidade de mídias assistidas.
	 */
	public static void obterClienteComMaisMidiasAssistidas() {
		var cliente = plataformaStreaming.getClientes().stream()
				.max(Comparator.comparingInt(c -> c.getListaJaVistas().size()));

		cliente.ifPresent(value -> System.out.println("Cliente que mais assistiu midia foi: " +
				value.getNomeDeUsuario() + " com " + cliente.get().getListaJaVistas().size() + " midias"));
	}

	/**
	 * Obtém o cliente que possui o maior número de avaliações.
	 * Imprime o nome do cliente e a quantidade de mídias avaliadas.
	 */
	public static void obterClienteComMaisAvaliacoes() {
		var cliente = plataformaStreaming.getClientes().stream()
				.max(Comparator.comparingInt(App::contarAvaliacoes));

		cliente.ifPresent(value -> System.out.println("Cliente que possui mais avaliacoes: " +
				value.getNomeDeUsuario() + " com " + cliente.get().getListaJaVistas().size() + " midias avaliadas"));
	}

	/**
	 * Calcula a porcentagem de clientes que possuem 15 ou mais avaliações em relação ao total de clientes.
	 * Imprime a porcentagem de clientes com mais de 15 avaliações.
	 */
	public static void porcentagemClientesQuinzeAvaliacoes() {
		long totalClientes = plataformaStreaming.getClientes().size();
		long clientesComAvaliacoes = plataformaStreaming.getClientes().stream()
				.filter(cliente -> contarAvaliacoes(cliente) >= 15)
				.count();

		var porcentagem = (double) clientesComAvaliacoes / totalClientes * 100;
		System.out.println("A porcentagem de clientes com mais de 15 avaliacoes eh: " + porcentagem + "%");
	}

	/**
	 * Conta o número total de avaliações de um cliente.
	 *
	 * @param cliente O objeto Cliente para o qual serão contadas as avaliações.
	 * @return O número total de avaliações do cliente.
	 */
	private static int contarAvaliacoes(Cliente cliente) {
		return cliente.getListaJaVistas().keySet().stream()
				.mapToInt(midia -> midia.getAvaliacoes().size())
				.sum();
	}


	/**
	 * Obtém as 10 melhores mídias com base na avaliação, considerando apenas aquelas que possuem pelo menos 100 avaliações.
	 * Imprime o nome e a avaliação de cada mídia.
	 */
	public static void melhoresMidias() {
		var melhoresMidias = plataformaStreaming.getClientes().stream()
				.flatMap(cliente -> cliente.getListaJaVistas().keySet().stream())
				.filter(midia -> midia.getAvaliacoes().size() >= 100)
				.distinct()
				.sorted(Comparator.comparingDouble(Midia::getAvaliacaoTotal).reversed())
				.limit(10)
				.toList();

		System.out.println("As 10 mídias de melhor avaliação, com pelo menos 100 avaliações: ");

		melhoresMidias.stream()
				.map(midia -> midia.getNome() + " - Avaliação: " + midia.getAvaliacaoTotal())
				.forEach(System.out::println);
	}

	/**
	 * Imprime as 10 mídias com maior audiência.
	 * Cada linha impressa contém o nome da mídia e sua audiência.
	 */
	public static void midiasMaisVisualizadas() {
		var midiasPorAudiencia = obterTop10MidiasPorAudiencia();

		midiasPorAudiencia.stream()
				.map(midia -> midia.getNome() + " - Audiencia: " + midia.getAudiencia())
				.forEach(System.out::println);
	}

	/**
	 * Obtém as 10 mídias com maior audiência, combinando séries e filmes.
	 *
	 * @return A lista das 10 mídias com maior audiência.
	 */
	public static List<Midia> obterTop10MidiasPorAudiencia() {
		List<Midia> midias = new ArrayList<>();

		midias.addAll(plataformaStreaming.getSeries());
		midias.addAll(plataformaStreaming.getFilmes());

		midias.sort(Comparator.comparing(Midia::getAudiencia).reversed());

		return midias.subList(0, Math.min(10, midias.size()));
	}

	/**
	 * Obtém as 10 melhores mídias de um determinado gênero, com base na avaliação,
	 * considerando apenas aquelas que possuem pelo menos 100 avaliações.
	 * Imprime o nome e a avaliação de cada mídia.
	 */
	public static void melhoresAvaliacoesPorGenero() {
		System.out.println("Escolha um genero: ");
		String generoDigitado = ler.next();

		try {
			GeneroEnum genero = GeneroEnum.valueOf(generoDigitado);

			List<Midia> melhoresAvaliacoes = plataformaStreaming.getClientes().stream()
					.flatMap(cliente -> cliente.getListaJaVistas().keySet().stream())
					.filter(midia -> midia.getAvaliacoes().size() >= 100 && genero.equals(midia.getGenero()))
					.distinct()
					.sorted(Comparator.comparingDouble(Midia::getAvaliacaoTotal).reversed())
					.limit(10)
					.toList();

			System.out.println("As 10 mídias de melhor avaliação, com pelo menos 100 avaliações, do gênero " + genero.nome() + ":");
			melhoresAvaliacoes.stream()
					.map(midia -> midia.getNome() + " - Avaliação: " + midia.getAvaliacaoTotal())
					.forEach(System.out::println);

		} catch (IllegalArgumentException e) {
			System.out.println("Gênero inválido.");
		}
	}

	/**
	 * Obtém as 10 mídias com mais visualizações de um determinado gênero.
	 * Imprime o nome da mídia e sua audiência.
	 */
	private static void melhoresVisualizacoesPorGenero() {
		System.out.println("Escolha um genero: ");
		String generoDigitado = ler.next();

		try {
			GeneroEnum genero = GeneroEnum.valueOf(generoDigitado);

			Map<Midia, Integer> visualizacoesPorMidia = plataformaStreaming.getClientes().stream()
					.flatMap(cliente -> cliente.getListaJaVistas().keySet().stream())
					.filter(midia -> midia.getGenero().equals(genero))
					.collect(Collectors.groupingBy(midia -> midia, Collectors.summingInt(Midia::getAudiencia)));

			List<Midia> top10MidiasMaisVisualizadas = visualizacoesPorMidia.entrySet().stream()
					.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					.limit(10)
					.map(Map.Entry::getKey)
					.toList();

			System.out.println("As 10 mídias com mais visualizações, do gênero " + genero.nome() + ":");
			top10MidiasMaisVisualizadas.stream()
					.map(midia -> midia.getNome() + " - Audiencia: " + midia.getAudiencia())
					.forEach(System.out::println);

		} catch (IllegalArgumentException e) {
			System.out.println("Gênero inválido.");
		}
	}

	/**
	 * Salva os dados da plataforma em um arquivo JSON.
	 */
	public static void salvarDadosEmJson() {
		try {
			Gson gson = new Gson();
			FileWriter fileWriter = new FileWriter("src/main/data/plataforma.json");
			gson.toJson(plataformaStreaming, fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Registra uma nova mídia na plataforma de streaming.
	 *
	 * @param midia A mídia a ser registrada.
	 * @param linha A linha contendo os dados da mídia.
	 */
	private static void registrarMidia(Midia midia, String linha) {
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
	private static String getMidiaNome(String linha) {
		String dados = linha.replaceAll(" ", "");
		return dados.split(";")[1];
	}

	/**
	 * Obtém a audiência da mídia a partir da linha de dados.
	 *
	 * @param linha A linha contendo os dados da mídia.
	 * @return A audiência da mídia.
	 */
	private static String getMidiaAudiencia(String linha) {
		String dados = linha.replaceAll(" ", "");
		return dados.split(";")[3];
	}

	/**
	 * Realiza o cadastro de um usuário na plataforma de streaming.
	 */
	private static void cadastrar() {
		System.out.println("Informe seu nome de usuario:");
		String usuario = ler.next();

		System.out.println("Informe sua senha:");
		String senha = ler.next();

		plataformaStreaming.cadastrar(usuario, senha);
		System.out.println("Cadastro realizado com sucesso!");
	}


	/**
	 * Realiza o login do usuário na plataforma de streaming.
	 * Verifica se o usuário e senha fornecidos são válidos.
	 * Exibe uma mensagem de sucesso ou falha de login.
	 */
	private static void login() {
		System.out.println("Informe seu usuario:");
		String usuario = ler.next();

		System.out.println("Informe sua senha:");
		String senha = ler.next();

		var clienteLogado = plataformaStreaming.login(usuario, senha);

		if (clienteLogado.isPresent()) {
			System.out.println("Login realizado com sucesso!");
		} else {
			System.out.println("Usuário ou senha inválidos!");
		}
	}

	/**
	 * Realiza o logoff do usuário atualmente logado na plataforma de streaming.
	 */
	private static void logoff() {
		plataformaStreaming.logoff();
	}

	/**
	 * Permite ao usuário assistir a uma mídia específica.
	 * Solicita o nome da mídia a ser assistida e verifica se ela está disponível.
	 * Chama o método "assistir" passando a mídia como parâmetro.
	 */
	private static void assistirMidia() {
		System.out.println("Informe o nome da midia que voce deseja assistir:");
		String nome = ler.next();

		Midia midia = plataformaStreaming.buscarMidia(nome);
		if (nonNull(midia)) {
			assistir(midia);
		} else {
			System.out.println("Midia procurada não encontrada.");
		}
	}

	/**
	 * Permite ao usuário assistir a um filme específico.
	 * Solicita o nome do filme a ser assistido e verifica se ele está disponível.
	 * Chama o método "assistir" passando o filme como parâmetro.
	 */
	private static void assistirFilme() {
		System.out.println("Informe o nome do filme que voce deseja assistir:");
		String nome = ler.next();

		var filme = plataformaStreaming.buscarFilme(nome);
		if (filme.isPresent()) {
			assistir(filme.get());
		} else {
			System.out.println("Filme procurado não encontrado.");
		}
	}

	/**
	 * Permite ao usuário assistir a uma série específica.
	 * Solicita o nome da série a ser assistida e verifica se ela está disponível.
	 * Chama o método "assistir" passando a série como parâmetro.
	 */
	private static void assistirSerie() {
		System.out.println("Informe o nome da serie que voce deseja assistir:");
		String nome = ler.next();

		var serie = plataformaStreaming.buscarSerie(nome);
		if (serie.isPresent()) {
			assistir(serie.get());
		} else {
			System.out.println("Serie procurada não encontrado.");
		}
	}

	/**
	 * Realiza a ação de assistir a uma mídia específica.
	 * Verifica se o usuário tem permissão para assistir à mídia, de acordo com seu tipo.
	 * Registra a audiência da mídia e adiciona a mídia à lista de já vistas do usuário.
	 * Se o usuário ainda não avaliou a mídia, solicita a nota e, opcionalmente, um comentário.
	 * Registra a avaliação da mídia pelo usuário.
	 *
	 * @param midia A mídia a ser assistida.
	 */
	private static void assistir(Midia midia) {
		if(midia.isLancamento() && !ClienteTipoEnum.PROFISSIONAL.equals(plataformaStreaming.getClienteAtual().getClienteTipo())){
			System.out.println("Você não tem uma assinatura para assistir filmes em lançamento.");
			return;
		}
		System.out.println("Assistindo...");
		midia.registrarAudiencia();
		plataformaStreaming.getClienteAtual().adicionarNaListaJaVista(midia);
		plataformaStreaming.getClienteAtual().retirarDaListaParaVer(midia.getNome());

		if(!existeAvaliacao(midia)){
			System.out.println("Informe a nota do filme: (1 a 5) ");
			int nota = ler.nextInt();

			String comentario = "";
			if (ClienteTipoEnum.ESPECIALISTA.equals(plataformaStreaming.getClienteAtual().getClienteTipo()) ||
					ClienteTipoEnum.PROFISSIONAL.equals(plataformaStreaming.getClienteAtual().getClienteTipo())) {
				System.out.println("Informe um comnetario: ");
				comentario = ler.next();
			}
			Avaliacao avaliacao = new Avaliacao(plataformaStreaming.getClienteAtual(), nota, comentario);
			midia.registrarAvaliacao(avaliacao);
		} else {
			System.out.println("Você já registrou uma avaliação para essa mídia! ");
		}
	}


	/**
	 * Verifica se existe uma avaliação feita pelo cliente atual para a mídia especificada.
	 *
	 * @param midia A mídia a ser verificada.
	 * @return {@code true} se o cliente atual já avaliou a mídia, {@code false} caso contrário.
	 */
	private static boolean existeAvaliacao(Midia midia) {
		return midia.getAvaliacoes().stream()
				.anyMatch(a -> a.getCliente().equals(plataformaStreaming.getClienteAtual()));
	}

	/**
	 * Filtra as séries pelo gênero informado pelo usuário e exibe os nomes das séries correspondentes.
	 */
	private static void filtrarSerieGenero() {
		System.out.println("Informe o genero da serie:");
		String genero = ler.next();

		if (validarGenero(genero)) {
			var series = plataformaStreaming.filtrarSeriePorGenero(genero);

			System.out.println("Series com o genero pesquisado: ");

			series.stream()
					.map(Serie::getNome)
					.forEach(System.out::println);
		} else {
			System.out.println("Genero informado inexistente");
		}
	}

	/**
	 * Filtra as séries pelo idioma informado pelo usuário e exibe os nomes das séries correspondentes.
	 */
	private static void filtrarSerieIdioma() {
		System.out.println("Informe o idioma da serie:");
		String idioma = ler.next();

		if (validarIdioma(idioma)) {
			var series = plataformaStreaming.filtrarSeriePorIdioma(idioma);

			System.out.println("Series com o idioma pesquisado: ");

			series.stream()
					.map(Serie::getNome)
					.forEach(System.out::println);
		} else {
			System.out.println("Idioma informado inexistente");
		}
	}

	/**
	 * Filtra as séries pela quantidade de episódios informada pelo usuário e exibe os nomes das séries correspondentes.
	 */
	private static void filtrarSerieQtdEpisodio() {
		System.out.println("Informe a quantidade de episodios da serie:");
		int episodios = ler.nextInt();

		var series = plataformaStreaming.filtrarSeriePorQtdEpisodios(episodios);

		if (series.isEmpty()) {
			System.out.println("Idioma informado inexistente");
		} else {
			System.out.println("Series com a quantidade de episodios pesquisado: ");

			series.stream()
					.map(Serie::getNome)
					.forEach(System.out::println);
		}
	}

	/**
	 * Filtra os filmes pelo gênero informado pelo usuário e exibe os nomes dos filmes correspondentes.
	 */
	private static void filtrarFilmeGenero() {
		System.out.println("Informe o genero do filme:");
		String genero = ler.next();

		if (validarGenero(genero)) {
			var filmes = plataformaStreaming.filtrarFilmePorGenero(genero);

			System.out.println("Filmes com o genero pesquisado: ");

			filmes.stream()
					.map(Filme::getNome)
					.forEach(System.out::println);
		} else {
			System.out.println("Genero informado inexistente");
		}
	}

	/**
	 * Filtra os filmes pelo idioma informado pelo usuário e exibe os nomes dos filmes correspondentes.
	 */
	private static void filtrarFilmeIdioma() {
		System.out.println("Informe o idioma do filme:");
		String idioma = ler.next();

		if (validarIdioma(idioma)) {
			var filmes = plataformaStreaming.filtrarFilmePorIdioma(idioma);

			System.out.println("Filmes com o idioma pesquisado: ");

			filmes.stream()
					.map(Filme::getNome)
					.forEach(System.out::println);
		} else {
			System.out.println("Idioma informado inexistente");
		}
	}

	/**
	 * Registra um novo filme na plataforma.
	 */
	private static void registrarFilme() {
		System.out.println("Informe o nome do filme:");
		String nome = ler.next();

		Filme filme = new Filme(nome);
		filme.setGenero(buildGenero());
		filme.setIdioma(buildIdioma());
		filme.setAudiencia(0);
		filme.setAvaliacoes(new ArrayList<>());
		filme.setAvaliacaoTotal(0.0);

		plataformaStreaming.adicionarFilme(filme);
	}

	/**
	 * Registra uma nova série na plataforma.
	 */
	private static void registrarSerie() {
		System.out.println("Informe o nome da Serie:");
		String nome = ler.next();

		Serie serie = new Serie(nome);
		serie.setGenero(buildGenero());
		serie.setIdioma(buildIdioma());
		serie.setAudiencia(0);
		serie.setAvaliacoes(new ArrayList<>());
		serie.setAvaliacaoTotal(0.0);
		serie.setQuantidadeEpisodios((int) Math.floor(Math.random() * (50 + 1) + 1));

		plataformaStreaming.adicionarSerie(serie);
	}

	/**
	 * Busca uma mídia pelo nome informado pelo usuário e exibe suas informações.
	 */
	private static void buscarMidia() {
		System.out.println("Informe o nome da midia que deseja buscar:");
		String nome = ler.next();

		Midia midia = plataformaStreaming.buscarMidia(nome);

		if (nonNull(midia)) {
			System.out.println("A midia que voce pesquisou se chama: " + midia.getNome());
			System.out.println("Genero: " + midia.getGenero());
			System.out.println("Idioma: " + midia.getIdioma());
			if (midia instanceof Serie) {
				System.out.println("Episodios: " + ((Serie) midia).getQuantidadeEpisodios());
			}
			System.out.println("Nota: " + (nonNull(midia.getAvaliacaoTotal()) ? midia.getAvaliacaoTotal() : 0));
		} else {
			System.out.println("Midia nao cadastrado com esse nome");
		}
	}

	/**
	 * Busca um filme na plataforma de streaming pelo nome.
	 */
	private static void buscarFilme() {
		System.out.println("Informe o nome do filme que deseja buscar:");
		String nome = ler.next();

		var filme = plataformaStreaming.buscarFilme(nome);

		if (filme.isPresent()) {
			System.out.println("A midia que voce pesquisou se chama: " + filme.get().getNome());
			System.out.println("Genero: " + filme.get().getGenero());
			System.out.println("Idioma: " + filme.get().getIdioma());
			System.out.println(
					"Nota: " + (nonNull(filme.get().getAvaliacaoTotal()) ? filme.get().getAvaliacaoTotal() : 0));
		} else {
			System.out.println("Filme nao cadastrado com esse nome");
		}
	}

	/**
	 * Busca uma série na plataforma de streaming pelo nome.
	 */
	private static void buscarSerie() {
		System.out.println("Informe o nome da serie que deseja buscar:");
		String nome = ler.next();

		var serie = plataformaStreaming.buscarSerie(nome);

		if (serie.isPresent()) {
			System.out.println("A serie que voce pesquisou se chama: " + serie.get().getNome());
			System.out.println("Genero: " + serie.get().getGenero());
			System.out.println("Idioma: " + serie.get().getIdioma());
			System.out.println(
					"Nota: " + (nonNull(serie.get().getAvaliacaoTotal()) ? serie.get().getAvaliacaoTotal() : 0));
			System.out.println("Episodios: " + serie.get().getQuantidadeEpisodios());
		} else {
			System.out.println("Serie nao cadastrado com esse nome");
		}
	}

	/**
	 * Valida se um gênero é válido.
	 *
	 * @param genero o gênero a ser validado
	 * @return true se o gênero é válido, caso contrário, false
	 */
	private static boolean validarGenero(String genero) {
		var midia = generos.stream()
				.filter(g -> genero.equalsIgnoreCase(g.nome()))
				.findFirst();
		return midia.isPresent();
	}

	/**
	 * Valida se um idioma é válido.
	 *
	 * @param idioma o idioma a ser validado
	 * @return true se o idioma é válido, caso contrário, false
	 */
	private static boolean validarIdioma(String idioma) {
		var midia = idiomas.stream()
				.filter(i -> idioma.equalsIgnoreCase(i.nome()))
				.findFirst();
		return midia.isPresent();
	}

	/**
	 * Constrói um idioma aleatório da lista de idiomas disponíveis.
	 *
	 * @return um idioma aleatório
	 */
	private static IdiomaEnum buildIdioma() {
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
	private static GeneroEnum buildGenero() {
		int size = generos.size();
		Random random = new Random();
		int randomInt = random.nextInt(size - 1 + 1);
		return generos.get(randomInt);
	}

	/**
	 * Inicializa a lista de gêneros e idiomas disponíveis.
	 */
	private static void inicializarGenerosIdiomas() {
		App.generos.add(GeneroEnum.ACAO);
		App.generos.add(GeneroEnum.ANIME);
		App.generos.add(GeneroEnum.AVENTURA);
		App.generos.add(GeneroEnum.COMEDIA);
		App.generos.add(GeneroEnum.DRAMA);
		App.generos.add(GeneroEnum.POLICIAL);
		App.generos.add(GeneroEnum.ROMANCE);
		App.idiomas.add(IdiomaEnum.PORTUGUES);
		App.idiomas.add(IdiomaEnum.ESPANHOL);
		App.idiomas.add(IdiomaEnum.INGLES);
	}

}
