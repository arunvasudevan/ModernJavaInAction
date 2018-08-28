import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class ComposingFunctions {

    public static void main(String[] args){
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);

        System.out.println("Result: "+result);

        ComposingFunctions c = new ComposingFunctions();
        c.compose();

    }

    public void compose(){
        Function<Integer, Integer> f = x-> x+1;
        Function<Integer, Integer> g = x-> x * 2;

        Function<Integer, Integer> h = f.compose(g);

        int result = h.apply(1);

        System.out.println("Compose - Result:"+result);
    }

    public double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

    public double integrate(DoubleUnaryOperator f, double a, double b) {
        return (f.applyAsDouble(a) + f.applyAsDouble(b)) * (b - a) / 2.0;
    }
}
