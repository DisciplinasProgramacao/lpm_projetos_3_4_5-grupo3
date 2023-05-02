import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class PlataformaStreaming {
    private String nome;
    private List<Serie> series;
    private List<Cliente> clientes;
    private List<Filme> filmes;
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
                .filter(g -> g.getGenero().equals(genero))
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

    public Stream buscarMidia(String nomeMidia){
    	List<Stream> midias = new ArrayList<>();
    	midias.add((Stream) series);
    	midias.add((Stream) filmes);
        return midias.stream()
                .filter(midia -> midia.getNome().equals(nomeMidia))
                .findFirst()
                .orElseThrow();
    }
}
