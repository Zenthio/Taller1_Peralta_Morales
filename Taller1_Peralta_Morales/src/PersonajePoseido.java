public class PersonajePoseido {
    private Cliente dueño;
    private Personaje personaje;
    private listaAspectosPoseidos AspectosPoseidos;
    
    public PersonajePoseido(Cliente dueño, Personaje personaje){
        this.dueño = dueño;
        this.personaje = personaje;
    }

    public Personaje getPersonaje(){
        return this.personaje;
    }

    public listaAspectosPoseidos getAspectosPoseidos(){
        return this.AspectosPoseidos;
    }
}
