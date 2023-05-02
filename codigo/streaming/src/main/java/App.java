public class App {
    public static void main(String[] args) {
    	PlataformaStreaming plataforma = new PlataformaStreaming();
    	
    	plataforma.login("usuario", "senha");
        
    	plataforma.adicionarSerie(new Serie());

    	plataforma.logoff();
    }
}
