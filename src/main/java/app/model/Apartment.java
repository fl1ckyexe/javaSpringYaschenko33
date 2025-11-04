package app.model;

public class Apartment {
    private Long id;
    private String title;
    private String address;
    private double price;
    private String ownerUsername;


    private int rooms;
    private String layoutType; // "Studio", "One-bedroom", ...

    public Apartment() {}

    public Apartment(Long id, String title, String address,
                     double price, String ownerUsername) {
        this(id, title, address, price, ownerUsername, 1, "Studio");
    }

    public Apartment(Long id, String title, String address,
                     double price, String ownerUsername,
                     int rooms, String layoutType) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.price = price;
        this.ownerUsername = ownerUsername;
        this.rooms = rooms;
        this.layoutType = layoutType;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getOwnerUsername() { return ownerUsername; }
    public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }

    public int getRooms() { return rooms; }
    public void setRooms(int rooms) { this.rooms = rooms; }

    public String getLayoutType() { return layoutType; }
    public void setLayoutType(String layoutType) { this.layoutType = layoutType; }
}
