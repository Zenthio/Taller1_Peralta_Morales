public class Personaje{

    private String nombre;
    private String rol;
    private int precio = 915;
    private int recaudacion;
    private listaAspecto listaAspectos;
    private int cantAspectos;


    public Personaje(String nombre, String rol, int cantAspectos){
        this.nombre = nombre;
        this.rol = rol;
        listaAspectos = new listaAspecto();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getRecaudacion() {
        return this.recaudacion;
    }

    public void setRecaudacion(int recaudacion) {
        this.recaudacion = recaudacion;
    }

    public void addRecaudacion(int recaudacion) {
        this.recaudacion += recaudacion;
    }

    public listaAspecto getListaAspectos() {
        return this.listaAspectos;
    }

    public int getCantAspectos(){
        return this.cantAspectos;
    }

    public void setCantAspectos(int cantAspectos){
        this.cantAspectos = cantAspectos;
    }
    
    public int getPrecio(){
        return this.precio;
    }
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