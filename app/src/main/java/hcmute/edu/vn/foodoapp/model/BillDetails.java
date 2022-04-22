package hcmute.edu.vn.foodoapp.model;

public class BillDetails {
    private Integer id;
    private Integer foodId;
    private Integer billId;
    private int amount;
    private int price;

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
}
