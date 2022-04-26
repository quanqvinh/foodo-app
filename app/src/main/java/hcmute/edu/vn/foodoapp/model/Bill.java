package hcmute.edu.vn.foodoapp.model;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private Integer id;
    private Integer userId;
    private String createdAt;
    private Integer storeId;
    private Integer totalPrice;

    private List<BillDetails> details;

    public Bill(Integer id, Integer userId, String createdAt, Integer storeId, Integer totalPrice) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.storeId = storeId;
        this.totalPrice = totalPrice;
    }

    public Bill(Integer id, Integer userId, String createdAt, Integer storeId, Integer totalPrice, List<BillDetails> details) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.storeId = storeId;
        this.totalPrice = totalPrice;
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


}
