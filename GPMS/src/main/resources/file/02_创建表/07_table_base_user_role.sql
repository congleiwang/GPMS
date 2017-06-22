--BASE_USER_ROLE 用户角色 
	--USER_ID  用户编号
	--ROLE_ID  角色编号

declare
  tCount pls_integer := 0;
begin
  select count(*)
    into tCount
    from user_tables ut
   where ut.TABLE_NAME = 'BASE_USER_ROLE';
  if tCount = 0 then
    execute immediate '
		create table BASE_USER_ROLE 
		(
		   USER_ID             NUMBER(10) not null,
		   ROLE_ID             NUMBER(10) not null,
           constraint PK_BASE_USER_ROLE primary key (USER_ID,ROLE_ID)		   
		)
		tablespace GPMS_TABLE
	';
  end if;
end;
/