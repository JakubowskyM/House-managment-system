public class Resident {
    private int id;
    private String name;
    private String surname;
    private int id_f;


    public Resident(int id, String name, String surname, int id_f) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.id_f = id_f;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId_f() {
        return id_f;
    }

    public void setId_f(int id_f) {
        this.id_f = id_f;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
