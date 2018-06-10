use bookme;

insert into Category values ("Science");
insert into Category values ("Art");
insert into Category values ("Religion");
insert into Category values ("History");
insert into Category values ("Geography");

insert into Publisher values ("Bantam Books");
insert into Publisher values ("Unknown");
insert into Publisher values ("OXFORD");

-- user_id, email, password, salt, first_name, last_name, phone_number, shipping_address, is_manager
insert into User values("0", "0", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("1", "1", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("2", "2", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("3", "3", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("4", "4", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("5", "5", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("6", "6", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("7", "7", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("8", "8", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("9", "9", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("10", "10", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("11", "11", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("12", "12", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("13", "13", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);
insert into User values("14", "14", X'9fad5e9eefdfb449', X'9fad5e9eefdfb449', "first name 0", "last name 0", "010", "alex", 0);

insert into Book values (0, "A Brief History Of Time", "Bantam Books", "1999", "Science", 29.0, 5, 20);
insert into Book values (1, "A Short History of Nearly Everything", "OXFORD", "2003", "Science", 39.0, 5, 20);
insert into Book values (2, "English Dictionary", "OXFORD", "2011", "Art", 20.0, 5, 20);
insert into Book values (3, "The Art of War", "Unknown", "1999", "History", 12.99, 5, 20);
insert into Book values (4, "1984", "Unknown", "2003", "History", 27.0, 5, 20);
insert into Book values (5, "The New Drawing on the Right Side of the Brain (Paperback) ", "Unknown", "2011", "Art", 39.0, 5, 20);

INSERT INTO Sale VALUES (UUID(),0, 0,current_date()- interval 1 month,3);
INSERT INTO Sale VALUES (UUID(),0, 1,current_date()-interval 5 month,3);
INSERT INTO Sale VALUES (UUID(),1, 2,current_date()-interval 3 month,3);
INSERT INTO Sale VALUES (UUID(),1, 3,current_date()-interval 1 month,3);
INSERT INTO Sale VALUES (UUID(),2, 4,current_date()-interval 4 month,3);
INSERT INTO Sale VALUES (UUID(),2, 5,current_date()-interval 2 month,3);
INSERT INTO Sale VALUES (UUID(),2, 0,current_date()-interval 9 month,3);
INSERT INTO Sale VALUES (UUID(),3, 1,current_date()-interval 7 month,3);
INSERT INTO Sale VALUES (UUID(),3, 2,current_date()-interval 2 month,3);
INSERT INTO Sale VALUES (UUID(),4, 3,current_date()-interval 1 month,3);
INSERT INTO Sale VALUES (UUID(),4, 4,current_date()-interval 1 month,3);
INSERT INTO Sale VALUES (UUID(),5, 5,current_date()-interval 4 month,3);
INSERT INTO Sale VALUES (UUID(),6, 0,current_date()-interval 7 month,3);
INSERT INTO Sale VALUES (UUID(),7, 1,current_date()-interval 5 month,3);
INSERT INTO Sale VALUES (UUID(),8, 2,current_date()-interval 1 month,3);
INSERT INTO Sale VALUES (UUID(),9, 3,current_date()-interval 5 month,3);
INSERT INTO Sale VALUES (UUID(),10, 4,current_date()-interval 2 month,3);


insert into Author values("Yakout", 0);
insert into Author values("Ahmed", 1);
insert into Author values("Essam", 2);
insert into Author values("Amr", 3);
insert into Author values("Hendy", 4);
insert into Author values("Khamis", 5);
insert into Author values("Hamada", 5);
insert into Author values("King", 5);
insert into Author values("Ali", 2);

update user set is_manager = 1 where email = 'iyakout@hotmail.com';