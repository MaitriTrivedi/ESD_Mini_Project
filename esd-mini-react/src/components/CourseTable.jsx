import React, { useState } from 'react';

function CourseTable({ courses=[] }) {
  const [selectedCourses, setSelectedCourses] = useState([]);

  const handleSelect = (courseId) => {
    setSelectedCourses((prevSelected) =>
      prevSelected.includes(courseId)
        ? prevSelected.filter((id) => id !== courseId)
        : [...prevSelected, courseId]
    );
  };

  return (
    <>
    <h2 className='text-center container-header'>Select Courses</h2>
    <table className="table table-striped table-hover">
      <thead>
        <tr>
          <th>#</th>
          <th>Course Name</th>
          <th>Course Code</th>
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
            <tr key={course.id}>
              <td>{index + 1}</td>
              <td>{course.name}</td>
              <td>{course.code}</td>
              <td>{course.credits}</td>
              <td>
                <input
                  type="checkbox"
                  checked={selectedCourses.includes(course.id)}
                  onChange={() => handleSelect(course.id)}
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
