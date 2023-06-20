import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlataformaStreamingTest {

    @InjectMocks
    private PlataformaStreaming plataformaStreaming;

    @BeforeEach
    void setUp() {
        plataformaStreaming = new PlataformaStreaming();
    }
//
//    @Test
//    void deveFazerLogin(){
//
//        Optional<Cliente> cliente = plataformaStreaming.login("nome", "12345678");
//
//    }

    @Test
    void deveCadastrar(){
        plataformaStreaming.setClientes(new ArrayList<>());

        Cliente cliente = new Cliente("nome", "12345678");
        plataformaStreaming.cadastrar("nome", "12345678");

        assertEquals(cliente.getSenha(), "12345678");
        assertEquals(cliente.getNomeDeUsuario(), "nome");
    }

    @Test
    void deveAdicionarCliente(){
        plataformaStreaming.setClientes(new ArrayList<>());

        Cliente cliente = new Cliente("nome", "12345678");
        plataformaStreaming.adicionarCliente(cliente);

        assertEquals(cliente.getSenha(), "12345678");
        assertEquals(cliente.getNomeDeUsuario(), "nome");
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
        serie.setIdioma(IdiomaEnum.ESPANHOL);

        plataformaStreaming.setSeries(List.of(serie));

        List<Serie> series = plataformaStreaming.filtrarSeriePorIdioma(IdiomaEnum.ESPANHOL.idioma);

        assertEquals(series.get(0).getIdioma(), IdiomaEnum.ESPANHOL);
    }

    @Test
    void deveFiltrarFilmePorIdioma(){
        Filme filme = new Filme("nome");
        filme.setIdioma(IdiomaEnum.PORTUGUES);

        plataformaStreaming.setFilmes(List.of(filme));

        List<Filme> filmes = plataformaStreaming.filtrarFilmePorIdioma(IdiomaEnum.PORTUGUES.idioma);

        assertEquals(filmes.get(0).getIdioma(), IdiomaEnum.PORTUGUES);
    }

    @Test
    void deveFiltrarPorQtdDeEpisodios(){
        Serie serie = new Serie("nome");
        serie.setQuantidadeEpisodios(10);

        plataformaStreaming.setSeries(List.of(serie));

        List<Serie> series = plataformaStreaming.filtrarSeriePorQtdEpisodios(10);

        assertEquals(series.get(0).getQuantidadeEpisodios(), 10);
    }

//    @Test
//    void deveFazerLogoff(){
//
//        Cliente clienteAtual = new Cliente();
//
//        plataformaStreaming.logoff();
//
//        assertEquals(clienteAtual, null);
//    }

    @Test
    void deveBuscarFIlme(){

    }

    @Test
    void deveBuscarSerie(){

    }
}