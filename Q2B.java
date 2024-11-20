import java.io.*;
import java.util.zip.*;
import java.util.logging.*;

// Custom Exception for invalid compression format
class InvalidCompressionFormatException extends Exception {
    public InvalidCompressionFormatException(String message) {
        super(message);
    }
}

// File compression and decompression class
public class Q2B {
    private static final Logger logger = Logger.getLogger(FileCompressionDecompression.class.getName());

    // Method to perform RLE compression on a text file
    public static void compressTextFile(String inputFilePath, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String compressedLine = runLengthEncode(line);
                writer.write(compressedLine);
                writer.newLine();
            }

            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputFilePath);

            logger.info("Original file size: " + inputFile.length() + " bytes");
            logger.info("Compressed file size: " + outputFile.length() + " bytes");

        } catch (IOException e) {
            System.err.println("Error during compression: " + e.getMessage());
        }
    }

    // Method to perform RLE compression on a binary file
    public static void compressBinaryFile(String inputFilePath, String outputFilePath) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFilePath));
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath))) {

            int currentByte;
            int count = 1;
            int previousByte = -1;

            while ((currentByte = inputStream.read()) != -1) {
                if (currentByte == previousByte) {
                    count++;
                } else {
                    if (previousByte != -1) {
                        outputStream.write(previousByte);
                        outputStream.write(count);
                    }
                    previousByte = currentByte;
                    count = 1;
                }
            }

            // Writing last byte sequence
            if (previousByte != -1) {
                outputStream.write(previousByte);
                outputStream.write(count);
            }

            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputFilePath);

            logger.info("Original file size: " + inputFile.length() + " bytes");
            logger.info("Compressed file size: " + outputFile.length() + " bytes");

        } catch (IOException e) {
            System.err.println("Error during binary compression: " + e.getMessage());
        }
    }

    // Method to perform RLE decompression on a text file
    public static void decompressTextFile(String inputFilePath, String outputFilePath) throws InvalidCompressionFormatException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String decompressedLine = runLengthDecode(line);
                writer.write(decompressedLine);
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error during decompression: " + e.getMessage());
        }
    }

    // Method to perform RLE decompression on a binary file
    public static void decompressBinaryFile(String inputFilePath, String outputFilePath) throws InvalidCompressionFormatException {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFilePath));
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath))) {

            int previousByte = -1;
            int count;

            while ((previousByte = inputStream.read()) != -1) {
                count = inputStream.read();
                if (count == -1) {
                    throw new InvalidCompressionFormatException("Invalid compression format during decompression.");
                }
                for (int i = 0; i < count; i++) {
                    outputStream.write(previousByte);
                }
            }

        } catch (IOException e) {
            System.err.println("Error during binary decompression: " + e.getMessage());
        }
    }

    // Helper method to perform Run-Length Encoding (RLE) for text
    private static String runLengthEncode(String input) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        char currentChar;

        for (int i = 0; i < input.length(); i++) {
            currentChar = input.charAt(i);
            if (i + 1 < input.length() && currentChar == input.charAt(i + 1)) {
                count++;
            } else {
                result.append(currentChar).append(count);
                count = 1;
            }
        }
        return result.toString();
    }

    // Helper method to perform Run-Length Decoding (RLE) for text
    private static String runLengthDecode(String input) throws InvalidCompressionFormatException {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int count;
            try {
                count = Integer.parseInt(String.valueOf(input.charAt(i + 1)));
            } catch (NumberFormatException e) {
                throw new InvalidCompressionFormatException("Invalid format in compressed data.");
            }
            for (int j = 0; j < count; j++) {
                result.append(currentChar);
            }
            i++; // Skip the number after the character
        }
        return result.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        String textFilePath = "sample.txt";
        String compressedTextFilePath = "compressed.txt";
        String decompressedTextFilePath = "decompressed.txt";
        String binaryFilePath = "sample.bin";
        String compressedBinaryFilePath = "compressed_bin.bin";
        String decompressedBinaryFilePath = "decompressed_bin.bin";

        // Compress and decompress text file
        compressTextFile(textFilePath, compressedTextFilePath);
        try {
            decompressTextFile(compressedTextFilePath, decompressedTextFilePath);
        } catch (InvalidCompressionFormatException e) {
            System.err.println(e.getMessage());
        }

        // Compress and decompress binary file
        compressBinaryFile(binaryFilePath, compressedBinaryFilePath);
        try {
            decompressBinaryFile(compressedBinaryFilePath, decompressedBinaryFilePath);
        } catch (InvalidCompressionFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}
