import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlataformaStreamingTest {

    private PlataformaStreaming plataformaStreaming;

    @BeforeEach
    void setUp(){
        plataformaStreaming = PlataformaStreaming.builder().build();
    }

    @Test
    void deveFazerLogin(){
//        Cliente cliente = plataformaStreaming.login("usuario", "senha");
//
//        assertEquals(cliente.getNomeDeUsuario(), plataformaStreaming.getClienteAtual().getNomeDeUsuario());
//        assertEquals(cliente.getSenha(), plataformaStreaming.getClienteAtual().getSenha());
    }

    @Test
    void deveAdicionarCliente(){
        plataformaStreaming.setClientes(new ArrayList<>());
        plataformaStreaming.adicionarCliente(new Cliente());

        assertEquals(plataformaStreaming.getClientes().size(), 1);
    }

    @Test
    void deveFiltrarPorGenero(){
        Serie serie = new Serie("nome");
        serie.setGenero(GeneroEnum.DRAMA);

        plataformaStreaming.setSeries(List.of(serie));

        List<Serie> series = plataformaStreaming.filtrarSeriePorGenero(GeneroEnum.DRAMA.nome());

        assertEquals(series.get(0).getGenero(), GeneroEnum.DRAMA);
    }

    @Test
    void deveFiltrarPorIdioma(){
        Serie serie = new Serie("nome");
        serie.setIdioma("Idioma");

        plataformaStreaming.setSeries(List.of(serie));

        List<Serie> series = plataformaStreaming.filtrarSeriePorIdioma("Idioma");

        assertEquals(series.get(0).getIdioma(), "Idioma");
    }

    @Test
    void deveFiltrarPorQtdDeEpisodios(){
        Serie serie = new Serie("nome");
        serie.setQuantidadeEpisodios(10);

        plataformaStreaming.setSeries(List.of(serie));

        List<Serie> series = plataformaStreaming.filtrarSeriePorQtdEpisodios(10);

        assertEquals(series.get(0).getQuantidadeEpisodios(), 10);
    }

    @Test
    void deveBuscarSeriePeloNome(){
//        Serie serie = new Serie("nome");
//        serie.setNome("Nome");
//
//        plataformaStreaming.setSeries(List.of(serie));
//        plataformaStreaming.setFilmes(new ArrayList<>());
//
//        Midia serieBuscada = plataformaStreaming.buscarMidia("Nome");
//
//        assertEquals(serieBuscada.getNome(), serie.getNome());
    }

    @Test
    void deveFazerLogoff(){
        plataformaStreaming.login("usuario", "senha");
        assertNotNull(plataformaStreaming.getClienteAtual());

        plataformaStreaming.logoff();
        assertNull(plataformaStreaming.getClienteAtual());
    }
}