/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 09
   *
   *  Tai yra demonstracinė šachmatų lentos konstruktoriaus klasė, 
   *    kurioje demonstruojama metodika, kai klasė yra išvestinė iš ScreenKTU.
   *  Atkreipkite dėmesį į rekursyvinį konstruktoriaus panaudojimą.
   *  EKSPERIMENTUOKITE su kitokiu figūrų pildymo metodu.
   ****************************************************************************/
package parodomieji;
import static java.awt.Color.*;
import java.awt.event.MouseEvent;
/**
 * @author Mutis
 */
public class ChessTablesRecursive extends studijosKTU.ScreenKTU {
    final private static char kingSym = 0x2654; // karaliaus figūra Unicode
    
    ChessTablesRecursive(int cellSize){
        super(cellSize, 8, Fonts.plainC);
        if(cellSize < 56)
            new ChessTablesRecursive(cellSize + 4);
        for(int i=0; i<64; i++){
            int r=i/8, c=i%8;
            print(r, c, (r+c)%2==0? green: gray);
        }
        refresh(200);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int r = e.getY() / cellH;
        int c = e.getX() / cellW;
        setColors((r+c)%2==0? green: gray, black);
        print(r, c, (char)(kingSym + (r+r+c) % 12));
        refresh();
    }
    public static void main(String[] args) {      
        new ChessTablesRecursive(32);
    }
}