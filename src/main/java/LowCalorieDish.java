import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
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
        lowCalorieDish.isVegetarianFriendly(menu);
        lowCalorieDish.findAndMatch(menu);
        lowCalorieDish.maxCalories(menu);
        lowCalorieDish.exploreCollectors(menu);
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


//        System.out.println("Understanding the flow....");
//
//        List<String> names =
//            menu.stream()
//                .filter(dish -> {
//                    System.out.println("filtering:" + dish.getName());
//                    return dish.getCalories() > 300;
//                })
//                .map(dish -> {
//                    System.out.println("mapping:" + dish.getName());
//                    return dish.getName();
//                })
//                .limit(3)
//                .collect(toList());
//        System.out.println(names);
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

    public void isVegetarianFriendly(List<Dish> menu){
        if(menu.stream()
            .anyMatch(Dish::isVegetarian)){
            System.out.println("Menu is Vegetarian Friendly!!!!");
        }

        System.out.println("Count of dishes (using map and reduce): "+menu.parallelStream()
            .map(d -> 1)
            .reduce(0, Integer::sum));

        menu.stream()
            .filter(Dish::isVegetarian)
            .findFirst()
            .ifPresent(dish -> System.out.println("A Veg dish from the menu:"+dish.getName()));
    }

    public void findAndMatch(List<Dish> menu){
        if(menu.stream().allMatch(d-> d.getCalories()<1000)){
            System.out.println("It's a Healthy menu");
        }

        if(menu.stream().noneMatch(d-> d.getCalories() >1000)) {
            System.out.println("Believe me it is a healthy menu...");
        }
    }

    public void maxCalories(List<Dish> menu){
        int maxCalories=menu.stream()
            .mapToInt(Dish::getCalories)
            .max()
            .orElse(1);

        System.out.println("Range(Exclusive):"+IntStream.range(1, 100).count());

        System.out.println("Range Closed(Inclusive):"+IntStream.rangeClosed(1,100).count());

        System.out.println("Max Calories:"+maxCalories);
    }


    public void exploreCollectors(List<Dish> menu){
        Long totalMenuItems=menu.stream().collect(counting());

        System.out.println("Total Menu Items:"+totalMenuItems);

        Comparator<Dish> comparator= Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> collect = menu.stream().collect(maxBy(comparator));
        System.out.println("Max Calorie Dish:"+collect.get().getName() + "," + collect.get().getCalories()+ "," + collect.get().getType());

        Integer sumOfCalories = menu.stream().collect(summingInt(Dish::getCalories));

        System.out.println("Sum of Calories:"+sumOfCalories);
        Double averageCalories=menu.stream().collect(averagingInt(Dish::getCalories));

        System.out.println("Average Calories:"+averageCalories);

        IntSummaryStatistics intSummaryStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));

        System.out.println("Summary Statistics:"+intSummaryStatistics.toString());
    }

}
