package hcmute.edu.vn.foodoapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Serializable {
    private Integer id;
    private Integer userId;
    private String createdAt;
    private Integer storeId;
    private Integer totalPrice;
    private String address;

    private List<BillDetails> details;

    public Bill(Integer id, Integer userId, String createdAt, Integer storeId, Integer totalPrice, String address) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.storeId = storeId;
        this.totalPrice = totalPrice;
        this.address = address;
    }

    public Bill(Integer id, Integer userId, String createdAt, Integer storeId,
                Integer totalPrice, String address, List<BillDetails> details) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.storeId = storeId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.details = details;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<BillDetails> getDetails() {
        return details;
    }

    public void setDetails(List<BillDetails> details) {
        this.details = details;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalPriceWithMoneyFormat() {
        String s = this.totalPrice + "";
        String result = " ??";
        int i;
        for (i = s.length() - 3; i > 0; i -= 3)
            result = "." + s.substring(i, i + 3) + result;
        if (i <= 0)
            result = s.substring(0, i + 3) + result;
        return result.charAt(0) == '.' ? result.substring(1) : result;
    }
}
