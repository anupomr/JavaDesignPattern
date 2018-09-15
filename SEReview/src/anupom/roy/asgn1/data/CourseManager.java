package anupom.roy.asgn1.data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import anupom.roy.asgn1.dto.Course;
import anupom.roy.asgn1.exception.CourseNotFoundException;
import anupom.roy.asgn1.exception.DuplicateCourseException;

public class CourseManager implements CourseCatalog {
	private static CourseManager courseManager = null;
	Map<String, Course> courseSet = null;
	private CourseManager() {
		courseSet = new ConcurrentHashMap<String, Course>();
	}

	public synchronized static CourseManager getInstance() {
		if(courseManager==null) {
			courseManager = new CourseManager();
		}			
		return courseManager;
	}	

	@Override
	public Course getCourse(String courseCode) throws CourseNotFoundException {
		if (!courseSet.containsKey(courseCode)) {
			throw new CourseNotFoundException(courseCode + " Not found");
		}
		return courseSet.get(courseCode);
	}

	@Override
	public Course addCourse(Course course) throws DuplicateCourseException {
		if (courseSet.containsKey(course.getCourseCode())) {
			throw new DuplicateCourseException(course.getCourseCode() + " is already exixts");
		}
		courseSet.put(course.getCourseCode(), course);
		return course;
	}

	@Override
	public Course deleteCourse(String courseCode) throws CourseNotFoundException {
		if (!courseSet.containsKey(courseCode)) {
			throw new CourseNotFoundException(courseCode + " Not found");
		}
		Course delCourse = courseSet.get(courseCode);
		courseSet.remove(courseCode);
		return delCourse;
	}

	@Override
	public Course updateCourse(Course course) throws CourseNotFoundException {

		if (!courseSet.containsKey(course.getCourseCode())) {
			throw new CourseNotFoundException(course.getCourseCode() + "  Not found");
		}
		courseSet.replace(course.getCourseCode(), course);
		return courseSet.get(course.getCourseCode());
	}

	public Collection<Course> getAllCourses() {

		List<Course> valueList = Collections.list(Collections.enumeration(courseSet.values()));
		return valueList;
	}

}
