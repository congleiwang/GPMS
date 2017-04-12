--BASE_ROLE 角色表
	--ROLE_ID	NUMBER(10)	角色id	
	--ROLE_NM	VARCHAR2(100)	角色名	
	--ROLE_TYPE	CHAR(1)	角色类型（1：学生、2教师、3管理员）	
	--REMARK	VARCHAR2(100)	备注	
	--is_Use    CHAR(1) 是否启用
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
   where ut.TABLE_NAME = 'BASE_ROLE';
  if tCount = 0 then
    execute immediate '
    create table BASE_ROLE 
	(
		ROLE_ID	NUMBER(10)  not null,
		ROLE_NM	VARCHAR2(100),
		ROLE_TYPE	CHAR(1),
		REMARK	VARCHAR2(100),
		is_Use    CHAR(1),
		CREATOR	NUMBER(10),
		CREATE_AT	TIMESTAMP(6),
		MODER	VARCHAR2(100),
		MOD_COUNT	NUMBER(10),
		MOD_AT	TIMESTAMP(6),
		IS_DEL	VARCHAR2(16),
	    constraint PK_BASE_ROLE primary key (ROLE_ID)
	)
    tablespace GPMS_TABLE
  ';
  end if;
  select count(*) into tCount from user_sequences us where us.sequence_name = 'SEQ_BASE_ROLE';
  if tCount = 0 then
     execute immediate 'create sequence SEQ_BASE_ROLE minvalue 1 maxvalue 9999999999 start with 1 increment by 1';
  end if;
end;
/