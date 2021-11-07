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
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getRP() {
        return this.RP;
    }

    public void setRP(int RP) {
        this.RP += RP;
    }

    public void takeRP(int numero){
        this.RP -= numero;
    }
    public listaPersonajesPoseidos getPersonajesPoseidos() {
        return this.personajesPoseidos;
    }

    public void setPersonajesPoseidos(listaPersonajesPoseidos personajesPoseidos) {
        this.personajesPoseidos = personajesPoseidos;
    }

    public boolean isBloqueo() {
        return this.bloqueo;
    }

    public boolean getBloqueo() {
        return this.bloqueo;
    }

    public void setBloqueo(boolean bloqueo) {
        this.bloqueo = bloqueo;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void copiar(Cliente cliente){
        this.setNombre(cliente.getNombre());
        this.setContraseña(cliente.getContraseña());
        this.setId(cliente.getId());
        this.setNivel(cliente.getNivel());
        this.setRP(cliente.getRP());
        this.setBloqueo(cliente.getBloqueo());
        this.setRegion(cliente.getRegion());
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", contraseña='" + getContraseña() + "'" +
            ", id='" + getId() + "'" +
            ", nivel='" + getNivel() + "'" +
            ", RP='" + getRP() + "'" +
            ", personajesPoseidos='" + getPersonajesPoseidos() + "'" +
            ", bloqueo='" + isBloqueo() + "'" +
            ", region='" + getRegion() + "'" +
            "}";
    }

}
