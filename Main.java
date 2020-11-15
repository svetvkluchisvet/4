import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.util.ArrayList;

public class Main {

    static class Person {
        String id, salary, name, gender, birthday;
        Subunit subunit;

        public Person (String a, String b, String c, String d, String e, Subunit f) {
            id = a;
            name = b;
            birthday = d;
            gender = c;
            salary = e;
            subunit = f;
        }
    }

    static class Subunit {
        String name;
        int id;

        public Subunit(String a, int b) {
            name = a;
            id = b;
        }
    }
    public static void main(String[] args) throws IOException {
        String csvFilePath = "D:\\Бурляева\\Универ\\3 курс 5 семестр\\Жаба\\(Л4) Считывание из файла в коллекцию\\foreign_names.csv";
        String separator = ";";
        ArrayList<Person> office = new ArrayList<>();
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream(csvFilePath)) {
            CSVReaderBuilder builder = new CSVReaderBuilder(new FileReader(csvFilePath));
            CSVReader reader = builder.withSkipLines(1).build();
            if (reader == null) {
                throw new FileNotFoundException(csvFilePath);
            }
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                nextLine = nextLine[0].split(separator);
                if (nextLine.length > 2) {
                    Person tmp = new Person(nextLine[0], nextLine[1],
                            nextLine[2], nextLine[3], nextLine[5], new Subunit(nextLine[4], nextLine[4].charAt(0) - 'A'));
                    office.add(tmp);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();

        } finally {
            for (Person tmp: office) {
                System.out.println(tmp.id + " " + tmp.name + " " + tmp.gender
                        + " " + tmp.birthday + " " + tmp.subunit.name + " "  +tmp.subunit.id + " " + tmp.salary);
            }
        }
    }

}