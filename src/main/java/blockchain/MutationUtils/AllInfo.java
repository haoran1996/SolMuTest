package blockchain.MutationUtils;

public class AllInfo {
    private int id;
    private String MutationInfo;
    private String TestFileName;
    private double BranchCoverage;
    private String CoveredLine;
    private String TestCondition;
    private int TimeCost;

    public AllInfo(int id, String mutationInfo, String testFileName, double branchCoverage, String coveredLine, String testCondition, int timeCost) {
        this.id = id;
        MutationInfo = mutationInfo;
        TestFileName = testFileName;
        BranchCoverage = branchCoverage;
        CoveredLine = coveredLine;
        TestCondition = testCondition;
        TimeCost = timeCost;
    }

    public String Write(){
        StringBuffer sb = new StringBuffer();
        sb.append(id);
        sb.append("-");
        sb.append(this.MutationInfo);
        sb.append("-");
        sb.append(TestFileName);
        sb.append("-");
        sb.append(BranchCoverage);
        sb.append(TestCondition);
        sb.append(TimeCost);
        return sb.toString();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMutationInfo() {
        return MutationInfo;
    }

    public void setMutationInfo(String mutationInfo) {
        MutationInfo = mutationInfo;
    }

    public String getTestFileName() {
        return TestFileName;
    }

    public void setTestFileName(String testFileName) {
        TestFileName = testFileName;
    }

    public double getBranchCoverage() {
        return BranchCoverage;
    }

    public void setBranchCoverage(double branchCoverage) {
        BranchCoverage = branchCoverage;
    }

    public String getCoveredLine() {
        return CoveredLine;
    }

    public void setCoveredLine(String coveredLine) {
        CoveredLine = coveredLine;
    }

    public String getTestCondition() {
        return TestCondition;
    }

    public void setTestCondition(String testCondition) {
        TestCondition = testCondition;
    }

    public int getTimeCost() {
        return TimeCost;
    }

    public void setTimeCost(int timeCost) {
        TimeCost = timeCost;
    }
}
