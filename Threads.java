/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package threads;
import java.util.Scanner;
/**
 *
 * @author ELCOT
 */
public class Threads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         InputThread inputThread = new InputThread();
        DivisibleByFiveThread divisibleByFiveThread = new DivisibleByFiveThread();
        AverageThread averageThread = new AverageThread();

        inputThread.start();
        try {
            inputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        divisibleByFiveThread.start();
        averageThread.start();
      
    }
    
}


class InputThread extends Thread {
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements: ");
        int n = scanner.nextInt();

        int[] numbers = new int[n];
        System.out.println("Enter " + n + " numbers: ");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        DivisibleByFiveThread.setNumbers(numbers);
        AverageThread.setNumbers(numbers);

        scanner.close();
    }
}

class DivisibleByFiveThread extends Thread {
    private static int[] numbers;

    public static void setNumbers(int[] numbers) {
        DivisibleByFiveThread.numbers = numbers;
    }

    @Override
    public void run() {
        System.out.println("Numbers divisible by five: ");
        for (int number : numbers) {
            if (number % 5 == 0) {
                System.out.print(number + " ");
            }
        }
        System.out.println();
    }
}

class AverageThread extends Thread {
    private static int[] numbers;

    public static void setNumbers(int[] numbers) {
        AverageThread.numbers = numbers;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        double average = (double) sum / numbers.length;
        System.out.println("Average of the numbers: " + average);
    }
}

