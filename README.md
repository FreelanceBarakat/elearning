# elearning
Here's the Sample possible requests:

Post:
body: empty
url: http://localhost:8080/viewAllUsers 

Post:
http://localhost:8080/StudentService/createCourse
{
	"courseName": "mathads",
	 "description": "firstg coursfe ",
	 "instructor": "ahmed",
     "publishDate": "2019/1/1",
	 "totalHours":10
}

Post:
http://localhost:8080/StudentService/unRegisterCourse
{
	"courseName": "maths",
	"studentName":"mostafa"
}

Post:
http://localhost:8080/StudentService/createStudent
{
	"email":"email",
	"name":"mostafa2",
	"userPassword": "USERPASSWORD",
	"gender": "mail",
	"DOB": "2910"
}

Get:
http://localhost:8080/StudentService/listCoursesOfStudent/mostafa2


Post:
http://localhost:8080/StudentService/registerCourse
{
	"courseName": "maths",
	"studentName":"mostafa"
}

registerCourse
Kindy use 
username: most
password: password

or simple using the endpoint /signup to create new user
