import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlataformaStreamingTest {

    private PlataformaStreaming plataformaStreaming;

    @BeforeEach
    void setUp(){
        plataformaStreaming = new PlataformaStreaming();
    }

    @Test
    void deveFazerLogin(){
        var cliente = plataformaStreaming.login("usuario", "senha");

        assertEquals(cliente.getNomeDeUsuario(), plataformaStreaming.getClienteAtual().getNomeDeUsuario());
        assertEquals(cliente.getSenha(), plataformaStreaming.getClienteAtual().getSenha());
    }

    @Test
    void deveAdicionarCliente(){
        plataformaStreaming.setClientes(new ArrayList<>());
        plataformaStreaming.adicionarCliente(new Cliente());

        assertEquals(plataformaStreaming.getClientes().size(), 1);
    }
}