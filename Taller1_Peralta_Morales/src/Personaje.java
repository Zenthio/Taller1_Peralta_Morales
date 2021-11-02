public class Personaje{

    private String nombre;
    private String rol;
    private int precio;
    private int recaudacion;
    private listaAspecto[] listaAspectos;


    public Personaje(String nombre, String rol, int precio, int recaudacion, listaAspecto[] listaAspectos) {
        this.nombre = nombre;
        this.rol = rol;
        this.recaudacion = recaudacion;
        this.listaAspectos = listaAspectos;
    
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

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getRecaudacion() {
        return this.recaudacion;
    }

    public void setRecaudacion(int recaudacion) {
        this.recaudacion = recaudacion;
    }

    public listaAspecto[] getListaAspectos() {
        return this.listaAspectos;
    }

    public void setListaAspectos(listaAspecto[] listaAspectos) {
        this.listaAspectos = listaAspectos;
    }
        
    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", rol='" + getRol() + "'" +
            ", precio='" + getPrecio() + "'" +
            ", recaudacion='" + getRecaudacion() + "'" +
            ", listaAspectos='" + getListaAspectos() + "'" +
            "}";
    }
    

    
}