package com.company;
import java.util.*;

class SaisieUtilisateur {
    public static String saisieConsVoy(String name){
        String[] voyelles = {"a","e","i","o","u","y"};
        String[] consonnes = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z"};

        System.out.println("Voulez-vous une consonne ou une voyelle, " + name + " ? (c ou v) ");
        String randomValeur ="";

        boolean valeurCorreect = true;
        while (valeurCorreect) {
            String valeurUtilisateur = Main.scan.nextLine();

            if (valeurUtilisateur.equals("c")){
                int rnd = new Random().nextInt(consonnes.length);
                randomValeur = consonnes[rnd];
                valeurCorreect = false;
            } else if (valeurUtilisateur.equals("v")){
                int rnd = new Random().nextInt(voyelles.length);
                randomValeur = voyelles[rnd];
                valeurCorreect = false;
            } else {
                System.out.println("Veuillez mettre les commandes : c ou v ");
            }
        }
        return randomValeur;
    }

    public static String saisieConsVoyRobot(){
        String[] voyelles = {"a","e","i","o","u","y"};
        String voyellesverif = "aeiouy";
        String[] consonnes = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z"};

        ArrayList<String[]> listeVoyelleConsonne = new ArrayList<>();
        listeVoyelleConsonne.add(voyelles);
        listeVoyelleConsonne.add(consonnes);


        int randTab = new Random().nextInt(listeVoyelleConsonne.size());
        String[] tabHasard = listeVoyelleConsonne.get(randTab);

        int randTabHasard = new Random().nextInt(listeVoyelleConsonne.size());
        String lettreTabHasard = tabHasard[randTabHasard];

        if (voyellesverif.contains(lettreTabHasard)){
            System.out.println("La machine choisit une voyelle.");
        } else {
            System.out.println("La machine choisit une consonne.");
        }

        return lettreTabHasard;
    }

    public static String saisitMot(){
        String motUtilisateur = Main.scan.nextLine();

        return  motUtilisateur;
    }

    public static int saisitChiffre(){
        int chiffreUtilisateur = Main.scan.nextInt();

    return  chiffreUtilisateur;
    }

    public static String tentativeUtilisateur(){

        String mot = Main.scan.nextLine();
        return mot;

    }

    static List<Integer> Rang1 = new ArrayList<>();
    static List<Integer> Rang2 = new ArrayList<>();
    static List<Integer> Rang3 = new ArrayList<>();

    public static void raffraichirRang(){
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= 10; j++) {
                Rang1.add(j);
            }
        }
        Rang2.add(25);
        Rang2.add(25);
        Rang2.add(50);
        Rang2.add(50);
        Rang3.add(75);
        Rang3.add(75);
        Rang3.add(100);
        Rang3.add(100);
    }
    public static int saisieRang(String choix){
        switch (choix){
            case "1" ->{
                int random = (int) (Math.random()*(Rang1.size()));
                int randomPlaque = Rang1.get(random);
                Rang1.remove(random);
                return randomPlaque;
            }
            case "2" ->{
                if (Rang2.size()==0){
                    System.out.println("Il n'y a plus de plaque dans le rang 2");
                    if (choix == LancerJeu.choixordi){
                        choix = (int) (Math.random()*(3)+1)+"";
                        System.out.println("La machine à choisit le rang " + choix + ".");
                    } else {
                        System.out.println("Dans quel rang piochez-vous ?");
                        choix = Main.scan.next();
                    }
                    int randomPlaque = saisieRang(choix);
                    return randomPlaque;
                }
                int random = (int) (Math.random()*(Rang2.size()));
                int randomPlaque = Rang2.get(random);
                Rang2.remove(random);
                return randomPlaque;
            }
            case "3" -> {
                if (Rang3.size() == 0) {
                    System.out.println("Il n'y a plus de plaque dans le rang 3");
                    if (choix == LancerJeu.choixordi) {
                        choix = (int) (Math.random() * (3) + 1) + "";
                        System.out.println("La machine à choisit le rang " + choix + ".");
                    } else {
                        System.out.println("Dans quel rang piochez-vous ?");
                        choix = Main.scan.next();
                    }
                    int randomPlaque = saisieRang(choix);
                    return randomPlaque;
                }
                int random = (int) (Math.random() * (Rang3.size()));
                int randomPlaque = Rang3.get(random);
                Rang3.remove(random);
                return randomPlaque;
            }
        }
        return 0;
    }
}

