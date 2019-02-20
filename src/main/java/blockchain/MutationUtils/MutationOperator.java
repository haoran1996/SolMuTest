package blockchain.MutationUtils;



public class MutationOperator {
    public static String[] getMutatorInfo(String MutationOperator){
        String[] strings = new String[3];
        switch (MutationOperator){
            case("BOOLEAN_FALSE_RETURN"): {
                strings[0] = "将return后的true变为false";
                strings[1] = "return true;";
                strings[2] = "return false;";
                break;
            }
            case("BOOLEAN_TRUE_RETURN"):{
                strings[0] = "将return后的false变为true";
                strings[1] = "return false;";
                strings[2] = "return true;";
                break;
            }



            default: {
                strings[0] = strings[1] = strings[2] = null;
            }
        }
        return strings;
    }
}
