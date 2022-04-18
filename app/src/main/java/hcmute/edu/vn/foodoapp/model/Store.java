package hcmute.edu.vn.foodoapp.model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private int image;
    private String name;
    private List<Food> foods;
    private String openAt, closeAt;
    private String description;

    public Store(int image, String name, List<Food> foods, String openAt, String closeAt, String description) {
        this.image = image;
        this.name = name;
        this.foods = foods;
        this.openAt = openAt;
        this.closeAt = closeAt;
        this.description = description;
    }

    public Store(int image, String name, String openAt, String closeAt, String description) {
        this.image = image;
        this.name = name;
        foods = new ArrayList<Food>();
        this.openAt = openAt;
        this.closeAt = closeAt;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public String getOpenAt() {
        return openAt;
    }

    public void setOpenAt(String openAt) {
        this.openAt = openAt;
    }

    public String getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(String closeAt) {
        this.closeAt = closeAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addFood(Food food) {
        this.foods.add(food);
    }

    public void addFood(int image, String name, String type, String description, int price) {
        this.foods.add(new Food(image, name, type, description, price));
    }

    public String getListFoods() {
        String s = "";
        for (int i = 0; i < this.foods.size(); i++)
            s += " ," + this.foods.get(i).getName();
        return s.length() > 0 ? s.substring(2) : "empty";
    }
}
