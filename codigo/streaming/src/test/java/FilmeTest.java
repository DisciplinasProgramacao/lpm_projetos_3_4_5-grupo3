import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmeTest {

    private Filme filme;

    @BeforeEach
    void setUp(){
        filme = new Filme("nome");
    }

    @Test
    void deveRegistrarDuracaoFilme(){
        String duracaoFilme = "2:13";

        filme.setDuracao(duracaoFilme);

        assertEquals(filme.getDuracao(), duracaoFilme);
    }
}