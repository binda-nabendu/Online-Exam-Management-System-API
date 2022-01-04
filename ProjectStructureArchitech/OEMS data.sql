use examManagementSystem;
insert into baseUser(nid, userName, fatherName, motherName, gender, contactNo, email, dob, address, adminApproval, role, password)
values
("6118194051", "Rohan","Kobir Hossain","Rohima",1, '19857629', 'rohan@yahoo.com', '1997-10-12', "5/15 D.I.T.Market (Ist. Floor ), Dhaka", 1,"Student",'2345'),
("6118194084", "Romiz","Alim","Shelina",1, '1782910846', 'romiz@yahoo.com', '1996-1-09', "shewrapara, rokeya sharani, mirpur, 1216", 0,"Student",'7383'),
("6118194052", "Riyad","Rohomot","Saleha", 1, '19957628', 'riyad@yahoo.com', '1995-11-1', "145, Motijheel c/a (3rd floor), Motijheel, 1000", 1, "Student",'2346'),
("6118194053", "Asad","Akbor","Afroza", 1, '17057167', 'asad@yahoo.com', '1998-10-12', "28 Dilkusha C/a (8th Floor), Dhaka", 1, "Student",'2347'),
("6118194054", "Saidul","Mobarok","Nahar", 1, '18125766', 'saidul@yahoo.com', '1998-10-11', "1/36, south mugdapara bank colony, (1st floor), 1214", 1, "Student",'2194'),
("6118194076", "Rohima Akter","Faisal","Forida", 2, '178229183', 'rohima@yahoo.com', '1997-06-17', "House#8, Road # 2 Dhanmondi R/a, Dhaka", 0, "Student",'3727'),
("6118194055", "Emon","Abdul Quddus","Nasima", 1, '18332833', 'emon@yahoo.com', '1994-11-01', "Road #10, baridhara, 1212", 1, "Student",'2344'),
("6118194056", "Rashed","Abu Raihan","Taslim", 2, '16117278', 'rashed@yahoo.com', '1993-01-09', "119, New Elephant Road, Dhaka", 1, "Student",'5473'),
("6118194022", "Liza","Rony rahman","Rina Akter", 2, '1983848293', 'liza@yahoo.com', '1993-01-09', "New elephant road, (2nd floor), katabon more, 1205", 0, "Student",'7291'),
("6118194057", "Laila","ABu Naser","Ayesha", 2, '13728190', 'laila@yahoo.com', '1992-09-18', "a-1/16, sonali bank colony, motijheel, 1000", 1, "Student",'8237'),
("6118194058", "Huraira","Abdus Salam","shamima", 2, '18372818', 'huraira@yahoo.com', '2000-03-28', "bhai Bhai Plaza,(9th Floor),khilkhet, 1229", 1, "Student",'8737'),
("6118194059", "Sourov","Nagesh","Nisha", 1, '19113928', 'sourov@yahoo.com', '2001-12-28', "House # 134, Lane # 1, Baridhara DOHS", 1, "Student",'7217'),
("6118194060", "Hizbul","Saiful Islam","Kaniz Kobita", 1, '17917391', 'hizbul@yahoo.com', '2003-02-10', "128,motijheel C/a, Dhaka", 1, "Student",'6372'),
("6118194061", "Eng. Nafis Kamal","Asif Iqbal","Nasrin Jahan", 1, '1711909293', 'nafis@yahoo@.com', '1987-12-31', "14-15 Motijheel C/a, Ispahani Building, 5th Flr", 1, "Teacher",'7231'),
("6118194062", "Abu Siddik","Abu Zafor","Amena", 1, '1891839322', 'siddik@yahoo@.com', '1979-01-23', "Puraton Kasba, Jessore", 1, "Teacher",'6378'),
("6118194063", "Afroza Jahan","Raiyan","Shirina Akter", 2, '1633827194', 'afroza@yahoo@.com', '1990-11-13', "patenga, chittagong, chittagong, 4204", 1, "Teacher",'6238'),
("6118194064", "Mukhesh","Raton Das","Sonali", 1, '1478920347', 'mukhesh@yahoo@.com', '1985-09-05', "29/h, Sonatongar, Hazaribagh", 1, "Teacher",'7392'),
("6118194065", "Mr. Areng","Jisan Areng","Moleng Areng", 1, '1873992294', 'areng@yahoo@.com', '1991-10-25', "(ground floor), west nakhalpara, tejgaon, 1215", 1, "Teacher",'7238'),
("6118194090", "Nilima Akter","Waris Karim","Falguni Akter", 2, '1673947294', 'nilima@yahoo@.com', '1993-02-16', "Bikrampur, Bhaban, Tengra", 0, "Teacher",'9303'),
("6118194066", "Helal Uddin","Faser Uddin","Salma Akter", 1, '1622812993', 'helal@yahoo@.com', '1989-12-19', "61, suklal das lane, sutrapur, 1100", 1, "Teacher",'7382'),
("6118194067", "Kaniz Fatima","Fokir Shah","Airin Akter", 2, '192684723', 'kaniz@yahoo@.com', '1994-09-12', "Soydana (hajir Pukur), Board Bazar, Gazipur", 1, "Teacher",'8394'),
("6118194068", "Ashraful Hoque","Moinul Islam","Dilsana", 1, '1712232344', 'ashraf@yahoo@.com', '1985-03-22', "Block-K, Rupnagar, Sialbari Scetion # 2, Mirpu", 1, "Teacher",'7829'),
("6118194069", "Roksana Akter","Selim AKter","Maimuna Akter", 2, '197382837', 'roksana@yahoo@.com', '1990-04-29', "1/1-e, (1st Fr.) Block-c, Lalmatia", 1, "Teacher",'7382'),
("6118194070", "Abdul Mueez","Ahsan Ali","Poly Akter", 1, '192793749', 'mueez@yahoo@.com', '1992-07-03', "Suite#704, 55-A, Purana Pltan, P.O. Box: 1000", 1, "Teacher",'8293');


insert into department(deptId, deptName)
values
( "2019", "CSE" ),
( "2020", "CSE" ),
( "3019", "EEE" ),
( "3021", "EEE" ),
( "4011", "CSE" ),
( "4012", "CSE" ),
( "5021", "EEE" ),
( "5022", "EEE" ),
( "6011", "CSE" ),
( "6012", "CSE" );


insert into student(std_Id, dept_Id, batch, semester)
values
("6118194051", "2019", 2, 2),
("6118194052", "2020", 2, 2),
("6118194053", "3019", 3, 3),
("6118194054", "3021", 3, 3),
("6118194055", "4011", 4, 4),
("6118194056", "4012", 4, 4),
("6118194057", "5021", 5, 5),
("6118194058", "5022", 5, 5),
("6118194059", "6011", 6, 6),
("6118194060", "6012", 6, 6)
;


insert into teacher(teacherId, eduQualification, expertise)
values
("6118194061", 'MSC in EEE',"Machine" ),
("6118194062", 'BSC in CSE' ,"Programming"),
("6118194063", 'MSC in EEE',"Ac current"),
("6118194065", 'MSC in CIVIL', "CAD")
("6118194066", 'BSC in EEE',"Dc current"),
("6118194067", 'BSC in EEE',"Survay Motor"),
("6118194068", 'MSC in CSE', "Networking"),
("6118194069", 'MSC in CSE', "Database"),
("6118194070", 'MSC in CSE', "Database");


insert into courses(courseCode, deptId, courseName, teacherId)
values
("CSE315", "2019","Computer Architceture","6118194062"),
("EEE255", "2020","Electrical Circuit and design","6118194063"),
("CSE341", "3019","Theory of computing","6118194068"),
("CSE344", "3021","Mathmatical analysis for computer science","6118194069"),
("EEE281", "4011","Electical circuit","6118194066"),
("EEE285", "4012", "Servo Motor and its applyance","6118194066"),
("CSE382", "5021","Image processing","6118194069"),
("CSE364", "5022","Microprocessor design","6118194069"),
("CSE441", "6011","Database design","6118194069"),
("EEE389", "6012","Database design lab","6118194069")
;


insert into examPaper(courseCode, teacherId, percentageValue, startingDateTime, endingDateTime, courseSession,total)
values
("CSE315","6118194062",.05,"2022-02-26 10:30:00","2022-02-26 12:30:00",2,20),

;

insert into question(examId,questionNo, question, mark)
values

(1,1, "which is assemble language keyword", 2),

;


insert into questionAns(examId, questionNo, optionNo, optionValue, ansStatus)
values

(1,1,1,"a","ADD",true),
(1,1,2,"a","System.out",false),
(1,1,3,"a","AVEG",false),
(1,1,4,"a","MID",false),

;



insert into studentMark(stdId, courseCode, examId, gotTotalMarks)
values
("6118194051", "CSE315", 1, 18),

;


	
insert into stdAnsScript(stdId, examId,questionNo, optionNo, ansStatus)
values	
("6118194051",1,1,3,false),

;

insert into result(stdId, courseCode, cgpa, grade, semester)
values
("6118194051", "CSE315",3.75,'A',2),

;