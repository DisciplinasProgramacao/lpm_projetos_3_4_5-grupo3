import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Serie extends Midia {
    private int quantidadeEpisodios;

    public Serie() {
        this.setNome("");
    }

    public Serie(String nome) {
        this.setNome(nome);
    }
}
