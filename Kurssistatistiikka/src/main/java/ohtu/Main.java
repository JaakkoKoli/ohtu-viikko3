package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        
        String courseBody = Request.Get("https://studies.cs.helsinki.fi/courses/courseinfo").execute().returnContent().asString();
        
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        Course[] courses = mapper.fromJson(courseBody, Course[].class);
        Object[] c = Arrays.stream(subs).map(x -> x.getCourse()).distinct().toArray();
        String jsonR=Request.Get("https://studies.cs.helsinki.fi/courses/rails2018/stats").execute().returnContent().asString().replace("\"1\":", "").replace("\"2\":", "").replace("\"3\":", "").replace("\"4\":", "").replace("\"5\":", "").replace("\"6\":", "").replace("\"7\":", "");
        jsonR=jsonR.substring(1, jsonR.length()-1);
        jsonR="["+jsonR+"]";
        Week[] courseRails = mapper.fromJson(jsonR, Week[].class);
        String jsonO=Request.Get("https://studies.cs.helsinki.fi/courses/ohtu2018/stats").execute().returnContent().asString().replace("\"1\":", "").replace("\"2\":", "").replace("\"3\":", "").replace("\"4\":", "").replace("\"5\":", "").replace("\"6\":", "").replace("\"7\":", "");
        jsonO=jsonO.substring(1, jsonO.length()-1);
        jsonO="["+jsonO+"]";
        Week[] courseOhtu = mapper.fromJson(jsonO, Week[].class);
        
        System.out.println("opiskelijanumero "+studentNr);
        for(Object o:c){
            Course course = Arrays.stream(courses).filter(x -> x.getName().equals((String) o)).findFirst().get();
            Week[] data = (((String) o).equals("ohtu2018")?courseOhtu:courseRails);
            Week curWeek = data[course.getV()-1];
            System.out.println(course.getFullName()+" "+course.getTerm()+" "+course.getYear()+"\n");
            for(Object sub:Arrays.stream(subs).filter(x->x.getCourse().equals((String) o)).toArray()){
                Submission s = (Submission) sub;
                System.out.println("viikko "+s.getWeek()+":\n\ttehtyjä tehtäviä "+s.getExercises().size()+"/"+curWeek.getExercises().size()+" aikaa kului "+s.getHours()+" tuntia. Tehdyt tehtävät: "+s.exercises());
            }
            System.out.println("Yhteensä: "+Arrays.stream(subs).filter(x->x.getCourse().equals((String) o)).mapToInt(x->x.getExercises().size()).sum()+"/"+
                    Arrays.stream((((String) o).equals("ohtu2018")?courseOhtu:courseRails)).mapToInt(x->x.getExercises().size()).sum()+
                    " tehtävää "+Arrays.stream(subs).filter(x->x.getCourse().equals((String) o)).mapToInt(x->x.getHours()).sum()+" tuntia\n"
            );
            System.out.println("Kurssilla oli yhteensä "+Arrays.stream(data).mapToInt(x->x.getStudents()).sum()+" palautusta, palautettuja tehtäviä "+Arrays.stream(data).mapToInt(x->x.getExercise_total()).sum()+" aikaa käytetty yhteensä "+Arrays.stream(data).mapToDouble(x->x.getHour_total()).sum()+" tuntia");
        }
    }
}
