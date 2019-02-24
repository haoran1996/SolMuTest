package blockchain.MutationUtils;




public class MutationOperator {

    public static String[] getMutatorInfo(String MutationOperator){
        String[] strings = new String[3];
        switch (MutationOperator){
            case("BooleanReturnFalse"): {
                strings[0] = "将return后的true变为false";
                strings[1] = "return true;";
                strings[2] = "return false;";
                break;
            }
            case("BooleanReturnTrue"):{
                strings[0] = "将return后的false变为true";
                strings[1] = "return false;";
                strings[2] = "return true;";
                break;
            }
            case("ConditionalBoundaryOperator1"):{
                strings[0] = "将 > 变为 <= ";
                strings[1] = " > ";
                strings[2] = " <= ";
                break;
            }
            case("ConditionalBoundaryOperator2"):{
                strings[0] = "将 >= 变为 < ";
                strings[1] = " >= ";
                strings[2] = " < ";
                break;
            }
            case("ChangeToInequality"):{
                strings[0] = "将 == 变为 != ";
                strings[1] = " == ";
                strings[2] = " != ";
                break;
            }
            case("ChangeToEquality"):{
                strings[0] = "将 != 变为 == ";
                strings[1] = " != ";
                strings[2] = " == ";
                break;
            }
            case("ChangeAddToMinus"):{
                strings[0] = "将 + 变为 - ";
                strings[1] = " + ";
                strings[2] = " - ";
                break;
            }
            case("ChangeMinusToAdd"):{
                strings[0] = "将 - 变为 + ";
                strings[1] = " - ";
                strings[2] = " + ";
                break;
            }
            case("ReplaceLogicalOperators1"):{
                strings[0] = "将 || 变为 && ";
                strings[1] = " || ";
                strings[2] = " && ";
                break;
            }
            case("ReplaceLogicalOperators2"):{
                strings[0] = "将 && 变为 || ";
                strings[1] = " && ";
                strings[2] = " || ";
                break;
            }
            case("ReplaceRelationalOperators"):{
                strings[0] = "将括号后！符号去掉 ";
                strings[1] = " (! ";
                strings[2] = " ( ";
                break;
            }
            case("ChangeBreakToContinue"):{
                strings[0] = "将break替换为continue";
                strings[1] = "break;";
                strings[2] = "continue;";
                break;
            }
            case("ChangeContinueToBreak"):{
                strings[0] = "将continue替换为break";
                strings[1] = "continue;";
                strings[2] = "break;";
                break;
            }
            case("ChangeReturnTrueTo1"):{
                strings[0] = "将return后true替换为1";
                strings[1] = "return true;";
                strings[2] = "return 1;";
                break;
            }
            case("ChangeReturnFalseTo0"):{
                strings[0] = "将return后false替换为0";
                strings[1] = "return false;";
                strings[2] = "return 0;";
                break;
            }
            case("ChangeReturn1ToTrue"):{
                strings[0] = "将return后1替换为true";
                strings[1] = "return 1;";
                strings[2] = "return true;";
                break;
            }
            case("ChangeReturn0ToFalse"):{
                strings[0] = "将return后0替换为False";
                strings[1] = "return 0;";
                strings[2] = "return false;";
                break;
            }
            case("RemoveReturn"):{
                strings[0] = "将return去掉";
                strings[1] = "return";
                strings[2] = "//return";
                break;
            }
            //极多
            case("ChangeUIntToInt"):{
                strings[0] = "将uint换为int";
                strings[1] = "uint";
                strings[2] = "int";
                break;
            }
            case("ChangeIntToUInt"):{
                strings[0] = "将int换为uint";
                strings[1] = "int";
                strings[2] = "uint";
                break;
            }
            case("RemoveThis"):{
                strings[0] = "去掉this.";
                strings[1] = "this.";
                strings[2] = "";
                break;
            }
            case("RemoveThrow"):{
                strings[0] = "去掉throw";
                strings[1] = "throw";
                strings[2] = "";
                break;
            }
            case("Replaceint16Toint8"):{
                strings[0] = " ";
                strings[1] = "int16";
                strings[2] = "int8";
                break;
            }
            case("Replaceint32Toint16"):{
                strings[0] = " ";
                strings[1] = "int32";
                strings[2] = "int16";
                break;
            }
            case("Replaceint64Toint32"):{
                strings[0] = " ";
                strings[1] = "int64";
                strings[2] = "int32";
                break;
            }
            case("Replaceint128Toint64"):{
                strings[0] = " ";
                strings[1] = "int128";
                strings[2] = "int64";
                break;
            }
            case("Replaceint256Toint128"):{
                strings[0] = " ";
                strings[1] = "int256";
                strings[2] = "int128";
                break;
            }
            //solidity 特有
            case("ReplaceMemoryToStorage"):{
                strings[0] = " ";
                strings[1] = "memory";
                strings[2] = "storage";
                break;
            }
            //solidity 特有
            case("ReplaceStorageToMemory"):{
                strings[0] = " ";
                strings[1] = "storage";
                strings[2] = "memory";
                break;
            }
            //solidity 特有
            case("ReplaceViewToPure"):{
                strings[0] = " ";
                strings[1] = "view";
                strings[2] = "pure";
                break;
            }
            //solidity 特有
            case("ReplacePureToView"):{
                strings[0] = " ";
                strings[1] = "prue";
                strings[2] = "view";
                break;
            }
            //solidity 特有
            case("ReplaceConstantToPure"):{
                strings[0] = " ";
                strings[1] = "constant";
                strings[2] = "pure";
                break;
            }
            //solidity 特有
            case("RemovePayable"):{
                strings[0] = " ";
                strings[1] = "payable";
                strings[2] = "";
                break;
            }
            case("RemovePrivate"):{
                strings[0] = " ";
                strings[1] = "private";
                strings[2] = "";
                break;
            }
            //solidity 特有
            case("ReplaceSenderToOrigin"):{
                strings[0] = " ";
                strings[1] = "msg.sender";
                strings[2] = "tx.origin";
                break;
            }
            //solidity 特有
            case("ReplaceOriginToSender"):{
                strings[0] = " ";
                strings[1] = "tx.origin";
                strings[2] = "msg.sender";
                break;
            }
            //solidity 特有
            case("ReplaceEtherToWei"):{
                strings[0] = " ";
                strings[1] = "ether";
                strings[2] = "wei";
                break;
            }
            //solidity 特有
            case("ReplaceWeiToEther"):{
                strings[0] = " ";
                strings[1] = "wei";
                strings[2] = "Ether";
                break;
            }
            //solidity 特有
            case("ReplaceNowTo0"):{
                strings[0] = " ";
                strings[1] = "now";
                strings[2] = "0";
                break;
            }
            //solidity 特有
            case("ReplaceTimestampTo0"):{
                strings[0] = " ";
                strings[1] = "block.timestamp";
                strings[2] = "0";
                break;
            }
            //solidity 特有
            case("ReplaceMsgValeTo0"):{
                strings[0] = " ";
                strings[1] = "msg.value";
                strings[2] = "0";
                break;
            }
            //solidity 特有
            case("ReplaceMsgValeTo1"):{
                strings[0] = " ";
                strings[1] = "msg.value";
                strings[2] = "1";
                break;
            }
            //solidity 特有
            case("ReplaceAddmodToMulmod"):{
                strings[0] = " ";
                strings[1] = "addmod";
                strings[2] = "mulmod";
                break;
            }
            //solidity 特有
            case("ReplaceMulmodToAddmod"):{
                strings[0] = " ";
                strings[1] = "mulmod";
                strings[2] = "addmod";
                break;
            }
            //solidity 特有
            case("ReplaceToselfdestruct"):{
                strings[0] = " ";
                strings[1] = "(^\\s*)(\\S+[^{}]+.*)\\n";
                strings[2] = "selfdestruct(msg.sender);";
                break;
            }
            //solidity 特有
            case("ReplaceTorevert()"):{
                strings[0] = " ";
                strings[1] = "(^\\s*)(\\S+[^{}]+.*)\\n";
                strings[2] = "revert();";
                break;
            }
            //solidity 特有
            case("ReplacecallTodelegatecall"):{
                strings[0] = " ";
                strings[1] = "call";
                strings[2] = "delegatecall";
                break;
            }
            //solidity 特有
            case("ReplacedelegatecallTocall"):{
                strings[0] = " ";
                strings[1] = "delegatecall";
                strings[2] = "call";
                break;
            }
            //solidity 特有
            case("ReplacecallTocallcode"):{
                strings[0] = " ";
                strings[1] = "call";
                strings[2] = "callcode";
                break;
            }
            //solidity 特有
            case("ReplacecallcodeTocall"):{
                strings[0] = " ";
                strings[1] = "callcode";
                strings[2] = "call";
                break;
            }
            //solidity 特有
            case("ReplacedelegatecallTocallcode"):{
                strings[0] = " ";
                strings[1] = "delegatecall";
                strings[2] = "callcode";
                break;
            }
            //solidity 特有
            case("Replaceknown1"):{
                strings[0] = "Replace(S+\\s+)(\\S+)(\\s+) ==> \\1\\3";
                strings[1] = "(\\S+\\s+)(\\S+)(\\s+)";
                strings[2] = "\\1\\3";
                break;
            }
            case("Replaceif ()Toif (false)"):{
                strings[0] = " ";
                strings[1] = "if (\\(.*\\))";
                strings[2] = "if (false)";
                break;
            }
            case("Replaceif()Toif(false)"):{
                strings[0] = " ";
                strings[1] = "if(\\(.*\\))";
                strings[2] = "if(false)";
                break;
            }
            case("Replaceif ())Toif (true)"):{
                strings[0] = " ";
                strings[1] = "if (\\(.*\\))";
                strings[2] = "if (true)";
                break;
            }
            case("Replaceif()Toif(true)"):{
                strings[0] = " ";
                strings[1] = "if(\\(.*\\))";
                strings[2] = "if(true)";
                break;
            }
            case("Replaceif(!Toif("):{
                strings[0] = " ";
                strings[1] = "if (!";
                strings[2] = "if (";
                break;
            }





            default: {
                strings[0] = strings[1] = strings[2] = null;
            }
        }
        return strings;
    }


}
