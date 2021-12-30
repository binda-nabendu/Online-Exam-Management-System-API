create database examManagementSystem;

use examManagementSystem;

create table baseUser(
	nid varchar(20) primary key,
    userName varchar (50),
    fatherName varchar(50),
    motherName varchar(50),
	gender enum('MALE','FEMALE'),
	contactNo varchar(20),
	email varchar(50),
	dob date,
	address varchar(200),
	adminApproval bool,
	role enum('ADMIN','TEACHER', 'STUDENT'),
	password varchar(100)
);

create table  student(
	stdId varchar(20) primary key,
	deptId varchar(10) not null,
	batch int(4) not null,
	semester int(2) not null,
    foreign key(stdId) references baseUser(nid)
);

create table teacher(
	teacherId varchar(20) primary key,
	eduQualification varchar(200) default 'Did not give yet',
	expertise varchar(50) not null,
    foreign key(teacherId) references baseUser(nid)
);

create table department(
	deptId varchar(10) primary key,
	deptName varchar(60) not null
);

create table courses(
	courseCode varchar(10) primary key,
    deptId varchar(10) primary key,
	courseName varchar(50) not null,
	teacherId varchar(20) default 'Not assigned',
    courseCurrSession int(10),
    foreign key(deptId) references department(deptId),
    foreign key(teacherId) references teacher(teacherId)
);

create table examPaper(
	examId int(10) primary key auto_increment,
	courseCode varchar(10) primary key,
	percentageValue decimal(5,2) not null,
	startingDateTime datetime not null,
	endingDateTime datetime not null,
    courseSession int(10) not null,
	total decimal(5,2) not null,
    foreign key(courseCode) references courses(courseCode)
);

create table question(
	questionId int(20) primary key auto_increment,
	examId int(10) primary key,
	question varchar(200) not null,
	questionImage blob,
	mark decimal(4,2) not null,
	foreign key(examId) references examPaper(examId)
 );

create table studentMark(
	stdId varchar(20) primary key,
	courseCode varchar(10) primary key,
	examId int(10) primary key,
	gotTotalMarks decimal(5,2) default 0.0,
    foreign key(stdId) references student(stdId),
    foreign key(courseCode) references courses(courseCode),
    foreign key(examId) references examPaper(examId)
);

create table questionAns(
	examId int(10) primary key,
	questionId int(20) primary key,
	optionNo varchar(2) not null,
	optionValue varchar(200) not null,
	ansStatus bool default false,
    foreign key(examId) references examPaper(examId),
    foreign key(questionId) references question(questionId)
);

create table stdAnsScript(
	stdId varchar(20) primary key,
	examId int(10) primary key,
	questionId int(20) primary key,
	optionNo varchar(2) default 'Z',
	ansStatus bool default false,
    foreign key(stdId) references student(stdId),
    foreign key(examId) references examPaper(examId),
    foreign key(questionId) references question(questionId)
);

create table result(
	stdId varchar(20) primary key,
	courseCode varchar(10) primary key,
	cgpa decimal(3,2) default -1,
	grade varchar(3) default 'X',
	semester int(2),
    foreign key(stdId) references student(stdId),
    foreign key(courseCode) references courses(courseCode)
);
