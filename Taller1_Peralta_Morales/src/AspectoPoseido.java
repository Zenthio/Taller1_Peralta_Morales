public class AspectoPoseido {
    
    private Aspecto aspecto;
    private Cliente dueño;
    
    public AspectoPoseido(Cliente dueño, Aspecto aspecto){
        this.dueño = dueño;
        this.aspecto = aspecto;
    }

    public Aspecto getAspecto(){
        return this.aspecto;
    }
    
}
