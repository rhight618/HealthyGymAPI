select * from healthygymdb1.checkins;
select * from healthygymdb1.users;
truncate healthygymdb1.users;
truncate healthygymdb1.checkins;

ALTER TABLE healthygymdb1.riskdetail
  DROP COLUMN hour;

insert into healthygymdb1.riskdetail (day_of_week, hour_value , risk_class) Values (1,3,0)

update healthygymdb1.checkins set risk_class = 0;

CALL UpdateRiskForCommonCheckins(2,'2021-10-19 21:00:00',2);

-- self report userId = 2, report_timestamp = 2021-10-11 21:00:00, 

-- common user checkins in the last 14 days
UPDATE healthygymdb1.checkins a
INNER JOIN healthygymdb1.checkins b ON a.checkin_timestamp = b.checkin_timestamp
set a.risk_class = 1
where
b.user_id = 2
AND 
b.checkin_timestamp between DATE_ADD('2021-10-15 21:00:00', INTERVAL -5 DAY) 
AND DATE_ADD('2021-10-15 21:00:00', INTERVAL 5 DAY) ;

update CheckIn a Inner Join CheckIn b ON a.checkin_timestamp = b.checkin_timestamp set a.risk_class = 1 where b.userId = :userId AND a.checkin_timestamp between DATE_ADD(:reportDate, INTERVAL -14 DAY) AND DATE_ADD(:reportDate, INTERVAL 10 DAY)

select count(*), risk_class from healthygymdb1.checkins group by risk_class

select distinct DAYOFWEEK(a.checkin_timestamp) as checkin_weekday, HOUR(a.checkin_timestamp) as checkin_hour from healthygymdb1.checkins a





select DATE(a.checkin_timestamp) as assocaited_date, TIME(a.checkin_timestamp) as assocaited_time, a.risk_class as class from  healthygymdb1.checkins a

select a.checkin_timestamp as assocaited_checkin, a.risk_class as class from  healthygymdb1.checkins a

select a.id as checkin_id, a.user_id as associated_user, b.user_id as report_user, TIME(a.checkin_timestamp) as assocaited_checkin, a.risk_class as class, b.risk_class as class_b
from  healthygymdb1.checkins a
INNER JOIN healthygymdb1.checkins b ON a.checkin_timestamp = b.checkin_timestamp
where b.user_id = 2 
AND 
b.checkin_timestamp between DATE_ADD('2021-10-15 21:00:00', INTERVAL -14 DAY) 
AND DATE_ADD('2021-10-15 21:00:00', INTERVAL 10 DAY) 

select * from healthygymdb1.checkins where user_id = 2







