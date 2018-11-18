package ohtu;

import java.util.List;

public class Submission {
    private int week;
    private int hours;
    private List<Integer> exercises;
    private String course;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getHours() {
        return hours;
    }

    public List<Integer> getExercises() {
        return exercises;
    }
    
    public String exercises(){
        String s="";
        for(int i=0;i<exercises.size();i++){
            s+=exercises.get(i);
            if(i+1!=exercises.size()) s+=", ";
        }
        return s;
    }

    public String getCourse() {
        return course;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        return course+", viikko "+week+" tehtyjä tehtäviä yhteensä "+exercises.size()+" aikaa kului "+hours+" tuntia. Tehdyt tehtävät: "+exercises();
    }
    
}