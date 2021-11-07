package com.example.homework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hw1 extends Thread {

    private ArrayList<Integer> nums;

    public Hw1 (ArrayList<Integer> nums){
        this.nums = nums;
    }

    public void run(){
        System.out.println(nums);
        int total = 0;
        for (int number : nums) {
            total += number;
        }
        System.out.println("Total: " + total);
    }


    public static void main(String[] args) {
        String numberString = null;
        int naiveSol = 0;
        try{
            File myObj = new File("Array.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                numberString = myReader.nextLine();
            }
            myReader.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        if (numberString == null)   return;
        //System.out.println(numberString);
        String[] numberStrArr = numberString.split(", ");
        int[] numbers = new int[numberStrArr.length];
        for (int i = 0; i < numberStrArr.length; i++){
            numbers[i] = Integer.parseInt(numberStrArr[i]);
            naiveSol += numbers[i];
        }
        // This is Naive Solution Part
        System.out.println("Naive Solution Value : " + naiveSol);

        // This is Multithread Part
        ArrayList<ArrayList<Integer>> splitedNums = new ArrayList<>();
        ArrayList<Integer> num1 = new ArrayList<>();
        ArrayList<Integer> num2 = new ArrayList<>();
        ArrayList<Integer> num3 = new ArrayList<>();
        ArrayList<Integer> num4 = new ArrayList<>();
        int limit = numbers.length / 4;
        for (int i = 1; i <= numbers.length; i++){
            if(i < 2500)
                num1.add(numbers[i-1]);
            else if(i < 5000)
                num2.add(numbers[i-1]);
            else if(i < 7500)
                num3.add(numbers[i-1]);
            else
                num4.add(numbers[i-1]);
            if(i % limit == 0){
                if(i == 2500)
                    splitedNums.add(num1);
                if(i == 5000)
                    splitedNums.add(num2);
                if(i == 7500)
                    splitedNums.add(num3);
                if(i == 10000)
                    splitedNums.add(num4);
                //System.out.println(num);
                System.out.println(splitedNums);
            }
        }
        /*for (int i = 0; i < 4; i++){
            System.out.println(splitedNums.get(i));
        }*/
        new Hw1(splitedNums.get(0)).start();
        new Hw1(splitedNums.get(1)).start();
        new Hw1(splitedNums.get(2)).start();
        new Hw1(splitedNums.get(3)).start();
    }
}