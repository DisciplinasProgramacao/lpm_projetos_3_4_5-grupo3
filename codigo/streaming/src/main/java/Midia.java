import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Midia {
    private GeneroEnum genero;
    private String nome;
    private IdiomaEnum idioma;
    private int audiencia;
    private Double avaliacaoTotal;
    private List<Avaliacao> avaliacoes = new ArrayList<>();
    private boolean lancamento;

    public void registrarAudiencia() {
        this.audiencia += 1;
    }

    public void registrarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);

        this.avaliacaoTotal = avaliacoes.stream()
                .mapToInt(Avaliacao::getNota)
                .average()
                .orElse(0);
    }
}
