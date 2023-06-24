import lombok.Getter;

@Getter
public class Avaliacao {
    private final Cliente cliente;
    private final Integer nota;
    private final String comentario;

    public Avaliacao(Cliente cliente, Integer nota, String comentario) {
        this.cliente = cliente;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Avaliacao(Cliente cliente, Integer nota) {
        this.cliente = cliente;
        this.nota = nota;
        this.comentario = "";
    }
}
