package anupom.roy.asgn1.dto;

import java.io.Serializable;

import anupom.roy.asgn1.exception.ImproperInputException;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	private String courseCode;
	private String courseTitle;
	private Professor professor;

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) throws ImproperInputException {
		if ( courseCode == null || courseCode.isEmpty() ) {
			throw new ImproperInputException("Course must have a course code");
		}
		courseCode.toUpperCase();
		if ( ! courseCode.matches("[A-Z]{3,4} ?[0-9]{3,4}" ) ) {
			throw new ImproperInputException(
				"Course code must be 3 or 4 letters followed by 3 or 4 digits" );
		}
		this.courseCode = courseCode;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		// assume a course can have an empty or null title
		this.courseTitle = courseTitle;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		// professor can be null when professor not assigned
		this.professor = professor;
	}

	public Course( String courseCode) throws ImproperInputException {
		super();
		setCourseCode(courseCode);
	}
	
	public Course(String courseCode, String courseTitle) throws ImproperInputException  {
		this( courseCode );
		setCourseTitle(courseTitle);
	}
	
	public Course(String code, String title, Professor professor) throws ImproperInputException   {
		this(code,title);
		this.professor = professor;
}

	@Override
	public String toString() {
		return "Course [" + (courseCode != null ? "Code=" + courseCode + ", " : "")
				+ (courseTitle != null ? "Title=" + courseTitle + ", " : "")
				+ (professor != null ? "Professor=" + professor : "") + "]";
	}


}
