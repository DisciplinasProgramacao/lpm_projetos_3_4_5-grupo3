import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlataformaStreaming {
    private String nome;
    private List<Serie> series;
    private List<Filme> filmes;
    private List<Cliente> clientes;
    private Cliente clienteAtual;
    private static PlataformaStreaming plataformaStreaming;

    public static synchronized PlataformaStreaming iniciarPlataformaStreaming() {
        if (plataformaStreaming == null) {
            plataformaStreaming = new PlataformaStreaming();
        }
        return (plataformaStreaming);
    }

    PlataformaStreaming() {
    }

    public void cadastrar(String nomeUsuario, String senha) {
        clientes.add(new Cliente(nomeUsuario, senha));
    }

    public Optional<Cliente> login(String nomeUsuario, String senha) {
        var cliente = clientes.stream()
                .filter(usuario -> nomeUsuario.equals(usuario.getNomeDeUsuario()))
                .filter(usuario -> senha.equals(usuario.getSenha()))
                .findFirst();
        this.clienteAtual = cliente.orElse(null);
        return cliente;
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarSerie(Serie serie) {
        series.add(serie);
    }

    public void adicionarFilme(Filme filme) {
        filmes.add(filme);
    }

    public List<Serie> filtrarSeriePorGenero(String genero) {
        return series.stream()
                .filter(g -> g.getGenero().nome().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarSeriePorIdioma(String idioma) {
        return series.stream()
                .filter(s -> s.getIdioma().nome().equalsIgnoreCase(idioma))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarSeriePorQtdEpisodios(int quantEpisodios) {
        return series.stream()
                .filter(q -> q.getQuantidadeEpisodios() == quantEpisodios)
                .collect(Collectors.toList());
    }

    public List<Filme> filtrarFilmePorGenero(String genero) {
        return filmes.stream()
                .filter(g -> g.getGenero().nome().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }

    public List<Filme> filtrarFilmePorIdioma(String idioma) {
        return filmes.stream()
                .filter(i -> i.getIdioma().nome().equalsIgnoreCase(idioma))
                .collect(Collectors.toList());
    }

    public void logoff() {
        clienteAtual = null;
    }

    public Midia buscarMidia(String nomeMidia) {
        Optional<Filme> filme = buscarFilme(nomeMidia);
        if (filme.isPresent()) {
            return filme.get();
        }
        Optional<Serie> serie = buscarSerie(nomeMidia);
        return serie.orElse(null);
    }

    public Optional<Filme> buscarFilme(String nomeFilme) {
        return filmes.stream()
                .filter(midia -> midia.getNome().equals(nomeFilme))
                .findFirst();
    }

    public Optional<Serie> buscarSerie(String nomeSerie) {
        return series.stream()
                .filter(midia -> midia.getNome().equals(nomeSerie))
                .findFirst();
    }
}
