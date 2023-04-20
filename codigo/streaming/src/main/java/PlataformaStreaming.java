import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class PlataformaStreaming {
    private String nome;
    private List<Serie> series;
    private List<Cliente> clientes;
    private Cliente clienteAtual;

    public Cliente login(String nomeUsuario, String senha){
        Cliente cliente = new Cliente(nomeUsuario, senha);
        this.clienteAtual = cliente;
        return cliente;
    }

    public void adicionarSerie(Serie serie){
        series.add(serie);
    }

    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
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

    public Serie buscarSerie(String nomeSerie){
        return series.stream()
                .filter(nome -> nome.getNome().equals(nomeSerie))
                .findFirst()
                .orElseThrow();
    }

}
