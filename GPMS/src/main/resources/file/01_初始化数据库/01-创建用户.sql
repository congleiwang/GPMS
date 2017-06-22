spool d:\create_gpms_log.log

--1、以DBA身份登录后创建用户frame,实际创建需替换
create user  gpms identified by gpms
default tablespace GPMS_TABLE
temporary tablespace temp
profile default
--限制配额
quota unlimited on GPMS_TABLE
quota unlimited on GPMS_INDEX;

-- grant/revoke role privileges 
grant connect to gpms;
grant resource to gpms;

-- grant/revoke system privileges 
grant alter session to gpms;
grant create procedure to gpms;
grant create sequence to gpms;
grant create session to gpms;
grant create synonym to gpms;
grant create table to gpms;
grant create trigger to gpms;
grant create type to gpms;
grant create view to gpms;
grant create any index to gpms;
grant create any procedure to gpms;
grant create any table to gpms;
grant create any trigger to gpms;
grant create any type to gpms;
grant create database link to gpms;
grant drop any index to gpms;
grant drop any table to gpms;
grant execute on dbms_lock to gpms;
grant execute on dbms_pipe to gpms;
grant insert any table to gpms;
grant query rewrite to gpms;
grant unlimited tablespace to gpms; 
grant execute any procedure  to gpms;