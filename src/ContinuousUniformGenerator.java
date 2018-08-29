public class ContinuousUniformGenerator extends VariateGenerator {


    public double a ;
    public double b;

    public ContinuousUniformGenerator(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public int nextInt(){
        return (int) nextDouble();
    }


    public double nextDouble(){
        return  Math.round((a +  Math.random()*(b-a))* 100d) /100d ;
    }

}
