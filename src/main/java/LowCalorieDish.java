import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LowCalorieDish {
    public static void main(String[] args){
        List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

        LowCalorieDish lowCalorieDish= new LowCalorieDish();
        lowCalorieDish.skipStreams(menu);
        lowCalorieDish.java7LowCalorieDish(menu);
        lowCalorieDish.java8LowCalorieDish(menu);
    }

    public void java7LowCalorieDish(List<Dish> menu){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish dish: menu) {
            if(dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish dish1, Dish dish2) {
                return Integer.compare(dish1.getCalories(), dish2.getCalories());
            }
        });
        List<String> lowCaloricDishesName = new ArrayList<>();
        for(Dish dish: lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }
        System.out.println("Java 7 Approach...."+lowCaloricDishes);


        List<String> names =
            menu.stream()
                .filter(dish -> {
                    System.out.println("filtering:" + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping:" + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());
        System.out.println(names);
    }

    // Streams API Implementation
    public void java8LowCalorieDish(List<Dish> menu){
        List<String> lowCalorieDishes = menu.parallelStream()
                                        .filter(d -> d.getCalories() < 400)
                                        .sorted(Comparator.comparing(Dish::getCalories))
                                        .map(Dish::getName)
                                        .collect(toList());

        System.out.println("Java 8 Approach...."+lowCalorieDishes);
    }

    public void skipStreams(List<Dish> menu){

        System.out.println("Limiting Streams......");
        menu.stream()
            .filter(d -> d.getType().equals(Dish.Type.MEAT))
            .map(Dish::getName)
            .limit(2)
            .forEach(System.out::println);
    }
}
