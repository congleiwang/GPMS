--BASE_ORG   机构表
  --ORG_ID  NUMBER(10)  机构id  
  --PORG_ID  VARCHAR2(40)  父id  
  --ORG_TYPE  VARCHAR2(1)  机构类型  
  --ORG_NM  VARCHAR2(100)  机构名  
  --ORG_DESC  VARCHAR2(255)  机构描述  
  --ADDRESS  VARCHAR2(255)  地址 
  --is_Use	VARCHAR2(16) 是否锁定  
  --CREATOR  NUMBER(10)  创建人  
  --CREATE_AT  TIMESTAMP(6)  创建时间  
  --MODER  VARCHAR2(100)  最近修改人  
  --MOD_COUNT  NUMBER(10)  修改次数  
  --MOD_AT  TIMESTAMP(6)  最近修改时间  
  --IS_DEL  VARCHAR2(16)  是否删除：1已删除 0未删除  


declare
  tCount pls_integer := 0;
begin
  select count(*)
    into tCount
    from user_tables ut
   where ut.TABLE_NAME = 'BASE_ORG';
  if tCount = 0 then
    execute immediate '
    create table BASE_ORG 
    (
      ORG_ID  NUMBER(10) not null,
      PORG_ID  VARCHAR2(40),
      ORG_TYPE  VARCHAR2(1),
      ORG_NM  VARCHAR2(100),
      ORG_DESC  VARCHAR2(255),
      ADDRESS  VARCHAR2(255),
	  is_Use	CHAR(1),
      CREATOR  NUMBER(10),
      CREATE_AT  TIMESTAMP(6),
      MODER  VARCHAR2(100),
      MOD_COUNT  NUMBER(10),
      MOD_AT  TIMESTAMP(6),
      IS_DEL  CHAR(1),
       constraint PK_BASE_ORG primary key (ORG_ID)
    )
    tablespace GPMS_TABLE
  ';
  end if;
  select count(*) into tCount from user_sequences us where us.sequence_name = 'SEQ_BASE_ORG';
  if tCount = 0 then
     execute immediate 'create sequence SEQ_BASE_ORG minvalue 1 maxvalue 9999999999 start with 1 increment by 1';
  end if;
end;
/