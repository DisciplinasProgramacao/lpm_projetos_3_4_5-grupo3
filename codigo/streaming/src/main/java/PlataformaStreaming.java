import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Setter
@Getter
public class PlataformaStreaming {
    private String nome;
    private List<Serie> series;
    private List<Filme> filmes;
    private List<Cliente> clientes;
    private Cliente clienteAtual;

    public Cliente login(String nomeUsuario, String senha){
        Cliente cliente = new Cliente(nomeUsuario, senha);
        this.clienteAtual = cliente;
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

    public List<Serie> filtrarPorGenero(String genero){
        return series.stream()
                .filter(g -> g.getGenero().nome().equals(genero))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        return series.stream()
                .filter(s -> s.getIdioma().equals(idioma))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
        return series.stream()
                .filter(q -> q.getQuantidadeEpisodios() == quantEpisodios)
                .collect(Collectors.toList());
    }

    public void registrarAudiencia(Serie serie){
        series.forEach(s -> serie.registrarAudiencia());
    }

    public void logoff(){
        clienteAtual = null;
    }

    public Midia buscarMidia(String nomeMidia) {
        Optional<Filme> filme = buscarFilme(nomeMidia);
        if(filme.isEmpty()){
            Optional<Serie> serie = buscarSerie(nomeMidia);
            if(serie.isEmpty()){
                return new Midia();
            }
            return serie.orElseThrow();
        }
        return filme.orElseThrow();
    }

    private Optional<Filme> buscarFilme(String nomeFilme) {
        return filmes.stream()
                .filter(midia -> midia.getNome().equals(nomeFilme))
                .findFirst();
    }

    private Optional<Serie> buscarSerie(String nomeSerie) {
        return series.stream()
                .filter(midia -> midia.getNome().equals(nomeSerie))
                .findFirst();
    }
}
