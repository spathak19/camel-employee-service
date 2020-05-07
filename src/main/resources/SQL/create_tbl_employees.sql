CREATE TABLE tbl_Employees (
	empId SERIAL Primary Key,
	empFirstName TEXT NOT NULL,
	empMiddleName TEXT DEFAULT NULL,
	empLastName TEXT NOT NULL,
	empDepartment TEXT NOT NULL,
	empSalary NUMERIC (5,2),
	empHireDate TIMESTAMPTZ NULL DEFAULT current_timestamp
);