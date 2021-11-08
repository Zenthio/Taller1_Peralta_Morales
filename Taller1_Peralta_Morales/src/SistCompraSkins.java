public interface SistCompraSkins{
    
    //RF2
    public boolean iniciarSesion();

    public boolean crearCuenta(String nombre, String contraseña, String id, int nivel, int rp, String region);

    //RF3 CLIENTE
    public boolean comprarSkin();

    public boolean comprarPersonaje();

    public String skinDisponibles();

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

    public void personajeSkin(Personaje personaje, Aspecto skin);

    public void personajeCuenta(Personaje personaje, Cliente cuenta);

    public void skinCuenta(AspectoPoseido aspectoP, Cliente cuenta, PersonajePoseido personajeP);

    public void dineroRecaudado();

    public void bloquearJugador();

    public void mayorMenor();

    public String obtenerCuentas();

    public listaPersonajes getListaPersonajes();

    public listaClientes getListaClientes();

    public int getPrivilegio();

    public String obtenerDatosPersonajes();

    public String obtenerDatosClientes();

    public String obtenerDatosRecaudacion();



}