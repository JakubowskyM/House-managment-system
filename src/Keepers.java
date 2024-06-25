

public class Keepers {
    private int id;
    private String name;
    private String surname;
    private int completed_tasks;


    public Keepers(int id, String name, String surname, int completed_tasks) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.completed_tasks = completed_tasks;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCompleted_tasks() {
        return completed_tasks;
    }

    public void setCompleted_tasks(int completed_tasks) {
        this.completed_tasks = completed_tasks;
    }
}
