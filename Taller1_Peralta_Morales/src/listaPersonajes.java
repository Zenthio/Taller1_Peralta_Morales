public class listaPersonajes {
    
    private Personaje[] listaPersonajes;
    int max = 155;
    int cantPersonajes;

    public listaPersonajes(){
        listaPersonajes = new Personaje[max];
        cantPersonajes = 0; 
    }

    public boolean ingresarPersonaje(Personaje personaje){
        if (cantPersonajes < max){
            listaPersonajes[cantPersonajes] = personaje;
            cantPersonajes++;
            return true;
        }
        return false;
    }

    public Personaje getPersonaje(String nombre){
        int i;
        for (i = 0; i < cantPersonajes; i++){
            if (listaPersonajes[i].getNombre().equals(nombre)){
                System.out.println(listaPersonajes[i].getNombre());
                return listaPersonajes[i];
            }
        }
        return null;
    }

    public Personaje getPersonajeI(int i) {
        if (i >= 0 && i < cantPersonajes){
            return listaPersonajes[i];
        } else {
            return null;
        }
    }
    
    public int getCantPersonajes(){
        return this.cantPersonajes;
    }
}
