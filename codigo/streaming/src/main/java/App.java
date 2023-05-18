import java.util.ArrayList;
import java.util.Scanner;

import static java.util.Objects.nonNull;

public class App {
	private static final Scanner ler = new Scanner(System.in);

	public static void main(String[] args) {
		PlataformaStreaming plataformaStreaming = iniciarPlataforma();
		int x;

		do{
			System.out.println("0 - LogOff");
			System.out.println("1 - LogIn");
			System.out.println("2 - Adicionar Filme");
			System.out.println("3 - Adicionar Serie");
			System.out.println("4 - Buscar Midia");
			System.out.println("5 - Assistir midia");
			System.out.println("Informe um numero:");
			x = ler.nextInt();

			//OBS: Essa nomeclatura do switch case só funciona no Java 17
			switch (x) {
				case 0 -> logoff(plataformaStreaming);
				case 1 -> login(plataformaStreaming);
				case 2 -> registrarFilme(plataformaStreaming);
				case 3 -> registrarSerie(plataformaStreaming);
				case 4 -> buscarMidia(plataformaStreaming);
				case 5 -> assistirFilme(plataformaStreaming);
			}
		} while(x != 0);
		ler.close();
    }

	private static PlataformaStreaming iniciarPlataforma() {
		return PlataformaStreaming.builder()
				.nome("Plataforma")
				.series(new ArrayList<>())
				.filmes(new ArrayList<>())
				.clientes(new ArrayList<>())
				.build();
	}

	private static void assistirFilme(PlataformaStreaming plataformaStreaming){
		System.out.println("Informe o nome da midia que voce deseja assistir:");
		String nome = ler.next();

		Midia midia = plataformaStreaming.buscarMidia(nome);
		System.out.println("Assistindo...");
		midia.registrarAudiencia();
		plataformaStreaming.getClienteAtual().adicionarNaListaJaVista(midia);

		System.out.println("Informe a nota do filme: (1 a 5) ");
		int avaliacao = ler.nextInt();

		String comentario = "";
		if(plataformaStreaming.getClienteAtual().isClienteEspecialista()){
			System.out.println("Informe um comnetario ao filme: ");
			comentario = ler.next();
		}
		midia.registrarAvaliacao(avaliacao, plataformaStreaming.getClienteAtual(), comentario);
	}

	private static void login(PlataformaStreaming plataformaStreaming){
		System.out.println("Informe seu usuario:");
		String usuario = ler.next();

		System.out.println("Informe sua senha:");
		String senha = ler.next();

		plataformaStreaming.login(usuario, senha);
	}

	private static void registrarFilme(PlataformaStreaming plataformaStreaming){
		System.out.println("Informe o nome do filme:");
		String nome = ler.next();

		Filme filme = new Filme(nome);

		plataformaStreaming.adicionarFilme(filme);
	}

	private static void registrarSerie(PlataformaStreaming plataformaStreaming){
		System.out.println("Informe o nome da Serie:");
		String nome = ler.next();

		Serie serie = new Serie(nome);

		plataformaStreaming.adicionarSerie(serie);
	}

	private static void buscarMidia(PlataformaStreaming plataformaStreaming) {
		System.out.println("Informe o nome do filme ou da serie que deseja buscar:");
		String nome = ler.next();

		Midia midia = plataformaStreaming.buscarMidia(nome);

		if(nonNull(midia.getNome())){
			System.out.println("A midia que voce pesquisou se chama: " + midia.getNome() + " nota: " + midia.getAvaliacaoTotal());
		}
		else{
			System.out.println("Midia nao cadastrado com esse nome");
		}
	}

	private static void logoff(PlataformaStreaming plataformaStreaming) {
		plataformaStreaming.logoff();
	}

}
