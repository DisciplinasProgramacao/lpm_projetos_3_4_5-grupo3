import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerieTest {

    private Serie serie;

    @BeforeEach
    void setUp(){
        serie = new Serie();
    }

    @Test
    void deveRegistrarAudiencia(){
        serie.setAudiencia(0);

        serie.registrarAudiencia();

        assertEquals(serie.getAudiencia(), 1);
    }
}