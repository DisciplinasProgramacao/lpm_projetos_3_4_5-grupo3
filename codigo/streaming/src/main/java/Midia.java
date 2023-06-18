import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

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
    private Map<Cliente, Integer> avaliacoes;
    private Double avaliacaoTotal;
    private List<String> comentarios = new ArrayList<>();

    public void registrarAudiencia() {
        this.audiencia += 1;
    }

    public void registrarAvaliacao(Integer avaliacao, Cliente cliente, String comentario) {
        avaliacoes.put(cliente, avaliacao);
        cliente.clienteEspecialista();
        if (cliente.isClienteEspecialista()) {
            comentarios.add(comentario);
        }
        OptionalDouble avaliacaoOptional = avaliacoes.values()
                .stream()
                .mapToDouble(Integer::doubleValue)
                .average();
        avaliacaoTotal = avaliacaoOptional.orElse(0.0);
    }
}
