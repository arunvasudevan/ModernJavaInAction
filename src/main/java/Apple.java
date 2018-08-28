public class Apple implements Fruit{
    public Apple(String color, int weight){
        this.color=color;
        this.weight=weight;
    }

    public Apple(){
    }

    public Apple(Integer integer) {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    String color;
    int weight;
}
