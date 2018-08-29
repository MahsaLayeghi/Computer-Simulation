public class DiscreteUniformGenerator extends VariateGenerator {

    private int a;
    private int b;

    public DiscreteUniformGenerator(int a, int b) {
        this.a = a;
        this.b = b;
    }

     public int nextInt(){
        return (int) nextDouble();
    }


    public double nextDouble(){
        int k = b - a + 1;
        return Math.ceil(k * Math.random()) + a - 1;
    }

}
