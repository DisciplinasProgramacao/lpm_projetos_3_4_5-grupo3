import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PlataformaStreamingTest {

    @InjectMocks
    private PlataformaStreaming plataformaStreaming;

    @BeforeEach
    void setUp() {
        plataformaStreaming = PlataformaStreaming.iniciarPlataformaStreaming();
    }

    @Test
    void deveFazerLogin(){
        Cliente cliente = new Cliente("nome", "12345678");

        plataformaStreaming.setClientes(List.of(cliente));

        Optional<Cliente> clientes = plataformaStreaming.login("nome", "12345678");

        assertEquals(cliente.getNomeDeUsuario(), clientes.get().getNomeDeUsuario());
    }

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

    @Test
    void deveFazerLogoff(){

       plataformaStreaming.login("nome", "1234567");

       plataformaStreaming.logoff();

       assertNull(plataformaStreaming.getClienteAtual());
    }

    @Test
    void deveBuscarFIlme(){
        Filme filme = new Filme("filme");

        plataformaStreaming.setFilmes(List.of(filme));

        Optional<Filme> filmes = plataformaStreaming.buscarFilme("filme");

        assertEquals(filme.getNome(), filmes.get().getNome());
    }

    @Test
    void deveBuscarSerie(){
       Serie serie = new Serie("serie");

        plataformaStreaming.setSeries(List.of(serie));

        Optional<Serie> series = plataformaStreaming.buscarSerie("serie");

        assertEquals(serie.getNome(), series.get().getNome());
    }

    @Test
    void deveBuscarMidia() {

        Filme filme = new Filme("midia");

        plataformaStreaming.setFilmes(List.of(filme));

        Midia midia = plataformaStreaming.buscarMidia("midia");

        assertEquals(filme, midia);
    }

    @Test
    void deveAdicionarSerie(){
        plataformaStreaming.setSeries(new ArrayList<>());

        Serie serie = new Serie("nome");

        plataformaStreaming.adicionarSerie(serie);

        assertEquals(serie.getNome(), "nome");
    }

    @Test
    void deveAdicionarFilme(){
        plataformaStreaming.setFilmes(new ArrayList<>());

        Filme filme = new Filme("nome");

        plataformaStreaming.adicionarFilme(filme);

        assertEquals(filme.getNome(), "nome");
    }

    @Test
    void deveRealizarAssinatura(){
        plataformaStreaming.setClienteAtual(new Cliente());
        plataformaStreaming.realizarAssinatura();

        assertEquals(plataformaStreaming.getClienteAtual().getClienteTipo(), ClienteTipoEnum.PROFISSIONAL);
    }

    @Test
    void deveValidarGenero() {
        Midia midia = new Midia();
        midia.setGenero(GeneroEnum.ACAO);

        Boolean verifica = plataformaStreaming.validarGenero("ACAO");

        assertEquals("Acao", midia.getGenero().genero);
        assertEquals(verifica, false);
    }

    @Test
    void deveValidarIdioma() {
        Midia midia = new Midia();
        midia.setIdioma(IdiomaEnum.ESPANHOL);

        Boolean verifica = plataformaStreaming.validarIdioma("Espanhol");

        assertEquals("Espanhol",midia.getIdioma().idioma);
        assertEquals(verifica, false);
    }

    @Test
    void porcentagemClientesQuinzeAvaliacoes(){
        Cliente cliente = new Cliente();
        Map<Midia, Date> listaJaVista = new HashMap<>();

        for(int i = 0; i < 16; i++){
            Midia midia = new Midia();
            Avaliacao avaliacao = new Avaliacao(cliente, 5);
            midia.getAvaliacoes().add(avaliacao);
            listaJaVista.put(midia, new Date());
        }

        plataformaStreaming.setClientes(List.of(cliente));
        cliente.setListaJaVistas(listaJaVista);

        var porcentagem = plataformaStreaming.porcentagemClientesQuinzeAvaliacoes();
        assertEquals(porcentagem, 100);
    }

    @Test
    void midiaMelhorAvaliacaoCom100Avaliacoes(){
        Cliente cliente = new Cliente();
        Midia midia = new Midia();
        Map<Midia, Date> listaJaVista = new HashMap<>();

        for(int i = 0; i < 100; i++){

            Avaliacao avaliacao = new Avaliacao(new Cliente(), 5);
            midia.getAvaliacoes().add(avaliacao);
            listaJaVista.put(midia, new Date());
        }

        plataformaStreaming.setClientes(List.of(cliente));
        cliente.setListaJaVistas(listaJaVista);

        var midias = plataformaStreaming.melhoresMidias();
        assertEquals(midias.get(0), midia);
    }
}