package cn.feng;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ChengFeng
 * @CreateTime: 2024-04-27
 */
public class Main {
    public static void main(String[] args) throws IOException {
        List<CSVRecord> adjectives = getRecords("/name_adjactives.csv");
        List<CSVRecord> prefixes = getRecords("/name_prefices.csv");
        List<CSVRecord> names = getRecords("/name_names.csv");
        List<CSVRecord> verbs = getRecords("/name_verbs.csv");

        for (int i = 0; i < 100; i++) {
            System.out.println(randomString(ThreadLocalRandom.current().nextBoolean()? adjectives : prefixes) + randomString(names) + randomString(verbs));
        }
    }

    private static String randomString(List<CSVRecord> records) {
        int i = ThreadLocalRandom.current().nextInt(0, records.size());
        return records.get(i).get(1);
    }

    private static List<CSVRecord> getRecords(String file) throws IOException {
        InputStreamReader reader = new InputStreamReader(Main.class.getResourceAsStream(file));
        List<CSVRecord> records = CSVFormat.DEFAULT.parse(reader).getRecords();
        records.removeIf(it -> !isNumber(it.get(0)));
        return records;
    }

    private static boolean isNumber(String str) {
        return str != null && str.matches("-?\\d+(\\.\\d+)?");
    }
}
