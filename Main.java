import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.util.ArrayList;

public class Main {

    static class Employee {
        String id, salary, name, gender, birthday;
        Subdivision subdivision;

        public Employee(String a, String b, String c, String d, String e, Subdivision f) {
            id = a;
            name = b;
            birthday = d;
            gender = c;
            salary = e;
            subdivision = f;
        }
    }

    static class Subdivision {
        String name;
        int id;

        public Subdivision(String a, int b) {
            name = a;
            id = b;
        }
    }
    public static void main(String[] args) throws IOException {
        String csvFilePath = "foreign_names.csv";
       String separator = ";";
        ArrayList<Employee> office = new ArrayList<>();
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
                    Employee tmp = new Employee(nextLine[0], nextLine[1],
                            nextLine[2], nextLine[3], nextLine[5], new Subdivision(nextLine[4], nextLine[4].charAt(0) - 'A'));
                    office.add(tmp);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            for (Employee tmp: office) {
                System.out.println(tmp.id + " " + tmp.name + " " + tmp.gender
                        + " " + tmp.birthday + " " + tmp.subdivision.name + " "  +tmp.subdivision.id + " " + tmp.salary);
            }
        }
    }

}