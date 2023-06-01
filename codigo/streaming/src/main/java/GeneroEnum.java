public enum GeneroEnum {
    ACAO("Acao"),
    ANIME("Anime"),
    AVENTURA("Aventura"),
    COMEDIA ("Comedia"),
    DOCUMENTARIO("Documentario"),
    DRAMA ("Drama"),
    POLICIAL("Policial"),
    ROMANCE ("Romance"),
    SUSPENSE ("Suspense");

    final String genero;

    GeneroEnum (String genero){
        this.genero = genero;
    }

    public String nome(){
        return genero;
    }
}

