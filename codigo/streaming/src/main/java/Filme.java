import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filme extends Midia {
    private String duracao;

    public Filme(String nome) {
        this.setNome(nome);
    }
}
