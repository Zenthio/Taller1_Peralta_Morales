public class listaClientes {
    
    private Cliente[] listaClientes;
    private int max = 10000; 
    private int cantClientes;

    public listaClientes(){
        Cliente[] listaClientes = new Cliente[max];
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
}
