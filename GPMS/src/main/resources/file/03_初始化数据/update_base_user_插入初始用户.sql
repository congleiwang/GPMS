  delete from BASE_USER t where t.USER_ID=1 and LOGIN_NM='admin';
    insert into BASE_USER (USER_ID,ORG_ID,USER_NM,LOGIN_NM,PASSWD,passwd_err,PHONE_NUM,ADDRESS,ip_addr,EMAIL,
      is_LOCK,sign_at,last_login_at,REMARK,CREATOR,CREATE_AT,MODER,MOD_COUNT,MOD_AT,IS_DEL)
    values (1, 01, '系统管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e',0, null, '抚州', '127.0.0.1','123@ecit.com',
      '0',sysdate,sysdate,'系统管理员账号，拥有最大权限',1,sysdate,null,1,sysdate,'0');
    COMMIT;
/