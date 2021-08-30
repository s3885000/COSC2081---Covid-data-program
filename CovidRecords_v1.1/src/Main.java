import Actions.ShowResults;
import DataHandle.ReadCSV;
import Interface.Menu;

public class Main {

    public static void main(String[] args) {

        ReadCSV read = new ReadCSV();
        read.read();

        Menu menu = new Menu();
        menu.startMenu(true);

    }

}
