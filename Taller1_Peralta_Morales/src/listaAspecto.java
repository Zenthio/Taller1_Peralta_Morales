

public class listaAspecto {

    private int max = 99;
    private int cantAspectos;
    private Aspecto[] listaAspectos;

    public listaAspecto(){
        listaAspectos = new Aspecto[this.max];
        this.cantAspectos = 0;
    }

    public void setCantAspectos(int cantAspectos){
        this.cantAspectos = cantAspectos;
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

    public Aspecto getAspectoI(int i) {
        if (i >= 0 && i < cantAspectos){
            return listaAspectos[i];
        } else {
            return null;
        }
    }

    public Aspecto getAspecto(String nombre){
        int i;
        for (i = 0; i < cantAspectos; i++){
            if (listaAspectos[i].getNombre().equals(nombre)){
                return listaAspectos[i];
            }
        }
        return null;
    }

    public int getMax() {
        return this.max;
    }

    @Override
    public String toString() {
        return "{" +
            " max='" + getMax() + "'" +
            ", cantAspectos='" + getCantAspectos() + "'" +
            "}";
    }
}
