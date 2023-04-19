import java.util.List;
import java.util.stream.Collectors;

public class PlataformaStreaming {
    private String nome;
    private List<Serie> series;
    private List<Cliente> clientes;
    private Cliente clienteAtual;

    public Cliente login(String nomeUsuario, String senha){
        return new Cliente(nomeUsuario, senha);
    }

    public void adicionarSerie(Serie serie){
        clienteAtual.getListaParaVer().add(serie);
    }

    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public List<Serie> filtrarPorGenero(String genero){
        return clienteAtual.getListaParaVer().stream()
                .filter(g -> g.getGenero().equals(genero))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        return clienteAtual.getListaParaVer().stream()
                .filter(s -> s.getIdioma().equals(idioma))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
        return clienteAtual.getListaParaVer().stream()
                .filter(q -> q.getQuantidadeEpisodios() == quantEpisodios)
                .collect(Collectors.toList());
    }

    public void registrarAudiencia(Serie serie){
        clienteAtual.getListaParaVer().forEach(s -> serie.registrarAudiencia());
    }

    public void logoff(){

    }

    public Serie buscarSerie(String nomeSerie){
        return clienteAtual.getListaParaVer()
                .stream()
                .filter(nome -> nome.getNome().equals(nomeSerie))
                .findFirst()
                .orElseThrow();
    }

}
