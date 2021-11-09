public class PersonajePoseido {
    private Cliente dueño;
    private Personaje personaje;
    private listaAspectosPoseidos AspectosPoseidos;
    
    public PersonajePoseido(Cliente dueño, Personaje personaje){
        this.dueño = dueño;
        this.personaje = personaje;
        AspectosPoseidos = new listaAspectosPoseidos();
    }

    
    /** 
     * @return Personaje
     */
    public Personaje getPersonaje(){
        return this.personaje;
    }

    
    /** 
     * @return listaAspectosPoseidos
     */
    public listaAspectosPoseidos getAspectosPoseidos(){
        return this.AspectosPoseidos;
    }
}
