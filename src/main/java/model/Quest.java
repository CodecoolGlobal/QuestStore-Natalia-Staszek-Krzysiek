package model;

import java.util.Objects;

public class Quest {

    private int id;
    private String name;
    private int points;
    private int idMentor;
    private String description;
    private String category;

    public Quest() {
    }

    public Quest(String name, int points, String description, String category) {
        this.name = name;
        this.points = points;
        this.description = description;
        this.category = category;
    }

    public Quest(int id, String name, int points, String description, String category) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.description = description;
        this.category = category;
    }

    public Quest(int id, int id_mentor, String name, int points, String description, String category) {
    }

    public int getIdMentor() {
        return idMentor;
    }

    public Quest setIdMentor(int idMentor) {
        this.idMentor = idMentor;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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

    @Override
    public String toString() {
        return "Quest{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return id == quest.id &&
                points == quest.points &&
                Objects.equals(name, quest.name) &&
                Objects.equals(description, quest.description) &&
                Objects.equals(category, quest.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, points, description, category);
    }
}
