public class listaClientes {
    
    private Cliente[] listaClientes;
    private int max = 10000; 
    private int cantClientes;

    public listaClientes(int max){
        listaClientes = new Cliente[max];
        cantClientes = 0;
    }

    public boolean ingresarCliente(Cliente cliente){
        if (cantClientes < max){
            listaClientes[cantClientes] = cliente;
            cantClientes++;
            return true;
        }
        return false;
    }

    public Cliente getCliente(String nombre){
        int i;
        for (i = 0; i < cantClientes; i++){
            if (listaClientes[i].getNombre().equals(nombre)){
                break;
            }
        }
        if (i == cantClientes){
            return null;
        } else {
            return listaClientes[i];
        }

    }

    public int getCantClientes(){
        return this.cantClientes;
    }

    public Cliente getClienteI(int i) {
        if (i >= 0 && i < cantClientes){
            return listaClientes[i];
        } else {
            return null;
        }
    }
}
