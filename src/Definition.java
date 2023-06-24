import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Definition {
    File file;
    Scanner scanner;
    String[] tokens;

    public Definition(){
        scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equalsIgnoreCase("%%")) {
            tokens = line.split(" ");
            switch (tokens[0]) {
                case "int": {
                    initInt();
                    break;
                }
                case "float": {
                    initFloat();
                    break;
                }
                case "":
                    break;
                default: {
                    if (!tokens[0].equalsIgnoreCase("int") && !tokens[0].equalsIgnoreCase("float")) {
                        throw new EmptyStackException();
                    }
                }
            }
            line = scanner.nextLine();
        }
    }

    private void initInt() {
        try {
            if (tokens.length == 4 && tokens[2].equalsIgnoreCase("=") && duplicate()) {
                Program.variables.put(tokens[1], Integer.parseInt(tokens[3]));
            } else if (tokens.length == 2 && duplicate()) {
                Program.variables.put(tokens[1], 0);
            } else {
                throw new EmptyStackException();
            }
        } catch (NumberFormatException e) {
            System.out.println("error!");
        }
    }

    private void initFloat() {
        try {
            if (tokens.length == 4 && tokens[2].equalsIgnoreCase("=") && duplicate()) {
                Program.variables.put(tokens[1], Float.parseFloat(tokens[3]));
            } else if (tokens.length == 2 && duplicate()) {
                Program.variables.put(tokens[1], 0F);
            } else {
                throw new EmptyStackException();
            }
        } catch (NumberFormatException e) {
            System.out.println("error!");
        }
    }

    private boolean duplicate() {
        return !(Program.variables.containsKey(tokens[1]));
    }
}

