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
                break;
            }
        }
        if (i == cantPersonajes){
            return null;
        }
        else {
            return listaPersonajes[i];
        }
    }
    
    public int getCantPersonajes(){
        return this.cantPersonajes;
    }
}
