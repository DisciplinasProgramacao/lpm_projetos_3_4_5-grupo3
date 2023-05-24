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
    private String idioma;
    private int audiencia;
    private Map<Cliente, Integer> avaliacoes;
    private Double avaliacaoTotal;
    private List<String> comentarios = new ArrayList<>();

    public Midia(String genero, String nome, String idioma, int audiencia){
        this.genero = GeneroEnum.valueOf(genero);
        this.nome = nome;
        this.idioma = idioma;
        this.audiencia = audiencia;
        this.avaliacaoTotal = 0.0;
    }

    public void registrarAudiencia(){
        this.audiencia += 1;
    }

    public void registrarAvaliacao(Integer avaliacao, Cliente cliente, String comentario){
        if(!avaliacoes.containsKey(cliente)) {
            avaliacoes.put(cliente, avaliacao);
            cliente.clienteEspecialista();
            if(cliente.isClienteEspecialista()) {
            	comentarios.add(comentario);
            }
            OptionalDouble avaliacaoOptional = avaliacoes.values().stream().mapToDouble(Integer::doubleValue).average();
            avaliacaoTotal = avaliacaoOptional.orElse(0.0);
        }

    }
}
