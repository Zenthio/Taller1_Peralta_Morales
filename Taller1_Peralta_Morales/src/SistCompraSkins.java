public interface SistCompraSkins{
    
    //RF2
    public boolean iniciarSesion();

    public boolean crearCuenta(String nombre, String contraseña, String id, int nivel, int rp, String region);

    //RF3 CLIENTE
    public boolean comprarSkin();

    public boolean comprarPersonaje();

    public String skinDisponibles();

    public String personajesDisponibles();

    public String mostrarInventario();

    public void recargarRp();

    public String datosCuenta();

    public void cambiarClave();

    //RF4 ADMIN
    public String obtenerVentasRol();

    public String obtenerVentasRegion();

    public String obtenerVentasPersonaje();

    public String obtenerPersonajeRol();

    public boolean registrarPersonaje();

    public boolean registrarSkin();

    public void addPersonaje(String nombre, String rol, int cantAspectos);

    public void addSkin(String nombre, String calidad, String nombreP);

    public void addPersonajeCuenta(String nombreP, String nombreC);

    public void addSkinCuenta(String nombreP, String nombreA, String nombreC);

    public void addRecaudacion(String nombreP, int recaudacion);

    public void bloquearJugador();

    public void mayorMenor();

    public String obtenerCuentas();

    public int getPrivilegio();

    public String obtenerDatosPersonajes();

    public String obtenerDatosClientes();

    public String obtenerDatosRecaudacion();


}