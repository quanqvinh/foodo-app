package hcmute.edu.vn.foodoapp.model;

import java.io.Serializable;

public class BillDetails implements Serializable {
    private Integer id;
    private Integer foodId;
    private Integer billId;
    private Integer amount;
    private Integer price;

    private Food food;

    public BillDetails(Integer id, Integer foodId, Integer billId, int amount, int price) {
        this.id = id;
        this.foodId = foodId;
        this.amount = amount;
        this.price = price;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceWithMoneyFormat() {
        String s = this.price + "";
        String result = " đ";
        int i;
        for (i = s.length() - 3; i > 0; i -= 3)
            result = "." + s.substring(i, i + 3) + result;
        if (i <= 0)
            result = s.substring(0, i + 3) + result;
        return result.charAt(0) == '.' ? result.substring(1) : result;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

}
