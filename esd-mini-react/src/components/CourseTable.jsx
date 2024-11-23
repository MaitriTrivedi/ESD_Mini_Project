import React, { useState, useEffect } from 'react';
import { fetchCourses } from '../utils/fetchCourses'; // Import the fetchCourses function

function CourseTable() {
  const [courses, setCourses] = useState([]);
  const [selectedCourses, setSelectedCourses] = useState([]);
  const token = localStorage.getItem('token'); // Get JWT token from localStorage
  const studentId = localStorage.getItem('studentId');

  // Fetch courses on component mount
  useEffect(() => {
    const getCourses = async () => {
      if (token && studentId) {
        try {
          const response = await fetchCourses(token, studentId); // Call the API function to fetch courses
          if (response.error) {
            console.error(response.error);
          } else {
            setCourses(response); // Set the received courses to state
          }
        } catch (error) {
          console.error('Error during fetchCourses execution:', error.message);
        }
      } else {
        console.error("Missing token or studentId");
      }
    };

    getCourses();
  }, [token, studentId]); // Dependencies for re-fetching

  // Handle course selection
  const handleSelect = (courseId) => {
    setSelectedCourses((prevSelected) =>
      prevSelected.includes(courseId)
        ? prevSelected.filter((id) => id !== courseId)
        : [...prevSelected, courseId]
    );
  };

  return (
    <>
      <h2 className="text-center container-header">Select Courses</h2>
      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <th>#</th>
            <th>Course Name</th>
            <th>Course Description</th>
            <th>Credits</th>
            <th>Select</th>
          </tr>
        </thead>
        <tbody>
          {courses.length === 0 ? (
            <tr>
              <td colSpan="5" className="text-center">
                No Courses Available
              </td>
            </tr>
          ) : (
            courses.map((course, index) => (
              <tr key={course.courseId}>
                <td>{index + 1}</td>
                <td>{course.courseName}</td>
                <td>{course.description}</td>
                <td>{course.credits}</td>
                <td>
                  <input
                    type="checkbox"
                    checked={selectedCourses.includes(course.courseId)}
                    onChange={() => handleSelect(course.courseId)}
                  />
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </>
  );
}

export default CourseTable;
