package holdem.core.mappers;

public class CardRankMapper {

    public String rankToString(byte byteRank) {
        String stringRank;
        switch (byteRank) {
            case 2:
                stringRank = "2";
                break;
            case 3:
                stringRank = "3";
                break;
            case 4:
                stringRank = "4";
                break;
            case 5:
                stringRank = "5";
                break;
            case 6:
                stringRank = "6";
                break;
            case 7:
                stringRank = "7";
                break;
            case 8:
                stringRank = "8";
                break;
            case 9:
                stringRank = "9";
                break;
            case 10:
                stringRank = "T";
                break;
            case 11:
                stringRank = "J";
                break;
            case 12:
                stringRank = "Q";
                break;
            case 13:
                stringRank = "K";
                break;
            case 14:
                stringRank = "A";
                break;
            default:
                stringRank = null;
        }
        return stringRank;
    }

    public byte rankToByte(String stringRank) {
        byte byteRank;
        switch (stringRank) {
            case "2":
                byteRank = 2;
                break;
            case "3":
                byteRank = 3;
                break;
            case "4":
                byteRank = 4;
                break;
            case "5":
                byteRank = 5;
                break;
            case "6":
                byteRank = 6;
                break;
            case "7":
                byteRank = 7;
                break;
            case "8":
                byteRank = 8;
                break;
            case "9":
                byteRank = 9;
                break;
            case "T":
                byteRank = 10;
                break;
            case "J":
                byteRank = 11;
                break;
            case "Q":
                byteRank = 12;
                break;
            case "K":
                byteRank = 13;
                break;
            case "A":
                byteRank = 14;
                break;
            default:
                throw new IllegalArgumentException("Invalid card rank value;");
        }
        return byteRank;
    }

}
