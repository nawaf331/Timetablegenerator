DROP TABLE IF EXISTS "backpaper_details";
CREATE TABLE backpaper_details ('usn' VARCHAR NOT NULL , 'subname' VARCHAR NOT NULL , 'internal' INTEGER, 'external' INTEGER, 'total' INTEGER, 'result' CHAR, PRIMARY KEY (usn, subname));
DROP TABLE IF EXISTS "student_details";
CREATE TABLE student_details ('usn' VARCHAR, 'name' VARCHAR, 'f_total' INTEGER, 'f_result' VARCHAR, PRIMARY KEY (usn));
DROP TABLE IF EXISTS "subject_details";
CREATE TABLE subject_details ('usn' VARCHAR NOT NULL , 'subname' VARCHAR NOT NULL , 'internal' INTEGER, 'external' INTEGER, 'total' INTEGER, 'result' CHAR, PRIMARY KEY (usn, subname));
