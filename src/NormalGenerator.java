public class NormalGenerator extends VariateGenerator{

    private double avg;
    private double varians;

    public NormalGenerator(double avg, double varians) {
        this.avg = avg;
        this.varians = varians;
    }

    private double standard (){

        double x = 0 ;
        for (int i = 0; i < 12 ; i++) {
            x += Math.random();
        }

         return x-6;

    }

    public int nextInt(){
        return (int) nextDouble();
    }


    public double nextDouble(){
        return  Math.round( (standard() * varians + avg) * 100d ) / 100d;
    }



}
