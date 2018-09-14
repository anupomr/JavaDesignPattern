package anupom.roy.asgn1.data;

import java.util.Collection;

import java.util.HashSet;
import java.util.Iterator;

import anupom.roy.asgn1.dto.Course;
import anupom.roy.asgn1.exception.CourseNotFoundException;
import anupom.roy.asgn1.exception.DuplicateCourseException;

public class CourseManager implements CourseCatalog {
	static CourseManager cm = new CourseManager();

	private CourseManager() {
		//return super();
	}

	public static CourseManager getInstance() {
		return cm;
	}

	HashSet<Course> courseSet = new HashSet<Course>();

	@Override
	public Course getCourse(String courseCode) throws CourseNotFoundException {
		Iterator<Course> display = courseSet.iterator();
		while (display.hasNext()) {
			if (display.next().getCourseCode().equals(courseCode)) {
				// return this.getCourse(courseCode);
				return display.next();

			}
		}
		return null;
	}

	@Override
	public Course addCourse(Course course) throws DuplicateCourseException {
		// TODO Auto-generated method stub
		courseSet.add(course);
		return null;
	}

	@Override
	public Course deleteCourse(String courseCode) throws CourseNotFoundException {
		Iterator<Course> display = courseSet.iterator();
		while (display.next().getCourseCode() == courseCode) {
			courseSet.remove(display.next());
		}

		return null;
	}

	@Override
	public Course updateCourse(Course course) throws CourseNotFoundException {
		Iterator<Course> display = courseSet.iterator();
		while (display.next().getCourseCode() == course.getCourseCode()) {
			courseSet.remove(display.next());
			courseSet.add(course);

		}
		return null;
	}

	public Collection<Course> getAllCourses() {
		// TODO Auto-generated method stub
		Iterator<Course> display = courseSet.iterator();
		while (display.hasNext()) {
			System.out.println(display.next());
		}
		return null;
	}

}
