

public class listaPersonajesPoseidos {

    private PersonajePoseido[] listaPersonajesPoseidos;
    private int max = 155;
    private int cantPersonajesPoseidos;

    public listaPersonajesPoseidos(){
        listaPersonajesPoseidos = new PersonajePoseido[this.max];
        this.cantPersonajesPoseidos = 0;
    }

    
    /** 
     * @param personaje
     * @return boolean
     */
    public boolean ingresarPersonaje(PersonajePoseido personaje) {
        if (cantPersonajesPoseidos < this.max){
            listaPersonajesPoseidos[cantPersonajesPoseidos] = personaje;
            this.cantPersonajesPoseidos++;
            return true;
        }
        return false;
    }

    
    /** 
     * @param nombre
     * @return PersonajePoseido
     */
    public PersonajePoseido getPersonaje(String nombre) {
        int i;
        for (i = 0; i < this.cantPersonajesPoseidos; i++){
            if (listaPersonajesPoseidos[i].getPersonaje().getNombre().equals(nombre)){
                return listaPersonajesPoseidos[i];
            }
        }
        return null;
    }
    
    /** 
     * @param cant
     */
    public void setCantPersonajesPoseidos(int cant){
        this.cantPersonajesPoseidos = cant;
    }

    
    /** 
     * @return int
     */
    public int getCantPersonajesPoseidos(){
        return this.cantPersonajesPoseidos;
    }

    
    /** 
     * @param i
     * @return PersonajePoseido
     */
    public PersonajePoseido getPersonajeI(int i){
        if (i >= 0 && i < this.cantPersonajesPoseidos){
            return listaPersonajesPoseidos[i];
        } else {
            return null;
        }
    }
}
