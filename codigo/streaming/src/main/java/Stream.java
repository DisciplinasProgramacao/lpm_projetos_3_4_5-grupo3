import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Stream {
    private GeneroEnum genero;
    private String nome;
    private String idioma;
    private int audiencia;

    public Stream(String genero, String nome, String idioma, int audiencia){
        this.genero = GeneroEnum.valueOf(genero);
        this.nome = nome;
        this.idioma = idioma;
        this.audiencia = audiencia;
    }

    public void registrarAudiencia(){
        this.audiencia += 1;
    }
}
