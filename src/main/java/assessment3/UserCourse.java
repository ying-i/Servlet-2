package assessment3;

public class UserCourse {
	private int userCourseId;
    private int courseId;
    private int userId;
    
    public UserCourse() {
    	
    }
    
	public UserCourse(int userCourseId, int courseId, int userId) {
		super();
		this.userCourseId = userCourseId;
		this.courseId = courseId;
		this.userId = userId;
	}

	public int getUserCourseId() {
		return userCourseId;
	}

	public void setUserCourseId(int userCourseId) {
		this.userCourseId = userCourseId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
   
   
     
   
}
