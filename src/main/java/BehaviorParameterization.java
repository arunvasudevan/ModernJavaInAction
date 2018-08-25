import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BehaviorParameterization {
    enum COLOR {
        GREEN,
        RED
    }
    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }
    public static Fruit giveMeFruit(String fruit, Integer weight){
        return map.get(fruit.toLowerCase())
            .apply(weight);
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        apple.setColor(COLOR.GREEN.toString());
        apple.setWeight(150);

        List<Apple> inventory = new ArrayList<>();
        inventory.add(apple);
        prettyPrintApple(inventory, new AppleDescribeFormatter());

        System.out.println("Give me fruit");
        Fruit fruit= giveMeFruit("apple", 150);
        System.out.println("Fruit:"+fruit);
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
        for(Apple apple: inventory) {
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }
}
