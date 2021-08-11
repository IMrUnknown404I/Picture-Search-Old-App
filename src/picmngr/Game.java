package picmngr;
/**
 *
 * @author Ruslan
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;

public class Game {
    static int [] canvas = {0,0,0,
                            0,0,0,
                            0,0,0};
    //012, 345, 678, 036, 147, 258, 048, 246
    static boolean end=false;
    
    public static void main(){
        
        out.println("\n");
        out.println("Who doesn't like tic-tac-toe? Noone does :)\nChoose the zone (0-9) to put your mark in.\nPast '666' at any turn to finish untimely.\n");
        boolean b;
        boolean isCurrentX = false;
        do {
            isCurrentX = !isCurrentX;
            drawCanvas();
            out.println("mark " + (isCurrentX ? "X" : "O"));
            int n = getNumber();
            if(n==666) { end=true; break; }
            canvas[n] = isCurrentX ? 1 : 2;
            b = !isGameOver(n);
            if (isDraw()){
                System.out.println("Draw");
                return;
            }
        } while (b);
        if(!end){
            drawCanvas();
            out.println();
            out.println("CONGRATULATIONS!\nThe winner is " + (isCurrentX ? "X" : "O") + "!");
            out.println("\nDeveloped by Subaev Ruslan.");
        } else out.println("\nHave a nice day then! Goodbuy.\nDeveloped by Subaev Ruslan.");
    }

    static int getNumber(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                int n = Integer.parseInt(reader.readLine());
                if(n==666) return n;
                if (n >= 0 && n < canvas.length && canvas[n]==0){
                    return n;
                }
                out.println("Choose free cell and enter its number");
            } catch (NumberFormatException e) {
                out.println("Please enter the number");
            } catch (IOException e) {
            }
        }
    }

    static boolean isGameOver(int n){
        // 0 1 2
        // 3 4 5
        // 6 7 8
        //поиск совпадений по горизонтали
        int row = n-n%3; //номер строки - проверяем только её
        if (canvas[row]==canvas[row+1] &&
                canvas[row]==canvas[row+2]) return true;
        //поиск совпадений по вертикали
        int column = n%3; //номер столбца - проверяем только его
        if (canvas[column]==canvas[column+3])
            if (canvas[column]==canvas[column+6]) return true;
        //мы здесь, значит, первый поиск не положительного результата
        //если значение n находится на одной из граней - возвращаем false
        if (n%2!=0) return false;
        //проверяем принадлежит ли к левой диагонали значение
        if (n%4==0){
            //проверяем есть ли совпадения на левой диагонали
            if (canvas[0] == canvas[4] &&
                    canvas[0] == canvas[8]) return true;
            if (n!=4) return false;
        }
        return canvas[2] == canvas[4] &&
                canvas[2] == canvas[6];
    }

    static void drawCanvas(){
        out.println("     |     |     ");
        for (int i = 0; i < canvas.length; i++) {
            if (i!=0){
                if (i%3==0) {
                    out.println();
                    out.println("_____|_____|_____");
                    out.println("     |     |     ");
                }
                else
                    out.print("|");
            }

            if (canvas[i]==0) out.print("  " + i + "  ");
            if (canvas[i]==1) out.print("  X  ");
            if (canvas[i]==2) out.print("  O  ");
        }
        out.println();
        out.println("     |     |     ");
    }

    public static boolean isDraw() {
        for (int n : canvas) if (n==0) return false;
        return true;
    }
}