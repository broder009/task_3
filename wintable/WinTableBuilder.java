package wintable;

public class WinTableBuilder {

    private final String[] moves;

    public WinTableBuilder(String[] moves) {
        this.moves = moves;
    }
    public String[] getMoves() {
        return moves;
    }
    public wintable.WinTable buildTable() {

        String[] header = new String[moves.length + 1];
        for (int i = 0; i < header.length; i++) {
            if (i == 0) {
                header[i] = "User\\PC";
            } else {
                header[i] = moves[i - 1];
            }
        }

        String[][] data = new String[moves.length][moves.length + 1];
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves.length + 1; j++) {
                data[i][0] = moves[i];
                if (j >= 1) {
                    data[i][j] = " ";
                }
            }
        }
        int i = 0;
        int j = 1;
        while (i < moves.length) {
            int HalfOfCircle = 1;
            int CounterOfMoves = 0;
            while (CounterOfMoves < moves.length) {
                if (HalfOfCircle <= moves.length / 2) {
                    if(j == i + 1){
                        data[i][j] = "draw";
                        CounterOfMoves++;
                    }
                    if (j < moves.length) {
                        j += 1;
                    }
                    else {
                        j = 1;
                    }
                    data[i][j] = "win";
                    CounterOfMoves++;
                    HalfOfCircle++;
                }
                else {
                    if (j < moves.length) {
                        j++;
                    } else {
                        j = 1;
                    }
                    data[i][j] = "lose";
                    CounterOfMoves++;
                }
            }
            i++;
            j = i  + 1;
        }
        return new wintable.WinTable(header, data);
    }
}
