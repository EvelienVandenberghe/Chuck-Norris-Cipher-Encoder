package chucknorris;
import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Main loop
        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = scanner.nextLine();
            
            if (operation.equals("encode")) {
                System.out.println("Input string:");
                String input = scanner.nextLine();
                String encoded = encode(input);
                System.out.println("Encoded string:");
                System.out.println(encoded);
                System.out.println();
                
            } else if (operation.equals("decode")) {
                System.out.println("Input encoded string:");
                String encoded = scanner.nextLine();
                
                if (!isValid(encoded)) {
                    System.out.println("Encoded string is not valid.");
                    System.out.println();
                } else {
                    String decoded = decode(encoded);
                    System.out.println("Decoded string:");
                    System.out.println(decoded);
                    System.out.println();
                }
                
            } else if (operation.equals("exit")) {
                System.out.println("Bye!");
                break;
                
            } else {
                System.out.println("There is no '" + operation + "' operation");
                System.out.println();
            }
        }
    }
    
    private static String encode(String input) {
        StringBuilder binary = new StringBuilder();
        
        // Converts each character to 7-bit
        for (char c : input.toCharArray()) {
            String binaryChar = Integer.toBinaryString(c);
            binary.append(String.format("%7s", binaryChar).replace(' ', '0'));
        }
        
        StringBuilder result = new StringBuilder();
        String binaryStr = binary.toString();
        
        // Loop through binaryStr
        for (int i = 0; i < binaryStr.length(); ) {
            char bit = binaryStr.charAt(i);
            int count = 0;
            
            while (i < binaryStr.length() && binaryStr.charAt(i) == bit) {
                count++; // How many consecutive identical bits
                i++;
            }            
            if (result.length() > 0) {
                result.append(" ");   // Add space (except for first group)
            }        
            
            result.append(bit == '1' ? "0" : "00"); // First block
            result.append(" ");
            result.append("0".repeat(count)); // Second block: count number of zeros
        }
        
        return result.toString();
    }
    
    private static String decode(String encoded) {
        String[] blocks = encoded.split(" ");
        StringBuilder binary = new StringBuilder();
        
        // Loop through blocks in pairs
        for (int i = 0; i < blocks.length; i += 2) {
            char bit = blocks[i].equals("0") ? '1' : '0';
            int count = blocks[i + 1].length(); // Length second block
            binary.append(String.valueOf(bit).repeat(count));
        }
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < binary.length(); i += 7) {
            String chunk = binary.substring(i, i + 7);
            result.append((char) Integer.parseInt(chunk, 2)); // Converts binary to decimal to character
        }
        
        return result.toString();
    }
    
    private static boolean isValid(String encoded) {
        if (!encoded.matches("[0 ]+")) {
            return false;
        }
        
        String[] blocks = encoded.split(" ");
        
        if (blocks.length % 2 != 0) {
            return false;
        }
        StringBuilder binary = new StringBuilder();
        
        for (int i = 0; i < blocks.length; i += 2) {  // Checks each block in pairs
            String firstBlock = blocks[i];
            String secondBlock = blocks[i + 1];
            
            if (!firstBlock.equals("0") && !firstBlock.equals("00")) {
                return false;
            }
            
            char bit = firstBlock.equals("0") ? '1' : '0'; // Builds binary to check divisibility by 7
            int count = secondBlock.length();
            binary.append(String.valueOf(bit).repeat(count));
        }
        
        if (binary.length() % 7 != 0) {
            return false;
        }
        
        return true;
    }
}
