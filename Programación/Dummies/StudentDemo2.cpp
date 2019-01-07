#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

class Student
{
public:
	int semesterHours;
	float gpa;

	// add a completed course to the record
	float addCourse(int hours, float grade);	
};

float Student::addCourse(int hours, float grade){
	float weightedGPA = semesterHours * gpa;

	// now add in the course
	semesterHours += hours;
	weightedGPA += grade * hours;
	gpa = weightedGPA / semesterHours;
	return gpa;
}

void someFn(Student& refS){
	refS.semesterHours = 10;
	refS.gpa = 3.0;
	refS.addCourse(3, 4.0); // call the member function
}

Student s;

int main(int argc, char const *argv[])
{
	// pass the address of s to someFn()
	someFn(s);
	cout << s.gpa << " - " << s.semesterHours << "\n";

	/*// pass the value of the pointer pS
	Student s2;
	Student* pS;
	pS = &s2;
	someFn(pS);
	cout << s2.gpa << " - " << s2.semesterHours << "\n";*/

	return 0;
}