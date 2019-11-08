package blockchain.MutationUtils;




public class MutationOperator {

    public static String[] getMutatorInfo(String MutationOperator){
        String[] strings = new String[3];
        switch (MutationOperator){
            case("BooleanReturnFalse"): {
                strings[0] = "将return后的true变为false";
                strings[1] = " return true;";
                strings[2] = " return false;";
                break;
            }
            case("BooleanReturnTrue"):{
                strings[0] = "将return后的false变为true";
                strings[1] = " return false;";
                strings[2] = " return true;";
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
                strings[1] = " (!";
                strings[2] = " ( ";
                break;
            }
            case("ChangeBreakToContinue"):{
                strings[0] = "将break替换为continue";
                strings[1] = " break;";
                strings[2] = " continue;";
                break;
            }
            case("ChangeContinueToBreak"):{
                strings[0] = "将continue替换为break";
                strings[1] = " continue;";
                strings[2] = " break;";
                break;
            }
//            case("ChangeReturnTrueTo1"):{
//                strings[0] = "将return后true替换为1";
//                strings[1] = "return true;";
//                strings[2] = "return 1;";
//                break;
//            }
//            case("ChangeReturnFalseTo0"):{
//                strings[0] = "将return后false替换为0";
//                strings[1] = " return false;";
//                strings[2] = " return 0;";
//                break;
//            }
//            case("ChangeReturn1ToTrue"):{
//                strings[0] = "将return后1替换为true";
//                strings[1] = " return 1;";
//                strings[2] = " return true;";
//                break;
//            }
//            case("ChangeReturn0ToFalse"):{
//                strings[0] = "将return后0替换为False";
//                strings[1] = " return 0;";
//                strings[2] = " return false;";
//                break;
//            }
            case("RemoveReturn"):{
                strings[0] = "将return去掉";
                strings[1] = " return ";
                strings[2] = "//return ";
                break;
            }

            case("RemoveThis"):{
                strings[0] = "去掉this.";
                strings[1] = " this.";
                strings[2] = " ";
                break;
            }
            case("RemoveThrow"):{
                strings[0] = "去掉throw";
                strings[1] = " throw";
                strings[2] = " ";
                break;
            }
            case("RemovePrivate"):{
                strings[0] = " ";
                strings[1] = " private ";
                strings[2] = " ";
                break;
            }
            case("Replaceif()Toif(false)"):{
                strings[0] = " ";
                strings[1] = "if(";
                strings[2] = "if(false&&";
                break;
            }
            case("Replaceif()Toif(true)"):{
                strings[0] = " ";
                strings[1] = " if(";
                strings[2] = " if(true||";
                break;
            }
            case("Replaceif(!Toif("):{
                strings[0] = " ";
                strings[1] = "if (!";
                strings[2] = "if (";
                break;
            }
            case("ReplaceUndefinedtoNull"):{
                strings[0] = " ";
                strings[1] = " undefined ";
                strings[2] = " null ";
                break;
            }


            case("ReplacePlusPlusToMinusMinus"):{
                strings[0] = "将 ++ 变为 -- ";
                strings[1] = " ++ ";
                strings[2] = " -- ";
                break;
            }
            case("ReplacePlusEqualToMinusEqual"):{
                strings[0] = "将 += 变为 -= ";
                strings[1] = " += ";
                strings[2] = " -= ";
                break;
            }
            case("ReplaceMinusMinusToPlusPlus"):{
                strings[0] = "将 -- 变为 ++ ";
                strings[1] = " -- ";
                strings[2] = " ++ ";
                break;
            }
            case("ReplaceMinusEqualToPlusEqual"):{
                strings[0] = "将 -= 变为 += ";
                strings[1] = " -= ";
                strings[2] = " += ";
                break;
            }

            case("RemoveSuper"):{
                strings[0] = "删除 super ";
                strings[1] = " super ";
                strings[2] = "  ";
                break;
            }

            case("ReplaceModToMultiplication"):{
                strings[0] = "将 % 变为 *";
                strings[1] = " % ";
                strings[2] = " * ";
                break;
            }

            case("ReplaceDivisionToMultiplication"):{
                strings[0] = "将 / 变为 *";
                strings[1] = " / ";
                strings[2] = " * ";
                break;
            }


            case("ReplaceMultiplicationToDivision"):{
                strings[0] = "将 * 变为 /";
                strings[1] = " * ";
                strings[2] = " / ";
                break;
            }





            case("ReplaceRightshiftToLeftshift"):{
                strings[0] = "将>> 变为 <<";
                strings[1] = " >> ";
                strings[2] = " << ";
                break;
            }

            case("ReplaceLeftshiftToRightshift"):{
                strings[0] = "将<< 变为 >>";
                strings[1] = " << ";
                strings[2] = " >> ";
                break;
            }


            case("ReplaceBitandToBitor"):{
                strings[0] = "将 & 变为 |";
                strings[1] = " & ";
                strings[2] = " | ";
                break;
            }

            case("ReplaceBitorToBitand"):{
                strings[0] = "将 | 变为 &";
                strings[1] = " | ";
                strings[2] = " & ";
                break;
            }

            case("ReplaceBitxorToBitand"):{
                strings[0] = "将 ^ 变为 &";
                strings[1] = " ^ ";
                strings[2] = " & ";
                break;
            }

            case("RemoveBitnon"):{
                strings[0] = "删除~";
                strings[1] = " ~ ";
                strings[2] = "  ";
                break;
            }

            case("ReplaceRightshiftEqualToLeftshiftEqual"):{
                strings[0] = " 将 >>= 变为 <<=";
                strings[1] = " >>= ";
                strings[2] = " <<= ";
                break;
            }

            case("ReplaceLeftshiftEqualToRightshiftEqual"):{
                strings[0] = " 将 <<= 变为 >>=";
                strings[1] = " <<= ";
                strings[2] = " >>= ";
                break;
            }


            case("ReplaceBitandEqualToBitorEqual"):{
                strings[0] = "将 &= 变为 |=";
                strings[1] = " &= ";
                strings[2] = " |= ";
                break;
            }

            case("ReplaceBitorEqualToBitandEqual"):{
                strings[0] = "将 |= 变为 &=";
                strings[1] = " |= ";
                strings[2] = " &= ";
                break;
            }

            case("ReplaceBitxorEqualToBitandEqual"):{
                strings[0] = "将 ^= 变为 &= ";
                strings[1] = " ^= ";
                strings[2] = " &= ";
                break;
            }

            case("ReplaceModEqualToMultiplicationEqual"):{
                strings[0] = "将 %= 变为 *=";
                strings[1] = " %= ";
                strings[2] = " *= ";
                break;
            }

            case("ReplaceDivisionEqualToMultiplicationEqual"):{
                strings[0] = "将 /= 变为 *=";
                strings[1] = " /= ";
                strings[2] = " *= ";
                break;
            }

            case("ReplaceMultiplicationEqualToDivisionEqual"):{
                strings[0] = "将 *= 变为 /=";
                strings[1] = " *= ";
                strings[2] = " /= ";
                break;
            }
//            case("ReplaceToselfdestruct"):{
//                strings[0] = " ";
//                strings[1] = "(^\\s*)(\\S+[^{}]+.*)\\n";
//                strings[2] = "selfdestruct(msg.sender);";
//                break;
//            }
            case("ReplaceTorevert()"):{
                strings[0] = " ";
                strings[1] = " (^\\s*)(\\S+[^{}]+.*)\\n ";
                strings[2] = " revert() ;";
                break;
            }
            //solidity 特有
            case("ReplacecallTodelegatecall"):{
                strings[0] = " ";
                strings[1] = " call ";
                strings[2] = " delegatecall ";
                break;
            }
            //solidity 特有
            case("ReplacedelegatecallTocall"):{
                strings[0] = " ";
                strings[1] = " delegatecall ";
                strings[2] = " call ";
                break;
            }
            //solidity 特有
            case("ReplacecallTocallcode"):{
                strings[0] = " ";
                strings[1] = " call ";
                strings[2] = " callcode ";
                break;
            }
            //solidity 特有
            case("ReplacecallcodeTocall"):{
                strings[0] = " ";
                strings[1] = " callcode ";
                strings[2] = " call ";
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



            //ASE Operators
            //FSC
            case("ReplaceViewToPureFSC"):{
                strings[0] = " ";
                strings[1] = " view ";
                strings[2] = " pure ";
                break;
            }



            //FVC
            case("ReplacePublicToExternalFVC"):{
                strings[1] = " public ";
                strings[2] = " external ";
                break;
            }
            case("ReplacePublicToInternalFVC"):{
                strings[1] = " public ";
                strings[2] = " internal ";
                break;
            }
            case("ReplacePublicToPrivateFVC"):{
                strings[1] = " public ";
                strings[2] = " private ";
                break;
            }
            case("ReplaceExternalToInternalFVC"):{
                strings[0] = "将 external 变为 internal ";
                strings[1] = " external ";
                strings[2] = " internal ";
                break;
            }
            case("ReplaceExternalToPrivateFVC"):{
                strings[1] = " external ";
                strings[2] = " private ";
                break;
            }
            case("ReplaceInternalToPrivateFVC"):{
                strings[1] = " internal ";
                strings[2] = " private ";
                break;
            }

            //DLR
            case("ReplaceStorageToMemoryDLR"):{
                strings[0] = " ";
                strings[1] = " storage ";
                strings[2] = " memory ";
                break;
            }

            //VTR
            case("ChangeUIntToIntVTR"):{
                strings[0] = "将uint换为int";
                strings[1] = " uint";
                strings[2] = " int";
                break;
            }
            case("ChangeIntToUIntVTR"):{
                strings[0] = "将int换为uint";
                strings[1] = " int";
                strings[2] = " uint";
                break;
            }
            case("ChangeUint256ToUint8VTR"):{
                strings[1] = " uint256 ";
                strings[2] = " uint8 ";
                break;
            }
            case("ChangeUint64ToUint8VTR"):{
                strings[1] = " uint64 ";
                strings[2] = " uint8 ";
                break;
            }
            case("ChangeUint32ToUint8VTR"):{
                strings[1] = " uint32 ";
                strings[2] = " uint8 ";
                break;
            }
            case("ChangeUint16ToUint8VTR"):{
                strings[1] = " uint16 ";
                strings[2] = " uint8 ";
                break;
            }
            case("Changeint256Toint8VTR"):{
                strings[1] = " int256 ";
                strings[2] = " int8 ";
                break;
            }
            case("Changeint128Toint8VTR"):{
                strings[1] = " int128 ";
                strings[2] = " int8 ";
                break;
            }
            case("Changeint64Toint8VTR"):{
                strings[1] = " int64 ";
                strings[2] = " int8 ";
                break;
            }
            case("Changeint32Toint8VTR"):{
                strings[1] = " int32 ";
                strings[2] = " int8 ";
                break;
            }
            case("Changeint16Toint8VTR"):{
                strings[1] = " int16 ";
                strings[2] = " int8 ";
                break;
            }
            case("ChangeUfixedToFixedVTR"):{
                strings[1] = " ufixed";
                strings[2] = " fixed";
                break;
            }
            case("ChangeFixedToUfixedVTR"):{
                strings[1] = " fixed";
                strings[2] = " ufixed";
                break;
            }
//            case("ChangeUfixedToUfixed8-VTR"):{
//                strings[1] = " ufixed";
//                strings[2] = " ufixed8";
//                break;
//            }
//            case("ChangeFixedToFixed8-VTR"):{
//                strings[1] = " fixed";
//                strings[2] = " fixed8";
//                break;
//            }
            case("ChangeUfixed256ToUfixed8VTR"):{
                strings[1] = " ufixed256";
                strings[2] = " ufixed8";
                break;
            }
            case("ChangeBytes32ToBytes1VTR"):{
                strings[1] = " bytes32 ";
                strings[2] = " bytes1 ";
                break;
            }
            case("ChangeBytes16ToBytes1VTR"):{
                strings[1] = " bytes16 ";
                strings[2] = " bytes1 ";
                break;
            }
            case("ChangeBytes8ToBytes1-VTR"):{
                strings[1] = " bytes8 ";
                strings[2] = " bytes1 ";
                break;
            }
            case("ChangeBytes4ToBytes1VTR"):{
                strings[1] = " bytes4 ";
                strings[2] = " bytes1 ";
                break;
            }
            case("ChangeBytes3ToBytes1VTR"):{
                strings[1] = " bytes3 ";
                strings[2] = " bytes1 ";
                break;
            }
            case("ChangeBytes2ToBytes1VTR"):{
                strings[1] = " bytes2 ";
                strings[2] = " bytes1 ";
                break;
            }

            //PKD
            case("RemovePayablePKD"):{
                strings[0] = " ";
                strings[1] = " payable ";
                strings[2] = " ";
                break;
            }

            //DKD
            case("RemoveDeleteDKD"):{
                strings[0] = " ";
                strings[1] = " delete ";
                strings[2] = " //delete ";
                break;
            }

            //GVC
            case("ChangeBlockDifficultyGVC"):{
                strings[0] = " ";
                strings[1] = "block.difficulty";
                strings[2] = "0";
                break;
            }
            case("ChangeBlockGaslimitGVC"):{
                strings[0] = " ";
                strings[1] = "block.gaslimit";
                strings[2] = "1000";
                break;
            }
            case("ChangeBlockNumberGVC"):{
                strings[0] = " ";
                strings[1] = "block.number";
                strings[2] = "1";
                break;
            }
            case("ChangeNowGVC"):{
                strings[0] = " ";
                strings[1] = "now";
                strings[2] = "0";
                break;
            }
            case("ChangeNow1GVC"):{
                strings[0] = " ";
                strings[1] = "now";
                strings[2] = "1";
                break;
            }
            case("ChangeBlockTimestampGVC"):{
                strings[0] = " ";
                strings[1] = "block.timestamp";
                strings[2] = "0";
                break;
            }
            case("ChangeMsgGasGVC"):{
                strings[0] = " ";
                strings[1] = "msg.gas";
                strings[2] = "1";
                break;
            }
            case("ChangeMsgValueGVC"):{
                strings[0] = " ";
                strings[1] = "msg.value";
                strings[2] = "1";
                break;
            }

            //MFR
            case("ReplaceAddmodToMulmodMFR"):{
                strings[0] = " ";
                strings[1] = "addmod(";
                strings[2] = "mulmod(";
                break;
            }
            case("ReplaceMulmodToAddmodMFR"):{
                strings[0] = " ";
                strings[1] = "mulmod(";
                strings[2] = "addmod(";
                break;
            }
            case("ReplaceAddToMulMFR"):{
                strings[0] = " ";
                strings[1] = "add(";
                strings[2] = "mul(";
                break;
            }
            case("ReplaceDivToSubMFR"):{
                strings[0] = " ";
                strings[1] = "div(";
                strings[2] = "sub(";
                break;
            }
            case("ReplaceMulToAddMFR"):{
                strings[0] = " ";
                strings[1] = "mul(";
                strings[2] = "add(";
                break;
            }
            case("ReplaceSubToDivMFR"):{
                strings[0] = " ";
                strings[1] = "sub(";
                strings[2] = "div(";
                break;
            }

            case("ReplaceMax64ToMin64MFR"):{
                strings[0] = " ";
                strings[1] = "max64(";
                strings[2] = "min64(";
                break;
            }
            case("ReplaceMin64ToMax64MFR"):{
                strings[0] = " ";
                strings[1] = "min64(";
                strings[2] = "max64(";
                break;
            }
            case("ReplaceMax256ToMin256MFR"):{
                strings[0] = " ";
                strings[1] = "max256(";
                strings[2] = "min256(";
                break;
            }
            case("ReplaceMin256ToMax256MFR"):{
                strings[0] = " ";
                strings[1] = "min256(";
                strings[2] = "max256(";
                break;
            }

            //AVR
            case("ReplaceSenderToOriginAVR"):{
                strings[0] = " ";
                strings[1] = "msg.sender";
                strings[2] = "tx.origin";
                break;
            }
            case("ReplaceSenderToCoinbaseAVR"):{
                strings[0] = " ";
                strings[1] = "msg.sender";
                strings[2] = "block.coinbase";
                break;
            }
            case("ReplaceOriginToSenderAVR"):{
                strings[0] = " ";
                strings[1] = "tx.origin";
                strings[2] = "msg.sender";
                break;
            }
            case("ReplaceOriginToCoinbaseAVR"):{
                strings[0] = " ";
                strings[1] = "tx.origin";
                strings[2] = "block.coinbase";
                break;
            }
            case("ReplaceCoinbaseToSenderAVR"):{
                strings[0] = " ";
                strings[1] = "block.coinbase";
                strings[2] = "msg.sender";
                break;
            }
            case("ReplaceCoinbaseToOriginAVR"):{
                strings[0] = " ";
                strings[1] = "block.coinbase";
                strings[2] = "tx.origin";
                break;
            }

            //EUR
            case("ReplaceEtherToWeiEUR"):{
                strings[0] = " ";
                strings[1] = " ether";
                strings[2] = " wei";
                break;
            }
            case("ReplaceWeiToEtherEUR"):{
                strings[0] = " ";
                strings[1] = " wei";
                strings[2] = " ether";
                break;
            }
            case("ReplaceWeiToFinneyEUR"):{
                strings[0] = " ";
                strings[1] = " wei";
                strings[2] = " finney";
                break;
            }
            case("ReplaceFinneyToWeiEUR"):{
                strings[0] = " ";
                strings[1] = " finney";
                strings[2] = " wei";
                break;
            }
            case("ReplaceFinneyToSazboEUR"):{
                strings[0] = " ";
                strings[1] = " finney";
                strings[2] = " sazbo";
                break;
            }
            case("ReplaceSzaboToFinneyEUR"):{
                strings[0] = " ";
                strings[1] = " sazbo";
                strings[2] = " finney";
                break;
            }

            //TUR
            case("ReplaceSecondsToMinutesTUR"):{
                strings[0] = " ";
                strings[1] = " seconds";
                strings[2] = " minutes";
                break;
            }
            case("ReplaceMinutesToSecondsTUR"):{
                strings[0] = " ";
                strings[1] = " minutes";
                strings[2] = " seconds";
                break;
            }
            case("ReplaceHoursToDaysTUR"):{
                strings[0] = " ";
                strings[1] = " hours";
                strings[2] = " days";
                break;
            }
            case("ReplaceDaysToHoursTUR"):{
                strings[0] = " ";
                strings[1] = " days";
                strings[2] = " hours";
                break;
            }
            case("ReplaceWeeksToMonthsTUR"):{
                strings[0] = " ";
                strings[1] = " Weeks";
                strings[2] = " Months";
                break;
            }
            case("ReplaceMonthsToWeeksTUR"):{
                strings[0] = " ";
                strings[1] = " months";
                strings[2] = " weeks";
                break;
            }

            //RSD
            case("RequireDeletionRSD"):{
                strings[0] = " ";
                strings[1] = " require(";
                strings[2] = " //require(";
                break;
            }

            //ASD
            case("AssertDeletionASD"):{
                strings[0] = " ";
                strings[1] = " assert(";
                strings[2] = " //assert(";
                break;
            }

            //ReventDeletion
            case("ReventDeletionRD"):{
                strings[0] = " ";
                strings[1] = " revent(";
                strings[2] = " //revent(";
                break;
            }

            default: {
                strings[0] = strings[1] = strings[2] = null;
            }
        }
        return strings;
    }


}
