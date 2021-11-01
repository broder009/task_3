import wintable.WinTable;

import java.util.Objects;

public class WinnerDefiner {

    private final WinTable winTable;
    private final String playerChoose;
    private final String randPSMove;

    public WinnerDefiner(WinTable winTable, String playerChose, String randPSMove) {
        this.winTable = winTable;
        this.playerChoose =playerChose;
        this.randPSMove = randPSMove;
    }

    public String defineWinner(WinTable table, String playerChose, String randPSMove) {
        String result = "";
        for(int i = 0; i < table.getHeader().length; i++){
            if(Objects.equals(playerChose, table.getData()[i][0])){
                for (int j = 1; j < table.getHeader().length + 1; j++){
                    if(Objects.equals(table.getHeader()[j - 1], randPSMove)){
                        result = table.getData()[i][j-1];
                        break;
                    }
                }
            }
            if (!Objects.equals(result, "")){
                break;
            }
        }
        return result;
    }
}