package blockchain.MutationUtils;

import java.util.ArrayList;
import java.util.List;

public class SetMutators {
    //选择一般变异算子
    public static List<String> selectnomalmutators(){
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

        mutatorlist.add("RemoveThis");
        mutatorlist.add("RemoveThrow");

        mutatorlist.add("Replaceif()Toif(false)");
        mutatorlist.add("Replaceif()Toif(true)");
        mutatorlist.add("Replaceif(!Toif(");

        mutatorlist.add("ReplaceTorevert()");
        mutatorlist.add("ReplacecallTodelegatecall");
        mutatorlist.add("ReplacedelegatecallTocall");
        mutatorlist.add("ReplacecallTocallcode");
        mutatorlist.add("ReplacecallcodeTocall");
        mutatorlist.add("ReplacedelegatecallTocallcode");
        mutatorlist.add("Replaceknown1");
        mutatorlist.add("ReplaceUndefinedtoNull");


        mutatorlist.add("ReplacePlusPlusToMinusMinus");
        mutatorlist.add("ReplacePlusEqualToMinusEqual");
        mutatorlist.add("ReplaceMinusMinusToPlusPlus");
        mutatorlist.add("ReplaceMinusEqualToPlusEqual");

        mutatorlist.add("RemoveSuper");

        mutatorlist.add("ReplaceMultiplicationToDivision");
        mutatorlist.add("ReplaceDivisionToMultiplication");
        mutatorlist.add("ReplaceModToMultiplication");

        mutatorlist.add("ReplaceRightshiftToLeftshift");
        mutatorlist.add("ReplaceLeftshiftToRightshift");
        mutatorlist.add("ReplaceBitandToBitor");
        mutatorlist.add("ReplaceBitorToBitand");
        mutatorlist.add("ReplaceBitxorToBitand");
        mutatorlist.add("RemoveBitnon");
        mutatorlist.add("ReplaceRightshiftEqualToLeftshiftEqual");
        mutatorlist.add("ReplaceLeftshiftEqualToRightshiftEqual");

        mutatorlist.add("ReplaceBitandEqualToBitorEqual");
        mutatorlist.add("ReplaceBitorEqualToBitandEqual");
        mutatorlist.add("ReplaceBitxorEqualToBitandEqual");


        mutatorlist.add("ReplaceMultiplicationEqualToDivisionEqual");
        mutatorlist.add("ReplaceDivisionEqualToMultiplicationEqual");
        mutatorlist.add("ReplaceModEqualToMultiplicationEqual");


        return mutatorlist;
    }

    //选择特殊变异算子
    public static List<String> selecspecificmutators() {
        List<String> mutatorlist = new ArrayList<>();
        //FSC
        mutatorlist.add("ReplaceViewToPureFSC");
        mutatorlist.add("ReplacePublicToExternalFVC");
        mutatorlist.add("ReplacePublicToInternalFVC");
        mutatorlist.add("ReplacePublicToPrivateFVC");
        mutatorlist.add("ReplaceExternalToInternalFVC");
        mutatorlist.add("ReplaceExternalToPrivateFVC");
        mutatorlist.add("ReplaceInternalToPrivateFVC");
        mutatorlist.add("ReplaceStorageToMemoryDLR");
        mutatorlist.add("ChangeUIntToIntVTR");
        mutatorlist.add("ChangeIntToUIntVTR");
        mutatorlist.add("ChangeUint256ToUint8VTR");
        mutatorlist.add("ChangeUint64ToUint8VTR");
        mutatorlist.add("ChangeUint32ToUint8VTR");
        mutatorlist.add("ChangeUint16ToUint8VTR");
        mutatorlist.add("Changeint256Toint8VTR");
        mutatorlist.add("Changeint128Toint8VTR");
        mutatorlist.add("Changeint64Toint8VTR");
        mutatorlist.add("Changeint32Toint8VTR");
        mutatorlist.add("Changeint16Toint8VTR");
        mutatorlist.add("ChangeUfixedToFixedVTR");
        mutatorlist.add("ChangeFixedToUfixedVTR");
        mutatorlist.add("ChangeUfixed256ToUfixed8VTR");
        mutatorlist.add("ChangeBytes32ToBytes1VTR");
        mutatorlist.add("ChangeBytes16ToBytes1VTR");
        mutatorlist.add("ChangeBytes8ToBytes1-VTR");
        mutatorlist.add("ChangeBytes4ToBytes1VTR");
        mutatorlist.add("ChangeBytes3ToBytes1VTR");
        mutatorlist.add("ChangeBytes2ToBytes1VTR");
        mutatorlist.add("RemovePayablePKD");
        mutatorlist.add("RemoveDeleteDKD");
        mutatorlist.add("ChangeBlockDifficultyGVC");
        mutatorlist.add("ChangeBlockGaslimitGVC");
        mutatorlist.add("ChangeBlockNumberGVC");
        mutatorlist.add("ChangeNowGVC");
        mutatorlist.add("ChangeBlockTimestampGVC");
        mutatorlist.add("ChangeMsgGasGVC");
        mutatorlist.add("ChangeMsgValueGVC");
        mutatorlist.add("ReplaceAddmodToMulmodMFR");
        mutatorlist.add("ReplaceMulmodToAddmodMFR");
        mutatorlist.add("ReplaceAddToMulMFR");
        mutatorlist.add("ReplaceMulToAddMFR");
        mutatorlist.add("ReplaceDivToSubMFR");
        mutatorlist.add("ReplaceSubToDivMFR");

        mutatorlist.add("ReplaceMax64ToMin64MFR");
        mutatorlist.add("ReplaceMin64ToMax64MFR");
        mutatorlist.add("ReplaceMax256ToMin256MFR");
        mutatorlist.add("ReplaceMin256ToMax256MFR");

        mutatorlist.add("ReplaceSenderToOriginAVR");
        mutatorlist.add("ReplaceSenderToCoinbaseAVR");
        mutatorlist.add("ReplaceOriginToSenderAVR");
        mutatorlist.add("ReplaceOriginToCoinbaseAVR");
        mutatorlist.add("ReplaceCoinbaseToSenderAVR");
        mutatorlist.add("ReplaceCoinbaseToOriginAVR");
        mutatorlist.add("ReplaceEtherToWeiEUR");
        mutatorlist.add("ReplaceWeiToEtherEUR");
        mutatorlist.add("ReplaceWeiToFinneyEUR");
        mutatorlist.add("ReplaceFinneyToWeiEUR");
        mutatorlist.add("ReplaceFinneyToSazboEUR");
        mutatorlist.add("ReplaceSzaboToFinneyEUR");
        mutatorlist.add("ReplaceSecondsToMinutesTUR");
        mutatorlist.add("ReplaceMinutesToSecondsTUR");
        mutatorlist.add("ReplaceHoursToDaysTUR");
        mutatorlist.add("ReplaceDaysToHoursTUR");
        mutatorlist.add("ReplaceWeeksToMonthsTUR");
        mutatorlist.add("ReplaceMonthsToWeeksTUR");
        mutatorlist.add("RequireDeletionRSD");
        mutatorlist.add("AssertDeletionASD");

        mutatorlist.add("ReventDeletionRD");
        mutatorlist.add("ChangeNow1GVC");

        return mutatorlist;
    }

    //选择全部变异算子
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

        mutatorlist.add("RemoveThis");
        mutatorlist.add("RemoveThrow");

        mutatorlist.add("Replaceif()Toif(false)");
        mutatorlist.add("Replaceif()Toif(true)");
        mutatorlist.add("Replaceif(!Toif(");

        mutatorlist.add("ReplaceTorevert()");
        mutatorlist.add("ReplacecallTodelegatecall");
        mutatorlist.add("ReplacedelegatecallTocall");
        mutatorlist.add("ReplacecallTocallcode");
        mutatorlist.add("ReplacecallcodeTocall");
        mutatorlist.add("ReplacedelegatecallTocallcode");
        mutatorlist.add("Replaceknown1");
        mutatorlist.add("ReplaceUndefinedtoNull");


        mutatorlist.add("ReplacePlusPlusToMinusMinus");
        mutatorlist.add("ReplacePlusEqualToMinusEqual");
        mutatorlist.add("ReplaceMinusMinusToPlusPlus");
        mutatorlist.add("ReplaceMinusEqualToPlusEqual");

        mutatorlist.add("RemoveSuper");

        mutatorlist.add("ReplaceMultiplicationToDivision");
        mutatorlist.add("ReplaceDivisionToMultiplication");
        mutatorlist.add("ReplaceModToMultiplication");

        mutatorlist.add("ReplaceRightshiftToLeftshift");
        mutatorlist.add("ReplaceLeftshiftToRightshift");
        mutatorlist.add("ReplaceBitandToBitor");
        mutatorlist.add("ReplaceBitorToBitand");
        mutatorlist.add("ReplaceBitxorToBitand");
        mutatorlist.add("RemoveBitnon");
        mutatorlist.add("ReplaceRightshiftEqualToLeftshiftEqual");
        mutatorlist.add("ReplaceLeftshiftEqualToRightshiftEqual");

        mutatorlist.add("ReplaceBitandEqualToBitorEqual");
        mutatorlist.add("ReplaceBitorEqualToBitandEqual");
        mutatorlist.add("ReplaceBitxorEqualToBitandEqual");


        mutatorlist.add("ReplaceMultiplicationEqualToDivisionEqual");
        mutatorlist.add("ReplaceDivisionEqualToMultiplicationEqual");
        mutatorlist.add("ReplaceModEqualToMultiplicationEqual");

        //特殊变异算子
        mutatorlist.add("ReplaceViewToPureFSC");
        mutatorlist.add("ReplacePublicToExternalFVC");
        mutatorlist.add("ReplacePublicToInternalFVC");
        mutatorlist.add("ReplacePublicToPrivateFVC");
        mutatorlist.add("ReplaceExternalToInternalFVC");
        mutatorlist.add("ReplaceExternalToPrivateFVC");
        mutatorlist.add("ReplaceInternalToPrivateFVC");
        mutatorlist.add("ReplaceStorageToMemoryDLR");
        mutatorlist.add("ChangeUIntToIntVTR");
        mutatorlist.add("ChangeIntToUIntVTR");
        mutatorlist.add("ChangeUint256ToUint8VTR");
        mutatorlist.add("ChangeUint64ToUint8VTR");
        mutatorlist.add("ChangeUint32ToUint8VTR");
        mutatorlist.add("ChangeUint16ToUint8VTR");
        mutatorlist.add("Changeint256Toint8VTR");
        mutatorlist.add("Changeint128Toint8VTR");
        mutatorlist.add("Changeint64Toint8VTR");
        mutatorlist.add("Changeint32Toint8VTR");
        mutatorlist.add("Changeint16Toint8VTR");
        mutatorlist.add("ChangeUfixedToFixedVTR");
        mutatorlist.add("ChangeFixedToUfixedVTR");
        mutatorlist.add("ChangeUfixed256ToUfixed8VTR");
        mutatorlist.add("ChangeBytes32ToBytes1VTR");
        mutatorlist.add("ChangeBytes16ToBytes1VTR");
        mutatorlist.add("ChangeBytes8ToBytes1-VTR");
        mutatorlist.add("ChangeBytes4ToBytes1VTR");
        mutatorlist.add("ChangeBytes3ToBytes1VTR");
        mutatorlist.add("ChangeBytes2ToBytes1VTR");
        mutatorlist.add("RemovePayablePKD");
        mutatorlist.add("RemoveDeleteDKD");
        mutatorlist.add("ChangeBlockDifficultyGVC");
        mutatorlist.add("ChangeBlockGaslimitGVC");
        mutatorlist.add("ChangeBlockNumberGVC");
        mutatorlist.add("ChangeNowGVC");
        mutatorlist.add("ChangeBlockTimestampGVC");
        mutatorlist.add("ChangeMsgGasGVC");
        mutatorlist.add("ChangeMsgValueGVC");
        mutatorlist.add("ReplaceAddmodToMulmodMFR");
        mutatorlist.add("ReplaceMulmodToAddmodMFR");
        mutatorlist.add("ReplaceAddToMulMFR");
        mutatorlist.add("ReplaceMulToAddMFR");
        mutatorlist.add("ReplaceDivToSubMFR");
        mutatorlist.add("ReplaceSubToDivMFR");

        mutatorlist.add("ReplaceMax64ToMin64MFR");
        mutatorlist.add("ReplaceMin64ToMax64MFR");
        mutatorlist.add("ReplaceMax256ToMin256MFR");
        mutatorlist.add("ReplaceMin256ToMax256MFR");

        mutatorlist.add("ReplaceSenderToOriginAVR");
        mutatorlist.add("ReplaceSenderToCoinbaseAVR");
        mutatorlist.add("ReplaceOriginToSenderAVR");
        mutatorlist.add("ReplaceOriginToCoinbaseAVR");
        mutatorlist.add("ReplaceCoinbaseToSenderAVR");
        mutatorlist.add("ReplaceCoinbaseToOriginAVR");
        mutatorlist.add("ReplaceEtherToWeiEUR");
        mutatorlist.add("ReplaceWeiToEtherEUR");
        mutatorlist.add("ReplaceWeiToFinneyEUR");
        mutatorlist.add("ReplaceFinneyToWeiEUR");
        mutatorlist.add("ReplaceFinneyToSazboEUR");
        mutatorlist.add("ReplaceSzaboToFinneyEUR");
        mutatorlist.add("ReplaceSecondsToMinutesTUR");
        mutatorlist.add("ReplaceMinutesToSecondsTUR");
        mutatorlist.add("ReplaceHoursToDaysTUR");
        mutatorlist.add("ReplaceDaysToHoursTUR");
        mutatorlist.add("ReplaceWeeksToMonthsTUR");
        mutatorlist.add("ReplaceMonthsToWeeksTUR");
        mutatorlist.add("RequireDeletionRSD");
        mutatorlist.add("AssertDeletionASD");

        mutatorlist.add("ReventDeletionRD");
        mutatorlist.add("ChangeNow1GVC");
        return mutatorlist;
    }
}
