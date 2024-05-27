public class Room {
    private RoomType type;
    private int door_amount;
    private int windows_amount;
    private String room_name;
    private boolean balcony;

    public Room(RoomType type, int door_amount, int windows_amount, String room_name, boolean balcony) {
        this.type = type;
        this.door_amount = door_amount;
        this.windows_amount = windows_amount;
        this.room_name = room_name;
        this.balcony = balcony;
    }
}
