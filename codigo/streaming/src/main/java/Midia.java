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

    /**
     * Incrementa o contador de audiência.
     */
    public void registrarAudiencia() {
        this.audiencia += 1;
    }

    /**
     * Registra uma avaliação e atualiza a avaliação média.
     *
     * @param avaliacao a avaliação a ser registrada
     */
    public void registrarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);

        this.avaliacaoTotal = avaliacoes.stream()
                .mapToInt(Avaliacao::getNota)
                .average()
                .orElse(0);
    }

    /**
     * Verifica se existe uma avaliação feita pelo cliente atual para a mídia
     * especificada.
     *
     * @param midia A mídia a ser verificada.
     * @return {@code true} se o cliente atual já avaliou a mídia, {@code false}
     *         caso contrário.
     */
    public boolean existeAvaliacao(Cliente cliente) {
        return avaliacoes.stream()
                .anyMatch(a -> a.getCliente().equals(cliente));
    }
}
