import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
public class App {
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        SistCompraSkins sys = new SistCompraSkinsImpl(5000);
        lecturaPersonajes(sys);
        lecturaCuentas(sys);
        lecturaRecaudacion(sys);
        menu(sys,scanner);
    }

    
    /** 
     * @param sys
     * @throws IOException
     */
    public static void lecturaPersonajes(SistCompraSkins sys) throws IOException {
        try {
            Scanner arch = new Scanner(new File("Personajes.txt"));
            while(arch.hasNextLine()) {
                String line = arch.nextLine();
                String[] datos = line.split(",");
                String nombreP = datos[0];
                String rol = datos[1];
                int cantAspectos = Integer.parseInt(datos[2]);
                sys.addPersonaje(nombreP, rol, cantAspectos);  
                for (int i = 3; i < datos.length; i+=2){
                    String nombreA = datos[i];
                    String calidadA = datos[i+1];
                    sys.addSkin(nombreA, calidadA, nombreP);                 
                }
                
    
            }
            arch.close();
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        
    }

    
    /** 
     * @param sys
     * @throws IOException
     */
    public static void lecturaCuentas(SistCompraSkins sys) throws IOException {
        try {
            Scanner arch = new Scanner(new File("Cuentas.txt"));
            while (arch.hasNextLine()){
                String line = arch.nextLine();
                String[] datos = line.split(",");
                String nombreC = datos[0];
                String contraseña = datos[1];
                String id = datos[2];
                int nivel = Integer.parseInt(datos[3]);
                int rp = Integer.parseInt(datos[4]);
                String region = datos[datos.length-1];
                sys.crearCuenta(nombreC,contraseña,id,nivel,rp,region);
                int i;
                for (i = 6; i < datos.length-1; i+= (2+Integer.parseInt(datos[i+1]))){
                    String nombreP = datos[i];
                    sys.addPersonajeCuenta(nombreP, nombreC);
                    int cantskins = Integer.parseInt(datos[i+1]);
                    for (int j = 1; j <= cantskins; j++){
                        String nombreA = datos[i+j+1];
                        sys.addSkinCuenta(nombreP,nombreA,nombreC);
                    }
                }
        
    
            }
        } catch (IOException | NullPointerException e){
                System.out.println(e.getMessage());
         }
    }

    
    /** 
     * @param sys
     * @throws IOException
     */
    public static void lecturaRecaudacion(SistCompraSkins sys) throws IOException {
        try {
            Scanner arch = new Scanner(new File("Estadisticas.txt"));
            while (arch.hasNextLine()){
                String line = arch.nextLine();
                String[] datos = line.split(",");
                String nombre = datos[0];
                int recaudacion = Integer.parseInt(datos[1]);
                sys.addRecaudacion(nombre,recaudacion);
    
                
    
            }
        } catch (IOException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    
    /** 
     * @param sys
     * @param scanner
     */
    public static void menu(SistCompraSkins sys, Scanner scanner){
        while (true){
            try {
                sys.iniciarSesion();
            } catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
            if (sys.getPrivilegio() == 1){
                menuCliente(sys, scanner);
            }
            if (sys.getPrivilegio() == 2){
                menuAdmin(sys, scanner);
            }
            if (sys.getPrivilegio() == 0){
                break;
            }

        }
        escritura(sys);
    }

    
    /** 
     * @param sys
     * @param scanner
     */
    private static void menuCliente(SistCompraSkins sys, Scanner scanner) {

        
        boolean salir = false;
        int opcion;
        do{
            System.out.println("<================================> MENU CLIENTE <================================>\n");
            System.out.println("[1] Comprar Skin\n[2] Comprar Personaje\n[3] Skins Disponibles\n[4] Mostrar Inventario"
            +"\n[5] Recargar RP\n[6] Mostrar datos de cuenta\n[7] Salir");
            try {
                System.out.print("Ingrese su opción: ");
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                    try {
                        sys.comprarSkin();
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;
    
                    case 2:
                    try {
                        sys.comprarPersonaje();
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;
    
                    case 3:
                    try {
                        System.out.println(sys.skinDisponibles());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;
    
                    case 4:
                    try {
                        System.out.println(sys.mostrarInventario());
    
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;
    
                    case 5:
                    try {
                        sys.recargarRp();
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;
    
                    case 6:
                    try {
                        System.out.println(sys.datosCuenta());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;
    
                    case 7:
                    System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                    salir = true;
                    break;
    
                    default:
                    System.out.println("La opción ingresada no es correcta");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar solo caracteres numéricos");
            }
        } while (salir != true);
    }
    

    
    /** 
     * @param sys
     * @param scanner
     */
    public static void menuAdmin(SistCompraSkins sys, Scanner scanner){
        
        boolean salir = false;
        int opcion;

        do {
            System.out.println("<================================> MENU ADMIN <================================>\n");
            System.out.println("[1] Desplegar recaudación de ventas por rol\n[2] Desplegar recaudación de ventas por región\n"
            +"[3] Desplegar recaudación de ventas por personaje\n[4] Desplegar cantidad de personajes por rol\n"
            +"[5] Agregar un personaje\n[6] Agregar una skin\n[7] Bloquear a un jugador\n[8] Desplegar cuentas\n[9] Salir");

            try {
                System.out.print("Ingrese su opción: ");
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                    try {
                        System.out.println(sys.obtenerVentasRol());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 2:
                    try {
                        System.out.println( sys.obtenerVentasRegion());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 3:
                    try {
                        System.out.println(sys.obtenerVentasPersonaje());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 4:
                    try {
                        System.out.println(sys.obtenerPersonajeRol());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 5:
                    try {
                        sys.registrarPersonaje();
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 6:
                    try {
                        sys.registrarSkin();
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 7:
                    try {
                        sys.bloquearJugador();
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }

                    case 8:
                    try {
                        sys.mayorMenor();
                        System.out.println(sys.obtenerCuentas());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 9:
                    System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                    salir = true;
                    break;

                    default:
                    System.out.println("La opción ingresada no es correcta.");
                    break;
                }
            } catch (NumberFormatException e){
                System.out.println("Se debe de ingresar sólo carácteres numéricos");
            }
        } while (salir != true);
    }

    
    /** 
     * @param sys
     */
    public static void escritura(SistCompraSkins sys){
        System.out.println("<==============================> ESCRITURA DE ARCHIVOS <==============================>\n");
        try {
            BufferedWriter personajes = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Personajess.txt")));
            personajes.write(sys.obtenerDatosPersonajes());
            personajes.close();
        } catch (IOException e){ 
            System.out.println(e.getMessage());
        }

        try {
            BufferedWriter clientes = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Cuentass.txt")));
            clientes.write(sys.obtenerDatosClientes());
            clientes.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        try {
            BufferedWriter recaudacion = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Estadisticass.txt")));
            recaudacion.write(sys.obtenerDatosRecaudacion());
            recaudacion.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Gracias por usar nuestro sistema, hasta luego! <3");
        System.out.println("<================================================================================>");
    }




} // Fin App
