import javax.swing.*;
import java.io.*;
import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculations {
    File file;
    Scanner scanner;
    String[] tokens;

    public Calculations() throws IOException {
        scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equalsIgnoreCase("end")) {
            line = scanner.nextLine();
            tokens = line.split(" ");
            if (tokens[0].equalsIgnoreCase("print")) {
                print();
            } else if (tokens[0].equalsIgnoreCase("for")) {
                int replay = Integer.parseInt(tokens[1]);
                File forFile = new File("src/forFile.txt");
                FileOutputStream fileOutputStream = new FileOutputStream(forFile);
                PrintWriter printWriter = new PrintWriter(fileOutputStream);
                while (!line.equalsIgnoreCase("end for")) {
                    line = scanner.nextLine();
                    printWriter.println(line);
                }
                printWriter.close();
                fileOutputStream.close();
                forMethod(forFile, replay);
            } else if (tokens[1].equalsIgnoreCase("=")) {
                switch (tokens[3]) {
                    case "+": {
                        if (Program.variables.get(tokens[0]) instanceof Integer) plusI();
                        else plusF();
                        break;
                    }
                    case "-": {
                        if (Program.variables.get(tokens[0]) instanceof Integer) minusI();
                        else minusF();
                        break;
                    }
                    case "*": {
                        if (Program.variables.get(tokens[0]) instanceof Integer) multiplyI();
                        else multiplyF();
                        break;
                    }
                    case "/": {
                        if (Program.variables.get(tokens[0]) instanceof Integer) dividedI();
                        else dividedF();
                        break;
                    }
                    case "":
                        break;
                    default: {
                        if (!tokens[3].equalsIgnoreCase("+") && !tokens[3].equalsIgnoreCase("-") && !tokens[3].equalsIgnoreCase("*") && !tokens[3].equalsIgnoreCase("/") && !tokens[0].equalsIgnoreCase("print")) {
                            throw new EmptyStackException();
                        }
                    }
                }
            }

        }
        System.exit(1); //close the program
    }

    public int plusI() {
        if (tokens.length == 5 && exist(0)) {
            int result;
            if (exist(2) && exist(4)) {
                try {
                    result = Integer.parseInt(Program.variables.get(tokens[2]).toString()) + Integer.parseInt(Program.variables.get(tokens[4]).toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            } else if (exist(2)) {
                try {
                    result = Integer.parseInt(Program.variables.get(tokens[2]).toString()) + Integer.parseInt(tokens[4].toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            } else if (exist(4)) {
                try {
                    result = Integer.parseInt(tokens[2].toString()) + Integer.parseInt(Program.variables.get(tokens[4]).toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            } else {
                try {
                    result = Integer.parseInt(tokens[2].toString()) + Integer.parseInt(tokens[4].toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            }
        } else {
            throw new EmptyStackException();
        }
        return 0;
    }

    public float plusF() {
        if (tokens.length == 5 && exist(0)) {
            if (exist(2) && exist(4)) {
                float result = Float.parseFloat(Program.variables.get(tokens[2]).toString()) + Float.parseFloat(Program.variables.get(tokens[4]).toString());
                Program.variables.replace(tokens[0], result);
                return result;
            } else if (exist(2)) {
                float result = Float.parseFloat(Program.variables.get(tokens[2]).toString()) + Float.parseFloat(tokens[4].toString());
                Program.variables.replace(tokens[0], result);
                return result;
            } else if (exist(4)) {
                float result = Float.parseFloat(tokens[2].toString()) + Float.parseFloat(Program.variables.get(tokens[4]).toString());
                Program.variables.replace(tokens[0], result);
                return result;
            } else {
                float result = Float.parseFloat(tokens[2].toString()) + Float.parseFloat(tokens[4].toString());
                Program.variables.replace(tokens[0], result);
                return result;
            }
        } else {
            throw new EmptyStackException();
        }
    }

    public int minusI() {
        if (tokens.length == 5 && exist(0)) {
            int result;
            if (exist(2) && exist(4)) {
                try {
                    result = Integer.parseInt(Program.variables.get(tokens[2]).toString()) - Integer.parseInt(Program.variables.get(tokens[4]).toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            } else if (exist(2)) {
                try {
                    result = Integer.parseInt(Program.variables.get(tokens[2]).toString()) - Integer.parseInt(tokens[4].toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            } else if (exist(4)) {
                try {
                    result = Integer.parseInt(tokens[2].toString()) - Integer.parseInt(Program.variables.get(tokens[4]).toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            } else {
                try {
                    result = Integer.parseInt(tokens[2].toString()) - Integer.parseInt(tokens[4].toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            }
        } else {
            throw new EmptyStackException();
        }
        return 0;
    }

    public float minusF() {
        if (tokens.length == 5 && exist(0)) {
            if (exist(2) && exist(4)) {
                float result = Float.parseFloat(Program.variables.get(tokens[2]).toString()) - Float.parseFloat(Program.variables.get(tokens[4]).toString());
                Program.variables.replace(tokens[0], result);
                return result;
            } else if (exist(2)) {
                float result = Float.parseFloat(Program.variables.get(tokens[2]).toString()) - Float.parseFloat(tokens[4].toString());
                Program.variables.replace(tokens[0], result);
                return result;
            } else if (exist(4)) {
                float result = Float.parseFloat(tokens[2].toString()) - Float.parseFloat(Program.variables.get(tokens[4]).toString());
                Program.variables.replace(tokens[0], result);
                return result;
            } else {
                float result = Float.parseFloat(tokens[2].toString()) - Float.parseFloat(tokens[4].toString());
                Program.variables.replace(tokens[0], result);
                return result;
            }
        } else {
            throw new EmptyStackException();
        }
    }

    public int multiplyI() {
        if (tokens.length == 5 && exist(0)) {
            int result;
            if (exist(2) && exist(4)) {
                try {
                    result = Integer.parseInt(Program.variables.get(tokens[2]).toString()) * Integer.parseInt(Program.variables.get(tokens[4]).toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            } else if (exist(2)) {
                try {
                    result = Integer.parseInt(Program.variables.get(tokens[2]).toString()) * Integer.parseInt(tokens[4].toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            } else if (exist(4)) {
                try {
                    result = Integer.parseInt(tokens[2].toString()) * Integer.parseInt(Program.variables.get(tokens[4]).toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            } else {
                try {
                    result = Integer.parseInt(tokens[2].toString()) * Integer.parseInt(tokens[4].toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                }
            }
        } else {
            throw new EmptyStackException();
        }
        return 0;
    }

    public float multiplyF() {
        if (tokens.length == 5 && exist(0)) {
            if (exist(2) && exist(4)) {
                float result = Float.parseFloat(Program.variables.get(tokens[2]).toString()) * Float.parseFloat(Program.variables.get(tokens[4]).toString());
                Program.variables.replace(tokens[0], result);
                return result;
            } else if (exist(2)) {
                float result = Float.parseFloat(Program.variables.get(tokens[2]).toString()) * Float.parseFloat(tokens[4].toString());
                Program.variables.replace(tokens[0], result);
                return result;
            } else if (exist(4)) {
                float result = Float.parseFloat(tokens[2].toString()) * Float.parseFloat(Program.variables.get(tokens[4]).toString());
                Program.variables.replace(tokens[0], result);
                return result;
            } else {
                float result = Float.parseFloat(tokens[2].toString()) * Float.parseFloat(tokens[4].toString());
                Program.variables.replace(tokens[0], result);
                return result;
            }
        } else {
            throw new EmptyStackException();
        }
    }

    public int dividedI() {
        if (tokens.length == 5 && exist(0)) {
            int result;
            if (exist(2) && exist(4)) {
                try {
                    result = Integer.parseInt(Program.variables.get(tokens[2]).toString()) / Integer.parseInt(Program.variables.get(tokens[4]).toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException");
                }
            } else if (exist(2)) {
                try {
                    result = Integer.parseInt(Program.variables.get(tokens[2]).toString()) / Integer.parseInt(tokens[4].toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException");
                }
            } else if (exist(4)) {
                try {
                    result = Integer.parseInt(tokens[2].toString()) / Integer.parseInt(Program.variables.get(tokens[4]).toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException");
                }
            } else {
                try {
                    result = Integer.parseInt(tokens[2].toString()) / Integer.parseInt(tokens[4].toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (NumberFormatException e) {
                    System.out.println("error!");
                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException");
                }
            }
        } else {
            throw new EmptyStackException();
        }
        return 0;
    }

    public float dividedF() {
        if (tokens.length == 5 && exist(0)) {
            if (exist(2) && exist(4)) {
                try {
                    float result = Float.parseFloat(Program.variables.get(tokens[2]).toString()) / Float.parseFloat(Program.variables.get(tokens[4]).toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException");
                }
            } else if (exist(2)) {
                try {
                    float result = Float.parseFloat(Program.variables.get(tokens[2]).toString()) / Float.parseFloat(tokens[4].toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException");
                }
            } else if (exist(4)) {
                try {
                    float result = Float.parseFloat(tokens[2].toString()) / Float.parseFloat(Program.variables.get(tokens[4]).toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException");
                }
            } else {
                try {
                    float result = Float.parseFloat(tokens[2].toString()) / Float.parseFloat(tokens[4].toString());
                    Program.variables.replace(tokens[0], result);
                    return result;
                } catch (InputMismatchException e) {
                    System.out.println("InputMismatchException");
                }
            }
        } else {
            throw new EmptyStackException();
        }
        return 0;
    }

    public int print() {
        if (tokens.length == 2 && exist(1)) {
            System.out.println(Program.variables.get(tokens[1]));
            return Program.variables.get(tokens[1]).toString().length();
        } else {
            throw new EmptyStackException();
        }
    }

    public void forMethod(File forFile, int forReplay) throws FileNotFoundException {
        Scanner forScanner = new Scanner(forFile);
        String forLine = forScanner.nextLine();
        int count = 0;
        while (count < forReplay) {
            while (forScanner.hasNextLine()) {
                tokens = forLine.split(" ");
                if (tokens[0].equalsIgnoreCase("print")) {
                    print();
                } else if (tokens[1].equalsIgnoreCase("=")) {
                    switch (tokens[3]) {
                        case "+": {
                            if (Program.variables.get(tokens[0]) instanceof Integer) plusI();
                            else plusF();
                            break;
                        }
                        case "-": {
                            if (Program.variables.get(tokens[0]) instanceof Integer) minusI();
                            else minusF();
                            break;
                        }
                        case "*": {
                            if (Program.variables.get(tokens[0]) instanceof Integer) multiplyI();
                            else multiplyF();
                            break;
                        }
                        case "/": {
                            if (Program.variables.get(tokens[0]) instanceof Integer) dividedI();
                            else dividedF();
                            break;
                        }
                        case "":
                            break;
                        default: {
                            if (!tokens[3].equalsIgnoreCase("+") && !tokens[3].equalsIgnoreCase("-") && !tokens[3].equalsIgnoreCase("*") && !tokens[3].equalsIgnoreCase("/") && !tokens[0].equalsIgnoreCase("print")) {
                                throw new EmptyStackException();
                            }
                        }
                    }
                }
                forLine = forScanner.nextLine();
            }
            forScanner = new Scanner(forFile);
            count++;
        }
    }

    private boolean exist(int n) {
        return Program.variables.containsKey(tokens[n]);
    }
}
