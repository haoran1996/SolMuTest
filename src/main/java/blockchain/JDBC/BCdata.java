package blockchain.JDBC;

public class BCdata {
    private int id;
    private String MutatedFileName;
    private String MutationOperator;
    private int MutatedLine;
    private String MutatedFileContent;
    private String TestFileName;
    private String TestFileContent;
    private double BranchCoverage;
    private String CoveredLine;
    private int isMutantKilled;

    public BCdata(int id, String mutatedFileName, String mutationOperator, int mutatedLine, String mutatedFileContent, String testFileName, String testFileContent, double branchCoverage, String coveredLine, int isMutantKilled) {
        this.id = id;
        MutatedFileName = mutatedFileName;
        MutationOperator = mutationOperator;
        MutatedLine = mutatedLine;
        MutatedFileContent = mutatedFileContent;
        TestFileName = testFileName;
        TestFileContent = testFileContent;
        BranchCoverage = branchCoverage;
        CoveredLine = coveredLine;
        this.isMutantKilled = isMutantKilled;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMutatedFileName(String mutatedFileName) {
        MutatedFileName = mutatedFileName;
    }

    public void setMutationOperator(String mutationOperator) {
        MutationOperator = mutationOperator;
    }

    public void setMutatedLine(int mutatedLine) {
        MutatedLine = mutatedLine;
    }

    public void setMutatedFileContent(String mutatedFileContent) {
        MutatedFileContent = mutatedFileContent;
    }

    public void setTestFileName(String testFileName) {
        TestFileName = testFileName;
    }

    public void setTestFileContent(String testFileContent) {
        TestFileContent = testFileContent;
    }

    public void setBranchCoverage(double branchCoverage) {
        BranchCoverage = branchCoverage;
    }

    public void setCoveredLine(String coveredLine) {
        CoveredLine = coveredLine;
    }

    public void setIsMutantKilled(int isMutantKilled) {
        this.isMutantKilled = isMutantKilled;
    }


    public int getId() {
        return id;
    }

    public String getMutatedFileName() {
        return MutatedFileName;
    }

    public String getMutationOperator() {
        return MutationOperator;
    }

    public int getMutatedLine() {
        return MutatedLine;
    }

    public String getMutatedFileContent() {
        return MutatedFileContent;
    }

    public String getTestFileName() {
        return TestFileName;
    }

    public String getTestFileContent() {
        return TestFileContent;
    }

    public double getBranchCoverage() {
        return BranchCoverage;
    }

    public String getCoveredLine() {
        return CoveredLine;
    }

    public int getIsMutantKilled() {
        return isMutantKilled;
    }



}
