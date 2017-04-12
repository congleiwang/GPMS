--BASE_ROLE ��ɫ��
	--ROLE_ID	NUMBER(10)	��ɫid	
	--ROLE_NM	VARCHAR2(100)	��ɫ��	
	--ROLE_TYPE	CHAR(1)	��ɫ���ͣ�1��ѧ����2��ʦ��3����Ա��	
	--REMARK	VARCHAR2(100)	��ע	
	--is_Use    CHAR(1) �Ƿ�����
	--CREATOR	NUMBER(10)	������	
	--CREATE_AT	TIMESTAMP(6)	����ʱ��	
	--MODER	VARCHAR2(100)	����޸���	
	--MOD_COUNT	NUMBER(10)	�޸Ĵ���	
	--MOD_AT	TIMESTAMP(6)	����޸�ʱ��	
	--IS_DEL	VARCHAR2(16)	�Ƿ�ɾ����1��ɾ�� 0δɾ��	


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