package com.sanderhelleso.maxProfit;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

/**
 * @author: Sander Hellesoe
 * @date: 10.14.2018
 */

/*****Each day the stock price fluctuates during the day.
      Given an array of stock prices, what would be the most
      efficient way to determine the best time to buy and sell
      to get the max profit. You must buy before you sell.

      Examples:
      --------------------------------------------------------------------------

      maxProfit( [ 1, 3, 5, 4, 3, 5, 10, 9, 8, 4, 9]) ->  buy at 1, sell at 10
      maxProfit( [ 2 , 3, 1, 5, 6, 9, 10, 15 14 ]) -> buy at 1, sell at 15
      maxProfit( [ 15, 12, 11, 9, 7, 5, 3, 1] ) -> no profit is possible
*****/

public class Main {

    // console colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    // random numb and scanner
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    // total excecution time
    private static long startTime = 0;
    private static long endTime = 0;

    // create report file
    private static File reportFile = new File("reports/stockMarketReport" + System.currentTimeMillis() + ".csv");
    private static PrintWriter pw;
    private static int dataIndex = 1;
    static {
        try {
            reportFile.getParentFile().mkdirs();
            pw = new PrintWriter(reportFile, "UTF-8");
            writeFileHead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        // prompt user for amount of reports
        System.out.print("How many reports do you want to generate?: ");
        int amount = scanner.nextInt() + 1;

        // run maxProfit
        startTime = System.nanoTime();
        for (int i = 1; i < amount; i++) {
            System.out.println("\nRunning report " + i);
            System.out.println(maxProfit(dataset(random.nextInt(10000) + 1000)));
            dataIndex++;
        }
        endTime = System.nanoTime();

        // display total execution time
        DecimalFormat df = new DecimalFormat("####0.00");
        String totalTime = df.format((double)(endTime - startTime) / 1000000000.0);
        System.out.println("\033[0;1m" + ANSI_GREEN + "\nTotal exceution time: " + totalTime + " seconds");
        pw.close();
    }

    // determine the max profit available
    // return a report of calculation data
    private static String maxProfit(int[] stockPrices) {

        System.out.println("Calculating data...\n");

        String noProfitPossible = "";
        int greatestDifference = 0;
        int min = 0;
        int max = 0;
        int minDay = 0;
        int maxDay = 0;

        for(int i = 0; i < stockPrices.length; i++) {
            for (int j = i + 1; j < stockPrices.length; j++) {

                // check if combined prices are less thar previous greatest difference
                if (stockPrices[i] - stockPrices[j] < greatestDifference) {
                    greatestDifference = stockPrices[i] - stockPrices[j];

                    // set min and max values
                    min = stockPrices[i];
                    max = stockPrices[j];

                    // set min and max days
                    minDay = i + 1;
                    maxDay = j + 1;
                }
            }
        }

        if (greatestDifference == 0) {
            noProfitPossible = ANSI_RED + "\n*** NO PROFIT IS POSSIBLE ***" + ANSI_RESET;
        }

        // display report
        String stockReport = "========== STOCK CALCULATION REPORT ==========\n" + "\nBuy when the stock value is " + ANSI_GREEN + min + ANSI_RESET + " on day " + ANSI_GREEN + minDay + ANSI_RESET +
                "\nSell when the stock value is " + ANSI_GREEN + max + ANSI_RESET + " on day " + ANSI_GREEN + maxDay + ANSI_RESET +
                "\nEstimated net gain from trade is " + ANSI_GREEN + Math.abs(greatestDifference) + ANSI_RESET + noProfitPossible +
                "\n\n============================================";

        // write calculated data to report file
        writeFileContent(dataIndex, min, max, minDay, maxDay);

        // return report and display for user
        return stockReport;
    }

    // generate a MASSIVE array of randomized 32-bit integers, used to test algorithm, or a small
    private static int[] dataset(final int SIZE) {
        System.out.println("Loading dataset...");

        int[] stockPrices = new int[SIZE];
        for (int i = 0; i < stockPrices.length; i++) {
            stockPrices[i] = random.nextInt(100000) + 125;
        }

        return stockPrices;
    }

    // write file head (sellValue, buyValue, sellDay, buyDay)
    private static void writeFileHead() {
        StringBuilder sb = new StringBuilder();
        sb.append("id");
        sb.append(',');
        sb.append("sellValue");
        sb.append(',');
        sb.append("buyValue");
        sb.append(',');
        sb.append("sellDay");
        sb.append(',');
        sb.append("buyDay");
        sb.append('\n');

        pw.write(sb.toString());
    }

    // write file content
    private static void writeFileContent(int id, int sellValue, int buyValue, int sellDay, int buyDay) {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(',');
        sb.append(sellValue);
        sb.append(',');
        sb.append(buyValue);
        sb.append(',');
        sb.append(sellDay);
        sb.append(',');
        sb.append(buyDay);
        sb.append('\n');

        pw.write(sb.toString());
    }
}
