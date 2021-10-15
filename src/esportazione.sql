--------------------------------------------------------
--  DDL for Table PERSON
--------------------------------------------------------

CREATE TABLE employee(
    employee_id NUMBER(19),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50),
    CONSTRAINT employee_pk PRIMARY KEY (employee_id)
);

CREATE TABLE company(
    company_id NUMBER(19),
    name VARCHAR(50),
    CONSTRAINT company_pk PRIMARY KEY (company_id)
);

CREATE TABLE contract (
    contract_id NUMBER(19),
    company_id NUMBER(19),
    employee_id NUMBER(19),
    hire_date DATE,
    firing_date DATE,
    CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES company(company_id),
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    PRIMARY KEY (contract_id)
)


--------------------------------------------------------
--  DDL for STORED PROCEDURE
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure SP_DELETE_COMPANY
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_DELETE_COMPANY" (
    v_id company.company_id%TYPE
)
AS
BEGIN
    DELETE FROM COMPANY WHERE v_id = company.company_id;
END;


--------------------------------------------------------
--  DDL for Procedure SP_DELETE_CONTRACT
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_DELETE_CONTRACT" (
    v_id contract.contract_id%TYPE
)
AS
BEGIN
    DELETE FROM CONTRACT WHERE v_id = contract.contract_id;
END;


--------------------------------------------------------
--  DDL for Procedure SP_DELETE_EMPLOYEE
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_DELETE_EMPLOYEE" (
    v_id employee.employee_id%TYPE
)
AS
BEGIN
    DELETE FROM EMPLOYEE WHERE v_id = employee.employee_id;
END;


--------------------------------------------------------
--  DDL for Procedure SP_ORDER_COMPANY_IDS
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_ORDER_COMPANY_IDS" (
    v_id company.company_id%TYPE
) AS
    BEGIN
    UPDATE COMPANY SET company_id = company_id-1 WHERE company_id > v_id;
END;


--------------------------------------------------------
--  DDL for Procedure SP_ORDER_CONTRACT_IDS
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_ORDER_CONTRACT_IDS" (
    v_id contract.contract_id%TYPE
) AS
    BEGIN
    UPDATE CONTRACT SET contract_id = contract_id-1 WHERE contract_id > v_id;
END;


--------------------------------------------------------
--  DDL for Procedure SP_ORDER_EMPLOYEE_IDS
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_ORDER_EMPLOYEE_IDS" (
    v_id employee.employee_id%TYPE
) AS
    BEGIN
    UPDATE EMPLOYEE SET employee_id = employee_id-1 WHERE employee_id > v_id;
END;


--------------------------------------------------------
--  DDL for Procedure SP_SAVE_NEW_COMPANY
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_SAVE_NEW_COMPANY" (
    myname VARCHAR2
)
AS
BEGIN
    INSERT INTO COMPANY
    VALUES ((FN_MAX_ID_COMPANY + 1), myname);
END;


--------------------------------------------------------
--  DDL for Procedure SP_SAVE_NEW_CONTRACT
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_SAVE_NEW_CONTRACT" (
    mycompany_id NUMBER,
    myemployee_id NUMBER,
    myhire_date DATE,
    myfiring_date DATE
)
AS
BEGIN
    INSERT INTO CONTRACT
    VALUES ((FN_MAX_ID_CONTRACT + 1), mycompany_id, myemployee_id, myhire_date, myfiring_date);
END;


--------------------------------------------------------
--  DDL for Procedure SP_SAVE_NEW_EMPLOYEE
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_SAVE_NEW_EMPLOYEE" (
    myfirstname VARCHAR2,
    mylastname VARCHAR2,
    myemail VARCHAR2
)
AS
BEGIN
    INSERT INTO EMPLOYEE
    VALUES ((FN_MAX_ID_EMPLOYEE + 1), myfirstname, mylastname, myemail);
END;


--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_COMPANY
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_UPDATE_COMPANY" (
    v_id company.company_id%TYPE,
    v_name company.name%TYPE
)
AS
BEGIN
    UPDATE COMPANY SET name = v_name WHERE company_id = v_id;
END;


--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_CONTRACT
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_UPDATE_CONTRACT" (
    v_id contract.contract_id%TYPE,
    v_firedate contract.firing_date%TYPE
)
AS
BEGIN
    UPDATE CONTRACT
    SET firing_date = v_firedate
    WHERE contract_id = v_id;
END;


--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_EMPLOYEE
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE "SP_UPDATE_EMPLOYEE" (
    v_id employee.employee_id%TYPE,
    v_firstname employee.first_name%TYPE,
    v_lastname employee.last_name%TYPE,
    v_email employee.email%TYPE
)
AS
BEGIN
    UPDATE EMPLOYEE
    SET first_name = v_firstname, last_name = v_lastname, email = v_email
    WHERE employee_id = v_id;
END;




--------------------------------------------------------
--  DDL for Function FN_MAX_ID_COMPANY
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "FN_MAX_ID_COMPANY"
    RETURN company.company_id%TYPE
AS
    v_id company.company_id%TYPE;
    BEGIN
        SELECT NVL(MAX(cmp.company_id), 0) AS MAX_VAL
        INTO v_id
        FROM COMPANY cmp;
    RETURN v_id;
END;


--------------------------------------------------------
--  DDL for Function FN_MAX_ID_CONTRACT
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "FN_MAX_ID_CONTRACT"
    RETURN contract.contract_id%TYPE
AS
    v_id contract.contract_id%TYPE;
    BEGIN
        SELECT NVL(MAX(cnt.contract_id), 0) AS MAX_VAL
        INTO v_id
        FROM CONTRACT cnt;
    RETURN v_id;
END;


--------------------------------------------------------
--  DDL for Function FN_MAX_ID_EMPLOYEE
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "FN_MAX_ID_EMPLOYEE"
    RETURN employee.employee_id%TYPE
AS
    v_id employee.employee_id%TYPE;
    BEGIN
        SELECT NVL(MAX(emp.employee_id), 0) AS MAX_VAL
        INTO v_id
        FROM EMPLOYEE emp;
    RETURN v_id;
END;

