package hcmute.edu.vn.foodoapp.model;

public class Food {
    private int image;
    private String name;
    private String type;
    private String description;
    private int price;

    public Food(int image, String name, String type, String description, int price) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMoneyDisplay() {
        String s = this.price + "";
        String result = " đ";
        int i;
        for (i = s.length() - 3; i > 0; i -= 3)
            result = "." + s.substring(i, i + 3) + result;
        if (i < 0)
            result = s.substring(0, i + 3) + result;
        return result.charAt(0) == '.' ? result.substring(1) : result;
    }
}
