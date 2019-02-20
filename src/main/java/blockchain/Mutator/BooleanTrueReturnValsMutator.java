package blockchain.Mutator;

public class BooleanTrueReturnValsMutator extends Mutator {
    public String getName(){
        return "BOOLEAN_TRUE_RETURN";
    }

    public String getDescribe(){
        return "将return后的false变为true";
    }

    public boolean isapplicable(String content){
        if(content.contains("return false;")) return true;
        else
            return  false;
    }


}
