import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    private Serie serie;

    private Cliente cliente;

    @BeforeEach
    void setUp(){
        serie = new Serie();
        cliente = new Cliente();
        cliente.setListaParaVer(new ArrayList<>());
    }

    @Test
    void deveAdicionarNaLista(){
        cliente.adicionarNaLista(serie);

        assertEquals(cliente.getListaParaVer().size(), 1);
    }

    @Test
    void deveRetirarDaLista(){
        serie.setNome("Serie");
        cliente.adicionarNaLista(serie);
        cliente.retirarDaLista("Serie");

        assertEquals(cliente.getListaParaVer().size(), 0);
    }

    @Test
    void deveFiltrarPorGenero(){
        serie.setGenero("Genero");
        cliente.adicionarNaLista(serie);

        var series = cliente.filtrarPorGenero("Genero");

        assertEquals(series.get(0).getGenero(), "Genero");
    }

    @Test
    void deveFiltrarPorIdioma(){
        serie.setIdioma("Idioma");
        cliente.adicionarNaLista(serie);

        var series = cliente.filtrarPorIdioma("Idioma");

        assertEquals(series.get(0).getIdioma(), "Idioma");
    }

    @Test
    void deveFiltrarPorQtdDeEpisodios(){
        serie.setQuantidadeEpisodios(10);
        cliente.adicionarNaLista(serie);

        var series = cliente.filtrarPorQtdEpisodios(10);

        assertEquals(series.get(0).getQuantidadeEpisodios(), 10);
    }

    @Test
    void deveRegistrarAudiencia(){
        cliente.setListaJaVistas(new ArrayList<>());
        cliente.getListaJaVistas().add(serie);

        cliente.registrarAudiencia(serie);
        cliente.registrarAudiencia(serie);
        cliente.registrarAudiencia(serie);

        assertEquals(serie.getAudiencia(), 3);
    }
}