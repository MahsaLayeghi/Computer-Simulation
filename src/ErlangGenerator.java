public class ErlangGenerator extends VariateGenerator {

    private int k ;
    private double teta;

    public ErlangGenerator(int k, double teta) {
        this.k = k;
        this.teta = teta;
    }

    public int nextInt(){
        return (int) nextDouble();
    }

    public double nextDouble(){

        int x = 0 ;

        for (int i = 1 ; i <= k  ; i++) {
            x += ( (-1 / k * teta ) * Math.log(Math.random()) );
        }
        return   Math.round( x  * 100d ) / 100d;
    }

}
