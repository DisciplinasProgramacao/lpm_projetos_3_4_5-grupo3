public enum ClienteTipoEnum {
    REGULAR ("Regular"),
    ESPECIALISTA("Especialista"),
    PROFISSIONAL ("Profissional");

    final String tipo;

    ClienteTipoEnum (String tipo){
        this.tipo = tipo;
    }

    public String nome(){
        return tipo;
    }
}
