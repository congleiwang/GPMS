--BASE_MENU    �˵���
	--SYS_NM	VARCHAR2(8)	ϵͳ����
	--MENU_CD	VARCHAR2(50)	�˵����
	--MENU_NM	VARCHAR2(50)	�˵�����
	--MENU_TYPE	CHAR(1)	�˵�����
	--MENU_URL	VARCHAR2(255)	�˵�url
	--PMENU_CD	VARCHAR2(50)	���˵����
	--remark	VARCHAR2(255)	�˵�����
	--ICON_FILE	VARCHAR2(20)	�˵�ͼƬ
	--ORDer_by	NUMBER(10)	����
	--IS_USE	CHAR(1)	�Ƿ�����
	--MENU_CONTROLLER	VARCHAR2(100)	�˵�������
	--MENU_VIEW	VARCHAR2(100)	�˵�URL


declare
  tCount pls_integer := 0;
begin
  select count(*)
    into tCount
    from user_tables ut
   where ut.TABLE_NAME = 'BASE_MENU';
  if tCount = 0 then
    execute immediate '
	create table BASE_MENU 
	(
	   SYS_NM	VARCHAR2(8) not null,
		MENU_CD	VARCHAR2(50) not null,
		MENU_NM	VARCHAR2(50),
		MENU_TYPE	CHAR(1),
		MENU_URL	VARCHAR2(255),
		PMENU_CD	VARCHAR2(50),
		remark	VARCHAR2(255),
		ICON_FILE	VARCHAR2(20),
		ORDer_by	NUMBER(10),
		IS_USE	CHAR(1),
		MENU_CONTROLLER	VARCHAR2(100),
		MENU_VIEW	VARCHAR2(100),

	   constraint PK_BASE_MENU primary key (SYS_NM, MENU_CD)
	)
	tablespace GPMS_TABLE
	';
  end if;
end;
/