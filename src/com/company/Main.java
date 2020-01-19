package com.company;

import java.util.*;

public class Main {

    // Given data for task 1
    public static int[] ARRAY_FOR_SORT = {17, 103, 22, 45, 7, 6, 21, 1};
    // Given data for task 2
    public static int FIND_X = 7;
    // Given data for task 3
    public static String STRING_LINE = "Start day, start way..... One, two, one, one. Stop test.";
    // Given data for task 4
    public static int FACTORIAL_X = 15;
    // Given data for task 5
    public static String BRACKET_SEQUENCE = "(){}(({{[]}}))";

    public static void main(String[] args) {

        System.out.println("Task 1: Sort\nGiven array:");
        printIntArray(ARRAY_FOR_SORT);
        intArraySort(ARRAY_FOR_SORT);
        System.out.println("Sorted array:");
        printIntArray(ARRAY_FOR_SORT);

        System.out.println("\nTask 2: Search\nGiven sorted array: ");
        printIntArray(ARRAY_FOR_SORT);
        System.out.println(String.format("Element %d has index: %d", FIND_X,
                sortedArraySearch(ARRAY_FOR_SORT, FIND_X)));

        System.out.println("\nTask 3: Strings\nGiven string: " + STRING_LINE + "\nResult of non-repeating words:");
        System.out.println(findSingleWords(STRING_LINE));

        System.out.println(String.format("\nTask 4: Factorial\nFactorial for %d: %d",
                FACTORIAL_X, calcFactorial(FACTORIAL_X)));

        System.out.println(String.format("\nTask 5: Right bracket sequence\nThis bracket sequence \"%s\" " +
                        "is right bracket sequence: %s", BRACKET_SEQUENCE, checkBracketSequence(BRACKET_SEQUENCE)));
    }

    public static void printIntArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void intArraySort(int[] arr) {
        for (int j = 0;j<arr.length-1;j++) {
            for (int i = 0; i < arr.length - j -1 ; i++) {
                int a;
                if (arr[i] > arr[i + 1]) {
                    a = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = a;
                }
            }
        }
    }

    public static int sortedArraySearch(int[] arr, int x) {

        int first = 0;
        int last = arr.length - 1;
        int i;

        while (last >= first ) {
            i = (last - first)/2 + first;
            if (arr[i] == x) {
                return i;
            }

            if (x < arr[i]) {
                last = i - 1;
            } else {
                first = i + 1;
            }
        }

        return -1;
    }

    public static List<String> findSingleWords(String stringLine) {
        String[] wordArray = stringLine.toLowerCase().split("\\W+");
        List<String> resultList = new LinkedList<>();

        for (int i=0; i<wordArray.length; i++) {
            boolean isEqual = false;
            if (wordArray[i] != null) {
                for (int j = 0; j < wordArray.length; j++) {
                    if (i != j && wordArray[j] != null
                            && wordArray[i].equals(wordArray[j])) {
                        isEqual = true;
                        wordArray[j] = null;
                    }
                }
                if (!isEqual) {
                    resultList.add(wordArray[i]);
                } else {
                    wordArray[i] = null;
                }
            }
        }
        return resultList;
    }

    static int calcFactorial(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial = i * factorial;
        }
        return factorial;
    }

    static boolean checkBracketSequence(String sequence) {

        Set<Character> openBrackets = new HashSet<>();
        openBrackets.add('{');
        openBrackets.add('(');
        openBrackets.add('[');

        LinkedList<Character> openCharsList = new LinkedList<>();

        for (Character character: sequence.toCharArray()) {
            if (openBrackets.contains(character)) {
                openCharsList.addLast(character);
            } else if (!openCharsList.isEmpty()) {
                char openCharacter = openCharsList.pollLast();
                if ((openCharacter != '{' && character == '}')
                        || (openCharacter != '(' && character == ')')
                        || (openCharacter != '[' && character == ']')
                        ) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return openCharsList.isEmpty();
    }
}
