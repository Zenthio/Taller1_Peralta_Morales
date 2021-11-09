public class listaAspectosPoseidos {
    
    private AspectoPoseido[] listaAspectosPoseidos;
    private int cantAspectosPoseidos;
    private int max = 99;

    public listaAspectosPoseidos(){
        listaAspectosPoseidos = new AspectoPoseido[max];
        cantAspectosPoseidos = 0;
    }

    
    /** 
     * @param aspectoP
     * @return boolean
     */
    public boolean ingresarAspecto (AspectoPoseido aspectoP){

        if (cantAspectosPoseidos < max){
            listaAspectosPoseidos[cantAspectosPoseidos] = aspectoP;
            cantAspectosPoseidos++;
            return true;
        }
        return false;
    }

    
    /** 
     * @return int
     */
    public int getCantAspectosPoseidos(){
        return cantAspectosPoseidos;
    }

    
    /** 
     * @param i
     * @return AspectoPoseido
     */
    public AspectoPoseido getAspectoI(int i) {
        if (i >= 0 && i < cantAspectosPoseidos){
            return listaAspectosPoseidos[i];
        } else {
            return null;
        }
    }

    
    /** 
     * @param nombre
     * @return AspectoPoseido
     */
    public AspectoPoseido getAspecto(String nombre){
        int i;
        for (i = 0; i < cantAspectosPoseidos; i++){
            if (listaAspectosPoseidos[i].getAspecto().getNombre().equals(nombre)){
                return listaAspectosPoseidos[i];
            }
        }
        return null;
        
    }

}
