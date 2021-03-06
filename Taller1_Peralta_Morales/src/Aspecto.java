public class Aspecto {
    
    private String nombre;
    private String calidad;
    private int precio;
    private Personaje personaje;
    private int contador;

    public Aspecto(String nombre, String calidad, Personaje personaje) {
        this.nombre = nombre;
        this.calidad = calidad;
        this.personaje = personaje;  
        if (calidad.equals("M")){
            this.precio = 3250;
        }
        if (calidad.equals("D")){
            this.precio = 2750;
        }
        if (calidad.equals("L")){
            this.precio = 1820;
        }
        if (calidad.equals("E")){
            this.precio = 1350;
        }
        if (calidad.equals("N")){
            this.precio = 975;
        }
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
    public String getCalidad() {
        return this.calidad;
    }

    
    /** 
     * @param calidad
     */
    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    
    /** 
     * @return int
     */
    public int getPrecio() {
        return this.precio;
    }

    
    /** 
     * @param precio
     */
    public void setPrecio(int precio) {
        this.precio = precio;
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
     * @return Personaje
     */
    public Personaje getPersonaje() {
        return this.personaje;
    }

    
    /** 
     * @param personaje
     */
    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
    
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", calidad='" + getCalidad() + "'" +
            ", precio='" + getPrecio() + "'" +
            ", personaje='" + getPersonaje() + "'" +
            "}";
    }


}
