import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;


public class CreationHoraire {

    public static void main(String[] args)
    {

        System.out.println("Creer un horaire de type 'Hiver', 'Ete' ou 'Automne'");

        Scanner scanner = new Scanner(System.in);
        String typeHoraire = scanner.nextLine();

        Horaire horaire;

        try {
            horaire = new Horaire(typeHoraire);
        } catch (IllegalArgumentException e)
        {
            return;
        }

        System.out.println("Horaire de session " +  horaire.getSession()  + " generer.\n");

        while (true) {
            System.out.println("\n-----------------------------------");
            System.out.println("Cours:");

            for (Cours cour : horaire.getCours())
            {
                System.out.print(" |" + cour.getSigle() + "| ");
            }
            System.out.println("\n-----------------------------------\n");

            System.out.println("---");
            System.out.println("Options:");
            System.out.println("Ajouter un cours   (1)");
            System.out.println("Modifier un cours  (2)");
            System.out.println("Supprimer un cours (3)");
            System.out.println("Afficher horaire   (4)");
            System.out.println("Stop               (5)");
            System.out.print("(Entre numero de l'option)\n");
            System.out.println("---");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option==1) {
                ajouterCours(horaire, scanner);
            }
             else if (option == 2) {
                modifierCour(horaire, scanner);
            }
             else if ( option == 3) {
                System.out.println("Entrer sigle du cours a supprimer");
                String sigleCour = scanner.nextLine();
                horaire.supprimerCour(sigleCour);
            }
            else if ( option == 4) {
                System.out.println("Entree mois");
                int mois = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Entree semaine");
                int semaine = scanner.nextInt();
                scanner.nextLine();
                System.out.println(horaire.printSemaine(mois, semaine));
            }
                else {
                return;
            }
        }
    } // fin main

    /**
     * Demande a l'usager d'ajouter un cours a l'horaire avec les informations necessaire. (matiere, numero, credits)
     *
     * @param horaire horaire a la quelle ajouter le cours.
     * @param scanner Scanner
     */
    private static void ajouterCours(Horaire horaire, Scanner scanner)
    {
        System.out.println("Entree Matiere du cours (Trois lettre)");
        String matiere = "";
        while (matiere.length() != 3) {
            matiere = scanner.nextLine().toUpperCase();
        }

        System.out.println("Entree numero du cour (Quatre chiffre)");
        int numero = 0;
        while (Integer.toString(numero).length() != 4) {
            numero = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println("Entree nombres de credits");
        int credits = scanner.nextInt();
        scanner.nextLine();

        Cours nouveauCour = new Cours(matiere, numero, credits, horaire.getMoisDebutSession(), horaire.getMoisFinSession());

        horaire.addCours(nouveauCour);

    }

    /**
     * Modifier un cour present a l'horaire. Affiche les options de modification de cours
     * et demande a l'usager de choisir une option.
     *
     * @param horaire Horaire du cours
     * @param scanner Scanner
     */
    private static void modifierCour(Horaire horaire, Scanner scanner)
    {

        System.out.println("Entrer sigle du cours a modifier");
        String sigleCour = scanner.nextLine();
        Cours cours = horaire.trouverCour(sigleCour);

        if (cours == null)
        {
            System.out.println("Cour non present a l'horaire\n");
            return;
        }

        System.out.println("---");
        System.out.println("Options: ");
        System.out.println("Ajouter une sceance sur toute la session     (1)");
        System.out.println("Ajouter une sceance individuelle             (2)");
        System.out.println("Ajouter une sceance d'examen                 (3)");
        System.out.println("Retirer une sceance sur toute la session     (4)");
        System.out.println("Retirer une sceance Individuelle             (5)");
        System.out.println("Modifier une sceance sur toute la session    (6)");
        System.out.println("Modifier une sceance Individuelle            (7)");
        System.out.println("Changer nombre credits                       (8)");
        System.out.println("Stop                                         (9)");
        System.out.print("(Entre numero de l'option)\n");
        System.out.println("---");
        int option = scanner.nextInt();
        scanner.nextLine();
        if(option==1) {
        addSceances(cours, option, scanner);
        }
        if(option==2) {
            addSceances(cours, option, scanner);
        }
        if(option==3) {
            addSceances(cours, option, scanner);
        }
        if(option==4) {
            retirerSceances(cours, option, scanner);
        }
        if(option==5) {
            retirerSceances(cours, option, scanner);
        }
        if(option==6) {
            modifierSceances(cours, option, scanner);
        }
        if(option==7) {
            modifierSceances(cours, option, scanner);
        }
        if(option==8) {
            System.out.print("Mettre A jour nombre de credits:");
            int credits = scanner.nextInt();
            scanner.nextLine();
            cours.setCredits(credits);
        }
        if(option==9) {
            return;

    }

    }

    /**
     * Ajoute une ou des sceances au cours selon l'options selectionner dans la method modifierCour.
     *
     * @param cours Cours au quel ajouter des sceances
     * @param option options pour l'ajout de sceance.
     * @param scanner Scanner
     */
    private static void addSceances(Cours cours, int option, Scanner scanner)
    {

        System.out.println("Entree jour d'ecole");
        System.out.println("(Lundi, Mardi, Mercredi, Jeudi, Vendredi)");

        String jourEcole = scanner.nextLine();

        LocalTime heureDebut = getHeure(scanner, "Depart");
        LocalTime heureFin = getHeure(scanner, "Fin");


        String type="";
        if (option == 1 | option == 2) {
            System.out.println("\nType de la sceance (Theorique ou Pratique)");
            type = scanner.nextLine();
        }

        int mois=0;
        int semaine=0;
        if (option == 2 | option == 3) {
            System.out.println("Entree mois de la sceance");
            mois = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Entree semaine de la sceance");
            semaine = scanner.nextInt();
            scanner.nextLine();
        }

        if (option == 1) {
            cours.addSceances(jourEcole, heureDebut,
                    heureFin, type);
        } else if (option == 2) {
                cours.addInstanceSceance(jourEcole,heureDebut, heureFin, type, mois, semaine);
        }else if (option == 3){
            cours.addSceanceExamen(jourEcole,heureDebut, heureFin, mois, semaine);

        }
    }


    /**
     * Retire une ou des sceances au cours selon l'options selectionner dans la method modifierCour.
     *
     * @param cours Cours au quel retirer des sceances.
     * @param option options pour le retrait de sceance
     * @param scanner Scanner
     */
    private static void retirerSceances(Cours cours, int option, Scanner scanner)
    {

        System.out.print("Entree jour d'ecole");
        System.out.print("(Lundi, Mardi, Mercredi, Jeudi, Vendredi)");

        String jourEcole = scanner.nextLine();

        LocalTime heureDebut = getHeure(scanner, "Depart");


        if (option == 4)
        {
            cours.enleverSceance(jourEcole, heureDebut);

        } else {

            System.out.println("Entree mois de la sceance");
            int mois = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Entree semaine de la sceance");
            int semaine = scanner.nextInt();
            scanner.nextLine();

            cours.enleverInstanceSceance(mois, semaine, jourEcole, heureDebut);


        }
    }

    /**
     * Modifie une ou des sceances au cours selon l'options selectionner dans la method modifierCour.
     *
     * @param cours Cours au quel modifier des sceances.
     * @param option options pour le retrait de sceance
     * @param scanner Scanner
     */
    private static void modifierSceances(Cours cours, int option, Scanner scanner)
    {


        System.out.print("Entree jour d'ecole");
        System.out.print("(Lundi, Mardi, Mercredi, Jeudi, Vendredi)");

        String jourEcole = scanner.nextLine();

        LocalTime heureDebut = getHeure(scanner, "Depart");


        if(option == 6)
        {
            System.out.print("\nEntree *NOUVELLES* Heure de depart et de fin");
            LocalTime nouvelleHeureDebut = getHeure(scanner, "Depart");

            LocalTime nouvelleHeureFin = getHeure(scanner, "Fin");

            System.out.print("Entree *NOUVEAU* jour d'ecole");
            System.out.print("(Lundi, Mardi, Mercredi, Jeudi, Vendredi)");
            String nouveauJourEcole = scanner.nextLine();


            ArrayList<Sceance> sceances = cours.getSceances(jourEcole, heureDebut);

            for(Sceance sceance : sceances)
            {
                sceance.setHeureDebut(nouvelleHeureDebut);
                sceance.setHeureFin(nouvelleHeureFin);
                sceance.setJourEcole(nouveauJourEcole);
            }

        } else {


            System.out.println("Entree mois de la sceance");
            int mois = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Entree semaine de la sceance");
            int semaine = scanner.nextInt();
            scanner.nextLine();


            System.out.print("\nEntree *NOUVELLES* Heure de depart et de fin");
            LocalTime nouvelleHeureDebut = getHeure(scanner, "Depart");
            LocalTime nouvelleHeureFin =getHeure(scanner, "Depart");

            System.out.print("Entree *NOUVEAU* jour d'ecole");
            System.out.print("(Lundi, Mardi, Mercredi, Jeudi, Vendredi)");
            String nouveauJourEcole = scanner.nextLine();

            System.out.println("Entree *NOUVEAU* mois de la sceance");
            int nouveauMois = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Entree *NOUVELLE* semaine de la sceance");
            int nouvelleSemaine = scanner.nextInt();
            scanner.nextLine();
            Sceance sceance = cours.getInstanceSceance(mois, semaine, jourEcole, heureDebut);

            sceance.setHeureDebut(nouvelleHeureDebut);
            sceance.setHeureDebut(nouvelleHeureFin);
            sceance.setJourEcole(nouveauJourEcole);
            sceance.setMois(nouveauMois);
            sceance.setSemaine(nouvelleSemaine);
        }
    }

    /**
     * Demander a l'user d'entree une heure dans le format heure:minute.
     *
     * @param scanner Scanner
     * @param type Affiche a l'usager quel type d'heure il s'agira.
     * @return heure (LocalTime)
     */
    private  static LocalTime getHeure(Scanner scanner, String type)
    {
        System.out.println("Entree Heure de " + type + "(Heure:Minute)");
        String[] heureSplit = {};

        while (heureSplit.length != 2) {
            String heure = scanner.nextLine();
            heureSplit = heure.split(":");
        }

        LocalTime heure = LocalTime.of(Integer.parseInt(heureSplit[0]),
                Integer.parseInt(heureSplit[1]));

        return heure;
    }
}
