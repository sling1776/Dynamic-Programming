import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;

public class main {
    static int MAX_TABLE_SIZE = 24;
    static int MAX_GROUP_SIZE = 12;
    static Teacup[][] table1 = new Teacup[MAX_TABLE_SIZE][MAX_GROUP_SIZE+1];
    static int[] prices = new int[(MAX_GROUP_SIZE + 1)];


    public static Teacup getTableEntry(int teacupsSold, int biggestSetAllowed){
        if(teacupsSold<0) return null;
        if(biggestSetAllowed < 0) return null;
        if(biggestSetAllowed > MAX_GROUP_SIZE) return null;

        if (table1[teacupsSold][biggestSetAllowed] != null) return table1[teacupsSold][biggestSetAllowed];

        if (teacupsSold == 0) {
            table1[teacupsSold][biggestSetAllowed] = new Teacup(0,0);
            return table1[teacupsSold][biggestSetAllowed];
        }
        if (biggestSetAllowed == 0) {
            table1[teacupsSold][biggestSetAllowed] = new Teacup(0,0);
            return table1[teacupsSold][biggestSetAllowed];
        }

        Teacup dontUse = getTableEntry(teacupsSold, biggestSetAllowed-1);
        Teacup usePart1 = getTableEntry(teacupsSold-biggestSetAllowed, biggestSetAllowed);
        Teacup derivedTeacup = null;
        if(usePart1 != null){
            derivedTeacup = new Teacup(usePart1, prices[biggestSetAllowed],biggestSetAllowed,teacupsSold );
        }

        Teacup newTableEntry = dontUse.max(derivedTeacup);

        table1[teacupsSold][biggestSetAllowed] = newTableEntry;
         return table1[teacupsSold][biggestSetAllowed];
    }


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("set2.txt");
        Scanner scanner = new Scanner(file);
        int i = 0;
        while(scanner.hasNext()){
            prices[i] = scanner.nextInt();
            i++;
        }

        for(int j = 1; j< MAX_TABLE_SIZE ; j++){
            String line = Objects.requireNonNull(getTableEntry(j, MAX_GROUP_SIZE)).toString();
            System.out.println(line);
        }

    }
}
