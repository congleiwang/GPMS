--登录记录(base_login_record)			
	--RECORD_ID	NUMBER(10)	记录id	
	--CREATE_AT	TIMESTAMP(6)	创建时间	
	--login_nm	VARCHAR2(255)	login_nm	
	--login_state	VARCHAR2(255)	登陆是否成功（1:成功,0:失败）	
	--ip_addr	VARCHAR2(255)	ip地址	



declare
  tCount pls_integer := 0;
begin
  select count(*)
    into tCount
    from user_tables ut
   where ut.TABLE_NAME = 'BASE_LOGIN_RECORD';
  if tCount = 0 then
    execute immediate '
    create table BASE_LOGIN_RECORD 
    (
      RECORD_ID	NUMBER(10) not null,
	  CREATE_AT	TIMESTAMP(6),
	  login_nm	VARCHAR2(255),
	  login_state	VARCHAR2(255),
	  ip_addr	VARCHAR2(255),
      constraint PK_BASE_LOGIN_RECORD primary key (RECORD_ID)
    )
    tablespace GPMS_TABLE
  ';
  end if;
   select count(*) into tCount from user_sequences us where us.sequence_name = 'SEQ_BASE_LOGIN_RECORD';
  if tCount = 0 then
     execute immediate 'create sequence SEQ_BASE_LOGIN_RECORD minvalue 1 maxvalue 9999999999 start with 1 increment by 1';
  end if;
end;
/