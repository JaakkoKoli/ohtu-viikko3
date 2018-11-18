package ohtu;

public class Course {
    private String name;
    private String term;
    private int year;
    private String fullName;
    private int __v;
    private int[] exercises;

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public int getV() {
        return __v;
    }

    public void setV(int __v) {
        this.__v = __v;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    
}
