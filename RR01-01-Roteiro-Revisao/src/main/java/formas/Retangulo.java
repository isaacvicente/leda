package formas;

public class Retangulo implements Forma {
    double base;
    double altura;

    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }
    
    @Override
    public double calcularArea() {
        return this.base * this.altura;
    }
}
