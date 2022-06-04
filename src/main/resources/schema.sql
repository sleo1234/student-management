CREATE SEQUENCE IF NOT EXISTS student_seq;

CREATE TABLE IF NOT EXISTS student (

student_id BIGINT NOT NULL DEFAULT nextval('student_seq') PRIMARY KEY,
CNP VARCHAR(100) NOT NULL,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL

);

CREATE SEQUENCE IF NOT EXISTS activity_seq;

CREATE TABLE IF NOT EXISTS activity (

activity_id BIGINT NOT NULL DEFAULT nextval('activity_seq') PRIMARY KEY,
name VARCHAR(100) NOT NULL,
description VARCHAR(500) NOT NULL,
start_date TIMESTAMP,
end_date TIMESTAMP

);


CREATE TABLE IF NOT EXISTS activity_student (

activity_id BIGINT REFERENCES activity, 
student_id BIGINT REFERENCES student

);