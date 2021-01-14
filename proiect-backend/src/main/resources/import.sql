select * from app_user;

update app_user set role = 'ROLE_ADMIN' where id = 1;

commit;

select * from FEEDBACK_QUESTION;
select * from feedback_question;
 insert into FEEDBACK_QUESTION (id, question) values ( 1,'Cadrul didactic a prezentat conținutul disciplinei într-o manieră unitară, scoțând în evidență esența conținutului');
 insert into FEEDBACK_QUESTION (id, question) values (2,'Metoda de predare utilizată a facilitat asimilarea de cunoștințe și dobândirea de competențe');
 insert into FEEDBACK_QUESTION (id, question) values (3,'Cadrul didactic a utilizat materiale didactice utile și de actualitate, în conformitate cu bibliografia de specialitate recomandată');
 insert into FEEDBACK_QUESTION (id, question) values (4,'Cadrul didactic gestionează eficient timpul alocat activității didactice, parcurgând întreaga materie în timpul total alocat');
 insert into FEEDBACK_QUESTION (id, question) values (5,'Cadrul didactic și-a manifestat disponibilitatea de a ajuta studenții să acumuleze cunoștințele predate');
 insert into FEEDBACK_QUESTION (id, question) values (6,'Cadrul didactic a arătat cum și în ce contexte pot fi folosite cunoștințele și competențele dobândite');
 insert into FEEDBACK_QUESTION (id, question) values (7,'Cadrul didactic respectă opiniile studenților, formulate pe baza materiei predate');
 insert into FEEDBACK_QUESTION (id, question) values (8,'Cadrul didactic pune la dispoziție studenților toate materialele necesare asimilării de cunoștințe');
commit;