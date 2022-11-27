import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Experiment {
    public static void throwsMethod1() throws ArithmeticException{
        int a = 1/0;
    }
    public static void tryCatchMethod1(){
        try{
            int a = 1/0;
        }
        catch (ArithmeticException e){}
    }

    public static void throwsMethod2() throws ArrayIndexOutOfBoundsException{
        int [] array = new int[1];
        array[1] = 5;
    }
    public static void tryCatchMethod2(){
        int [] array = new int[1];
        try{
            array[1] = 5;
        }
        catch(ArrayIndexOutOfBoundsException e){}
    }


    public static void main(String[] args) {
        int iterations = 10000;

        long repeatThrow = 0;
        long beginThrow;
        long endThrow;
        long repeatThrow2 = 0;
        long beginThrow2;
        long endThrow2;

        long repeatTry = 0;
        long beginTry;
        long endTry;
        long repeatTry2 = 0;
        long beginTry2;
        long endTry2;


        for (int i=1; i<=iterations; i++){

            beginThrow = System.nanoTime();
            try {
                throwsMethod1();
            }
            catch (ArithmeticException e){}
            endThrow = System.nanoTime() - beginThrow;
            repeatThrow+=endThrow;


            beginThrow2 = System.nanoTime();
            try {
                throwsMethod2();
            }
            catch (ArrayIndexOutOfBoundsException e){}
            endThrow2 = System.nanoTime() - beginThrow2;
            repeatThrow2+=endThrow2;




            beginTry = System.nanoTime();
            tryCatchMethod1();
            endTry = System.nanoTime() - beginTry;
            repeatTry+=endTry;


            beginTry2 = System.nanoTime();
            tryCatchMethod2();
            endTry2 = System.nanoTime() - beginTry2;
            repeatTry2+=endTry2;

        }

        String throws1 = "Throws(/0) time: " + repeatThrow + " ns, " + "iterations: " + iterations + "\n";
        String try1 = "Try(/0) time: " + repeatTry + " ns, " + "iterations: " + iterations + "\n\n";
        String throws2 = "Throws(arrayIndex) time: " + repeatThrow2 + " ns, " + "iterations: " + iterations + "\n";
        String try2 = "Try(arrayIndex) time: " + repeatTry2 + " ns, " + "iterations: " + iterations + "\n\n\n\n\n";

        System.out.print(throws1);
        System.out.print(try1);

        System.out.print(throws2);
        System.out.print(try2);


        try (BufferedWriter br = new BufferedWriter(new FileWriter("results.txt", true))){
            br.write(throws1);
            br.write(try1);
            br.write(throws2);
            br.write(try2);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
