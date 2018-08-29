public class ExponentialGenerator extends VariateGenerator {

    private double lambda;

    public ExponentialGenerator(double lambda) {
        this.lambda = lambda;
    }

    public int nextInt(){
      return (int) nextDouble();
    }


    public double nextDouble(){
        return  Math.round( (Math.log(Math.random()) * (-1 / lambda)) * 100d ) / 100d;
    }


}
