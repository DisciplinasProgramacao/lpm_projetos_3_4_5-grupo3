import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Midia {
    private GeneroEnum genero;
    private String nome;
    private String idioma;
    private int audiencia;
    private int avaliacao;

    public Midia(String genero, String nome, String idioma, int audiencia){
        this.genero = GeneroEnum.valueOf(genero);
        this.nome = nome;
        this.idioma = idioma;
        this.audiencia = audiencia;
    }

    public void registrarAudiencia(){
        this.audiencia += 1;
    }

    public void registrarAvaliacao(int avaliacao){
        this.avaliacao = avaliacao;
    }
}
