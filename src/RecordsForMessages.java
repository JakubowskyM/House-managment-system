public class RecordsForMessages {

    private int id;
    private String title;
    private String message;
    private String dateSent;
    private int id_f;
    private int send_to;
    private int send_from;
    private String takenDate;
    private int keeper_id;

    public int getSend_to() {
        return send_to;
    }

    public int getSend_from() {
        return send_from;
    }

    public String getTakenDate() {
        return takenDate;
    }

    public int getKeeper_id() {
        return keeper_id;
    }

    public RecordsForMessages(int id, String title, String dateSent, int id_f, String takenDate) {
        this.id = id;
        this.title = title;
        this.dateSent = dateSent;
        this.id_f = id_f;
        this.takenDate = takenDate;
    }

    public RecordsForMessages(int id, String title, String message, String dateSent, int id_f) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.dateSent = dateSent;
        this.id_f = id_f;
    }
    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getDateSent(){
        return dateSent;
    }

    public int getId_f() {
        return id_f;
    }

    public int getId() {
        return id;
    }
}
