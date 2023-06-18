public enum IdiomaEnum {
    PORTUGUES("Portugues"),
    ESPANHOL("Espanhol"),
    INGLES("Ingles");

    final String idioma;

    IdiomaEnum(String idioma) {
        this.idioma = idioma;
    }

    public String nome() {
        return idioma;
    }
}
