package org.hcms.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalTablePrinter {
    public static void main(String[] args) {
        class Test {
            String s;
            String s1;
            String s2;
            public Test(String s, String s1, String s2) {
                this.s = s;
                this.s1 = s1;
                this.s2 = s2;
            }
        }

        Function<Test, List<String>> mapper = (el) -> {
            return Arrays.asList(el.s, el.s1, el.s2);
        };

        List<String> header = Arrays.asList("id", "First Name", "Last Name");

        printTable(header, Arrays.asList(new Test("1", "Mako", "Polo")), mapper);
    }

    public static <ELEMENT> void printTable(List<String> header, List<ELEMENT> rows, Function<ELEMENT, List<String>> mapper) {
        if (header == null || header.isEmpty() || mapper == null) {
            return;
        }
        if (rows == null || rows.isEmpty()) {
            System.out.println("No data");
        }

        List<List<String>> rowsTable = rows
                .stream()
                .map(mapper)
                .collect(Collectors.toList());

        String[][] table2 = new String[rowsTable.size()+1][3];
        table2[0] = header.toArray(String[]::new);
        for (int i = 0; i < rowsTable.size(); i++) {
            table2[i+1] = rowsTable.get(i).toArray(String[]::new);
        }
        printTableUtil(table2);
    }

    private static void printTableUtil(String[][] table) {
        /*
         * leftJustifiedRows - If true, it will add "-" as a flag to format string to
         * make it left justified. Otherwise right justified.
         */
        boolean leftJustifiedRows = false;


        /*
         * Calculate appropriate Length of each column by looking at width of data in
         * each column.
         *
         * Map columnLengths is <column_number, column_length>
         */
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
        // System.out.println("columnLengths = " + columnLengths);

        /*
         * Prepare format String
         */
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");
        // System.out.println("formatString = " + formatString.toString());

        /*
         * Prepare line for top, bottom & below header row.
         */
        String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
            String templn = "+-";
            templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
                    (a1, b1) -> a1 + b1);
            templn = templn + "-";
            return ln + templn;
        }, (a, b) -> a + b);
        line = line + "+\n";
        // System.out.println("Line = " + line);

        /*
         * Print table
         */
        System.out.print(line);
        Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
        System.out.print(line);

        Stream.iterate(1, (i -> i < table.length), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), table[a]));
        System.out.print(line);
    }


}
