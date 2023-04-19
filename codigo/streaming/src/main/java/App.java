public class App {
    public static void main(String[] args) {
        PlataformaStreaming plataformaStreaming = new PlataformaStreaming();
        plataformaStreaming.login("usuario", "senha");
        plataformaStreaming.adicionarSerie(new Serie());
        plataformaStreaming.logoff();
    }
}
