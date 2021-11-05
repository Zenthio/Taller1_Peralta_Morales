import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class App {
    public static void main(String[] args) throws Exception {



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
            for (int i = 2; i < (Integer.parseInt(datos[2])*2)+2; i+=2){
                Aspecto a = new Aspecto();
                a.setNombre(datos[i+1]);
                a.setCalidad(datos[i+2]);
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
            int i;
            for (i = 6; i < datos.length; i+= (2+Integer.parseInt(datos[i+1]))){
                c.getPersonajesPoseidos().ingresarPersonaje(datos[i]);
                int cantskins = Integer.parseInt(datos[i+1]);
                for (int j = 1; j <= cantskins; j++){
                    c.getPersonajesPoseidos().getPersonajePoseido(datos[i]).getAspectosPoseidos().ingresarAspecto(datos[i+j+1]);
                }
            }
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
