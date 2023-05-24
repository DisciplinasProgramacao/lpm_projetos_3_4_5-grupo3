import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PlataformaStreaming {
    private String nome;
    private List<Serie> series;
    private List<Filme> filmes;
    private List<Cliente> clientes;
    private Cliente clienteAtual;

    public void cadastrar(String nomeUsuario, String senha){
        clientes.add(new Cliente(nomeUsuario, senha));
    }

    public Optional<Cliente> login(String nomeUsuario, String senha){
        var cliente = clientes.stream()
                .filter(usuario -> nomeUsuario.equals(usuario.getNomeDeUsuario()))
                .filter(usuario -> senha.equals(usuario.getSenha()))
                .findFirst();
        this.clienteAtual = cliente.orElse(null);
        return cliente;
    }
    
    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public void adicionarSerie(Serie serie){
        series.add(serie);
    }
    
    public void adicionarFilme(Filme filme){
        filmes.add(filme);
    }

    public List<Serie> filtrarSeriePorGenero(String genero){
        return series.stream()
                .filter(g -> g.getGenero().nome().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarSeriePorIdioma(String idioma) {
        return series.stream()
                .filter(s -> s.getIdioma().equalsIgnoreCase(idioma))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarSeriePorQtdEpisodios(int quantEpisodios){
        return series.stream()
                .filter(q -> q.getQuantidadeEpisodios() == quantEpisodios)
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarFilmePorGenero(String genero){
        return series.stream()
                .filter(g -> g.getGenero().nome().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarFilmePorIdioma(String idioma) {
        return series.stream()
                .filter(s -> s.getIdioma().equalsIgnoreCase(idioma))
                .collect(Collectors.toList());
    }

    public void logoff(){
        clienteAtual = null;
    }

    public Midia buscarMidia(String nomeMidia) {
        Optional<Filme> filme = buscarFilme(nomeMidia);
        if(filme.isPresent()){
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
