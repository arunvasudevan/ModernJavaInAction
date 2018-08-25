import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AppleSorter {
    enum COLOR{
            GREEN,
            RED
    }

    public static void main(String[] args){
        List<Apple> list = new ArrayList<>();

        list.add(0, new Apple(COLOR.GREEN.toString(),150));
        list.add(1, new Apple(COLOR.GREEN.toString(),100));
        list.add(2, new Apple(COLOR.RED.toString(),180));

        list.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return (o1.getWeight() - o2.getWeight());
            }
        });

        AppleSorter appleSorter = new AppleSorter();
        appleSorter.methodReference(list);
    }

    public void methodReference(List<Apple> list){
        list.sort(Comparator.comparing(Apple::getWeight));

        for(Apple apple: list){
            System.out.println("Color:"+apple.getColor()+", Weight:"+apple.getWeight());
        }
    }
}
