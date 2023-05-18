import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private List<Midia> listaParaVer;
    private Map<Midia, Date> listaJaVistas;
    private boolean clienteEspecialista = false;

    public Cliente(){
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new HashMap<>();
    }

    public Cliente(String nome, String senha){
        this.nomeDeUsuario = nome;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new HashMap<>();
    }

    public void adicionarNaListaParaVer(Midia midia){
        this.listaParaVer.add(midia);
    }

    public void adicionarNaListaJaVista(Midia midia){
        this.listaJaVistas.put(midia, new Date());
    }

    public void retirarDaLista(String nomeSerie){
        var serie = listaParaVer.stream()
                .filter(s -> s.getNome().equals(nomeSerie))
                .findFirst()
                .orElseThrow();

        listaParaVer.remove(serie);
    }

    public List<Midia> filtrarPorGenero(GeneroEnum genero){
        return listaParaVer.stream()
                .filter(g -> g.getGenero().equals(genero))
                .collect(Collectors.toList());
    }

    public List<Midia> filtrarPorIdioma(String idioma) {
        return listaParaVer.stream()
                .filter(s -> s.getIdioma().equals(idioma))
                .collect(Collectors.toList());
    }

    /* public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
        return listaParaVer.stream()
                .filter(q -> q.getQuantidadeEpisodios() == quantEpisodios)
                .collect(Collectors.toList());
    }*/

    private boolean clienteEspecialista(){
        return false;
    }
}
