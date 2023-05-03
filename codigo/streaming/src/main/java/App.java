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
			System.out.println("4 - Buscar Filme ou Serie");
			System.out.println("Informe um numero:");
			x = ler.nextInt();

			//OBS: Essa nomeclatura do switch case só funciona no Java 17
			switch (x) {
				case 0 -> logoff(plataformaStreaming);
				case 1 -> login(plataformaStreaming);
				case 2 -> registrarFilme(plataformaStreaming);
				case 3 -> registrarSerie(plataformaStreaming);
				case 4 -> buscarMidia(plataformaStreaming);
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
			System.out.println("A midia que voce pesquisou se chama: " + midia.getNome());
		}
		else{
			System.out.println("Midia nao cadastrado com esse nome");
		}
	}

	private static void logoff(PlataformaStreaming plataformaStreaming) {
		plataformaStreaming.logoff();
	}

}
