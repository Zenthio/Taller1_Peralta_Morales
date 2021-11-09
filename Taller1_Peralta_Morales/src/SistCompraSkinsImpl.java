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
    public SistCompraSkinsImpl(int cantClientes){
        listaClientes = new listaClientes(cantClientes);
        listaPersonajes = new listaPersonajes();
    }

    
    /** 
     * @return int
     */
    public int getPrivilegio(){
        return this.privilegio;
    }

    
    /** 
     * @return boolean
     */
    @Override
    public boolean iniciarSesion() {
        System.out.println("<==============================> INICIO DE SESIÓN <==============================>\n[1] Iniciar Sesión\n[2] Registrarse\n[3] Salir");
        System.out.print("Ingrese opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());
        if (opcion == 1){

            System.out.print("Ingresar nombre: ");
            String nombre = scanner.nextLine();
            if (nombre.equals("admin")){
                this.privilegio = 2;
                System.out.println("<================================================================================>\n                                        |\n                                        ▼");
            } else {
                if (listaClientes.getCliente(nombre) != null && !listaClientes.getCliente(nombre).getBloqueo()){
                    System.out.print("Ingresar contraseña: ");
                    String contraseña = scanner.nextLine();
                    if (listaClientes.getCliente(nombre) == null || !listaClientes.getCliente(nombre).getContraseña().equals(contraseña)){
                        System.out.println("Uno de los datos es erroneo o no existe, desea registrarse?\n[1] Sí\n[2] No");
                        int opcionA = Integer.parseInt(scanner.nextLine());
                        if (opcionA == 1){
                            System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                            registrarCuenta();
                        }
                        //throw new NullPointerException("Uno de los datos ingresados es erróneo o no existe.");
                    } else {
                        this.c = listaClientes.getCliente(nombre);
                        this.privilegio = 1;
                        return true;
                    }
                } else {
                    this.privilegio = 4;
                    throw new NullPointerException("Este usuario se encuentra bloqueado.");
                }
            }
        }
        if (opcion == 2){
            System.out.println("<================================================================================>                                        |\n                                        ▼\n");
            registrarCuenta();
        }
        if (opcion == 3){
            this.privilegio = 0;
            System.out.println("<================================================================================>\n                                        |\n                                        ▼");
            return true;
        }
        return false;
    
    }

    
    /** 
     * @return boolean
     */
    public boolean registrarCuenta(){
        System.out.println("<==============================> REGISTRO CUENTA <==============================>\n");
        System.out.println("Ingresar nombre de cuenta a registrar: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingresar ID(Nick) a registrar: ");
        String id = scanner.nextLine();
        if (listaClientes.getCliente(nombre).getBloqueo()){
            throw new NullPointerException("Este usuario se encuentra bloqueado.");
        }
        if (listaClientes.getCliente(nombre) != null){ //
            throw new NullPointerException("El nombre ya existe en el sistema");
        }
        System.out.println("Ingrese contraseña a registrar: ");
        String contraseña = scanner.nextLine();
        System.out.println("Ingresar región en la que se encuentra su cuenta: ");
        String region = scanner.nextLine();
        Cliente clienteNuevo = new Cliente(nombre, contraseña, id, 0, 0, region);
        if (!listaClientes.ingresarCliente(clienteNuevo)){
            throw new NullPointerException("Se alcanzó el límite de usuarios registrados");
        } else {
            System.out.println("<================================================================================>\n                                        |\n                                        ▼");
            return true;
        }
        
    }
    
    /** 
     * @param nombre
     * @param contraseña
     * @param id
     * @param nivel
     * @param rp
     * @param region
     * @return boolean
     */
    @Override
    public boolean crearCuenta(String nombre, String contraseña, String id, int nivel, int rp, String region) {
        if (listaClientes.getCliente(nombre) == null){
                Cliente c = new Cliente(nombre, contraseña, id, nivel, rp, region);
                if (!listaClientes.ingresarCliente(c)){
                    throw new NullPointerException("Se alcanzó el límite de usuarios registrados.");
                } else {
                    return true;
                }
                
            } else {
                throw new NullPointerException("No se encontró la cuenta con los datos ingresados.");
            }
        
    }
    
    /** 
     * @return boolean
     */
    @Override
    public boolean comprarSkin(){
        System.out.println("<==============================> COMPRA SKIN <==============================>\n");
        skinDisponibles();
        System.out.println("Ingrese nombre de personaje: \n");
        String nombreP = scanner.nextLine();
        if (listaPersonajes.getPersonaje(nombreP) == (null)){
            throw new NullPointerException("El personaje no existe.");
        } else {
            if (c.getPersonajesPoseidos().getPersonaje(nombreP) == (null)){
                throw new NullPointerException("El personaje no es poseido por la cuenta.");
            } else {
                System.out.println("Ingrese nombre de skin a comprar: \n");
                String nombreS = scanner.nextLine();
                PersonajePoseido personajeP = c.getPersonajesPoseidos().getPersonaje(nombreP);
                Personaje personaje = listaPersonajes.getPersonaje(nombreP);
                if (listaPersonajes.getPersonaje(nombreP).getListaAspectos().getAspecto(nombreS) == (null)){
                    throw new NullPointerException("El aspecto no existe para este personaje.");
                } else {                    
                    if (personajeP.getAspectosPoseidos().getAspecto(nombreS) != null){
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
                            System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                            return true;
                        }
                    }
                }
            }
        }
    }
    
    /** 
     * @return boolean
     */
    @Override
    public boolean comprarPersonaje() {
        System.out.println("<==============================> COMPRA PERSONAJE <==============================>\nEstos son los personajes que no poseé");
        personajesDisponibles();
        System.out.println("Ingrese nombre de personaje: \n");
        String nombreP = scanner.nextLine();
        if (listaPersonajes.getPersonaje(nombreP) == (null)){
            throw new NullPointerException("El personaje no existe.");
        } else {
            if (c.getPersonajesPoseidos().getPersonaje(nombreP) != (null)){
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
                    System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                    return true;
                }

            }
        }
    }
    
    /** 
     * @return String
     */
    @Override
    public String skinDisponibles() {
        System.out.println("<==============================> SKINS DISPONIBLES <==============================>\n");
        String retorno = "";
        for (int i = 0; i < c.getPersonajesPoseidos().getCantPersonajesPoseidos(); i++){
            PersonajePoseido p = c.getPersonajesPoseidos().getPersonajeI(i);
            retorno += "Personaje: "+p.getPersonaje().getNombre()+"\n";
            //System.out.println(p.getPersonaje().getListaAspectos().getCantAspectos());
            //System.out.println(p.getAspectosPoseidos().getCantAspectosPoseidos());
            for (int j = 0; j < p.getPersonaje().getListaAspectos().getCantAspectos(); j++){
                //System.out.println("Entré aquí al J");
                Aspecto a = p.getPersonaje().getListaAspectos().getAspectoI(j);
                //System.out.println(a.getNombre()+" se compara con: ");
                for (int k = 0; k < p.getAspectosPoseidos().getCantAspectosPoseidos(); k++){
                    //System.out.println("Entré aquí al K");
                    AspectoPoseido aP = p.getAspectosPoseidos().getAspectoI(k);
                    //System.out.print(aP.getAspecto().getNombre());
                    if (a.getNombre().equals(aP.getAspecto().getNombre())){
                        a.setContador(a.getContador()+1);
                        //retorno += "Skin: "+p.getPersonaje().getListaAspectos().getAspectoI(j).getNombre()+"\n";
                    }
                }
                if (a.getContador() == 0) {
                    retorno += "Skin: "+a.getNombre()+"\n";
                } else {
                    retorno+= "Ya posee todas las skins\n";
                }
            }
            retorno += "\n";
        }
            
        retorno +=  "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }

    
    /** 
     * @return String
     */
    public String personajesDisponibles(){
        String retorno = "";
        for (int i = 0; i < listaPersonajes.getCantPersonajes(); i++){
            Personaje p = listaPersonajes.getPersonajeI(i);
            for (int j = 0; j < c.getPersonajesPoseidos().getCantPersonajesPoseidos(); j++) {
                PersonajePoseido pP = c.getPersonajesPoseidos().getPersonajeI(j);
                if (p.getNombre().equals(pP.getPersonaje().getNombre())){
                    p.setContador(p.getContador()+1);
                }
            }
            if (p.getContador() == 0){
                retorno += "Personaje: "+p.getNombre()+"\n";
            } else {
                retorno += "Ya poseé todos los personajes\n";
            }
        }
        return retorno;
    }

    
    /** 
     * @return String
     */
    @Override
    public String mostrarInventario() {
        System.out.println("<==============================> MOSTRAR INVENTARIO <==============================>\n");
        String retorno = "";
         listaPersonajesPoseidos listaPj = c.getPersonajesPoseidos();
         for (int i = 0; i < listaPj.getCantPersonajesPoseidos(); i++){
            retorno += "Personaje: "+listaPj.getPersonajeI(i).getPersonaje().getNombre()+"\n";
            listaAspectosPoseidos listaAs = listaPj.getPersonajeI(i).getAspectosPoseidos();
            for (int j = 0; j < listaAs.getCantAspectosPoseidos(); j++){
                retorno += "Aspecto: "+listaAs.getAspectoI(j).getAspecto().getNombre()+"\n";
            }
         }
         retorno +=  "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }
    @Override
    public void recargarRp() {
        System.out.println("<==============================> RECARGAR RP <==============================>\n");
        System.out.println("Ingrese RP a cargar: \n");
        int rp = Integer.parseInt(scanner.nextLine());
        c.setRP(rp);
        System.out.println("<================================================================================>\n                                        |\n                                        ▼");
    }

    
    /** 
     * @return String
     */
    @Override
    public String datosCuenta() {
        System.out.println("<==============================> DATOS CUENTA <==============================>\n");
        String retorno = "";
        String contraEncriptada = "";
        for (int i = 0; i < c.getContraseña().length()-3; i++){
            contraEncriptada+= "*";
        }
        contraEncriptada+= c.getContraseña().substring(c.getContraseña().length()-3, c.getContraseña().length());
        retorno += "Nombre de cuenta: "+c.getNombre()+"\nNick: "+c.getId()+"\nContraseña: "+contraEncriptada;
        retorno +=  "\n<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }
    @Override
    public void cambiarClave() {
        System.out.println("<==============================> CAMBIO DE CLAVE <==============================>\n");
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
                    System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                }
            }
        }
    }
    
    /** 
     * @return String
     */
    @Override
    public String obtenerVentasRol() {
        System.out.println("<==============================> VENTAS ROL <==============================>\n");
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
        retorno +=  "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }
    
    /** 
     * @return String
     */
    @Override
    public String obtenerVentasRegion() {
        System.out.println("<==============================> VENTAS REGION <==============================>\n");
        String retorno = "";
        retorno+= "Ventas LAS: "+recaudacionLAS+" CLP\nVentas LAN: "+recaudacionLAN+" CLP\nVentas EUW: "+
        recaudacionEUW+" CLP\n Ventas KR: "+recaudacionKR+" CLP\nVentas NA: "+recaudacionNA+" CLP\nVentas RU: "+
        recaudacionRU+" CLP";
        retorno +=  "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }
    
    /** 
     * @return String
     */
    @Override
    public String obtenerVentasPersonaje() {
        System.out.println("<==============================> VENTAS PERSONAJE <==============================>\n");
        String retorno = "";
        for (int i = 0; i < listaPersonajes.getCantPersonajes(); i++){
            double recaudacion = listaPersonajes.getPersonajeI(i).getRecaudacion()*6.15;
            retorno+= "Personaje: "+listaPersonajes.getPersonajeI(i).getNombre()+" | Recaudación: "+recaudacion+" CLP\n";
        }
        retorno +=  "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }
    
    /** 
     * @return String
     */
    @Override
    public String obtenerPersonajeRol() {
        System.out.println("<==============================> PERSONAJES POR ROL <==============================>\n");
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
        retorno +=  "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }
    
    /** 
     * @return boolean
     */
    @Override
    public boolean registrarPersonaje() {
        System.out.println("<==============================> REGISTRAR PERSONAJE <==============================>\n");
        System.out.println("Ingrese nombre de personaje que desea agregar: \n");
        String nombreP = scanner.nextLine();
        if (listaPersonajes.getPersonaje(nombreP) != (null)){
            throw new NullPointerException("El nombre ingresado ya existe en el sistema.");
        } else {
            System.out.println("Ingresar su rol: \n");
            String rol = scanner.nextLine().toUpperCase();
            System.out.println("Ingresar cantidad de skins que posee");
            int cantSkins = Integer.parseInt(scanner.nextLine());
            Personaje personaje = new Personaje(nombreP, rol, 0);
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
                System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                return true;
            }
        }
    }

    
    /** 
     * @return boolean
     */
    @Override
    public boolean registrarSkin() {
        System.out.println("<==============================> REGISTRAR SKIN <==============================>\n");
        System.out.println("Ingrese nombre de personaje al cual le desea agregar skins: ");
        String nombreP = scanner.nextLine();
        if (listaPersonajes.getPersonaje(nombreP) == (null)){
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
                System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                return true;
            }
        }
    }

    
    /** 
     * @param nombre
     * @param rol
     * @param cantAspectos
     */
    @Override
    public void addPersonaje(String nombre, String rol, int cantAspectos){
        Personaje personaje = new Personaje(nombre, rol, cantAspectos);
        if (!listaPersonajes.ingresarPersonaje(personaje)){
            throw new NullPointerException("La lista de personajes está llena");
        }
    }

    
    /** 
     * @param nombreA
     * @param calidadA
     * @param nombreP
     */
    @Override
    public void addSkin(String nombreA, String calidadA, String nombreP){
        if (listaPersonajes.getPersonaje(nombreP) != null){
            Personaje p = listaPersonajes.getPersonaje(nombreP);
            Aspecto skin = new Aspecto(nombreA, calidadA,p);
            if (p.getListaAspectos().getAspecto(nombreA) == null){
                if (!p.getListaAspectos().ingresarAspecto(skin)){
                    throw new NullPointerException("La lista de aspectos está llena");
                }            
            } else {
                throw new NullPointerException("Ya se encuentra la skin en este personaje");
            }
        } else {
            throw new NullPointerException("No se encontró el personaje o no existe.");
        }
    }

    
    /** 
     * @param nombreP
     * @param nombreC
     */
    @Override
    public void addPersonajeCuenta(String nombreP, String nombreC){
        if (listaClientes.getCliente(nombreC) != null){
            Cliente cliente = listaClientes.getCliente(nombreC);
            if (listaPersonajes.getPersonaje(nombreP) != null){
                Personaje p = listaPersonajes.getPersonaje(nombreP);
                PersonajePoseido pP = new PersonajePoseido(cliente,p);
                if (cliente.getPersonajesPoseidos().getPersonaje(nombreP) == null){
                    if (!cliente.getPersonajesPoseidos().ingresarPersonaje(pP)){
                        throw new NullPointerException("Se alcanzó el límite de personajes poseídos");
                    }
                } else {
                    throw new NullPointerException("El cliente ya poseé el personaje");
                }
            } else {
                throw new NullPointerException("No existe el personaje");
            }
        } else {
            throw new NullPointerException("No existe el cliente");
        }
    }

    
    /** 
     * @param nombreP
     * @param nombreA
     * @param nombreC
     */
    @Override
    public void addSkinCuenta(String nombreP, String nombreA, String nombreC){
        if (listaClientes.getCliente(nombreC) != null){
            Cliente cliente = listaClientes.getCliente(nombreC);
            if (cliente.getPersonajesPoseidos().getPersonaje(nombreP) != null){
                if (listaPersonajes.getPersonaje(nombreP).getListaAspectos().getAspecto(nombreA) != null){
                    Aspecto a = listaPersonajes.getPersonaje(nombreP).getListaAspectos().getAspecto(nombreA);
                    AspectoPoseido aP = new AspectoPoseido(cliente,a);
                    if (cliente.getPersonajesPoseidos().getPersonaje(nombreP).getAspectosPoseidos().getAspecto(nombreA) == null){ //Si el personajeposeido no poseé la skin
                        if (!cliente.getPersonajesPoseidos().getPersonaje(nombreP).getAspectosPoseidos().ingresarAspecto(aP)){
                            throw new NullPointerException("Se alcanzó el límite de skins poseidas");
                        }
                    } else {
                        throw new NullPointerException("El personaje poseido ya poseé la skin");
                    }
                } else {
                    throw new NullPointerException("La skin no existe");
                } 
            } else {
                throw new NullPointerException("El cliente no poseé el personaje")  ;
            } 
                
        } else {
            throw new NullPointerException("El cliente no existe"); 
        }
    }
    

    
    /** 
     * @param nombreP
     * @param recaudacion
     */
    @Override
    public void addRecaudacion(String nombreP, int recaudacion) {
        if (listaPersonajes.getPersonaje(nombreP) != null){
            Personaje p = listaPersonajes.getPersonaje(nombreP);
            p.setRecaudacion(recaudacion);
        }
    }
    @Override
    public void bloquearJugador() {
        System.out.println("<==============================> BLOQUEO JUGADOR <==============================>\n");
        System.out.println("Ingresar nombre de cliente a bloquear: ");
        String nombre = scanner.nextLine();
        if (listaClientes.getCliente(nombre) == (null)){
            throw new NullPointerException("No se encontró al cliente");
        } else {
            Cliente c = listaClientes.getCliente(nombre);
            c.setBloqueo(true);
            System.out.println("<================================================================================>\n                                        |\n                                        ▼");
        }
        
    }
    @Override
    public void mayorMenor() {
        for (int i = 0; i <= listaClientes.getCantClientes()-2; i++){
            for (int j = i+1; j <=listaClientes.getCantClientes()-1; j++){
                Cliente clientei = listaClientes.getClienteI(i);
                Cliente clientej = listaClientes.getClienteI(j);
                if (listaClientes.getClienteI(i).getNivel() < listaClientes.getClienteI(j).getNivel()){
                    Cliente temp = new Cliente();
                    temp.copiar(clientei);
                    clientei.copiar(clientej);
                    clientej.copiar(temp);
                }
            }
        }
        
    }
    
    /** 
     * @return String
     */
    @Override
    public String obtenerCuentas() {
        System.out.println("<==============================> DESPLIEGUE CUENTAS <==============================>\n");
        String retorno = "";
        for (int i = 0; i < listaClientes.getCantClientes(); i++){
            Cliente cliente = listaClientes.getClienteI(i);
            retorno += "Nombre: "+cliente.getNombre()+" | Nivel: "+cliente.getNivel()+"\n";
        }
        retorno +=  "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }

    
    /** 
     * @return String
     */
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

    
    /** 
     * @return String
     */
    @Override
    public String obtenerDatosClientes() {
        String retorno = "";
        for (int i = 0; i < listaClientes.getCantClientes(); i++){
            Cliente cliente = listaClientes.getClienteI(i);
            if (i < listaClientes.getCantClientes()-1){
                retorno += cliente.getNombre()+","+cliente.getContraseña()+","+cliente.getId()+","+
                cliente.getNivel()+","+cliente.getRP()+","+cliente.getPersonajesPoseidos().getCantPersonajesPoseidos();
                for (int j = 0; j < cliente.getPersonajesPoseidos().getCantPersonajesPoseidos(); j++) {
                    retorno += ","+cliente.getPersonajesPoseidos().getPersonajeI(j).getPersonaje().getNombre()+","+cliente.getPersonajesPoseidos().getPersonajeI(j).getAspectosPoseidos().getCantAspectosPoseidos();
                    for (int k = 0; k < cliente.getPersonajesPoseidos().getPersonajeI(j).getAspectosPoseidos().getCantAspectosPoseidos(); k++){
                        retorno += ","+cliente.getPersonajesPoseidos().getPersonajeI(j).getAspectosPoseidos().getAspectoI(k).getAspecto().getNombre();
                    }
                }
                retorno +=","+cliente.getRegion();
                retorno += "\n";
            } else {
                retorno += cliente.getNombre()+","+cliente.getContraseña()+","+cliente.getId()+","+
                cliente.getNivel()+","+cliente.getPersonajesPoseidos().getCantPersonajesPoseidos();
                for (int j = 0; j < cliente.getPersonajesPoseidos().getCantPersonajesPoseidos(); j++) {
                    retorno += ","+cliente.getPersonajesPoseidos().getPersonajeI(j).getPersonaje().getNombre()+","+cliente.getPersonajesPoseidos().getPersonajeI(j).getAspectosPoseidos().getCantAspectosPoseidos();
                    for (int k = 0; k < cliente.getPersonajesPoseidos().getPersonajeI(j).getAspectosPoseidos().getCantAspectosPoseidos(); k++){
                        retorno += ","+cliente.getPersonajesPoseidos().getPersonajeI(j).getAspectosPoseidos().getAspectoI(k).getAspecto().getNombre();
                    }
                }
                retorno +=","+cliente.getRegion();
            }
        }
        return retorno;
    }

    
    /** 
     * @return String
     */
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