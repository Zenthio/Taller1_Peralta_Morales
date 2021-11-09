

public class listaAspecto {

    private int max = 99;
    private int cantAspectos;
    private Aspecto[] listaAspectos;

    public listaAspecto(){
        listaAspectos = new Aspecto[this.max];
        this.cantAspectos = 0;
    }

    
    /** 
     * @param cantAspectos
     */
    public void setCantAspectos(int cantAspectos){
        this.cantAspectos = cantAspectos;
    }

    
    /** 
     * @return int
     */
    public int getCantAspectos(){
        return cantAspectos;
    }

    
    /** 
     * @param aspecto
     * @return boolean
     */
    public boolean ingresarAspecto(Aspecto aspecto){
        if (cantAspectos < max){
            listaAspectos[this.cantAspectos] = aspecto;
            this.cantAspectos++;
            return true;
        }
        return false;
    }

    
    /** 
     * @param i
     * @return Aspecto
     */
    public Aspecto getAspectoI(int i) {
        if (i >= 0 && i < cantAspectos){
            return listaAspectos[i];
        } else {
            return null;
        }
    }

    
    /** 
     * @param nombre
     * @return Aspecto
     */
    public Aspecto getAspecto(String nombre){
        int i;
        for (i = 0; i < cantAspectos; i++){
            if (listaAspectos[i].getNombre().equals(nombre)){
                return listaAspectos[i];
            }
        }
        return null;
    }

    
    /** 
     * @return int
     */
    public int getMax() {
        return this.max;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " max='" + getMax() + "'" +
            ", cantAspectos='" + getCantAspectos() + "'" +
            "}";
    }
}
