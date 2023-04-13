import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private List<Serie> listaParaVer;
    private List<Serie> listaJaVistas;

    public void adicionarNaLista(Serie serie){
        this.listaParaVer.add(serie);
    }

    public void retirarDaLista(String nomeSerie){
        var serie = listaParaVer.stream()
                .filter(s -> s.getNome().equals(nomeSerie))
                .findFirst()
                .orElseThrow();

        listaParaVer.remove(serie);
    }

    public List<Serie> filtrarPorGenero(String genero){
        var serie = listaParaVer.stream()
                .filter(g -> g.getGenero().equals(genero))
                .findFirst()
                .orElseThrow();

        List<Serie> seriePorGenero = new ArrayList<>();
        seriePorGenero.add(serie);

        return seriePorGenero;
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        var serie = listaParaVer.stream()
                .filter(s -> s.getIdioma().equals(idioma))
                .findFirst()
                .orElseThrow();

        List<Serie> seriePorIdioma = new ArrayList<>();
        seriePorIdioma.add(serie);

        return seriePorIdioma;
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
        var serie = listaParaVer.stream()
                .filter(q -> q.getQuantidadeEpisodios() == quantEpisodios)
                .findFirst()
                .orElseThrow();

        List<Serie> seriePorQtdEpisodios = new ArrayList<>();
        seriePorQtdEpisodios.add(serie);

        return seriePorQtdEpisodios;
    }

    public void registrarAudiencia(Serie serie){
        listaJaVistas.forEach(s -> serie.registrarAudiencia());
    }

}
