/*********************************************************
 * ��������ǰ �ȴ���Graduation Project Management System���� 
 * ��ռ��ʼ���ű���ϵͳ�ʱ��ʼ��ִ��
 * 1.0.0 cl.wang 20170124 ��������ʼ�汾 
 * ���ݿ��ַ���˿ڣ�127.0.0.1:1521 ʵ����ORCL
 */
 
 /*1����ռ䴴��
  *      GPMS_TABLE
  *      GPMS_INDEX
  *2�����滻��ռ�·������С����ʵ��Ҫ����д
  */
CREATE TABLESPACE "GPMS_TABLE"  LOGGING DATAFILE 'd:/oracle/oradata/GPMS_TABLE.ora' SIZE 1000M autoextend on next 100M;
CREATE TABLESPACE "GPMS_INDEX"  LOGGING DATAFILE 'd:/oracle/oradata/GPMS_INDEX.ora' SIZE 100M autoextend on next 100M;
/* �û������ű��������ʹ��DCT��׼�⣬�����û�����
 * 01-�����û�����׼�棩.sql
 */
