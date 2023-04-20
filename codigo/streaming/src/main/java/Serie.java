import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Serie {
    private final String[] GENEROS = {"Terror", "Romance", "Drama", "Comedia", "Suspense", "Animacao", "Ficcao Cientifica"};
    private String nome;
    private String genero;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia;

    public void registrarAudiencia(){
        this.audiencia += 1;
    }
}
