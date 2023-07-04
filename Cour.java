
import java.util.Calendar;
import java.util.ArrayList;
public class Cour {

    private int numero;
    private String matiere;
    private int credits;
    private int duree;

    private ArrayList<Sceance> sceances;

    public Cour(String matiere, int numero)
    {
        this.matiere = matiere;
        this.numero = numero;
    }

    public ArrayList<Sceance> getSceances() {
        return sceances;
    }

    public String getSigle()
    {
        return this.matiere + this.numero;
    }

    public void setSceances(ArrayList<Sceance> sceances) {
        this.sceances = sceances;
    }


}
