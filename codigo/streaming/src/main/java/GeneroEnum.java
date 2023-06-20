public enum GeneroEnum {
    ACAO("Acao"),
    ANIME("Anime"),
    AVENTURA("Aventura"),
    COMEDIA ("Comedia"),
    DRAMA ("Drama"),
    POLICIAL("Policial"),
    ROMANCE ("Romance");

    final String genero;

    GeneroEnum (String genero){
        this.genero = genero;
    }

    public String nome(){
        return genero;
    }
}

