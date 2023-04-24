import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StreamTest {

    private Stream stream;

    @Test
    void deveRegistrarAudienciaDaSerie(){
        stream = new Serie();
        stream.setAudiencia(0);

        stream.registrarAudiencia();
        stream.registrarAudiencia();

        assertEquals(stream.getAudiencia(), 2);
        assertTrue(stream instanceof Serie);
    }

    @Test
    void deveRegistrarAudienciaDoFilme(){
        stream = new Filme();
        stream.setAudiencia(0);

        stream.registrarAudiencia();
        stream.registrarAudiencia();
        stream.registrarAudiencia();

        assertEquals(stream.getAudiencia(), 3);
        assertTrue(stream instanceof Filme);
    }

}