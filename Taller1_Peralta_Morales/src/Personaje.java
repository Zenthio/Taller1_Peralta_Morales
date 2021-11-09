public class Personaje{

    private String nombre;
    private String rol;
    private int precio = 915;
    private int recaudacion;
    private listaAspecto listaAspectos;
    private int cantAspectos;
    private int contador;


    public Personaje(String nombre, String rol, int cantAspectos){
        this.nombre = nombre;
        this.rol = rol;
        listaAspectos = new listaAspecto();
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
    public String getRol() {
        return this.rol;
    }

    
    /** 
     * @param rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    
    /** 
     * @return int
     */
    public int getRecaudacion() {
        return this.recaudacion;
    }

    
    /** 
     * @param recaudacion
     */
    public void setRecaudacion(int recaudacion) {
        this.recaudacion = recaudacion;
    }

    
    /** 
     * @param recaudacion
     */
    public void addRecaudacion(int recaudacion) {
        this.recaudacion += recaudacion;
    }

    
    /** 
     * @return listaAspecto
     */
    public listaAspecto getListaAspectos() {
        return this.listaAspectos;
    }

    
    /** 
     * @return int
     */
    public int getCantAspectos(){
        return this.cantAspectos;
    }

    
    /** 
     * @param cantAspectos
     */
    public void setCantAspectos(int cantAspectos){
        this.cantAspectos = cantAspectos;
    }
    
    
    /** 
     * @return int
     */
    public int getPrecio(){
        return this.precio;
    }

    
    /** 
     * @return int
     */
    public int getContador(){
        return this.contador;
    }

    
    /** 
     * @param contador
     */
    public void setContador(int contador){
        this.contador = contador;
    }
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", rol='" + getRol() + "'" +
            ", recaudacion='" + getRecaudacion() + "'" +
            ", listaAspectos='" + getListaAspectos() + "'" +
            "}";
    }
    

    
}