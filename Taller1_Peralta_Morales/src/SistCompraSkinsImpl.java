import java.util.Scanner;

public class SistCompraSkinsImpl implements SistCompraSkins {

    private listaClientes listaClientes;
    private listaPersonajes listaPersonajes;
    private Cliente c;
    private int privilegio; 
    Scanner scanner = new Scanner(System.in);
    private static double recaudacionLAS = 0;
    private static double recaudacionLAN = 0;
    private static double recaudacionEUW = 0;
    private static double recaudacionKR = 0;
    private static double recaudacionNA = 0;
    private static double recaudacionRU = 0;
    private Cliente admin = new Cliente("admin","admin","admin",0,9999999,"NA");

    //Al tener precondicion, hay que controlar en el main el throw exception 
    public SistCompraSkinsImpl(int cantClientes){
        listaClientes = new listaClientes(cantClientes);
        listaPersonajes = new listaPersonajes();
    }

    public int getPrivilegio(){
        return this.privilegio;
    }

    @Override
    public boolean iniciarSesion() {
        System.out.println("<==============================> INICIO DE SESIÓN <==============================>\n[1] Iniciar Sesión\n[2] Registrarse\n[3] Salir");
        int opcion = Integer.parseInt(scanner.nextLine());
        if (opcion == 1){

            System.out.println("Ingresar nombre: ");
            String nombre = scanner.nextLine();
            System.out.println("Ingresar contraseña: ");
            String contraseña = scanner.nextLine();
            if (listaClientes.buscarCliente(nombre) == null || !listaClientes.buscarCliente(nombre).getContraseña().equals(contraseña)){
                System.out.println("Uno de los datos es erroneo o no existe, desea registrarse?\n[1] Sí\n[2] No");
                int opcionA = Integer.parseInt(scanner.nextLine());
                if (opcionA == 1){
                    registrarCuenta();
                }
                //throw new NullPointerException("Uno de los datos ingresados es erróneo o no existe.");
            } else {
                if (nombre.equals("admin") && contraseña.equals("admin")){
                    this.c.copiar(admin);
                    this.privilegio = 2;
                    return true;
                } else {
                    this.c = listaClientes.buscarCliente(nombre);
                    this.privilegio = 1;
                    return true;
                }
            }
        }
        if (opcion == 2){
            registrarCuenta();
        }
        if (opcion == 3){
            this.privilegio = 0;
        }
        return false;
    }

    public boolean registrarCuenta(){
        System.out.println("Ingresar nombre de cuenta a registrar: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingresar ID(Nick) a registrar: ");
        String id = scanner.nextLine();
        if (listaClientes.buscarCliente(nombre) == null || listaClientes.buscarCliente(nombre).getId().equals(id)){
            throw new NullPointerException("Uno de los datos ingresados ya existe en el sistema");
        } else {
            System.out.println("Ingrese contraseña a registrar: ");
            String contraseña = scanner.nextLine();
            System.out.println("Ingresar región en la que se encuentra su cuenta: ");
            String region = scanner.nextLine();
            Cliente clienteNuevo = new Cliente(nombre, contraseña, id, 0, 0, region);
            if (!listaClientes.ingresarCliente(clienteNuevo)){
                throw new NullPointerException("Se alcanzó el límite de usuarios registrados");
            } else {
                return true;
            }
        }
    }
    @Override
    public boolean crearCuenta(String nombre, String contraseña, String id, int nivel, int rp, String region) {
        // TODO Auto-generated method stub
        if (listaClientes.buscarCliente(nombre) == null){
                Cliente c = new Cliente(nombre, contraseña, id, nivel, rp, region);
                 if (!listaClientes.ingresarCliente(c)){
                     throw new NullPointerException("Se alcanzó el límite de usuarios registrados.");
                 } else {
                     return true;
                 }

        } else {
            throw new NullPointerException("No se encontró la cuenta con los datos ingresados.");
            //return false; el return sobra cuando se usa un throw
        }
        
    }
    @Override
    public boolean comprarSkin(){
        System.out.println("Ingrese nombre de personaje: \n");
        String nombreP = scanner.nextLine();
        if (listaPersonajes.getPersonaje(nombreP).equals(null)){
            throw new NullPointerException("El personaje no existe.");
        } else {
            if (c.getPersonajesPoseidos().getPersonaje(nombreP).equals(null)){
                throw new NullPointerException("El personaje no es poseido por la cuenta.");
            } else {
                System.out.println("Ingrese nombre de skin a comprar: \n");
                String nombreS = scanner.nextLine();
                PersonajePoseido personajeP = c.getPersonajesPoseidos().getPersonaje(nombreP);
                Personaje personaje = listaPersonajes.getPersonaje(nombreP);
                if (listaPersonajes.getPersonaje(nombreP).getListaAspectos().getAspecto(nombreS).equals(null)){
                    throw new NullPointerException("El aspecto no existe para este personaje.");
                } else {                    
                    if (!personajeP.getAspectosPoseidos().getAspecto(nombreS).equals(null)){
                        throw new NullPointerException("El aspecto ya está comprado para este personaje.");
                    } else {
                        AspectoPoseido aspectoPoseido = personajeP.getAspectosPoseidos().getAspecto(nombreS);
                        if (c.getRP() < aspectoPoseido.getAspecto().getPrecio()){
                            throw new NullPointerException("No tiene RP suficiente para comprar esta skin.");
                        } else {
                            personajeP.getAspectosPoseidos().ingresarAspecto(aspectoPoseido);
                            c.takeRP(aspectoPoseido.getAspecto().getPrecio());
                            personaje.addRecaudacion(aspectoPoseido.getAspecto().getPrecio());
                            if (c.getRegion().equals("LAS")){
                                recaudacionLAS += aspectoPoseido.getAspecto().getPrecio()*6.15;
                            }
                            if (c.getRegion().equals("LAN")){
                                recaudacionLAN += aspectoPoseido.getAspecto().getPrecio()*6.15;
                            }
                            if (c.getRegion().equals("EUW")){
                                recaudacionEUW += aspectoPoseido.getAspecto().getPrecio()*6.15;
                            }
                            if (c.getRegion().equals("KR")){
                                recaudacionKR += aspectoPoseido.getAspecto().getPrecio()*6.15;
                            }
                            if (c.getRegion().equals("NA")){
                                recaudacionNA += aspectoPoseido.getAspecto().getPrecio()*6.15;
                            }
                            if (c.getRegion().equals("RU")){
                                recaudacionRU += aspectoPoseido.getAspecto().getPrecio()*6.15;
                            }
                            c.setNivel(c.getNivel()+1);
                            return true;
                        }
                    }
                }
            }
        }
    }
    @Override
    public boolean comprarPersonaje() {
        System.out.println("Ingrese nombre de personaje: \n");
        String nombreP = scanner.nextLine();
        if (listaPersonajes.getPersonaje(nombreP).equals(null)){
            throw new NullPointerException("El personaje no existe.");
        } else {
            if (!c.getPersonajesPoseidos().getPersonaje(nombreP).equals(null)){
                throw new NullPointerException("El personaje es poseido por la cuenta.");
            } else {
                PersonajePoseido personajeP = c.getPersonajesPoseidos().getPersonaje(nombreP);
                Personaje personaje = listaPersonajes.getPersonaje(nombreP);
                if (personaje.getPrecio() > c.getRP()){
                    throw new NullPointerException("No tiene saldo suficiente para esta compra.");
                } else {
                    c.getPersonajesPoseidos().ingresarPersonaje(personajeP);
                    c.takeRP(personaje.getPrecio()); //
                    personaje.addRecaudacion(personaje.getPrecio());
                    c.setNivel(c.getNivel()+1);
                    return true;
                }

            }
        }
    }
    @Override
    public String skinDisponibles() {
        String retorno = "";
        for (int i = 0; i < c.getPersonajesPoseidos().getCantPersonajesPoseidos(); i++){
            PersonajePoseido p = c.getPersonajesPoseidos().getPersonajeI(i);
            retorno += "Personaje: "+p.getPersonaje().getNombre()+"\n";
            for (int j = 0; j < p.getPersonaje().getCantAspectos(); j++){
                for (int k = 0; k < p.getAspectosPoseidos().getCantAspectosPoseidos(); k++){
                    if (!p.getPersonaje().getListaAspectos().getAspectoI(j).getNombre().equals(p.getAspectosPoseidos().getAspectoI(k).getAspecto().getNombre())){
                        retorno += "Skin: "+p.getPersonaje().getListaAspectos().getAspectoI(j).getNombre()+"\n";
                    }
                }
            }
            retorno += "\n";
        }
            
        
        return retorno;
    }

    @Override
    public String mostrarInventario() {
        String retorno = "";
         listaPersonajesPoseidos listaPj = c.getPersonajesPoseidos();
         for (int i = 0; i < listaPj.getCantPersonajesPoseidos(); i++){
            retorno += "Personaje: "+listaPj.getPersonajeI(i).getPersonaje().getNombre()+"\n";
            listaAspectosPoseidos listaAs = listaPj.getPersonajeI(i).getAspectosPoseidos();
            for (int j = 0; j < listaAs.getCantAspectosPoseidos(); j++){
                retorno += "Aspecto: "+listaAs.getAspectoI(j).getAspecto().getNombre()+"\n";
            }
         }
        return retorno;
    }
    @Override
    public void recargarRp() {
        System.out.println("Ingrese RP a cargar: \n");
        int rp = Integer.parseInt(scanner.nextLine());
        c.setRP(rp);
    }
    @Override
    public String datosCuenta() {
        String retorno = "";
        String contraEncriptada = "";
        for (int i = 0; i < c.getContraseña().length()-3; i++){
            contraEncriptada+= "*";
        }
        contraEncriptada+= c.getContraseña().substring(c.getContraseña().length()-3, c.getContraseña().length());
        retorno += "Nombre de cuenta: "+c.getNombre()+"\nNick: "+c.getId()+"\nContraseña: "+contraEncriptada;

        return retorno;
    }
    @Override
    public void cambiarClave() {
        System.out.println("Ingrese su antigua contraseña: \n");
        String contraseñaPrueba = scanner.nextLine();
        if (c.getContraseña().equals(contraseñaPrueba)){
            while(true){
                System.out.println("Ingrese nueva contraseña: \n");
                String nuevaContraseña = scanner.nextLine();
                System.out.println("Ingrese la nueva contraseña nuevamente: \n");
                String nuevaContraseña2 = scanner.nextLine();

                if (!nuevaContraseña.equals(nuevaContraseña2)){
                    System.out.println("Las contraseñas no son iguales, intentar nuevamente.");
                    
                } else {
                    c.setContraseña(nuevaContraseña);
                    System.out.println("Contraseña cambiada exitosamente.");
                }
            }
        }
    }
    @Override
    public String obtenerVentasRol() {
        String retorno = "";
        double recaudacionADC = 0;
        double recaudacionSUP = 0;
        double recaudacionTOP = 0;
        double recaudacionMID = 0;
        double recaudacionJG = 0;

        for (int i = 0; i < listaPersonajes.getCantPersonajes(); i++) {
            if (listaPersonajes.getPersonajeI(i).getRol().equals("ADC")){

                recaudacionADC += listaPersonajes.getPersonajeI(i).getRecaudacion();
                recaudacionADC *= 6.15;
            }
            if (listaPersonajes.getPersonajeI(i).getRol().equals("SUP")){

                recaudacionSUP += listaPersonajes.getPersonajeI(i).getRecaudacion();
                recaudacionSUP *= 6.15;
            }
            if (listaPersonajes.getPersonajeI(i).getRol().equals("TOP")){

                recaudacionTOP += listaPersonajes.getPersonajeI(i).getRecaudacion();
                recaudacionTOP *= 6.15;
            }
            if (listaPersonajes.getPersonajeI(i).getRol().equals("MID")){

                recaudacionMID += listaPersonajes.getPersonajeI(i).getRecaudacion();
                recaudacionMID *= 6.15;
            }
            if (listaPersonajes.getPersonajeI(i).getRol().equals("JG")){

                recaudacionJG += listaPersonajes.getPersonajeI(i).getRecaudacion();
                recaudacionJG *= 6.15;
            }
        }
        retorno += "Recaudación de Personajes ADC: "+recaudacionADC+" CLP\nRecaudación de Personajes SUP: "+
        recaudacionSUP+" CLP\nRecaudación de TOP: "+recaudacionTOP+" CLP\nRecaudación de Personajes MID: "+
        recaudacionMID+ "CLP\nRecaudación de JG: "+recaudacionJG+" CLP\n";
        return retorno;
    }
    @Override
    public String obtenerVentasRegion() {
        String retorno = "";
        retorno+= "Ventas LAS: "+recaudacionLAS+" CLP\nVentas LAN: "+recaudacionLAN+" CLP\nVentas EUW: "+
        recaudacionEUW+" CLP\n Ventas KR: "+recaudacionKR+" CLP\nVentas NA: "+recaudacionNA+" CLP\nVentas RU: "+
        recaudacionRU+" CLP";
        return retorno;
    }
    @Override
    public String obtenerVentasPersonaje() {
        String retorno = "";
        for (int i = 0; i < listaPersonajes.getCantPersonajes(); i++){
            double recaudacion = listaPersonajes.getPersonajeI(i).getRecaudacion()*6.15;
            retorno+= "Personaje: "+listaPersonajes.getPersonajeI(i).getNombre()+" | Recaudación: "+recaudacion+" CLP\n";
        }
        return retorno;
    }
    @Override
    public String obtenerPersonajeRol() {
        String retorno = "";
        int cantidadADC = 0;
        int cantidadSUP = 0;
        int cantidadTOP = 0;
        int cantidadMID = 0;
        int cantidadJG = 0;
        for (int i = 0; i < listaPersonajes.getCantPersonajes(); i++) {
            if (listaPersonajes.getPersonajeI(i).getRol().equals("ADC")){
                cantidadADC++;
            }
            if (listaPersonajes.getPersonajeI(i).getRol().equals("SUP")){
                cantidadSUP++;
            }
            if (listaPersonajes.getPersonajeI(i).getRol().equals("TOP")){
                cantidadTOP++;
            }
            if (listaPersonajes.getPersonajeI(i).getRol().equals("MID")){
                cantidadMID++;
            }
            if (listaPersonajes.getPersonajeI(i).getRol().equals("JG")){
                cantidadJG++;
            }
        }
        retorno += "Cantidad de campeones ADC: "+cantidadADC+"\nCantidad de campeones SUP: "+
        cantidadSUP+"\nCantidad de campeones TOP: "+cantidadTOP+"\nCantidad de campeones MID: "+
        cantidadMID+"\nCantidad de campeones JG: "+cantidadJG;
        return retorno;
    }
    @Override
    public boolean registrarPersonaje() {
        System.out.println("Ingrese nombre de personaje que desea agregar: \n");
        String nombreP = scanner.nextLine();
        if (!listaPersonajes.getPersonaje(nombreP).equals(null)){
            throw new NullPointerException("El nombre ingresado ya existe en el sistema.");
        } else {
            System.out.println("Ingresar su rol: \n");
            String rol = scanner.nextLine().toUpperCase();
            System.out.println("Ingresar cantidad de skins que posee");
            int cantSkins = Integer.parseInt(scanner.nextLine());
            Personaje personaje = new Personaje(nombreP, rol);
            if (!listaPersonajes.ingresarPersonaje(personaje)){
                throw new NullPointerException("Ya se alcanzó el límite de personajes en el sistema.");
            } else {
                listaAspecto lista = personaje.getListaAspectos();
                for (int i = 0; i < cantSkins; i++){
                    System.out.println("Ingresar nombre de la skin n°"+i+": ");
                    String nombreS = scanner.nextLine();
                    System.out.println("Ingresar calidad (M,D,L,E,N) : ");
                    String calidad = scanner.nextLine();
                    Aspecto a = new Aspecto(nombreS, calidad, personaje);
                    if (!lista.ingresarAspecto(a)){
                        throw new NullPointerException("Ya se alcanzó el límite de skins para el personaje.");
                    }
                    
                }
                return true;
            }
        }
    }

    @Override
    public boolean registrarSkin() {
        System.out.println("Ingrese nombre de personaje al cual le desea agregar skins: ");
        String nombreP = scanner.nextLine();
        if (listaPersonajes.getPersonaje(nombreP).equals(null)){
            throw new NullPointerException("No existe algún personaje con el nombre ingresado: ");
        } else {
            Personaje personaje = listaPersonajes.getPersonaje(nombreP);
            listaAspecto lista = personaje.getListaAspectos();
            System.out.println("Ingrese cantidad de skins a agregar: ");
            int cantSkins = Integer.parseInt(scanner.nextLine());
            if (lista.getCantAspectos() + cantSkins > lista.getMax()){
                throw new NullPointerException("Las skins agregadas superarán el máximo de skins por personaje.");
            } else {
                for (int i = 0; i < cantSkins; i++) {
                    System.out.println("Ingresar nombre de la skin n°"+i+": ");
                    String nombreS = scanner.nextLine();
                    System.out.println("Ingresar calidad (M,D,L,E,N) : ");
                    String calidad = scanner.nextLine();
                    Aspecto a = new Aspecto(nombreS, calidad, personaje);
                    if (!lista.ingresarAspecto(a)){
                        throw new NullPointerException("Ya se superó el número máximo de skins");
                    }
                }
                return true;
            }
        }
    }

    @Override
    public void personajeSkin(Personaje personaje, Aspecto skin) {
        if (listaPersonajes.getPersonaje(personaje.getNombre()).equals(null)){
            throw new NullPointerException("El personaje no existe.");
        } else {
            if (personaje.getListaAspectos().getAspecto(skin.getNombre()).equals(null)){
                throw new NullPointerException("La skin no existe.");
            } else {
                if (!personaje.getListaAspectos().ingresarAspecto(skin)){
                    throw new NullPointerException("La skin ya se encuentra en el personaje.");
                }
            }
        }
        
    }
    @Override
    public void personajeCuenta(Personaje personaje, Cliente cuenta) {
        if (listaPersonajes.getPersonaje(personaje.getNombre()).equals(null)){
            throw new NullPointerException("El personaje no existe");
        } else {
            PersonajePoseido personajeP = new PersonajePoseido(cuenta, personaje);
            if (listaClientes.buscarCliente(cuenta.getNombre()).equals(null)){
                throw new NullPointerException("El cliente no existe");
            } else {
                if (!cuenta.getPersonajesPoseidos().ingresarPersonaje(personajeP));
            }
        }
        
    }
    @Override
    public void skinCuenta(AspectoPoseido aspectoP, Cliente cuenta, PersonajePoseido personajeP) {
        if (cuenta.getPersonajesPoseidos().getPersonaje(personajeP.getPersonaje().getNombre()).equals(null)){
            throw new NullPointerException("El personaje no existe en el cliente.");
        } else {
                if (!personajeP.getAspectosPoseidos().getAspecto(aspectoP.getAspecto().getNombre()).equals(null)){
                    throw new NullPointerException("El aspecto ya existe en el personaje.");
                } else {
                    if (!personajeP.getAspectosPoseidos().ingresarAspecto(aspectoP)){
                        throw new NullPointerException("Ya se alcanzó el límite de skins del personaje.");
                    }
                }
        }
    }

    @Override
    public void dineroRecaudado() {
        
    }
    @Override
    public void bloquearJugador() {
        System.out.println("Ingresar nombre de cliente a bloquear: ");
        String nombre = scanner.nextLine();
        if (listaClientes.buscarCliente(nombre).equals(null)){
            throw new NullPointerException("No se encontró al cliente");
        } else {
            Cliente c = listaClientes.buscarCliente(nombre);
            c.setBloqueo(true);
        }
        
    }
    @Override
    public void mayorMenor() {
        for (int i = 0; i <= listaClientes.getCantClientes()-2; i++){
            for (int j = i+1; j <=listaClientes.getCantClientes()-1; j++){
                Cliente clientei = listaClientes.getClienteI(i);
                Cliente clientej = listaClientes.getClienteI(j);
                if (listaClientes.getClienteI(i).getNivel() < listaClientes.getClienteI(j).getNivel()){
                    Cliente aux = listaClientes.getClienteI(i);
                    clientei.copiar(clientej);
                    clientej.copiar(aux);
                }
            }
        }
        
    }
    @Override
    public String obtenerCuentas() {
        String retorno = "";
        for (int i = 0; i < listaClientes.getCantClientes(); i++){
            Cliente cliente = listaClientes.getClienteI(i);
            retorno += "Nombre: "+cliente.getNombre()+" | Nivel: "+cliente.getNivel()+"\n";
        }
        return retorno;
    }

    @Override
    public listaPersonajes getListaPersonajes() {
        return this.listaPersonajes;
    }

    @Override
    public listaClientes getListaClientes(){
        return this.listaClientes;
    }

    @Override
    public String obtenerDatosPersonajes() {
        String retorno = "";
        for (int i = 0; i < listaPersonajes.getCantPersonajes(); i++){
            Personaje personaje = listaPersonajes.getPersonajeI(i);
            if (i < listaPersonajes.getCantPersonajes()-1){
                retorno += personaje.getNombre()+","+personaje.getRol()+","+personaje.getListaAspectos().getCantAspectos();
                for (int j = 0; j < personaje.getListaAspectos().getCantAspectos(); j++){
                    retorno += ","+personaje.getListaAspectos().getAspectoI(j).getNombre()+","+personaje.getListaAspectos().getAspectoI(j).getCalidad();
                }
                retorno += "\n";
            } else {
                retorno += personaje.getNombre()+","+personaje.getRol()+","+personaje.getListaAspectos().getCantAspectos();
                for (int j = 0; j < personaje.getListaAspectos().getCantAspectos(); j++){
                    retorno += ","+personaje.getListaAspectos().getAspectoI(j).getNombre()+","+personaje.getListaAspectos().getAspectoI(j).getCalidad();
                }
            }
        }
        return retorno;
    }

    @Override
    public String obtenerDatosClientes() {
        String retorno = "";
        for (int i = 0; i < listaClientes.getCantClientes(); i++){
            Cliente cliente = listaClientes.getClienteI(i);
            if (i < listaClientes.getCantClientes()-1){
                retorno += cliente.getNombre()+","+cliente.getContraseña()+","+cliente.getId()+","+
                cliente.getNivel()+","+cliente.getPersonajesPoseidos().getCantPersonajesPoseidos();
                for (int j = 0; j < cliente.getPersonajesPoseidos().getCantPersonajesPoseidos(); j++) {
                    retorno += ","+cliente.getPersonajesPoseidos().getPersonajeI(i).getPersonaje().getNombre();
                    for (int k = 0; k < cliente.getPersonajesPoseidos().getPersonajeI(i).getAspectosPoseidos().getCantAspectosPoseidos(); k++){
                        retorno += ","+cliente.getPersonajesPoseidos().getPersonajeI(i).getAspectosPoseidos().getAspectoI(k).getAspecto().getNombre();
                    }
                }
                retorno += "\n";
            } else {
                retorno += cliente.getNombre()+","+cliente.getContraseña()+","+cliente.getId()+","+
                cliente.getNivel()+","+cliente.getPersonajesPoseidos().getCantPersonajesPoseidos();
                for (int j = 0; j < cliente.getPersonajesPoseidos().getCantPersonajesPoseidos(); j++) {
                    retorno += ","+cliente.getPersonajesPoseidos().getPersonajeI(i).getPersonaje().getNombre();
                    for (int k = 0; k < cliente.getPersonajesPoseidos().getPersonajeI(i).getAspectosPoseidos().getCantAspectosPoseidos(); k++){
                        retorno += ","+cliente.getPersonajesPoseidos().getPersonajeI(i).getAspectosPoseidos().getAspectoI(k).getAspecto().getNombre();
                    }
                }
            }
        }
        return retorno;
    }

    @Override
    public String obtenerDatosRecaudacion() {
        String retorno = "";
        for (int i = 0; i < listaPersonajes.getCantPersonajes(); i++){
            Personaje personaje = listaPersonajes.getPersonajeI(i);
            if ( i < listaPersonajes.getCantPersonajes()-1){
                retorno += personaje.getNombre()+","+personaje.getRecaudacion()+"\n";
            } else {
                retorno += personaje.getNombre()+","+personaje.getRecaudacion();
            }
        }
        return retorno;
    }

}