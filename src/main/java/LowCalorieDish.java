import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LowCalorieDish {
    public static void main(String[] args){
        ArrayList<Dish> menu= new ArrayList<>();
        menu.add(new Dish("Paneer Butter Masala", 150));
        menu.add(new Dish("Rasamalai", 200));

        LowCalorieDish lowCalorieDish= new LowCalorieDish();
        lowCalorieDish.java7LowCalorieDish(menu);
    }

    public void java7LowCalorieDish(ArrayList<Dish> menu){
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

        for (String dish: lowCaloricDishesName) {
            System.out.println("Dish:"+dish);
        }
    }
}
