public class WeibullGenerator extends VariateGenerator {

    private double a;
    private double b;
    private double v;

    public WeibullGenerator(double a, double b, double v) {
        this.a = a;
        this.b = b;
        this.v = v;
    }

    public int nextInt(){
        return (int) nextDouble();
    }

    public double nextDouble(){
        return   Math.round(( a * Math.pow( -(Math.log( Math.random() )) , 1/b) ) * 100d ) / 100d;
    }

}
