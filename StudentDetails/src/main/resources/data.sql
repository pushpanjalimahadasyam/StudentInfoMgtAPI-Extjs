CREATE TABLE STUDENT.STUDENTDETAILS (id integer(10),STU_REGNO integer(10) unique, STU_NAME VARCHAR(130),STU_ADDRESS VARCHAR(255), STU_DOB DATE, primary key(id));
insert into STUDENT.STUDENTDETAILS values(1,101,'Pushpanjali','Hyderabad','1986-06-06');
insert into STUDENT.STUDENTDETAILS values(2,102,'Bharath Kuamr','Pragathi Nagar,Hyderabad','1987-04-18');
insert into STUDENT.STUDENTDETAILS values(3,103,'Partha Sarathi','Hyderabad, Pragathi Nagar','1988-09-11');