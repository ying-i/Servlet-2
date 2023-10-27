package assessment3;

public class Assess {
	private int assessmentId;
	private int userId;
	private int courseId;
	private String quizeMark;
	private String assignmentMark;
	private String finalExamMark;
	
	public Assess() {
		
	}

	public Assess(int assessmentId, int userId, int courseId, String quizeMark, String assignmentMark,
			String finalExamMark) {
		this.assessmentId = assessmentId;
		this.userId = userId;
		this.courseId = courseId;
		this.quizeMark = quizeMark;
		this.assignmentMark = assignmentMark;
		this.finalExamMark = finalExamMark;
	}

	public int getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId2) {
		this.courseId = courseId2;
	}

	public String getQuizeMark() {
		return quizeMark;
	}

	public void setQuizeMark(String quizeMark) {
		this.quizeMark = quizeMark;
	}

	public String getAssignmentMark() {
		return assignmentMark;
	}

	public void setAssignmentMark(String assignmentMark) {
		this.assignmentMark = assignmentMark;
	}

	public String getFinalExamMark() {
		return finalExamMark;
	}

	public void setFinalExamMark(String finalExamMark) {
		this.finalExamMark = finalExamMark;
	}
	
	
	

}
