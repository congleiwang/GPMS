--BASE_DICTIONARY 数据字典表
	--SYS_NM    	系统名称	
	--KEY_NO      	字典编号	
	--KEY_VALUE   	字典值		
	--CAPTION    	中文名称	
	--is_Use	CHAR(1) 是否启用
	--IS_MOD    	修改标识	
	--REMARK       	备注		
	--ENGLISH    	英文名称	
	--ORDER_BY     	顺序号		

declare
  tCount pls_integer := 0;
begin
  select count(*)
    into tCount
    from user_tables ut
   where ut.TABLE_NAME = 'BASE_DICTIONARY';
  if tCount = 0 then
    execute immediate '
    create table BASE_DICTIONARY 
    (
      SYS_NM  VARCHAR2(8)  not null,
      KEY_NO  VARCHAR2(30)  not null,
      KEY_VALUE  VARCHAR2(64) not null,
      CAPTION  VARCHAR2(255),
      IS_MOD  CHAR(1),
	  is_Use	CHAR(1),
      REMARK  VARCHAR2(1000),
      ENGLISH  VARCHAR2(60),
      ORDER_BY  NUMBER,
      constraint PK_BASE_DICTIONARY primary key (SYS_NM, KEY_NO, KEY_VALUE)
    )
    tablespace GPMS_TABLE
  ';
  end if;
end;
/
