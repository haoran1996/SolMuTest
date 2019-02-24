package blockchain.MutationUtils;

public class MutationInfo {
    private String MutatedFileName;
    private String MutationOperator;
    private int MutatedLine;
    private String MutatedFileContent;

    public MutationInfo(String mutatedFileName, String mutationOperator, int mutatedLine, String mutatedFileContent) {
        MutatedFileName = mutatedFileName;
        MutationOperator = mutationOperator;
        MutatedLine = mutatedLine;
        MutatedFileContent = mutatedFileContent;
    }

    public MutationInfo(String mutatedFileName, String mutationOperator, int mutatedLine){
        new MutationInfo(mutatedFileName,mutationOperator,mutatedLine,null);
    }

    public String getMutationInfo(){
        StringBuffer sb = new StringBuffer();
        sb.append(MutatedFileName);
        sb.append("-");
        sb.append(MutationOperator);
        sb.append("-");
        sb.append(MutatedLine);
        return sb.toString();
    }

    public String getMutatedFileName() {
        return MutatedFileName;
    }

    public void setMutatedFileName(String mutatedFileName) {
        MutatedFileName = mutatedFileName;
    }

    public String getMutationOperator() {
        return MutationOperator;
    }

    public void setMutationOperator(String mutationOperator) {
        MutationOperator = mutationOperator;
    }

    public int getMutatedLine() {
        return MutatedLine;
    }

    public void setMutatedLine(int mutatedLine) {
        MutatedLine = mutatedLine;
    }

    public String getMutatedFileContent() {
        return MutatedFileContent;
    }

    public void setMutatedFileContent(String mutatedFileContent) {
        MutatedFileContent = mutatedFileContent;
    }

}
