import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.*;
import java.util.*;
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
				System.out.println("19 - Sair");
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
				System.out.println("17 - Caastrar serie pelo arquivo");
				System.out.println("18 - Salvar dados em Json");
				System.out.println("0 - LogOff");
				System.out.println("19 - Sair");
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
			}
		} while (x != 19);
		salvarDadosEmJson();
		ler.close();
	}

	private static void cadastrarFilmeArquivo() {
		Filme filme = new Filme();
		lerArquivo(filme, "src/main/data/POO_Filmes.csv");
	}

	private static void cadastrarSerieArquivo() {
		Serie filme = new Serie();
		lerArquivo(filme, "src/main/data/POO_Series.csv");
	}

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

	private static void registrarMidia(Midia midia, String linha) {
		midia.setNome(getMidiaNome(linha));
		midia.setGenero(buildGenero());
		midia.setIdioma(buildIdioma());
		midia.setAudiencia(0);
		midia.setAvaliacoes(new ArrayList<>());
		midia.setAvaliacaoTotal(0.0);

		if (midia instanceof Serie) {
			((Serie) midia).setQuantidadeEpisodios((int) Math.floor(Math.random() * (50 + 1) + 1));
			plataformaStreaming.adicionarSerie((Serie) midia);
		}
		if (midia instanceof Filme) {
			plataformaStreaming.adicionarFilme((Filme) midia);
		}
	}

	private static String getMidiaNome(String linha) {
		String dados = linha.replaceAll(" ", "");
		return dados.split(";")[1];
	}

	private static void cadastrar() {
		System.out.println("Informe seu nome de usuario:");
		String usuario = ler.next();

		System.out.println("Informe sua senha:");
		String senha = ler.next();

		plataformaStreaming.cadastrar(usuario, senha);
		System.out.println("Cadastro realizado com sucesso!");
	}

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

	private static void logoff() {
		plataformaStreaming.logoff();
	}

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

	private static void assistir(Midia midia) {
		System.out.println("Assistindo...");
		midia.registrarAudiencia();
		plataformaStreaming.getClienteAtual().adicionarNaListaJaVista(midia);
		plataformaStreaming.getClienteAtual().retirarDaListaParaVer(midia.getNome());

		if(!existeAvaliacao(midia)){
			System.out.println("Informe a nota do filme: (1 a 5) ");
			int nota = ler.nextInt();

			String comentario = "";
			if (plataformaStreaming.getClienteAtual().isClienteEspecialista()) {
				System.out.println("Informe um comnetario: ");
				comentario = ler.next();
			}
			Avaliacao avaliacao = new Avaliacao(plataformaStreaming.getClienteAtual(), nota, comentario);
			midia.registrarAvaliacao(avaliacao);
		} else {
			System.out.println("Você já registrou uma avaliação para essa mídia! ");
		}
	}

	private static boolean existeAvaliacao(Midia midia) {
		return midia.getAvaliacoes().stream()
				.anyMatch(a -> a.getCliente().equals(plataformaStreaming.getClienteAtual()));
	}

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

	private static boolean validarGenero(String genero) {
		var midia = generos.stream()
				.filter(g -> genero.equalsIgnoreCase(g.nome()))
				.findFirst();
		return midia.isPresent();
	}

	private static boolean validarIdioma(String idioma) {
		var midia = idiomas.stream()
				.filter(i -> idioma.equalsIgnoreCase(i.nome()))
				.findFirst();
		return midia.isPresent();
	}

	private static IdiomaEnum buildIdioma() {
		int size = idiomas.size();
		Random random = new Random();
		int randomInt = random.nextInt(size - 1 + 1);
		return idiomas.get(randomInt);
	}

	private static GeneroEnum buildGenero() {
		int size = generos.size();
		Random random = new Random();
		int randomInt = random.nextInt(size - 1 + 1);
		return generos.get(randomInt);
	}

	private static void inicializarGenerosIdiomas() {
		App.generos.add(GeneroEnum.POLICIAL);
		App.generos.add(GeneroEnum.ROMANCE);
		App.generos.add(GeneroEnum.DRAMA);
		App.generos.add(GeneroEnum.COMEDIA);
		App.generos.add(GeneroEnum.SUSPENSE);
		App.generos.add(GeneroEnum.ANIME);
		App.generos.add(GeneroEnum.ACAO);
		App.generos.add(GeneroEnum.AVENTURA);
		App.generos.add(GeneroEnum.DOCUMENTARIO);
		App.idiomas.add(IdiomaEnum.PORTUGUES);
		App.idiomas.add(IdiomaEnum.ESPANHOL);
		App.idiomas.add(IdiomaEnum.INGLES);
	}

}
