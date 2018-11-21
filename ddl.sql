--------------------------------------------------------
--  DDL for Table ALLCARS
--------------------------------------------------------

  CREATE TABLE "RDANGE"."ALLCARS" 
   (	"MAKE" VARCHAR2(250 BYTE), 
	"MODEL" VARCHAR2(250 BYTE), 
	"YEAR" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table BASICFAULTS
--------------------------------------------------------

  CREATE TABLE "RDANGE"."BASICFAULTS" 
   (	"BASIC_FID" VARCHAR2(250 BYTE), 
	"FAULTS" VARCHAR2(250 BYTE), 
	"DIAGNOSTIC" VARCHAR2(250 BYTE), 
	"DIAGNOSTIC_FEE" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table BASICTASK
--------------------------------------------------------

  CREATE TABLE "RDANGE"."BASICTASK" 
   (	"BASIC_TASKID" VARCHAR2(250 BYTE), 
	"TASK_NAME" VARCHAR2(250 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CAR
--------------------------------------------------------

  CREATE TABLE "RDANGE"."CAR" 
   (	"MAKE" VARCHAR2(20 BYTE), 
	"MODEL" VARCHAR2(20 BYTE), 
	"YEAR" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CUSTOMER
--------------------------------------------------------

  CREATE TABLE "RDANGE"."CUSTOMER" 
   (	"C_ID" VARCHAR2(250 BYTE), 
	"C_EMAIL" VARCHAR2(250 BYTE), 
	"C_ADDR" VARCHAR2(250 BYTE), 
	"C_TEL_NO" VARCHAR2(250 BYTE), 
	"C_NAME" VARCHAR2(250 BYTE), 
	"SC_ID" VARCHAR2(250 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table DISTRIBUTOR
--------------------------------------------------------

  CREATE TABLE "RDANGE"."DISTRIBUTOR" 
   (	"D_ID" VARCHAR2(250 BYTE), 
	"D_NAME" VARCHAR2(250 BYTE), 
	"D_ADDR" VARCHAR2(250 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table EMPLOYEE
--------------------------------------------------------

  CREATE TABLE "RDANGE"."EMPLOYEE" 
   (	"E_ID" VARCHAR2(250 BYTE), 
	"E_NAME" VARCHAR2(250 BYTE), 
	"E_EMAIL" VARCHAR2(250 BYTE), 
	"SC_ID" VARCHAR2(250 BYTE), 
	"E_ADDRESS" VARCHAR2(250 BYTE), 
	"E_TEL_NO" VARCHAR2(250 BYTE), 
	"E_ROLE" VARCHAR2(250 BYTE), 
	"START_DATE" DATE, 
	"COMPENSATION" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FAULTS
--------------------------------------------------------

  CREATE TABLE "RDANGE"."FAULTS" 
   (	"F_ID" VARCHAR2(250 BYTE), 
	"BASIC_FID" VARCHAR2(20 BYTE), 
	"BASIC_TASKID" VARCHAR2(250 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table INVENTORY
--------------------------------------------------------

  CREATE TABLE "RDANGE"."INVENTORY" 
   (	"I_ID" VARCHAR2(20 BYTE), 
	"SC_ID" VARCHAR2(250 BYTE), 
	"P_ID" VARCHAR2(250 BYTE), 
	"QUANTITY" NUMBER(*,0), 
	"THRESHOLD_QUANTITY" NUMBER(*,0), 
	"MIN_ORDER" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MAINTENANCE
--------------------------------------------------------

  CREATE TABLE "RDANGE"."MAINTENANCE" 
   (	"M_ID" VARCHAR2(250 BYTE), 
	"TYPE" VARCHAR2(250 BYTE), 
	"BASIC_TASKID" VARCHAR2(250 BYTE), 
	"MAKE" VARCHAR2(250 BYTE), 
	"MODEL" VARCHAR2(250 BYTE), 
	"MILEAGE" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MECHANIC
--------------------------------------------------------

  CREATE TABLE "RDANGE"."MECHANIC" 
   (	"E_ID" VARCHAR2(20 BYTE), 
	"HOURS" FLOAT(126), 
	"WORKD_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table NOTIFICATION
--------------------------------------------------------

  CREATE TABLE "RDANGE"."NOTIFICATION" 
   (	"N_ID" VARCHAR2(250 BYTE), 
	"SC_ID" VARCHAR2(250 BYTE), 
	"NOTIF_DATE" DATE, 
	"MESSAGE" VARCHAR2(250 BYTE), 
	"O_ID" VARCHAR2(250 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ORDERS
--------------------------------------------------------

  CREATE TABLE "RDANGE"."ORDERS" 
   (	"ORDER_PLACEMENT_DATE" TIMESTAMP (6), 
	"EXPECTED_DELIVERY_DATE" TIMESTAMP (6), 
	"ACTUAL_DELIVERY_DATE" TIMESTAMP (6), 
	"O_ID" VARCHAR2(250 BYTE), 
	"ORIGIN_D_ID" VARCHAR2(250 BYTE), 
	"ORIGIN_SC_ID" VARCHAR2(250 BYTE), 
	"P_ID" VARCHAR2(250 BYTE), 
	"DESTINATION_SC_ID" VARCHAR2(250 BYTE), 
	"QUANTITY" NUMBER(*,0), 
	"O_STATE" VARCHAR2(250 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PARTS
--------------------------------------------------------

  CREATE TABLE "RDANGE"."PARTS" 
   (	"P_ID" VARCHAR2(250 BYTE), 
	"P_NAME" VARCHAR2(250 BYTE), 
	"UNIT_PRICE" VARCHAR2(250 BYTE), 
	"WARRANTY_MONTHS" NUMBER(*,0), 
	"MAKE" VARCHAR2(250 BYTE), 
	"D1" NUMBER, 
	"D2" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PAYROLL
--------------------------------------------------------

  CREATE TABLE "RDANGE"."PAYROLL" 
   (	"E_ID" VARCHAR2(250 BYTE), 
	"E_ROLE" VARCHAR2(250 BYTE), 
	"START_DATE" DATE, 
	"EARNINGSYTD" NUMBER, 
	"PREVEARNINGS" NUMBER, 
	"PR_ID" NUMBER, 
	"LASTPAYCHECK" DATE, 
	"UNITS" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

  CREATE TABLE "RDANGE"."PERSON" 
   (	"USERID" VARCHAR2(250 BYTE), 
	"EMAILID" VARCHAR2(250 BYTE), 
	"PASSWORD" VARCHAR2(250 BYTE), 
	"ROLE" VARCHAR2(250 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SERVICE
--------------------------------------------------------

  CREATE TABLE "RDANGE"."SERVICE" 
   (	"SER_ID" VARCHAR2(250 BYTE), 
	"E_ID" VARCHAR2(250 BYTE), 
	"C_ID" VARCHAR2(250 BYTE), 
	"SC_ID" VARCHAR2(250 BYTE), 
	"LICENSE_NO" VARCHAR2(250 BYTE), 
	"END_TIME" TIMESTAMP (6), 
	"BASIC_FID" VARCHAR2(250 BYTE), 
	"MAINTENANCE_TYPE" VARCHAR2(250 BYTE), 
	"START_DATE" TIMESTAMP (6), 
	"LABORTIME" NUMBER, 
	"TOTALCOST" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SERVICECENTER
--------------------------------------------------------

  CREATE TABLE "RDANGE"."SERVICECENTER" 
   (	"SC_ID" VARCHAR2(250 BYTE), 
	"SC_NAME" VARCHAR2(250 BYTE), 
	"SC_TEL_NO" VARCHAR2(250 BYTE), 
	"SC_ADDRESS" VARCHAR2(250 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TASKS
--------------------------------------------------------

  CREATE TABLE "RDANGE"."TASKS" 
   (	"TASK_ID" VARCHAR2(250 BYTE), 
	"BASIC_TASKID" VARCHAR2(250 BYTE), 
	"CHARGE_RATE" VARCHAR2(250 BYTE), 
	"TIME_REQUIRED" FLOAT(126), 
	"P_ID" VARCHAR2(250 BYTE), 
	"PART_QUANTITY" NUMBER(*,0), 
	"MAKE" VARCHAR2(250 BYTE), 
	"MODEL" VARCHAR2(250 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TEST
--------------------------------------------------------

  CREATE TABLE "RDANGE"."TEST" 
   (	"ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table VEHICLE
--------------------------------------------------------

  CREATE TABLE "RDANGE"."VEHICLE" 
   (	"MODEL" VARCHAR2(250 BYTE), 
	"MAKE" VARCHAR2(250 BYTE), 
	"LICENSE_NO" VARCHAR2(250 BYTE), 
	"DATE_OF_PURCHASE" DATE, 
	"MILEAGE" FLOAT(126), 
	"C_ID" VARCHAR2(250 BYTE), 
	"YEAR" NUMBER(*,0), 
	"LAST_SERVICE_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table WAGE_TYPE
--------------------------------------------------------

  CREATE TABLE "RDANGE"."WAGE_TYPE" 
   (	"ROLE" VARCHAR2(250 BYTE), 
	"PAY_TYPE" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for View MECHPAYROLLINTER
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "RDANGE"."MECHPAYROLLINTER" ("E_ID", "PAYYTD", "SERVICE_START_DATE") AS 
  select
    service.e_id, employee.compensation * service.labortime AS payYTD, service.start_date
from 
    service, employee
where
    EXTRACT(YEAR FROM service.start_date) = EXTRACT(YEAR FROM sysdate)
    and
    employee.e_id = service.e_id
;
--------------------------------------------------------
--  DDL for View PAYROLL_INTER
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "RDANGE"."PAYROLL_INTER" ("E_ID", "E_ROLE", "DAYS_WORKED", "COMPENSATION") AS 
  SELECT
   employee.e_id, employee.e_role, CASE WHEN employee.start_date > TRUNC(TO_DATE('1-JAN-2018')) THEN ((TRUNC(sysdate) - employee.start_date ))/15
       ELSE (TRUNC(sysdate) - TRUNC(TO_DATE('1-JAN-2018')))/15 END AS days_worked, employee.compensation
FROM
    employee
;
--------------------------------------------------------
--  DDL for View TEMP
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "RDANGE"."TEMP" ("E_ID", "COMPENSATION", "LABORTIME", "START_DATE") AS 
  select distinct
    service.e_id, employee.compensation, service.labortime, service.start_date
from 
    service, employee
where
    employee.e_id = service.e_id
order by service.start_date desc

--------------------------------------------------------
--  DDL for Trigger TEST
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "RDANGE"."TEST" 
BEFORE DELETE OR INSERT OR UPDATE ON service
FOR EACH ROW 
DECLARE 
   serviceCost number; 
BEGIN 
   serviceCost := 0;
   dbms_output.put_line('Service Cost: ' || serviceCost); 
END;
/
ALTER TRIGGER "RDANGE"."TEST" ENABLE;
--------------------------------------------------------
--  DDL for Trigger UPDATE_DATE_AND_MILEAGE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "RDANGE"."UPDATE_DATE_AND_MILEAGE" 
AFTER INSERT ON SERVICE
FOR EACH ROW 
BEGIN 
UPDATE VEHICLE
SET LAST_SERVICE_DATE = :NEW.START_DATE
WHERE LICENSE_NO =:NEW.LICENSE_NO;
END;
/
ALTER TRIGGER "RDANGE"."UPDATE_DATE_AND_MILEAGE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger UPDATE_EMAIL
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "RDANGE"."UPDATE_EMAIL" 
AFTER UPDATE OF E_EMAIL ON EMPLOYEE
FOR EACH ROW 
BEGIN 
UPDATE PERSON
SET EMAILID = :NEW.E_EMAIL       
WHERE USERID =:NEW.E_ID;
END;
/
ALTER TRIGGER "RDANGE"."UPDATE_EMAIL" ENABLE;
--------------------------------------------------------
--  DDL for Function CHECKFORPALINDROME
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "RDANGE"."CHECKFORPALINDROME" (inputString VARCHAR2)
   RETURN VARCHAR2 
   IS result VARCHAR2(75);
   
   reversedString VARCHAR2(50); 
   BEGIN 
      SELECT REVERSE(inputString) INTO reversedString FROM DUAL;
            
      -- Using UPPER to ignore case sensitivity.
      IF UPPER(inputString) = UPPER(reversedString)
      THEN
      RETURN(inputString||' IS a palindrome.');
      END IF;
      RETURN (inputString||' IS NOT a palindrome.');
      
    END checkForPalindrome;

/
--------------------------------------------------------
--  DDL for Function FUNCTION1
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "RDANGE"."FUNCTION1" RETURN VARCHAR2 AS 
BEGIN
  RETURN NULL;
END FUNCTION1;

/
--------------------------------------------------------
--  DDL for Function TOTALSTRENGTH
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "RDANGE"."TOTALSTRENGTH" 
  
return integer 
as 
total integer:=0; 

begin                         

select sum(strength) into total from section; 
return total; 

end totalStrength; 

set serveroutput on; 

declare 
answer integer; 

begin 
answer:=totalstrength(); 
   dbms_output.put_line('Total strength of students is  ' || answer);   
end;

/
--------------------------------------------------------
--  Constraints for Table SERVICE
--------------------------------------------------------

  ALTER TABLE "RDANGE"."SERVICE" MODIFY ("SER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."SERVICE" MODIFY ("E_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."SERVICE" MODIFY ("C_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."SERVICE" MODIFY ("SC_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."SERVICE" MODIFY ("LICENSE_NO" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."SERVICE" ADD PRIMARY KEY ("SER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "RDANGE"."SERVICE" MODIFY ("START_DATE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ORDERS
--------------------------------------------------------

  ALTER TABLE "RDANGE"."ORDERS" MODIFY ("O_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."ORDERS" MODIFY ("P_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."ORDERS" MODIFY ("DESTINATION_SC_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."ORDERS" MODIFY ("QUANTITY" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."ORDERS" MODIFY ("O_STATE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."ORDERS" ADD PRIMARY KEY ("O_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table PAYROLL
--------------------------------------------------------

  ALTER TABLE "RDANGE"."PAYROLL" MODIFY ("E_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."PAYROLL" MODIFY ("E_ROLE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."PAYROLL" MODIFY ("START_DATE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "RDANGE"."PERSON" MODIFY ("USERID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."PERSON" MODIFY ("EMAILID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."PERSON" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."PERSON" MODIFY ("ROLE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."PERSON" ADD PRIMARY KEY ("USERID", "EMAILID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CUSTOMER
--------------------------------------------------------

  ALTER TABLE "RDANGE"."CUSTOMER" MODIFY ("C_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."CUSTOMER" MODIFY ("C_EMAIL" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."CUSTOMER" MODIFY ("C_ADDR" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."CUSTOMER" MODIFY ("C_TEL_NO" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."CUSTOMER" MODIFY ("C_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."CUSTOMER" MODIFY ("SC_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."CUSTOMER" ADD PRIMARY KEY ("C_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table VEHICLE
--------------------------------------------------------

  ALTER TABLE "RDANGE"."VEHICLE" MODIFY ("MODEL" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."VEHICLE" MODIFY ("MAKE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."VEHICLE" MODIFY ("LICENSE_NO" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."VEHICLE" MODIFY ("DATE_OF_PURCHASE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."VEHICLE" MODIFY ("MILEAGE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."VEHICLE" MODIFY ("C_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."VEHICLE" MODIFY ("YEAR" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."VEHICLE" ADD PRIMARY KEY ("LICENSE_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table EMPLOYEE
--------------------------------------------------------

  ALTER TABLE "RDANGE"."EMPLOYEE" MODIFY ("E_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."EMPLOYEE" MODIFY ("E_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."EMPLOYEE" MODIFY ("E_EMAIL" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."EMPLOYEE" MODIFY ("SC_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."EMPLOYEE" MODIFY ("E_ADDRESS" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."EMPLOYEE" MODIFY ("E_TEL_NO" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."EMPLOYEE" MODIFY ("E_ROLE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."EMPLOYEE" MODIFY ("START_DATE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."EMPLOYEE" MODIFY ("COMPENSATION" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."EMPLOYEE" ADD PRIMARY KEY ("E_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table NOTIFICATION
--------------------------------------------------------

  ALTER TABLE "RDANGE"."NOTIFICATION" MODIFY ("N_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."NOTIFICATION" MODIFY ("SC_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."NOTIFICATION" MODIFY ("NOTIF_DATE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."NOTIFICATION" MODIFY ("MESSAGE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."NOTIFICATION" ADD PRIMARY KEY ("N_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table TASKS
--------------------------------------------------------

  ALTER TABLE "RDANGE"."TASKS" MODIFY ("TASK_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."TASKS" MODIFY ("BASIC_TASKID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."TASKS" MODIFY ("CHARGE_RATE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."TASKS" MODIFY ("TIME_REQUIRED" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."TASKS" MODIFY ("P_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."TASKS" MODIFY ("PART_QUANTITY" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."TASKS" MODIFY ("MAKE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."TASKS" MODIFY ("MODEL" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."TASKS" ADD PRIMARY KEY ("TASK_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BASICFAULTS
--------------------------------------------------------

  ALTER TABLE "RDANGE"."BASICFAULTS" MODIFY ("BASIC_FID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."BASICFAULTS" MODIFY ("FAULTS" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."BASICFAULTS" MODIFY ("DIAGNOSTIC" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."BASICFAULTS" MODIFY ("DIAGNOSTIC_FEE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."BASICFAULTS" ADD PRIMARY KEY ("BASIC_FID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table INVENTORY
--------------------------------------------------------

  ALTER TABLE "RDANGE"."INVENTORY" MODIFY ("I_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."INVENTORY" MODIFY ("SC_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."INVENTORY" MODIFY ("P_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."INVENTORY" MODIFY ("QUANTITY" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."INVENTORY" MODIFY ("THRESHOLD_QUANTITY" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."INVENTORY" MODIFY ("MIN_ORDER" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."INVENTORY" ADD PRIMARY KEY ("I_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table BASICTASK
--------------------------------------------------------

  ALTER TABLE "RDANGE"."BASICTASK" MODIFY ("BASIC_TASKID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."BASICTASK" MODIFY ("TASK_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."BASICTASK" ADD PRIMARY KEY ("BASIC_TASKID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ALLCARS
--------------------------------------------------------

  ALTER TABLE "RDANGE"."ALLCARS" MODIFY ("MAKE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."ALLCARS" MODIFY ("MODEL" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."ALLCARS" MODIFY ("YEAR" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table WAGE_TYPE
--------------------------------------------------------

  ALTER TABLE "RDANGE"."WAGE_TYPE" MODIFY ("ROLE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."WAGE_TYPE" MODIFY ("PAY_TYPE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAULTS
--------------------------------------------------------

  ALTER TABLE "RDANGE"."FAULTS" MODIFY ("F_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."FAULTS" MODIFY ("BASIC_FID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."FAULTS" MODIFY ("BASIC_TASKID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."FAULTS" ADD PRIMARY KEY ("F_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CAR
--------------------------------------------------------

  ALTER TABLE "RDANGE"."CAR" MODIFY ("MAKE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."CAR" MODIFY ("MODEL" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."CAR" MODIFY ("YEAR" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PARTS
--------------------------------------------------------

  ALTER TABLE "RDANGE"."PARTS" MODIFY ("P_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."PARTS" MODIFY ("P_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."PARTS" MODIFY ("UNIT_PRICE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."PARTS" MODIFY ("MAKE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."PARTS" ADD PRIMARY KEY ("P_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SERVICECENTER
--------------------------------------------------------

  ALTER TABLE "RDANGE"."SERVICECENTER" MODIFY ("SC_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."SERVICECENTER" MODIFY ("SC_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."SERVICECENTER" MODIFY ("SC_TEL_NO" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."SERVICECENTER" MODIFY ("SC_ADDRESS" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."SERVICECENTER" ADD PRIMARY KEY ("SC_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MAINTENANCE
--------------------------------------------------------

  ALTER TABLE "RDANGE"."MAINTENANCE" MODIFY ("M_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."MAINTENANCE" MODIFY ("TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."MAINTENANCE" MODIFY ("BASIC_TASKID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."MAINTENANCE" MODIFY ("MAKE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."MAINTENANCE" MODIFY ("MODEL" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."MAINTENANCE" MODIFY ("MILEAGE" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."MAINTENANCE" ADD PRIMARY KEY ("M_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MECHANIC
--------------------------------------------------------

  ALTER TABLE "RDANGE"."MECHANIC" MODIFY ("E_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."MECHANIC" MODIFY ("HOURS" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DISTRIBUTOR
--------------------------------------------------------

  ALTER TABLE "RDANGE"."DISTRIBUTOR" MODIFY ("D_ID" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."DISTRIBUTOR" MODIFY ("D_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."DISTRIBUTOR" MODIFY ("D_ADDR" NOT NULL ENABLE);
 
  ALTER TABLE "RDANGE"."DISTRIBUTOR" ADD PRIMARY KEY ("D_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CUSTOMER
--------------------------------------------------------

  ALTER TABLE "RDANGE"."CUSTOMER" ADD FOREIGN KEY ("SC_ID")
    REFERENCES "RDANGE"."SERVICECENTER" ("SC_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EMPLOYEE
--------------------------------------------------------

  ALTER TABLE "RDANGE"."EMPLOYEE" ADD FOREIGN KEY ("SC_ID")
    REFERENCES "RDANGE"."SERVICECENTER" ("SC_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAULTS
--------------------------------------------------------

  ALTER TABLE "RDANGE"."FAULTS" ADD FOREIGN KEY ("BASIC_TASKID")
    REFERENCES "RDANGE"."BASICTASK" ("BASIC_TASKID") ENABLE;
 
  ALTER TABLE "RDANGE"."FAULTS" ADD FOREIGN KEY ("BASIC_FID")
    REFERENCES "RDANGE"."BASICFAULTS" ("BASIC_FID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INVENTORY
--------------------------------------------------------

  ALTER TABLE "RDANGE"."INVENTORY" ADD FOREIGN KEY ("P_ID")
    REFERENCES "RDANGE"."PARTS" ("P_ID") ENABLE;
 
  ALTER TABLE "RDANGE"."INVENTORY" ADD FOREIGN KEY ("SC_ID")
    REFERENCES "RDANGE"."SERVICECENTER" ("SC_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MAINTENANCE
--------------------------------------------------------

  ALTER TABLE "RDANGE"."MAINTENANCE" ADD FOREIGN KEY ("BASIC_TASKID")
    REFERENCES "RDANGE"."BASICTASK" ("BASIC_TASKID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table NOTIFICATION
--------------------------------------------------------

  ALTER TABLE "RDANGE"."NOTIFICATION" ADD FOREIGN KEY ("SC_ID")
    REFERENCES "RDANGE"."SERVICECENTER" ("SC_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ORDERS
--------------------------------------------------------

  ALTER TABLE "RDANGE"."ORDERS" ADD FOREIGN KEY ("ORIGIN_SC_ID")
    REFERENCES "RDANGE"."SERVICECENTER" ("SC_ID") ENABLE;
 
  ALTER TABLE "RDANGE"."ORDERS" ADD FOREIGN KEY ("ORIGIN_D_ID")
    REFERENCES "RDANGE"."DISTRIBUTOR" ("D_ID") ENABLE;
 
  ALTER TABLE "RDANGE"."ORDERS" ADD FOREIGN KEY ("DESTINATION_SC_ID")
    REFERENCES "RDANGE"."SERVICECENTER" ("SC_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SERVICE
--------------------------------------------------------

  ALTER TABLE "RDANGE"."SERVICE" ADD FOREIGN KEY ("BASIC_FID")
    REFERENCES "RDANGE"."BASICFAULTS" ("BASIC_FID") ENABLE;
 
  ALTER TABLE "RDANGE"."SERVICE" ADD FOREIGN KEY ("E_ID")
    REFERENCES "RDANGE"."EMPLOYEE" ("E_ID") ENABLE;
 
  ALTER TABLE "RDANGE"."SERVICE" ADD FOREIGN KEY ("C_ID")
    REFERENCES "RDANGE"."CUSTOMER" ("C_ID") ENABLE;
 
  ALTER TABLE "RDANGE"."SERVICE" ADD FOREIGN KEY ("SC_ID")
    REFERENCES "RDANGE"."SERVICECENTER" ("SC_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TASKS
--------------------------------------------------------

  ALTER TABLE "RDANGE"."TASKS" ADD FOREIGN KEY ("BASIC_TASKID")
    REFERENCES "RDANGE"."BASICTASK" ("BASIC_TASKID") ENABLE;
 
  ALTER TABLE "RDANGE"."TASKS" ADD FOREIGN KEY ("P_ID")
    REFERENCES "RDANGE"."PARTS" ("P_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table VEHICLE
--------------------------------------------------------

  ALTER TABLE "RDANGE"."VEHICLE" ADD FOREIGN KEY ("C_ID")
    REFERENCES "RDANGE"."CUSTOMER" ("C_ID") ENABLE;
