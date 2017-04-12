--BASE_USER  用户表
	--USER_ID	NUMBER(10)	用户ID	
	--ORG_ID	NUMBER(10)	机构ID	
	--USER_NM	VARCHAR2(64)	用户名	
	--LOGIN_NM	VARCHAR2(32)	登陆名	
	--PASSWD	VARCHAR2(255)	密码	
	--passwd_err	NUMBER(10)	密码错误次数	
	--PHONE_NUM	VARCHAR2(20)	联系电话	
	--ADDRESS	VARCHAR2(128)	地址	
	--ip_addr	VARCHAR2(20)	ip地址	
	--EMAIL	VARCHAR2(255)	邮件	
	--is_LOCK	VARCHAR2(16)	状态：1:正常 0:停用	
	--sign_at	TIMESTAMP(6)	注册时间
	--last_login_at	TIMESTAMP(6)	最后一次登陆时间
	--REMARK	VARCHAR2(1000)	备注	
	--CREATOR	NUMBER(10)	创建人	
	--CREATE_AT	TIMESTAMP(6)	创建时间	
	--MODER	VARCHAR2(100)	最近修改人	
	--MOD_COUNT	NUMBER(10)	修改次数	
	--MOD_AT	TIMESTAMP(6)	最近修改时间	
	--IS_DEL	VARCHAR2(16)	是否删除：1已删除 0未删除	



declare
  tCount pls_integer := 0;
begin
  select count(*)
    into tCount
    from user_tables ut
   where ut.TABLE_NAME = 'BASE_USER';
  if tCount = 0 then
    execute immediate '
    create table BASE_USER 
	(
		USER_ID	NUMBER(10) not null,
		ORG_ID	NUMBER(10),
		USER_NM	VARCHAR2(64),
		LOGIN_NM	VARCHAR2(32) not null,
		PASSWD	VARCHAR2(255) not null,
		passwd_err NUMBER(10),
		PHONE_NUM	VARCHAR2(20),
		ADDRESS	VARCHAR2(128),
		ip_addr	VARCHAR2(20),
		EMAIL	VARCHAR2(255),
		is_LOCK	VARCHAR2(16),
		sign_at	TIMESTAMP(6),
		last_login_at TIMESTAMP(6),
		REMARK	VARCHAR2(1000),
		CREATOR	NUMBER(10),
		CREATE_AT	TIMESTAMP(6),
		MODER	VARCHAR2(100),
		MOD_COUNT	NUMBER(10),
		MOD_AT	TIMESTAMP(6),
		IS_DEL	VARCHAR2(16),
	   constraint PK_BASE_USER primary key (USER_ID)
	)
    tablespace GPMS_TABLE
  ';
  end if;
  select count(*) into tCount from user_sequences us where us.sequence_name = 'SEQ_BASE_USER';
  if tCount = 0 then
     execute immediate 'create sequence SEQ_BASE_USER minvalue 1 maxvalue 9999999999 start with 1 increment by 1';
  end if;
end;
/