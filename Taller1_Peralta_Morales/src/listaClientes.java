public class listaClientes {
    
    private Cliente[] listaClientes;
    private int max;
    private int cantClientes;

    public listaClientes(int max){
        this.max = max;
        listaClientes = new Cliente[this.max];
        cantClientes = 0;
    }

    
    /** 
     * @param cliente
     * @return boolean
     */
    public boolean ingresarCliente(Cliente cliente){
        if (cantClientes < max){
            listaClientes[cantClientes] = cliente;
            cantClientes++;
            return true;
        }
        return false;
    }

    
    /** 
     * @param nombre
     * @return Cliente
     */
    public Cliente getCliente(String nombre){
        int i;
        for (i = 0; i < cantClientes; i++){
            if (listaClientes[i].getNombre().equals(nombre)){
               return listaClientes[i];
            }
        }
        return null;
    }

    
    /** 
     * @return int
     */
    public int getCantClientes(){
        return this.cantClientes;
    }

    
    /** 
     * @param i
     * @return Cliente
     */
    public Cliente getClienteI(int i) {
        if (i >= 0 && i < cantClientes){
            return listaClientes[i];
        } else {
            return null;
        }
    }
}
