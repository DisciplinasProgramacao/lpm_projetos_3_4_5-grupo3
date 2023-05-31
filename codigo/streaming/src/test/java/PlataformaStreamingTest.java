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

    }

    @Test
    void deveCadastrar(){

    }

    @Test
    void deveAdicionarCliente(){
        plataformaStreaming.setClientes(new ArrayList<>());
        plataformaStreaming.adicionarCliente(new Cliente());

        assertEquals(plataformaStreaming.getClientes().size(), 1);
    }

    @Test
    void deveFiltrarSeriePorGenero(){
        Serie serie = new Serie("nome");
        serie.setGenero(GeneroEnum.DRAMA);

        plataformaStreaming.setSeries(List.of(serie));

        List<Serie> series = plataformaStreaming.filtrarSeriePorGenero(GeneroEnum.DRAMA.nome());

        assertEquals(series.get(0).getGenero(), GeneroEnum.DRAMA);
    }


    @Test
    void deveFiltrarFilmePorGenero(){
        Filme filme = new Filme("nome");

        filme.setGenero(GeneroEnum.DRAMA);

        plataformaStreaming.setFilmes(List.of(filme));

        List<Filme> filmes = plataformaStreaming.filtrarFilmePorGenero(GeneroEnum.DRAMA.nome());

        assertEquals(filmes.get(0).getGenero(), GeneroEnum.DRAMA);
    }

    @Test
    void deveFiltrarSeriePorIdioma(){
        Serie serie = new Serie("nome");
        serie.setIdioma("Idioma");

        plataformaStreaming.setSeries(List.of(serie));

        List<Serie> series = plataformaStreaming.filtrarSeriePorIdioma("Idioma");

        assertEquals(series.get(0).getIdioma(), "Idioma");
    }

    @Test
    void deveFiltrarFilmePorIdioma(){
        Filme filme = new Filme("nome");
        filme.setIdioma("Idioma");

        plataformaStreaming.setFilmes(List.of(filme));

        List<Filme> filmes = plataformaStreaming.filtrarFilmePorIdioma("Idioma");

        assertEquals(filmes.get(0).getIdioma(), "Idioma");
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
    void deveFazerLogoff(){

    }

    @Test
    void deveBuscarFIlme(){

    }

    @Test
    void deveBuscarSerie(){

    }
}