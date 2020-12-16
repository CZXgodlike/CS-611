package Assets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphicReader {

    public GraphicReader(){}

    public StringBuilder readGraphic(String pathname) {
        StringBuilder graphic = new StringBuilder();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(pathname));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            graphic.append(s);
            graphic.append('\n');
        }
        return graphic;
    }
}
