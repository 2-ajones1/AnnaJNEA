/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anna
 */
public class Calculator {
    
    public static double calculate(int[] positions, double value1, double value2){
        int value1Position = positions[0];
        int value2Position = positions[1];
        double result = 0;
        if(value1Position == 2){
            result = value1/value2;
        }else if(value2Position == 2){
            result = value2/value1;
        }else{
            result = value1 * value2;
        }
        
        return result;
    }
    
    public static int findLCM(int number1, int number2){
        int x = 0;
        int lcm = 0;
        if(number1 > number2){
            x = number1;
        }else{
            x = number2;
        }
        while(true){
            if((x % number1 == 0) && (x % number2 == 0)){
                lcm = x;
                break;
            }
            x++;
        }
        return lcm;
    }
    
}
