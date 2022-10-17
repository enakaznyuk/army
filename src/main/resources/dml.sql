use army_db;

insert into armies(number) values (1);

insert into small_arms(name, number) values 
	('Stechkin', 1),
    ('Stechkin', 2),
    ('Stechkin', 3),
    ('Stechkin', 4),
    ('Stechkin', 5),
    ('Stechkin', 6),
    ('AK-12', 7),
    ('AK-12', 8),
	('AK-12', 9),
	('AK-12', 10),
	('AK-12', 11),
    ('AK-12', 12),
    ('AK-12', 13),
    ('AK-12', 14),
    ('AK-12', 15),
    ('AK-12', 16),
    ('AK-12', 17),
    ('AK-12', 18),
    ('AK-12', 19);
    
insert into small_arms(name, number) values ('Stechkin', 20);
    
insert into generals(army_id, small_arm_id, first_name, last_name, military_badge) values (1, 1, 'Ivan', 'Ivanov', 1);

insert into officers(general_id, small_arm_id, first_name, last_name, military_badge) values 
	(1, 1, 'Artyom', 'Drozd', 6),
	(1, 2, 'Dmitriy', 'Dmitriyev', 1),
    (1, 3, 'Sergey', 'Sergeev', 2),
    (1, 4, 'Constantin', 'Simonov', 3),
    (1, 5, 'Aleksandr', 'Aleksandrov', 4),
    (1, 6, 'Pavel', 'Pavlov', 5);
    
insert into tanks(officer_id, number, type, name) values (1, 1, 'main_battle_tank', 'T-80');
    
insert into planes(officer_id, number, type, name) values (2, 1, 'fighter', 'SU-34');

insert into infantry_vehicles(officer_id, number, type, name) values (3, 1, 'main_transport_vehicl', 'Ural');

insert into mobile_artilleries(officer_id, number, type, name) values (4, 1, 'howitzer', '2А61');

insert into heavy_artilleries(officer_id, number, type, name) values (5, 1, 'self-propelled_howitzer', 'Pion');
    
insert into heavy_weapons(name, number) values 
	('RSG', 1),
    ('RSG', 2),
    ('RSG', 3);
    
insert into rewards(name) values 
('for_courage'),
('for_distinction');

insert into soldiers(officer_id, small_arm_id, first_name, last_name, demobilization, military_badge) values 
	(1, 7, 'Maksim', 'Ushakov', '2022-01-01', 1),
    (1, 8, 'Ilya', 'Abramov', '2022-01-01', 2),
    (1, 9, 'Makar', 'Sidorov', '2022-01-01', 3),
    
    (5, 10, 'Roman', 'Gurov', '2022-05-01', 4),
    (5, 11, 'Mark', 'Karpov', '2022-05-01', 5),
    (5, 12, 'Stepan', 'Smirnov', '2022-05-01', 6),
    
    (4, 13, 'Ivan', 'Orlov', '2022-08-01', 7),
    (4, 14, 'Mihail', 'Kuznecov', '2022-08-01', 8),
    (4, 15, 'Artyom', 'Mironov', '2022-08-01', 9),
    
    (3, 16, 'Egor', 'Petrov', '2022-10-01', 10);
    
insert into soldiers(officer_id, small_arm_id, heavy_weapon_id, first_name, last_name, demobilization, military_badge) values 
	(6, 17, 1, 'Matvey', 'Chernov', '2022-03-01', 11),
    (6, 18, 2, 'Igor', 'Morozov', '2022-03-01', 12),
    (6, 19, 3, 'Daniil', 'Efimov', '2022-03-01', 13);
    
insert into soldier_rewards(soldier_id, rewards_id) values
(1, 2),
(5, 2),
(8, 1);

# отключение безопасного режима, который не позволял апдейтить таблицы без использования колонки id
SET SQL_SAFE_UPDATES = 0;

insert into tanks(officer_id, number, type, name) values 
(1, 2, 'main_battle_tank', 'T-80'),
(1, 3, 'main_battle_tank', 'T-80'),
(1, 4, 'main_battle_tank', 'T-80'),
(1, 5, 'main_battle_tank', 'T-80'),
(1, 6, 'main_battle_tank', 'T-80'),
(1, 7, 'main_battle_tank', 'T-80'),
(1, 8, 'main_battle_tank', 'T-80');

#меняем всем солдатам с фамилией ефимов на борисов
update soldiers set last_name = 'Borisov' where last_name = 'Efimov';

#меняем все ак-12 на ак-15
update small_arms set name = 'AK-15' where name = 'AK-12';

#меняем название танка на т-90 с айдишником 1
update tanks set name = 'T-90' where id = 1;

#меняем название пистолета всем ручным оружиям с айдишниками от 1 до 6
update small_arms set name = 'Strike_One' where id > 0 and id < 7;

#в танках меняем офицеров с айди 2 на айди + 1
update tanks set officer_id = officer_id + 1 where id = 2;

#меняем тип танков с айдишниками от 2 до 5
update tanks set type = 'Light_tank' where id > 1 and id < 6;

#меняем название танков с айдишниками от 2 до 5
update tanks set name = 'T-64' where id > 1 and id < 6;

#меняем айди личного оружия офицера 
update officers set small_arm_id = 20 where id = 1;

#меняем тип танков с айдишниками 6+
update tanks set type = 'essences_tank' where id > 5;

#меняем название танков с айдишниками 6+
update tanks set name = 'T-34' where id > 5;

# удаляем танк с айдишником 8
delete from tanks where id = 8;

#удаляем танки с айдишниками от 6 до 7
delete from tanks where id > 5 and id < 8;

# удаляем танк с айдишником 5 и именем т64
delete from tanks where id = 5 and name = 'T-64';

#удаляем все танки с типом лёгкий танк
delete from tanks where type = 'Light_tank';

# в 5 строках ниже выводим все колонки таблиц офицеров, солдат, личного оружия, тяжёлого оружия, наград
select * from officers;
select * from soldiers;
select * from small_arms;
select * from heavy_weapons;
select * from rewards;

# вывожу 4 колонки из таблицы солдат
select military_badge, first_name, last_name, demobilization from soldiers;

#вывожу 5 колонок из таблиц солдат но только где id тяжёлого оружия пустое
select military_badge, first_name, last_name, demobilization, heavy_weapon_id from soldiers where heavy_weapon_id is null;

#вывожу 5 колонок из таблиц солдат но только где id тяжёлого оружия не пустое
select military_badge, first_name, last_name, demobilization, heavy_weapon_id from soldiers where heavy_weapon_id is not null;

#вывожу 4 колонки из таблицы солдат, но только где даты демобилизации соотвествуют 1 из условий
select military_badge, first_name, last_name, demobilization from soldiers where demobilization = '2022-05-01' or demobilization = '2022-08-01';

#аналогичный вывод вышестоящему селекту, но записанный компактным образом через in
select military_badge, first_name, last_name, demobilization from soldiers where demobilization in ('2022-05-01', '2022-08-01');

#вывод 4 колонок из таблицы солдат, в которых значение демобилизации соответствует 1 из 2 значений и при этом значение heavy_weapon_id is null
select military_badge, first_name, last_name, demobilization, heavy_weapon_id from soldiers 
	where demobilization = '2022-05-01' or demobilization = '2022-03-01' and heavy_weapon_id is null;

#вывод 4 колонок из таблицы солдат, упорядоченных по колонке first_name
select military_badge, first_name, last_name, demobilization from soldiers order by first_name;

#вывод таблицы с колонками из таблиц офицеров и солдат. строки из левой и правой таблиц сопоставляются, и только потом выводятся.
select o.id as officers_id, o.first_name, o.last_name, 
s.id as soldiers_id, s.first_name, s.last_name 
from officers o inner join soldiers s on s.officer_id = o.id;

#вывод таблицы с колонками из таблиц тяжёлого оружия и солдат. строки из левой таблицы выволдятся, даже если они не соответствуют правой таблице.
select s.id as soldiers_id, s.first_name, s.last_name,
h.id as heavy_weapon_id, h.name, h.number
from soldiers s left join heavy_weapons h on s.heavy_weapon_id = h.id;

#вывод колонок из 3 таблиц. сначала выводятся колонки из 2 таблиц, потом к ним присоединяется третья
select g.id as general_id, g.first_name, g.last_name,
o.id as officers_id, o.general_id, o.first_name, o.last_name, 
s.id as soldiers_id, s.first_name, s.last_name 
from officers o left join generals g on o.general_id = g.id
left join soldiers s on s.officer_id = o.id;

#группирую по колонке heavy_weapon_id, создаю 2 строки, в которые вывожу сколько солдат имеют тяжёлое оружие, а сколько нет. потом с помощью хевинг отсортировываю строки по условию count(*) > 4
SELECT
IF(isnull(s.heavy_weapon_id), 'no weapon', 'has weapon') as having_weapon,
COUNT(*) as sum
FROM soldiers s 
GROUP BY isnull(s.heavy_weapon_id) having count(*) > 4;

#селект в котором удаляются все дубликаты имен и выводятся уникальные имена офицеров и солдат
select s.first_name from soldiers s 
union
select o.first_name from officers o;

#здесь дубликаты не удаляются и выводятся просто все имена офицеров и солдат, даже если они дублируются
select s.first_name from soldiers s 
union All
select o.first_name from officers o;



