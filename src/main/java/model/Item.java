package model;

import java.util.Objects;

public class Item {

    private String name;
    private Integer price;
    private String description;
    private String category;
    private int id;

    public Item(String name, Integer price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Item(String name, Integer price, String description, String category, int id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                Objects.equals(description, item.description) &&
                Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description, category, id);
    }
}
