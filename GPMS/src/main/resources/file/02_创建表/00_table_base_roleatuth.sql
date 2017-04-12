--BASE_ROLE_ATUTH 角色权限表 
--ROLE_ID    	  角色编号
--MENU_CD  		  菜单编号

declare
  tCount pls_integer := 0;
begin
  select count(*)
    into tCount
    from user_tables ut
   where ut.TABLE_NAME = 'BASE_ROLE_ATUTH';
  if tCount = 0 then
    execute immediate '
	create table BASE_ROLE_ATUTH 
	(
	   ROLE_ID             NUMBER(10) not null,
	   MENU_CD  	       VARCHAR2(50) not null,
	   constraint PK_BASE_ROLE_ATUTH primary key (ROLE_ID,MENU_CD)
	)
	tablespace GPMS_TABLE
  ';
  end if;
end;
/
COMMENT ON table BASE_ROLE_ATUTH IS '角色权限表';
comment on column BASE_ROLE_ATUTH.ROLE_ID  is '角色编号';
comment on column BASE_ROLE_ATUTH.MENU_CD is '菜单编号';