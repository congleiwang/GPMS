/*********************************************************
 * 构建环境前 先创建Graduation Project Management System环境 
 * 表空间初始化脚本；系统搭建时初始化执行
 * 1.0.0 cl.wang 20170124 创建，初始版本 
 * 数据库地址及端口：127.0.0.1:1521 实例：ORCL
 */
 
 /*1、表空间创建
  *      GPMS_TABLE
  *      GPMS_INDEX
  *2、需替换表空间路径，大小根据实际要求填写
  */
CREATE TABLESPACE "GPMS_TABLE"  LOGGING DATAFILE 'd:/oracle/oradata/GPMS_TABLE.ora' SIZE 1000M autoextend on next 100M;
CREATE TABLESPACE "GPMS_INDEX"  LOGGING DATAFILE 'd:/oracle/oradata/GPMS_INDEX.ora' SIZE 100M autoextend on next 100M;
/* 用户创建脚本【如果是使用DCT标准库，共用用户名】
 * 01-创建用户（标准版）.sql
 */
