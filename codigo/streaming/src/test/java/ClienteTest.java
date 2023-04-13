import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    @Mock
    private Serie serie;

    private Cliente cliente;

    @BeforeEach
    void setUp(){
        cliente = new Cliente();
    }

    @Test
    void deveAdicionarNaLista(){
        //TODO arrumar lista nula
        cliente.adicionarNaLista(serie);

        assertEquals(cliente.getListaParaVer().size(), 1);
    }

    //TODO terminar testes
}