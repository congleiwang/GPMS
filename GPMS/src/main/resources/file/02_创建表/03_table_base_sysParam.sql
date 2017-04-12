-- BASE_SYS_PARAM 系统参数表
  --SYS_NM  VARCHAR2(8)  系统名称
  --PARAM_KEY  VARCHAR2(50)  参数项
  --PARAM_VALUE  VARCHAR2(200)  参数值
  --TYPE  VARCHAR2(2)  类型
  --VALUE_BOUND  VARCHAR2(1000)  值域
  --PARAM_DESC  VARCHAR2(1000)  参数描述
  --ORDER_BY  NUMBER  顺序


declare
  tCount pls_integer := 0;
begin
  select count(*)
    into tCount
    from user_tables ut
   where ut.TABLE_NAME = 'BASE_SYS_PARAM';
  if tCount = 0 then
    execute immediate 'create table BASE_SYS_PARAM
    (
      SYS_NM       VARCHAR2(8) not null,
      PARAM_KEY VARCHAR2(20) not null,
      PARAM_VALUE      VARCHAR2(50),
      TYPE          VARCHAR2(2),
      VALUE_BOUND    VARCHAR2(1000),
      PARAM_DESC VARCHAR2(1000),
		  ORDER_BY         NUMBER,
		  constraint PK_BASE_SYS_PARAM primary key (SYS_NM,PARAM_KEY)
		)
		tablespace GPMS_TABLE
	';
  end if;
end;
/