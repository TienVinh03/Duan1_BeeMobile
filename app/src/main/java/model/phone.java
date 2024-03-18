package model;

public class phone {
    private int id;
    private String name;
    private  String color;
    private  int image;
    private int status;

    public phone() {
    }

    public phone(int id, String name, String color, int image, int status) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.image = image;
        this.status = status;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
