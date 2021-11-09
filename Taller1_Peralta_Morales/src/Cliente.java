public class Cliente {
    
    private String nombre;
    private String contraseña;
    private String id;
    private int nivel;
    private int RP = 0;
    private listaPersonajesPoseidos personajesPoseidos;
    private boolean bloqueo;
    private String region;

    public Cliente(String nombre, String contraseña, String id, int nivel, int RP, String region) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.id = id;
        this.nivel = nivel;
        this.RP = RP;
        this.bloqueo = false;
        this.region = region;
        personajesPoseidos = new listaPersonajesPoseidos();
    }

    public Cliente(){

    }


    
    /** 
     * @return String
     */
    public String getNombre() {
        return this.nombre;
    }

    
    /** 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * @return String
     */
    public String getContraseña() {
        return this.contraseña;
    }

    
    /** 
     * @param contraseña
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    
    /** 
     * @return String
     */
    public String getId() {
        return this.id;
    }

    
    /** 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    
    /** 
     * @return int
     */
    public int getNivel() {
        return this.nivel;
    }

    
    /** 
     * @param nivel
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    
    /** 
     * @return int
     */
    public int getRP() {
        return this.RP;
    }

    
    /** 
     * @param RP
     */
    public void setRP(int RP) {
        this.RP += RP;
    }

    
    /** 
     * @param numero
     */
    public void takeRP(int numero){
        this.RP -= numero;
    }
    
    /** 
     * @return listaPersonajesPoseidos
     */
    public listaPersonajesPoseidos getPersonajesPoseidos() {
        return this.personajesPoseidos;
    }

    
    /** 
     * @param personajesPoseidos
     */
    public void setPersonajesPoseidos(listaPersonajesPoseidos personajesPoseidos) {
        this.personajesPoseidos = personajesPoseidos;
    }

    
    /** 
     * @return boolean
     */
    public boolean getBloqueo() {
        return this.bloqueo;
    }

    
    /** 
     * @param bloqueo
     */
    public void setBloqueo(boolean bloqueo) {
        this.bloqueo = bloqueo;
    }

    
    /** 
     * @return String
     */
    public String getRegion() {
        return this.region;
    }

    
    /** 
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    
    /** 
     * @param cliente
     */
    public void copiar(Cliente cliente){
        this.setNombre(cliente.getNombre());
        this.setContraseña(cliente.getContraseña());
        this.setId(cliente.getId());
        this.setNivel(cliente.getNivel());
        this.setRP(cliente.getRP());
        this.setBloqueo(cliente.getBloqueo());
        this.setRegion(cliente.getRegion());
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", contraseña='" + getContraseña() + "'" +
            ", id='" + getId() + "'" +
            ", nivel='" + getNivel() + "'" +
            ", RP='" + getRP() + "'" +
            ", personajesPoseidos='" + getPersonajesPoseidos() + "'" +
            ", bloqueo='" + getBloqueo() + "'" +
            ", region='" + getRegion() + "'" +
            "}";
    }

}
