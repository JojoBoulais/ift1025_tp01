import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        Horaire horaire = new Horaire();

        // //Creer des cours ici
        ArrayList<Cour> cours = new ArrayList<Cour>();
        Cour ift1025 = new Cour("IFT", 1025, 3);
        Cour ift1015 = new Cour("IFT", 1015, 3);
        Cour ift1005 = new Cour("IFT", 1005, 3);
        Cour ift2015 = new Cour("IFT", 2015, 3);
        Cour ift2035 = new Cour("IFT", 2035, 3);
        Cour ift1215 = new Cour("IFT", 1215, 3);
        Cour ift1227 = new Cour("IFT", 1227, 3);
        Cour ift2245 = new Cour("IFT", 2245, 3);
        Cour ift2255 = new Cour("IFT", 2255, 3);
        Cour ift1065 = new Cour("IFT", 1065, 3);
        Cour ift1575 = new Cour("IFT", 1575, 3);
        ift1025.addSceance(0,
                           LocalTime.of(10,10),
                            LocalTime.of(10,10),
                            "pratique");

        ift1025.addSceance(2,
                LocalTime.of(10,10),
                LocalTime.of(10,10),
                "pratique");






        cours.add(ift1025);
        cours.add(ift1015);
        cours.add(ift2035);
        cours.add(ift2015);
        cours.add(ift1005);
        cours.add(ift1005);
        horaire.addCours(cours);
        System.out.println(horaire);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK, 10, 30);

        System.out.println(cal);

    }
}