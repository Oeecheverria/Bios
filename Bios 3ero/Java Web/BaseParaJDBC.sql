DROP DATABASE IF EXISTS EjemploJDBC;

CREATE DATABASE EjemploJDBC;

USE EjemploJDBC;

CREATE TABLE Empleados (
	Cedula BIGINT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Edad INT,
    Sueldo DOUBLE,
    Casado BIT

);

CREATE TABLE Telefonos(
IdTel INT AUTO_INCREMENT,
NroTel VARCHAR (20) UNIQUE,
Empleado BIGINT,
PRIMARY KEY (IdTel),
FOREIGN KEY (Empleado) REFERENCES Empleados(Cedula)
);


INSERT INTO Empleados
VALUES
	(1, 'Juan', NULL, 1000, 0),
    (2, 'Andrea', 32, 1200, NULL),
    (3, 'Pablo', 56, NULL, 0);
    
INSERT INTO Telefonos
VALUES
	(NULL, '11111111', 1),
    (NULL, '22222221', 2),
    (NULL, '22222222', 2);
    
    
    -- Comentario de una linea
    /* Comentario de 
    varias lineas*/
    
    CREATE PROCEDURE ListarTodos()
    SELECT *
    FROM Empleados;
    
	-- CALL ListarTodos();
    
    CREATE PROCEDURE MayoresDe(IN pEdad INT)
    SELECT * 
    FROM Empleados
    WHERE Edad > pEdad;
    
 --  CALL MayoresDe(50);
    
 --   SHOW PROCEDURE STATUS WHERE Db = 'ejemploJDBC';
    
 --   SHOW CREATE PROCEDURE MayoresDe;
    
 --   DROP PROCEDURE MayoresDe;
    
    
    DELIMITER //
    CREATE PROCEDURE EliminarEmpleado(in  pCedula BIGINT)
    BEGIN 
			DELETE
            FROM Telefonos
            WHERE Empleado = pCedula;
    
    
    
		DELETE 
			FROM Empleados
			WHERE Empleado = pCedula;
    end // 
    DELIMITER ;
    
    
    select*
    from EMPLEADOS;
    
    
    
    CALL EliminarEmpelado(1);
    
    
    
    -- CALL Listartodos();
    
    
     CREATE PROCEDURE ObtenerMAyorSuelado(OUT pMayor DOUBLE)
     Select MAX(Sueldo)
     From Empleados
     INTO pMayor;
    

/*
SET @mayorSueldo = 0;
CALL ObtenerMAyorSuelado(@mayorSueldo);

Select @mayorSueldo;
*/

     CREATE PROCEDURE  PruebaIO(INOUT valor int )
     SET valor  = valor +1;
     
          SET @valor = 10;
      CALL PruebaIO(@valor);    
          
	Select @valor;


DELIMITER // 

CREATE FUNCTION Buscar(pCedula BIGINT )RETURNS VARCHAR (50)

BEGIN 
		DECLARE vNombre VARCHAR(50);
        
        SELECT Nombre
        FROM Empleados
        WHERE Cedula = pCedula
        INTO vNombre;
        
        RETURN vNombre;
        END //

        DELIMITER ;


-- SHOW function status WHERE Db = 'ejemplojdbc';


-- set @nombre = Buscar (2);

-- Select @nombre;

DROP PROCEDURE IF EXISTS EliminarEmpleado;


DELIMITER //
CREATE PROCEDURE EliminarEmpleado(pCedula BIGINT, out pError varchar(50))
cuerpo:begin 
	declare mensajeError varchar(50);
    declare transaccionActiva BIt;
    
    
    
    declare Exit handler for sqlexception #  tambien existe el continue handler que continua la ejecucion del proc.
    begin
		if transaccionActiva then 
        rollback;
              
        end if ;
        set pError = mensajeError;
    end;
if not exists (
				select*
                from Empleados
                where Cedula  = pCedula) then
                set pError = 'El empleado no existe';
                
                leave cuerpo;
                
     end if;           
     
     set transaccionActiva = 1;
     start transaction;# no se permite utilizar transaccionses en las funciones , solo en los proc.
     
     set mensajeError = 'no se pudo eliminar los telefonos del empleado. ';
     delete
     from Telefonos 
     where Empleado = pCedula;
      
      
     set mensajeError = 'no se pudo eliminar el empleado. ';
     Delete
     from Empleados
     where Cedula = pCedula;
     
     
     commit;
     
     set transaccionActiva = 0;
     
end // 

DELIMITER ;

create view LosEmpleadosCasados as Select* from Empleados
									where Casado = 1;
                                    
select*
from LosEmpleadosCasados;
