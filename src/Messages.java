import java.time.LocalDate;

public class Messages {
    private int Id;
    private String title;
    private String text;
    private java.time.LocalDate report_date;
    private int send_to;
    private int send_from;
    private int stan;
    private java.time.LocalDate take_date;
    private int keeper_id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getReport_date() {
        return report_date;
    }

    public void setReport_date(LocalDate report_date) {
        this.report_date = report_date;
    }

    public int getSend_to() {
        return send_to;
    }

    public void setSend_to(int send_to) {
        this.send_to = send_to;
    }

    public int getSend_from() {
        return send_from;
    }

    public void setSend_from(int send_from) {
        this.send_from = send_from;
    }

    public int getStan() {
        return stan;
    }

    public void setStan(int stan) {
        this.stan = stan;
    }

    public LocalDate getTake_date() {
        return take_date;
    }

    public void setTake_date(LocalDate take_date) {
        this.take_date = take_date;
    }

    public int getKeeper_id() {
        return keeper_id;
    }

    public void setKeeper_id(int keeper_id) {
        this.keeper_id = keeper_id;
    }
}
