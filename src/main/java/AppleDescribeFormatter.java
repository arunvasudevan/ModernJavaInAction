public class AppleDescribeFormatter implements AppleFormatter{
    public String accept(Apple apple) {
        return "An Apple of "+ apple.getColor() + " color, with a weight of " + apple.getWeight() + "g";
    }
}
