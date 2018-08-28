public class Dish {

    public Dish() {
    }

    public Dish(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    private String name;
    private int calories;

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
