import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    private Serie serie;
    private Cliente cliente;
    private Midia midia;

    @BeforeEach
    void setUp(){
        serie = new Serie("nome");
        cliente = new Cliente();
        midia = new Midia();

        cliente.setListaParaVer(new ArrayList<>());
    }

    @Test
    void deveAdicionarNaListaParaVerQuandoReceberMidia(){
        cliente.adicionarNaListaParaVer(midia);

        assertEquals(cliente.getListaParaVer().size(), 1);
    }

    @Test
    void deveAdicionarNaListaJaVistaQuandoReceberMidia(){
        cliente.adicionarNaListaJaVista(midia);

        assertEquals(cliente.getListaJaVistas().size(), 1);
    }

    @Test
    void deveRetirarDaListaQuandoReceberNomeSerie(){
        serie.setNome("Serie");

        cliente.adicionarNaListaParaVer(serie);
        cliente.retirarDaListaParaVer("Serie");

        assertEquals(cliente.getListaParaVer().size(), 0);
    }

    @Test
    void deveContarAvaliacoes() {
        cliente.contarAvaliacoes();

        assertEquals(cliente.contarAvaliacoes(), 0);
    }
}