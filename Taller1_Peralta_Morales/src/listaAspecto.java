

public class listaAspecto {

    private int max = 99;
    private int cantAspectos;
    private Aspecto[] listaAspectos;

    public listaAspecto(int max){
        listaAspectos = new Aspecto[max];
        cantAspectos = 0;
        this.max = max;
    }

    public int getCantAspectos(){
        return cantAspectos;
    }

    public boolean ingresarAspecto(Aspecto aspecto){
        if (cantAspectos < max){
            listaAspectos[cantAspectos] = aspecto;
            cantAspectos++;
            return true;
        }
        return false;
    }

    public Aspecto getAspecto (int i) {
        if (i >= 0 && i < cantAspectos){
            return listaAspectos[i];
        } else {
            return null;
        }
    }


}
