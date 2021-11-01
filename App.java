import wintable.WinTable;
import wintable.WinTableBuilder;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class App {

    public static <T> T getRandomElement(T[] arr){
        return arr[ThreadLocalRandom.current().nextInt(arr.length)];
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        Set<String> set = new HashSet<>(Arrays.asList(args));
        if (args.length <= 1 || args.length % 2 == 0 || set.size() != args.length){
            throw new IllegalArgumentException("Input odd number of unrepeatable moves!");
        }
        KeyGen keyGen = new KeyGen();
        byte[] key = keyGen.generateKey();

        String randPSMove = getRandomElement(args);

        System.out.print("HMAC: ");

        keyGen.generateHMAC(key, randPSMove);

        WinTableBuilder tableBuilder = new WinTableBuilder(args);

        WinTable winTable = tableBuilder.buildTable();

        System.out.println("Available moves: ");
        for(int i = 0; i < args.length; i++){
            System.out.println((i+1) + "-" + args[i]);
        }
        System.out.println("0 - exit\n? - help");
        String playerChose = scanner.nextLine();
        if (playerChose.equals("?")) {
            System.out.println(winTable);
            System.exit(0);
        }
        else if (playerChose.equals("0")) {
            System.exit(0);
        }
        else {
            while (true) {
                try {
                    if (playerChose.equals("0")) {
                        System.exit(0);
                    }

                    if(Integer.parseInt(playerChose) <= args.length){
                        break;
                    }
//                    if (Objects.equals(playerChose, "?")) {
//                        System.out.println(winTable);
//                        System.exit(0);
//                    }
                    System.out.println("Available moves: ");
                    for (int i = 0; i < args.length; i++) {
                        System.out.println((i + 1) + "-" + args[i]);
                    }
                    System.out.println("0 - exit\n? - help");
                    playerChose = scanner.nextLine();
                }
                catch (NumberFormatException e){
                    System.out.println("Available moves: ");
                    for (int i = 0; i < args.length; i++) {
                        System.out.println((i + 1) + "-" + args[i]);
                    }
                    System.out.println("0 - exit\n? - help");
                    if (Objects.equals(playerChose, "?")) {
                        System.out.println(winTable);
                        System.exit(0);
                    }
                    playerChose = scanner.nextLine();
                }
            }
        }
        System.out.println("Your move: "+ args[Integer.parseInt(playerChose)-1]);
        System.out.println("PC move: " + randPSMove);

        WinnerDefiner definer = new WinnerDefiner(winTable, args[Integer.parseInt(playerChose)-1],randPSMove);

        String winner = definer.defineWinner(winTable, args[Integer.parseInt(playerChose)-1],randPSMove);
        System.out.println(winner);
        System.out.println("HMAC key: "+KeyGen.bytestohex(key));
    }
}
