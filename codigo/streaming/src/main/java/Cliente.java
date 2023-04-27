import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private List<Serie> listaParaVer;
    private List<Serie> listaJaVistas;

    public Cliente(){
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new ArrayList<>();
    }

    public Cliente(String nome, String senha){
        this.nomeDeUsuario = nome;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new ArrayList<>();
    }

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
        return listaParaVer.stream()
                .filter(g -> g.getGenero().nome().equals(genero))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarPorIdioma(String idioma) {
        return listaParaVer.stream()
                .filter(s -> s.getIdioma().equals(idioma))
                .collect(Collectors.toList());
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
        return listaParaVer.stream()
                .filter(q -> q.getQuantidadeEpisodios() == quantEpisodios)
                .collect(Collectors.toList());
    }

    public void registrarAudiencia(Serie serie){
        listaJaVistas.forEach(s -> serie.registrarAudiencia());
    }
}
