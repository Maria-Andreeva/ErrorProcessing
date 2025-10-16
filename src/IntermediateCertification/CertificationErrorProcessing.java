package IntermediateCertification;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class CertificationErrorProcessing {
    public static void main(String[] args) throws InvalidDataException, IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter data: LastName FirstName MiddleName date_of_birth phone_number gender");
        String[] inputData = scanner.nextLine().split(" ");

        try {
            validateInput(inputData);
            writeToOutputFile(inputData);
            System.out.println("Data successfully written to file.");
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing data to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void validateInput(String[] inputData) throws InvalidDataException {
        if (inputData.length < 6) {
            throw new InvalidDataException("Not enough data entered. Please enter 6 values.");
        } else if (inputData.length > 6) {
            throw new InvalidDataException("More data entered than required. Please enter exactly 6 values.");
        }

        String dateOfBirth = inputData[3];
        if (!dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new InvalidDataException("Invalid date of birth format. Use format dd.mm.yyyy.");
        }

        try {
            Long.parseLong(inputData[4]);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Invalid phone number format. Enter an integer.");
        }

        if (!inputData[5].equals("f") && !inputData[5].equals("m")) {
            throw new InvalidDataException("Invalid gender format. Enter 'f' or 'm'.");
        }
    }

    private static void writeToOutputFile(String[] data) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : data) {
            stringBuilder.append(item).append(" ");
        }

        if (Files.exists(Paths.get(data[0] + ".txt"))) {
            List<String> content = Files.readAllLines(Paths.get(data[0] + ".txt"));
            if (content.contains(stringBuilder.toString())) return;
        }

        String filename = data[0] + ".txt";
        FileWriter writer = new FileWriter(filename, true);
        stringBuilder.append("\n");
        writer.write(stringBuilder.toString());
        writer.close();
    }
}

class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}
