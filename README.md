POST
http://localhost:8080/student-management-system/api/v1/students/newStudent
examples:
{
"firstName": "Sophia",
"lastName": "Hamilton",
"email": "test@mail.com",
"phoneNumber": ""
}
------------------------------------------------------------------------------
GET
http://localhost:8080/student-management-system/api/v1/students/getStudent/1
-----------------------------------------------------------------------------
http://localhost:8080/student-management-system/api/v1/students/getAllStudents
------------------------------------------------------------------------------
http://localhost:8080/student-management-system/api/v1/students/getStudentByLastname?lastName=Hamilton
examples:
Key:lastName Value:Hamilton
--------------------------------------------------------------------------------
PUT
http://localhost:8080/student-management-system/api/v1/students/updateStudent/1 
(För att kolla om studenten finns i database, skulle studenteten finnas så skickar vi in det nya data)
examples:
{
"id": 1,
"firstName": "Sophia",
"lastName": "Thomas",
"email": "test@mail.com",
"phoneNumber": "12325646"
}
-------------------------------------------------------------------------------
DELETE
http://localhost:8080/student-management-system/api/v1/students/deleteStudent/1
-------------------------------------------------------------------------------

Comment:
Det största svårigheten med labbet är att skapa en fungerande felhantering för POST & Delete-metoden.
