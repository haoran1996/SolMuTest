package blockchain.MutationUtils;

import java.util.ArrayList;
import java.util.List;

public class SetMutators {
    public static List<String> selectallmutators(){
        List<String> mutatorlist = new ArrayList<>();
        mutatorlist.add("BooleanReturnFalse");
        mutatorlist.add("BooleanReturnTrue");
        mutatorlist.add("ConditionalBoundaryOperator1");
        mutatorlist.add("ConditionalBoundaryOperator2");
        mutatorlist.add("ChangeToInequality");
        mutatorlist.add("ChangeToEquality");
        mutatorlist.add("ChangeAddToMinus");
        mutatorlist.add("ChangeMinusToAdd");
        mutatorlist.add("ReplaceLogicalOperators1");
        mutatorlist.add("ReplaceLogicalOperators2");
        mutatorlist.add("ReplaceRelationalOperators");
        mutatorlist.add("ChangeBreakToContinue");
        mutatorlist.add("ChangeReturnTrueTo1");
        mutatorlist.add("ChangeReturnFalseTo0");
        mutatorlist.add("ChangeReturn0ToFalse");
//        mutatorlist.add("RemoveReturn");//有问题
//        mutatorlist.add("ChangeUIntToInt");
//        mutatorlist.add("ChangeIntToUInt");
        mutatorlist.add("RemoveThis");
//        mutatorlist.add("RemoveThrow");
        mutatorlist.add("Replaceint16Toint8");
        mutatorlist.add("Replaceint32Toint16");
        mutatorlist.add("Replaceint64Toint32");
        mutatorlist.add("Replaceint128Toint64");
        mutatorlist.add("Replaceint256Toint128");
        mutatorlist.add("ReplaceMemoryToStorage");
        mutatorlist.add("ReplaceStorageToMemory");
        mutatorlist.add("ReplaceViewToPure");
        mutatorlist.add("ReplacePureToView");
        mutatorlist.add("ReplaceConstantToPure");
        mutatorlist.add("RemovePayable");
        mutatorlist.add("RemovePrivate");
//        mutatorlist.add("ReplaceSenderToOrigin");
        mutatorlist.add("ReplaceOriginToSender");
//        mutatorlist.add("ReplaceEtherToWei");
        mutatorlist.add("ReplaceWeiToEther");
        mutatorlist.add("ReplaceNowTo0");
        mutatorlist.add("ReplaceTimestampTo0");
        mutatorlist.add("ReplaceMsgValeTo0");
//        mutatorlist.add("ReplaceMsgValeTo1");
        mutatorlist.add("ReplaceAddmodToMulmod");
        mutatorlist.add("ReplaceMulmodToAddmod");
        mutatorlist.add("ReplaceToselfdestruct");
        mutatorlist.add("ReplaceTorevert()");
        mutatorlist.add("ReplacecallTodelegatecall");
        mutatorlist.add("ReplacedelegatecallTocall");
        mutatorlist.add("ReplacecallTocallcode");
        mutatorlist.add("ReplacecallcodeTocall");
        mutatorlist.add("ReplacedelegatecallTocallcode");
        mutatorlist.add("Replaceknown1");
        mutatorlist.add("Replaceif ()Toif (false)");
        mutatorlist.add("Replaceif()Toif(false)");
        mutatorlist.add("Replaceif ())Toif (true)");
        mutatorlist.add("Replaceif()Toif(true)");
        mutatorlist.add("Replaceif(!Toif(");

        return mutatorlist;
    }
}
