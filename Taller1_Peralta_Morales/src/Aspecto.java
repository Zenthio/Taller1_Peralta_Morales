public class Aspecto {
    
    private String nombre;
    private String calidad;
    private int precio;
    private Personaje personaje;


    public Aspecto(String nombre, String calidad, int precio, Personaje personaje) {
        this.nombre = nombre;
        this.calidad = calidad;
        this.precio = precio;
        this.personaje = personaje;

    }
    
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalidad() {
        return this.calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Personaje getPersonaje() {
        return this.personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
    
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
