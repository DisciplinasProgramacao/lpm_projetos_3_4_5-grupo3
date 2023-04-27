import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlataformaStreamingTest {

    private PlataformaStreaming plataformaStreaming;

    @BeforeEach
    void setUp(){
        plataformaStreaming = new PlataformaStreaming();
    }

    @Test
    void deveFazerLogin(){
        Cliente cliente = plataformaStreaming.login("usuario", "senha");

        assertEquals(cliente.getNomeDeUsuario(), plataformaStreaming.getClienteAtual().getNomeDeUsuario());
        assertEquals(cliente.getSenha(), plataformaStreaming.getClienteAtual().getSenha());
    }

    @Test
    void deveAdicionarCliente(){
        plataformaStreaming.setClientes(new ArrayList<>());
        plataformaStreaming.adicionarCliente(new Cliente());

        assertEquals(plataformaStreaming.getClientes().size(), 1);
    }

    @Test
    void deveFiltrarPorGenero(){
        Serie serie = new Serie();
        serie.setGenero(GeneroEnum.DRAMA);

        plataformaStreaming.setSeries(List.of(serie));

        List<Serie> series = plataformaStreaming.filtrarPorGenero("Genero");

        assertEquals(series.get(0).getGenero(), "Genero");
    }

    @Test
    void deveFiltrarPorIdioma(){
        Serie serie = new Serie();
        serie.setIdioma("Idioma");

        plataformaStreaming.setSeries(List.of(serie));

        List<Serie> series = plataformaStreaming.filtrarPorIdioma("Idioma");

        assertEquals(series.get(0).getIdioma(), "Idioma");
    }

    @Test
    void deveFiltrarPorQtdDeEpisodios(){
        Serie serie = new Serie();
        serie.setQuantidadeEpisodios(10);

        plataformaStreaming.setSeries(List.of(serie));

        List<Serie> series = plataformaStreaming.filtrarPorQtdEpisodios(10);

        assertEquals(series.get(0).getQuantidadeEpisodios(), 10);
    }

    @Test
    void deveRegistrarAudiencia(){
        Serie serie = new Serie();

        plataformaStreaming.setSeries(List.of(serie));

        plataformaStreaming.registrarAudiencia(serie);
        plataformaStreaming.registrarAudiencia(serie);

        assertEquals(serie.getAudiencia(), 2);
    }

    @Test
    void deveBuscarSeriePeloNome(){
        Serie serie = new Serie();
        serie.setNome("Nome");

        plataformaStreaming.setSeries(List.of(serie));

        Serie serieBuscada = plataformaStreaming.buscarSerie("Nome");

        assertEquals(serieBuscada.getNome(), serie.getNome());
    }

    @Test
    void deveFazerLogoff(){
        plataformaStreaming.login("usuario", "senha");
        assertNotNull(plataformaStreaming.getClienteAtual());

        plataformaStreaming.logoff();
        assertNull(plataformaStreaming.getClienteAtual());
    }
}