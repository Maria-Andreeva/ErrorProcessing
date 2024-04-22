package IntermediateCertification;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CertificationErrorProcessing {
        public static void main(String[] args) throws InvalidDataException, IOException {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите данные: Фамилия Имя Отчество дата_рождения номер_телефона пол");
            String[] inputData = scanner.nextLine().split(" ");

            try {
                validateInput(inputData);
                writeToOutputFile(inputData);
                System.out.println("Данные успешно записаны в файл.");
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Ошибка записи данных в файл: " + e.getMessage());
                e.printStackTrace();
            }

        }

        private static void validateInput(String[] inputData) throws InvalidDataException {
            if (inputData.length < 6) {
                throw new InvalidDataException("Введено недостаточно данных. Введите 6 значений.");
            }
            else if ( (inputData.length > 6))
            {
                throw new InvalidDataException("Введено больше данных, чем требуется. Введите 6 значений.");
            }

            String dateOfBirth = inputData[3];
            if (!dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                throw new InvalidDataException("Неверный формат даты рождения. Используйте формат dd.mm.yyyy.");
            }

            try {
                Long.parseLong(inputData[4]);
            } catch (NumberFormatException e) {
                throw new InvalidDataException("Неверный формат номера телефона. Введите целое число.");
            }

            if (!inputData[5].equals("f") && !inputData[5].equals("m")) {
                throw new InvalidDataException("Неверный формат пола. Введите 'f' или 'm'.");
            }
        }

        private static void writeToOutputFile(String[] data) throws IOException {

            StringBuilder stringBuilder = new StringBuilder();
            for (String item : data) {
                stringBuilder.append(item).append(" ");
            }

            if (Files.exists(Paths.get(data[0] + ".txt")))
            {
                List<String> content = Files.readAllLines(Paths.get(data[0] + ".txt"));

                if (content.contains(stringBuilder.toString()))  return;

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
