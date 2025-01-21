import java.io.File;
//import java.util.*;


public class Perceptron{
    
    private double[] weights; 
    private double bias;
    private int classValue;
    //Constructor
    public Perceptron(int classValue){
        //initializes the weights to zero
        double[] weights = new double[64];
        double bias = 0;
    
        this.classValue = classValue;
        this.weights = weights;
        this.bias = bias;

    }
    
    //identifys a files class value and returns it
    public static int classIdentifier(String filePath){
        int fileClass = Integer.MIN_VALUE;
        File file = new File(filePath);
        String fileName = file.getName();
        String split = "class";
        String classString = "";

        //Seperates the word "class" from the fileName to make the class value easy to grab
        int splitIndex = fileName.indexOf(split) + split.length();
        String classFullString = fileName.substring(splitIndex);
        int i = 0;
        
        //grabs just the class value from the fileName
        while(classFullString.charAt(i) != '_'){
            classString += classFullString.charAt(i);
            i++;
        }

        try{
        fileClass = Integer.parseInt(classString);
        }

        catch(NumberFormatException e){
            System.out.println("Error: " + classString + " could not parse. " + fileName );
            throw new NumberFormatException();
        }
        return fileClass;
    }
    
    //trains the Perceptron updating all the weights and bias
    public void training(double[] normHist, String fileName){
        int d;
        double y;
        //going to hold the wi*hi products
        double product = 0;
        //initializes d to positive or negative based on the files class value
        if(classValue == classIdentifier(fileName)) d = 1;
        else d = -1;

        //Calculates sum of hi*wi
        for(int i = 0; i < normHist.length; i++){   
             product += normHist[i] * weights[i];
        }
        
        //Perceptrons calculations
        y = bias + product;

        //updates weights
        for(int i = 0; i < weights.length; i++){
            weights[i] += (d - y) * normHist[i];
        }

        //updates bias
        bias += (d - y);
    }
    
    //Calculates how certain it is that the image is a apart of a certain class
    public double testing(double[] normHist) {
        double y;
        //going to hold the wi*hi products
        double product = 0;
        //Calculates sum of hi*wi
        for(int i = 0; i < normHist.length; i++){   
             product += normHist[i] * weights[i];
        }
        
        //Perceptrons calculations
        y = bias + product;

        return y;
    }
    
    //prints out current weight values
    public void printWeightValues(){

        for(int i = 0; i < weights.length; i++){

            System.out.format("%.6f" + " ", weights[i]);
        }
        System.out.format("%.6f" + " ", bias);        
    }

    public int getClassValue(){
        return classValue;
    }

    public double[] getWeights(){
        return weights;
    }

    public double getBias(){
        return bias;
    }
}