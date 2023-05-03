import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MidiaTest {

    private Midia midia;

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

}