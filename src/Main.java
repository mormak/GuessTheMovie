
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        //read the file
        String patch = "films.txt";
        File file = new File(patch);
        //check if file exists
        try {
            Scanner scanner = new Scanner(file);
        } catch (FileNotFoundException exception) {
            System.out.println("Nie ma takiego pliku: " + patch);
            System.exit(1);
        }

        Scanner scanner = new Scanner(file);
        //get number of lines
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
        lineNumberReader.skip(Long.MAX_VALUE);
        int lines = lineNumberReader.getLineNumber();
        lineNumberReader.close();
        //array of films from file
        String[] films = new String[50];


        while (scanner.hasNextLine()) {
            for (int i = 0; i < lines; i++) {
                String line = scanner.nextLine();
                films[i] = line;
                //test of array
                //System.out.println(i + " " + films[i]);
                //test of array
            }
        }
        Random rand = new Random();
        // Get a number between [0 - lines].
        int randomNumber = rand.nextInt(lines);
        //int count = 0;
        String chosenFilm = films[randomNumber];
        //test of random title of the film
        //System.out.println(chosenFilm);
        Character[] chosenFilm2 = new Character[chosenFilm.length()];
        Character[] chosenFilm2Hide = new Character[chosenFilm.length()];

        //test of random title of the film
        for (int i = 0; i < chosenFilm.length(); i++) {
            if (Character.isLetter(chosenFilm.charAt(i))) {
                chosenFilm2[i] = chosenFilm.charAt(i);
                chosenFilm2Hide[i] = '_';
            } else {
                chosenFilm2Hide[i] = ' ';
            }
        }

        showChosenFilmHide(chosenFilm2Hide);

        int totalTry = 0;
        Scanner scanner2 = new Scanner(System.in);

        for (int i = 10; i > 0; i--) {
            checkTotalTry(chosenFilm2Hide,totalTry);
            Character guess = scanner2.nextLine().charAt(0);
            //System.out.println(guess); this was a test

            checkFilmHide(chosenFilm2Hide,chosenFilm2,guess);
            showChosenFilmHide(chosenFilm2Hide);
            boolean iswon = checkWin(chosenFilm2Hide);
            if (iswon) {
                System.out.println("Udało Ci się odgadnąć tytuł filmu: " + chosenFilm.toUpperCase());
                System.out.println("Gratulacje!");
                System.exit(0);
            } else{
                if (i == 1) System.out.println("Przykro mi, przegrałeś. Nie udało Ci się odgadnąć tytułu filmu: " + chosenFilm.toUpperCase());
            }

        }

    }

    public static void showChosenFilmHide(Character[] chosenFilmHide) {
        System.out.println("Odgadnij tytuł film: ");
        for (int i = 0; i < chosenFilmHide.length; i++) {
            System.out.print(chosenFilmHide[i]);
        }   System.out.println();
    }

    public static void checkFilmHide(Character[] chosenFilmHide, Character[] chosenFilm,Character hideChar) {
        for (int i = 0; i < chosenFilm.length; i++) {
            if (chosenFilm[i] == hideChar) {
                chosenFilmHide[i] = hideChar;
            }
        }
    }

    public static void checkTotalTry(Character[] chosenFilmHide,int totalTry) {
        for (int i = 0; i < chosenFilmHide.length; i++) {
            if (chosenFilmHide[i] != '_' && chosenFilmHide[i] != ' ') {
                totalTry++;
            }
        }
        System.out.println("Odkryłeś liter: " + totalTry );
        System.out.println("Odsłoń literę tytułu filmu: ");
    }

    public static boolean checkWin(Character[] chosenFilmHide) {
        int char1 = 0;
        boolean hasWon = false;

        for (int i = 0; i < chosenFilmHide.length; i++) {
            if (chosenFilmHide[i] == '_') {
                char1++;
                break;
            }
        }
        if (char1 == 0) hasWon = true;
        return hasWon;
    }

}
