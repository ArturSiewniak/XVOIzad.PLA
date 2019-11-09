//SDK: Java version: 13.0.1
//Kod testowany na plikach

package siwy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class Main implements Runnable {
    //Zmienne
    //Liczba budynków stojących w rzędzie
    int n;
    //Tablica która będzie zawierała wymiary budynków
    int[] dimensionsArray;
    //Licznik plakatów poza tymi znajdującymi się nad jednym "dużym" (powinien zawierać się w przedziale 2 - 3)
    int standalonePostersCounter;
    //Ścieżka pliku z którego mają być odczytywane dane wejściowe
    String filePath = new File("pla.in").getAbsolutePath();

    public static void main(String[] args) {
        new Thread(new Main()).start();
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            //Odczytane linie z pliku wejściowego
            String widthHeight = br.readLine();
            StringTokenizer st = new StringTokenizer(widthHeight, " ");
            //Pobranie ilości domów w rzędzie z pliku wejściowego
            n = Integer.parseInt(st.nextToken());
            dimensionsArray = new int[n];
            standalonePostersCounter = 0;
            //Liczba plakatów znajdujących się nad jednym "dużym" plakatem, który ciągnie się prawie do samego końca rzędu budynków
            int postersAboveBigOne = 0;
            for (int i = 0; i < n; ++i) {
                widthHeight = br.readLine();
                st = new StringTokenizer(widthHeight);
                st.nextToken();
                //Pobiera kolejne wartości wysokości z pliku wejściowego
                int height = Integer.parseInt(st.nextToken());
                while (standalonePostersCounter > 0 && dimensionsArray[standalonePostersCounter - 1] > height) {
                    postersAboveBigOne++;
                    standalonePostersCounter--;
                }
                if (standalonePostersCounter == 0 || dimensionsArray[standalonePostersCounter - 1] < height) {
                    dimensionsArray[standalonePostersCounter++] = height;
                }
            }
            //Wartość która zostanie zapisana w pliku wyjściowym "pla.out"
            int outcome = (standalonePostersCounter + postersAboveBigOne);
            String fileContent = String.valueOf(outcome);
            //Zapis do pliku wyjściowego "pla.out"
            BufferedWriter writer = new BufferedWriter(new FileWriter("pla.out"));
            writer.write(fileContent);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
