public enum GeneroEnum {
    TERROR ("Terror"),
    ROMANCE ("Romance"),
    DRAMA ("Drama"),
    COMEDIA ("Comedia"),
    SUSPENSE ("Suspense"),
    ANIMACAO("Animacao"),
    FICCAO ("Ficcao Cientifica");

    String genero;

    GeneroEnum (String genero){
        this.genero = genero;
    }

    public String nome(){
        return genero;
    }
}

