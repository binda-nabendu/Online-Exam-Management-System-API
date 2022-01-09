create database examManagementSystem;

use examManagementSystem;

create table baseUser(
	nid varchar(20) primary key,
    userName varchar (50) not null,
    fatherName varchar(50) not null,
    motherName varchar(50) not null,
	gender tinyint(3) not null,
	contactNo varchar(20) not null,
	email varchar(50),
	dob date not null,
	address varchar(200) not null,
	adminApproval tinyint(3) default 0,
	role varchar(10)  not null,
	password varchar(100)  not null,
    check (role='ADMIN' OR role='TEACHER' OR role='STUDENT'),
    check (adminApproval=0 or adminApproval=1),
    check (email like '%@%')
);
create table department(
	deptId varchar(10) primary key,
	deptName varchar(60) not null
);
create table student(
	stdId varchar(20) primary key,
	deptId varchar(10) not null,
	batch int(4),
	semester int(2) not null,
    foreign key(stdId) references baseUser(nid),
    foreign key(deptId) references department(deptId)
);
create table teacher(
	teacherId varchar(20) primary key,
	eduQualification varchar(200) default 'Did not give yet',
	expertise varchar(50) not null,
    foreign key(teacherId) references baseUser(nid)
);
create table courses(
	courseCode varchar(10),
    deptId varchar(10),
	courseName varchar(50) not null,
	teacherId varchar(20) default 'Not assigned',
    courseCurrSession int(10),
    constraint pk_courses primary key (courseCode,deptId),
    foreign key(deptId) references department(deptId),
    foreign key(teacherId) references teacher(teacherId)
);
create table examPaper(
	examId int(10),
	courseCode varchar(10),
    teacherId varchar(20),
	percentageValue decimal(5,2) not null,
	startingDateTime datetime not null,
	endingDateTime datetime not null,
    courseSession int(10) not null,
	total decimal(5,2) not null,
    constraint pk_examPaper primary key (examId,courseCode),
    foreign key(courseCode) references courses(courseCode),
    foreign key(teacherId) references teacher(teacherId)
);
create table question(
	examId int(10),
	questionNo int(20) ,
	question varchar(200) not null,
	questionImage blob,
	mark decimal(4,2) not null,
    constraint pk_question primary key (examId, questionNo),
	foreign key(examId) references examPaper(examId)
 );
create table questionAns(
	examId int(10),
	questionNo int(20),
	optionNo int(2) not null,
	optionValue varchar(200) not null,
	ansStatus boolean default false,
    constraint pk_questionAns primary key (examId,questionNo,optionNo),
    foreign key(examId) references examPaper(examId),
    foreign key(questionNo) references question(questionNo)
);
create table studentMark(
	stdId varchar(20),
	courseCode varchar(10),
	examId int(10),
	gotTotalMarks decimal(5,2) default 0.0,
	review boolean default false,
    constraint pk_studentMark primary key (stdId,courseCode,examId),
    foreign key(stdId) references student(stdId),
    foreign key(courseCode) references courses(courseCode),
    foreign key(examId) references examPaper(examId)
);
create table stdAnsScript(
	stdId varchar(20),
	examId int(10),
	questionNo int(20),
	optionNo varchar(2) default 'Z',
	ansStatus bool default false,
    constraint pk_stdAnsScript primary key (stdId,examId,questionNo),
    foreign key(stdId) references student(stdId),
    foreign key(examId) references examPaper(examId),
    foreign key(questionNo) references question(questionNo)
);
create table result( 
	stdId varchar(20),
	courseCode varchar(10),
	cgpa decimal(3,2) default -1,
	grade varchar(3) default 'X',
	semester int(2),
    constraint pk_result primary key (stdId,courseCode),
    foreign key(stdId) references student(stdId),
    foreign key(courseCode) references courses(courseCode)
);
