import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private List<Serie> listaParaVer;
    private List<Serie> listaJaVistas;

    public void adicionarNaLista(Serie serie){

    }

    public void retirarDaLista(String nomeSerie){

    }

    public List<Serie> filtrarPorGenero(String genero){
        return null;
    }

    public List<Serie> filtrarPorIdioma(String idioma){
        return null;
    }

    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
        return null;
    }

    public void registrarAudiencia(Serie serie){

    }

}
