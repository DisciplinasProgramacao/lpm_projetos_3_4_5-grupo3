import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerieTest {
    private Serie serie;

    @BeforeEach
    void setUp(){
        serie = new Serie();
    }

    @Test
    void deveRegistrarDuracaoFilme(){
        int quantidadeEpisodio = 13;

        serie.setQuantidadeEpisodios(quantidadeEpisodio);

        assertEquals(serie.getQuantidadeEpisodios(), quantidadeEpisodio);
    }
}