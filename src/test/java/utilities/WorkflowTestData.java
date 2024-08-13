package utilities;





public class WorkflowTestData {

	String ReportType,Rad,ReportingText,RAD2,ReportingText2, DTextBox,DComments,PeerReview;
	public String getPeerReview() {
		return PeerReview;
	}
	public void setPeerReview(String peerReview) {
		PeerReview = peerReview;
	}
	double TotalReads;
	
	
	public String getDTextBox() {
		return DTextBox;
	}
	public void setDTextBox(String dTextBox) {
		DTextBox = dTextBox;
	}
	public String getDComments() {
		return DComments;
	}
	public void setDComments(String dComments) {
		DComments = dComments;
	}
	public String getReportType() {
		return ReportType;
	}
	public void setReportType(String reportType) {
		ReportType = reportType;
	}
	public String getRad() {
		return Rad;
	}
	public void setRad(String rad) {
		Rad = rad;
	}
	public String getReportingText() {
		return ReportingText;
	}
	public void setReportingText(String reportingText) {
		ReportingText = reportingText;
	}
	public String getRAD2() {
		return RAD2;
	}
	public void setRAD2(String rAD2) {
		RAD2 = rAD2;
	}
	public String getReportingText2() {
		return ReportingText2;
	}
	public void setReportingText2(String reportingText2) {
		ReportingText2 = reportingText2;
	}
	public double getTotalReads() {
		return TotalReads;
	}
	public void setTotalReads(double totalReads) {
		TotalReads = totalReads;
	}
}
