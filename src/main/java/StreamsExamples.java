import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class StreamsExamples {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("Hello", "World");
        StreamsExamples streamsExamples = new StreamsExamples();
        streamsExamples.strToChar(strList);


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        streamsExamples.squareNumber(numbers);

        List<Integer> listOne = Arrays.asList(1, 2, 3);
        List<Integer> listTwo = Arrays.asList(3, 4);

        streamsExamples.returnPairs(listOne, listTwo);
        streamsExamples.reduceOperations(numbers);

    }

    public void returnPairs(List<Integer> listOne, List<Integer> listTwo) {
        System.out.println("Print Pairs....");

        List<int[]> pairs = listOne.stream()
            .flatMap(i -> listTwo.stream()
                .filter(j-> (i+j)%3==0)
                .map(j -> new int[] { i, j }))
            .collect(toList());

        for (int[] pair : pairs
            ) {
            System.out.println("(" + pair[0] + "," + pair[1] + ")");
        }
    }

    public void strToChar(List<String> str) {
        List<String> collect = str.stream()
            .map(d -> d.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(toList());
        System.out.println("String to Distinct Char....");
        for (String splitStr : collect
            ) {
            System.out.println(splitStr);
        }
    }


    public void squareNumber(List<Integer> numbers) {
        numbers.stream()
            .map(d -> d * d)
            .filter(d-> d%3==0)
            .findFirst()
            .ifPresent(n-> System.out.println("First Square Number divisible by 3:"+n));
    }

    public void reduceOperations(List<Integer> numbers){
        Integer sum=numbers.stream()
            .reduce(0, Integer::sum);

        numbers.stream()
            .reduce(Integer::max)
            .ifPresent(max -> System.out.println("Max:"+max));

        numbers.stream()
            .reduce(Integer::min)
            .ifPresent(min -> System.out.println("Min:"+min));

        System.out.println("Sum:"+sum);
    }
}
