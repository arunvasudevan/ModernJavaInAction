import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PythagoreanTriples {

    public static void main(String[] args) {
        PythagoreanTriples pythagoreanTriples = new PythagoreanTriples();
        pythagoreanTriples.pyTriples();
        pythagoreanTriples.pyTriplesApproach2();
    }

    public void pyTriples() {
        System.out.println("Approach 1- ");
        Stream<int[]> pyStream = IntStream.rangeClosed(1, 100)
            .boxed()
            .flatMap(
                a -> IntStream.rangeClosed(a, 100)
                    .filter(b -> ((Math.sqrt(a * a + b * b)) % 1 == 0))
                    .mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) })
            );

        pyStream.limit(5).forEach(t -> System.out.println("{" + t[0] + "," + t[1] + "," + t[2] + "}"));
    }

    public void pyTriplesApproach2() {
        System.out.println("Approach 2-");
        Stream<double[]> pyStream = IntStream.rangeClosed(1, 100)
            .boxed()
            .flatMap(
                a -> IntStream.rangeClosed(a, 100)
                    .mapToObj(b -> new double[] { a, b, Math.sqrt(a * a + b * b) })
                    .filter(t-> t[2] % 1 == 0)
            );

        pyStream.limit(5).forEach(t -> System.out.println("{" + t[0] + "," + t[1] + "," + t[2] + "}"));
    }
}
