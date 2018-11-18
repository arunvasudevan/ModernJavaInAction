import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class LowCalorieDish {
    public enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String[] args){
        List<Dish> menu = asList(
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
        lowCalorieDish.findAndMatch(menu);
        lowCalorieDish.multiLevelGroups(menu);
        lowCalorieDish.collectingAndTransforming(menu);
        lowCalorieDish.partitionMenu(menu);
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
//            dishMenu.stream()
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
            .ifPresent(dish -> System.out.println("A Veg dish from the dishMenu:"+dish.getName()));
    }

    public void findAndMatch(List<Dish> menu){
        if(menu.stream().allMatch(d-> d.getCalories()<1000)){
            System.out.println("It's a Healthy dishMenu");
        }

        if(menu.stream().noneMatch(d-> d.getCalories() >1000)) {
            System.out.println("Believe me it is a healthy dishMenu...");
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

        Comparator<Dish> comparator= comparingInt(Dish::getCalories);
        Optional<Dish> collect = menu.stream().collect(maxBy(comparator));
        System.out.println("Max Calorie Dish:"+collect.get().getName() + "," + collect.get().getCalories()+ "," + collect.get().getType());

        Integer sumOfCalories = menu.stream().collect(summingInt(Dish::getCalories));

        System.out.println("Sum of Calories:"+sumOfCalories);
        Double averageCalories=menu.stream().collect(averagingInt(Dish::getCalories));

        System.out.println("Average Calories:"+averageCalories);

        IntSummaryStatistics intSummaryStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));

        System.out.println("Summary Statistics:"+intSummaryStatistics.toString());

        String shortMenu= menu.stream().map(Dish::getName).collect(joining(","));

        System.out.println("Name of all dishes:"+shortMenu);

        Map<Dish.Type, List<Dish>> typeListMap = menu.stream().collect(groupingBy(Dish::getType));

        System.out.println("Type List Map:"+typeListMap);

        Map<Dish.Type, List<String>> dishNamesByType =
            menu.stream()
                .collect(groupingBy(Dish::getType,
                    mapping(Dish::getName, toList())));

        System.out.println("Dish names by type (names mapped to string):"+dishNamesByType);

        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));
    }

    public void multiLevelGroups(List<Dish> menu){
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect1 = menu.stream()
            .collect(groupingBy(Dish::getType,
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })));

        System.out.println("Group of Group:");
        for (Dish.Type type : collect1.keySet()
            ) {
            Map<CaloricLevel, List<Dish>> caloricLevelListMap = collect1.get(type);
            System.out.println(type + "=" + caloricLevelListMap + ",");
        }

        Map<Dish.Type, Long> collectByCount = menu.stream()
            .collect(groupingBy(Dish::getType, counting()));

        System.out.println("No. of Dishes by Type:"+collectByCount);


    }

    public void collectingAndTransforming(List<Dish> menu){

        Map<Dish.Type, Dish> collectingAndTransforming = menu.stream()
            .collect(groupingBy(Dish::getType,
                collectingAndThen(
                    maxBy(comparingInt(Dish::getCalories)),
                    Optional::get)));

        System.out.println("Max Calorie dish by Type:"+collectingAndTransforming);


        Map<Dish.Type, Integer> integerMap = menu.stream()
            .collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));

        System.out.println("Sum of Calories by Type:"+integerMap);


        Map<Dish.Type, HashSet<CaloricLevel>> mapCaloricLevel = menu.stream()
            .collect(groupingBy(Dish::getType,
                mapping(
                    dish -> {
                        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return CaloricLevel.FAT;
                        else return CaloricLevel.NORMAL;
                    }, toCollection(HashSet::new)
                )));

        System.out.println("Caloric Levels by Type:"+mapCaloricLevel);
    }


    public void partitionMenu(List<Dish> menu){
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));

        System.out.println("Partiioned Menu"+partitionedMenu);

        System.out.println("Vegetarian Dishes in the dishMenu:"+partitionedMenu.get(true));

        Map<Boolean, Map<Dish.Type, List<Dish>>> partitionedMenuGroupedByType = menu.stream().collect(partitioningBy(Dish::isVegetarian,
            groupingBy(Dish::getType)));

        System.out.println("Partitioned Menu Grouped by type:"+partitionedMenuGroupedByType);


        Map<Boolean, Long> countPartitions = menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));

        System.out.println("Count of Partitions:"+countPartitions);

        // Dish.getType() doesn't return a predicate
//        System.out.println("Partition dishMenu by Veg and Type:"+dishMenu.stream().collect(partitioningBy(Dish::isVegetarian,
//            partitioningBy(Dish::getType))));


        System.out.println("Partition by Veg and Calories:"+menu.stream().collect(partitioningBy(Dish::isVegetarian,
            partitioningBy(d -> d.getCalories() > 500))));

    }

}
