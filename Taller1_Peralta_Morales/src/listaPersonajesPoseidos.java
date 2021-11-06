

public class listaPersonajesPoseidos {

    private PersonajePoseido[] listaPersonajesPoseidos;
    private int max = 99;
    private int cantPersonajesPoseidos;

    public listaPersonajesPoseidos(int max){
        this.max = max;
        PersonajePoseido[] listaPersonajesPoseidos = new PersonajePoseido[max];
        cantPersonajesPoseidos = 0;
    }

    public void ingresarPersonaje(String string) {

    }

    public Personaje getPersonaje(String string) {
        
    }
    public void setCantPersonajesPoseidos(int cant){
        this.cantPersonajesPoseidos = cant;
    }

}
