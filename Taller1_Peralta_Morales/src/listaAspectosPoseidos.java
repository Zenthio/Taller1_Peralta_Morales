public class listaAspectosPoseidos {
    
    private AspectoPoseido[] listaAspectosPoseidos;
    private int cantAspectosPoseidos;
    private int max = 99;

    public listaAspectosPoseidos(int max){
        listaAspectosPoseidos = new AspectoPoseido[max];
        cantAspectosPoseidos = 0;
        this.max = max;
    }

    public boolean ingresarAspecto (AspectoPoseido aspectoP){

        if (cantAspectosPoseidos < max){
            listaAspectosPoseidos[cantAspectosPoseidos] = aspectoP;
            cantAspectosPoseidos++;
            return true;
        }
        return false;
    }

    public int getCantAspectosPoseidos(){
        return cantAspectosPoseidos;
    }

    public AspectoPoseido getAspectoI(int i) {
        if (i >= 0 && i < cantAspectosPoseidos){
            return listaAspectosPoseidos[i];
        } else {
            return null;
        }
    }

    public AspectoPoseido getAspecto(String nombre){
        int i;
        for (i = 0; i < cantAspectosPoseidos; i++){
            if (listaAspectosPoseidos[i].getAspecto().getNombre().equals(nombre)){
                break;
            }
        }
        if (i == cantAspectosPoseidos){
            return null;
        }
        else {
            return listaAspectosPoseidos[i];
        }
    }

}
