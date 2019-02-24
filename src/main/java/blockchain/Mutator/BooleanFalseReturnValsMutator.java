package blockchain.Mutator;

import java.io.*;


public class BooleanFalseReturnValsMutator extends Mutator {

    public String getName(){
        return "BOOLEAN_FALSE_RETURN";
    }

    public String getDescribe(){
        return "将return后的true变为false";
    }
    public boolean isapplicable(String line){
        if(line.contains("return true;")) return true;
        else
            return  false;
    }



}
