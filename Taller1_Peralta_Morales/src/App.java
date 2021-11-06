import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class App {
    public static void main(String[] args) throws Exception {
        String line = "NotPepito,PepitoGamer777,vegetta1111,434,12000,2,Vex,1,Portadora del amanecer,Jhin,2,Oscuridad Cosmica,forajido,LAS";
        String[] datos = line.split(",");
        String nombre = datos[0];
        String contra = datos[1];
        String id = datos[2];
        int lv = Integer.parseInt(datos[3]);
        int rp = Integer.parseInt(datos[4]);
        String server = datos[datos.length-1];
        int i;
            for (i = 6; i < datos.length-1; i+= (2+Integer.parseInt(datos[i+1]))){
                System.out.println("Nombre c: "+datos[i]);
                int cantskins = Integer.parseInt(datos[i+1]);
                for (int j = 1; j <= cantskins; j++){
                   System.out.println("Nombre a: "+datos[i+j+1]);
                }
            }


    } // Fin Main

    public void lecturaPersonajes() throws IOException {
        
        Scanner arch = new Scanner(new File("personajes.txt"));
        listaPersonajes listapj = new listaPersonajes();
        while(arch.hasNextLine()) {
            Personaje p = new Personaje();
            String line = arch.nextLine();
            String[] datos = line.split(",");
            p.setNombre(datos[0]);
            p.setRol(datos[1]);
            p.setCantAspectos(Integer.parseInt(datos[2]));
            for (int i = 3; i < datos.length; i+=2){
                Aspecto a = new Aspecto();
                a.setNombre(datos[i]);
                a.setCalidad(datos[i+1]);
                p.getListaAspectos().ingresarAspecto(a);
            }
            listapj.ingresarPersonaje(p);
            

        }
        arch.close();
    }

    public void lecturaCuentas() throws IOException {
        Scanner arch = new Scanner(new File("cuentas.txt"));
        listaClientes listacl = new listaClientes();
        while (arch.hasNextLine()){
            Cliente c = new Cliente();
            String line = arch.nextLine();
            String[] datos = line.split(",");
            c.setNombre(datos[0]);
            c.setContraseña(datos[1]);
            c.setId(datos[2]);
            c.setNivel(Integer.parseInt(datos[3]));
            c.setRP(Integer.parseInt(datos[4]));
            c.getPersonajesPoseidos().setCantPersonajesPoseidos(Integer.parseInt(datos[5]));
            int i;
            for (i = 6; i < datos.length; i+= (2+Integer.parseInt(datos[i+1]))){
                c.getPersonajesPoseidos().ingresarPersonaje(datos[i]);
                int cantskins = Integer.parseInt(datos[i+1]);
                for (int j = 1; j <= cantskins; j++){
                    c.getPersonajesPoseidos().getPersonajePoseido(datos[i]).getAspectosPoseidos().ingresarAspecto(datos[i+j+1]);
                }
            }
            c.setRegion(datos[datos.length-1]);

        }
    }

    public void lecturaRecaudacion(listaPersonajes listaPersonajes) throws IOException {
        Scanner arch = new Scanner(new File("estadisticas.txt"));
        while (arch.hasNextLine()){
            String line = arch.nextLine();
            String[] datos = line.split(",");
            String nombre = datos[0];
            String recaudacion = datos[1];
            if (listaPersonajes.buscarPersonaje(nombre) != null){
                listaPersonajes.buscarPersonaje(nombre).setRecaudacion(Integer.parseInt(recaudacion));
            }

            

        }
    }





} // Fin App
