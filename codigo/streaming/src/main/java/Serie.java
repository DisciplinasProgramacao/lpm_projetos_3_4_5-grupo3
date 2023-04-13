import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class Serie {
    private final String[] GENEROS = {"Terror", "Romance", "Drama", "Comedia", "Suspense", "Animacao", "Ficcao Cientifica"};
    private String nome;
    private String genero;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia;

    public void registrarAudiencia(){

    }
}
