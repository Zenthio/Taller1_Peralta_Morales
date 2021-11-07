

public class listaPersonajesPoseidos {

    private PersonajePoseido[] listaPersonajesPoseidos;
    private int max = 155;
    private int cantPersonajesPoseidos;

    public listaPersonajesPoseidos(){
        PersonajePoseido[] listaPersonajesPoseidos = new PersonajePoseido[max];
        cantPersonajesPoseidos = 0;
    }

    public boolean ingresarPersonaje(PersonajePoseido personaje) {
        if (cantPersonajesPoseidos < max){
            listaPersonajesPoseidos[cantPersonajesPoseidos] = personaje;
            cantPersonajesPoseidos++;
            return true;
        }
        return false;
    }

    public PersonajePoseido getPersonaje(String nombre) {
        int i;
        for (i = 0; i < cantPersonajesPoseidos; i++){
            if (listaPersonajesPoseidos[i].getPersonaje().getNombre().equals(nombre)){
                break;
            }
        }
        if (i == cantPersonajesPoseidos){
            return null;
        }
        else {
            return listaPersonajesPoseidos[i];
        }
    }
    public void setCantPersonajesPoseidos(int cant){
        this.cantPersonajesPoseidos = cant;
    }

    public int getCantPersonajesPoseidos(){
        return this.cantPersonajesPoseidos;
    }

    public PersonajePoseido getPersonajeI(int i){
        if (i >= 0 && i < cantPersonajesPoseidos){
            return listaPersonajesPoseidos[i];
        } else {
            return null;
        }
    }
}
