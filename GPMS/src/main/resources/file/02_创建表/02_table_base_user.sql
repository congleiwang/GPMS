--BASE_USER  �û���
	--USER_ID	NUMBER(10)	�û�ID	
	--ORG_ID	NUMBER(10)	����ID	
	--USER_NM	VARCHAR2(64)	�û���	
	--LOGIN_NM	VARCHAR2(32)	��½��	
	--PASSWD	VARCHAR2(255)	����	
	--passwd_err	NUMBER(10)	����������	
	--PHONE_NUM	VARCHAR2(20)	��ϵ�绰	
	--ADDRESS	VARCHAR2(128)	��ַ	
	--ip_addr	VARCHAR2(20)	ip��ַ	
	--EMAIL	VARCHAR2(255)	�ʼ�	
	--is_LOCK	VARCHAR2(16)	״̬��1:���� 0:ͣ��	
	--sign_at	TIMESTAMP(6)	ע��ʱ��
	--last_login_at	TIMESTAMP(6)	���һ�ε�½ʱ��
	--REMARK	VARCHAR2(1000)	��ע	
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