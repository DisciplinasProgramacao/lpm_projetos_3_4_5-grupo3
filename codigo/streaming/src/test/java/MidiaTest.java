import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MidiaTest {

    @Mock
    private Midia midia;

    @BeforeEach
    void setUp(){
        midia = new Midia();
    }

    @Test
    void deveRegistrarAudienciaDaSerie(){
        midia = new Serie("nome");
        midia.setAudiencia(0);

        midia.registrarAudiencia();
        midia.registrarAudiencia();

        assertEquals(midia.getAudiencia(), 2);
        assertTrue(midia instanceof Serie);
    }

    @Test
    void deveRegistrarAudienciaDoFilme(){
        midia = new Filme("nome");
        midia.setAudiencia(0);

        midia.registrarAudiencia();
        midia.registrarAudiencia();
        midia.registrarAudiencia();

        assertEquals(midia.getAudiencia(), 3);
        assertTrue(midia instanceof Filme);
    }

    @Test
    void deveRegistrarAvaliacaoQuandoReceberAvaliacao(){
        Avaliacao avaliacao = new Avaliacao(new Cliente(), 10, "bom");

        midia.registrarAvaliacao(avaliacao);

        assertEquals(avaliacao.getComentario(), "bom");
        assertEquals(avaliacao.getNota(), 10);
    }
}