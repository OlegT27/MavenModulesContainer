CREATE TABLE public.USERS(
   USER_ID 			   SERIAL PRIMARY KEY     NOT NULL,
   USER_NAME           CHAR(50),    		
   USER_SNAME          CHAR(50),    		
   USER_PATR      	   CHAR(50),
   USER_BDATE		   DATE,
   USER_EXIST          BOOLEAN				DEFAULT TRUE
);
