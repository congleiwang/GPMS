--BASE_ROLE_ATUTH ��ɫȨ�ޱ� 
--ROLE_ID    	  ��ɫ���
--MENU_CD  		  �˵����

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
COMMENT ON table BASE_ROLE_ATUTH IS '��ɫȨ�ޱ�';
comment on column BASE_ROLE_ATUTH.ROLE_ID  is '��ɫ���';
comment on column BASE_ROLE_ATUTH.MENU_CD is '�˵����';