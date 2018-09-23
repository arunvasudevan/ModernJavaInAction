import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMisc {
    public static void main(String[] args) {
        Stream.of("Modern", "Java", "In", "Action", "As", "Stream")
            .forEach(t-> {
                System.out.print(t + " ");
            });
        System.out.println();
        int[] num = { 1, 2, 3, 4, 5 };
        System.out.println("Summation of first 5 numbers:" + Arrays.stream(num).sum());

        try (
            Stream<String> lines = Files.lines(Paths.get("/Users/avasudevan/workspace/streamingplatform/ModernJavaPractice/src/main/resources/data.txt"))) {

            long wordCount = lines.flatMap(line -> Arrays.stream(line.split("")))
                .distinct()
                .count();

            System.out.println("Word Count:" + wordCount);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        System.out.print("Stream Iterate(Example):");
        Stream.iterate(0, n -> n + 2)
            .limit(10)
            .forEach(n -> {
                System.out.print(n + ",");
            });
        System.out.println();
        StreamMisc streamMisc = new StreamMisc();
        streamMisc.fibonacciTuples();
        System.out.println();
        System.out.println("Generate Random:");
        Stream.generate(Math::random)
            .limit(5)
            .forEach(System.out::println);
    }


    public void fibonacciTuples() {
        System.out.print("Fibonacci Tuples(Exercise):");
        Stream.iterate(new int[] { 0, 1 }, arr -> new int[] { arr[1], arr[0] + arr[1] })
            .limit(10)
            .forEach(n -> System.out.print("(" + n[0] + "," + n[1] + ")"));
    }
}
