create database ems;

use ems;

create table baseUser(
	nid varchar(20) primary key,
    userName varchar (50) not null,
    fatherName varchar(50) not null,
    motherName varchar(50) not null,
	gender tinyint not null,
	contactNo varchar(20) not null,
	email varchar(50),
	dob date not null,
	address varchar(200) not null,
	adminApproval tinyint default 0,
	role varchar(10)  not null,
	password varchar(100)  not null,
    check (role='ADMIN' OR role='TEACHER' OR role='STUDENT'),
    check (adminApproval=0 or adminApproval=1),
    check (email like '%@%')
);
create table department(
	deptId varchar(10) primary key,
	deptName varchar(60) not null,
	currentBatch int not null default 1
);
create table student(
	stdId varchar(20) primary key,
	deptId varchar(10) not null,
	batch int not null,
	semester int not null,
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
    courseCurrSession int,
    constraint pk_courses primary key (courseCode,deptId),
    
    foreign key(deptId) references department(deptId),
    foreign key(teacherId) references teacher(teacherId)
);
create table examPaper(
	examId int primary key,
	courseCode varchar(10) not null,
    deptId varchar(10) not null,
    teacherId varchar(20) not null,
	percentageValue decimal(5,2) not null,
	startingDateTime datetime not null,
	endingDateTime datetime not null,
    courseSession int not null,
	total decimal(5,2) not null,
    published bool default false,
    
    foreign key(courseCode, deptId) references courses(courseCode, deptId),
    foreign key(teacherId) references teacher(teacherId)
);
create table question(
	examId int,
	questionNo int,
	question varchar(200) not null,
	questionImage blob, 
	mark decimal(5,2) not null,
    constraint pk_question primary key (examId, questionNo),
	foreign key(examId) references examPaper(examId)
 );
create table questionAns(
	examId int,
	questionNo int,
	optionNo tinyint not null,
	optionValue varchar(200) not null,
	ansStatus boolean default false,
    constraint pk_questionAns primary key (examId,questionNo,optionNo),

    foreign key(examId, questionNo) references question(examId,questionNo)
);
create table studentMark(
	stdId varchar(20),
	courseCode varchar(10),
    deptId varchar(10),
	examId int,
	gotTotalMarks decimal(5,2) default 0.0,
	review boolean default false,
    constraint pk_studentMark primary key (stdId,courseCode,deptId,examId),
    foreign key(stdId) references student(stdId),
    foreign key(courseCode,deptId) references courses(courseCode,deptId),
    foreign key(examId) references examPaper(examId)
);
create table stdAnsScript(
	stdId varchar(20),
	examId int,
	questionNo int,
	optionNo tinyint default 0,
	ansStatus bool default false,
    constraint pk_stdAnsScript primary key (stdId,examId,questionNo,optionNo),
    
    foreign key(stdId) references student(stdId),
    foreign key(examId, questionNo) references question(examId,questionNo)
);
create table result( 
	stdId varchar(20),
	courseCode varchar(10),
    deptId varchar(10),
    courseSession int not null,
	cgpa decimal(3,2) default -1,
	grade varchar(3) default 'X',
	semester int not null,
    previousSemCrs bool default false,
    constraint pk_result primary key (stdId,courseCode,deptId),
    
    foreign key(stdId) references student(stdId),
    foreign key(courseCode,deptId) references courses(courseCode,deptId)
);
create table requestCourse
(
    stdId varchar(20),
    courseCode varchar(10),
    deptId varchar(10),
    constraint pk_requestCourse primary key(stdId, courseCode,deptId),
    foreign key(stdId) references student(stdId),
    foreign key(courseCode, deptId) references courses(courseCode, deptId)
);
create table faq
(
	faqId int primary key auto_increment,
    faqQuestion varchar(500),
    faqAns varchar(2000)
);
