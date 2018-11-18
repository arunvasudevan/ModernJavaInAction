import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.*;


public class CollectorsInaNutshell {

    public static void main(String[] args) {
        CollectorsInaNutshell collectorsInaNutshell = new CollectorsInaNutshell();

        collectorsInaNutshell.reducingAndSummarizing(Menu.dishMenu);
    }

    public void reducingAndSummarizing(List<Dish> menu){
        System.out.println("No. of Items in the Menu:"+menu.stream().count());
        Comparator<Dish> comparator= Comparator.comparingInt(Dish::getCalories);
        System.out.println("Max. Calorie Dish in the Menu:"+menu.stream().collect(maxBy(comparator)));
        System.out.println("Min. Calories Dish in the menu:"+menu.stream().collect(minBy(comparator)));

        System.out.println("Sum of Calories:"+menu.stream().collect(summingInt(Dish::getCalories)));
        System.out.println("Average of Calories:"+menu.stream().collect(averagingInt(Dish::getCalories)));
        System.out.println("Summarizing (Based on calories):"+menu.stream().collect(summarizingInt(Dish::getCalories)));
    }
}
