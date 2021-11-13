



/* if exists(Select * FROM SysDataBases WHERE name='BDProyecto2Do')
BEGIN
DROP DATABASE BDProyecto2Do
END
GO*/

CREATE DATABASE BDJavaWeb3ro1erObli;

-- Tablas
CREATE TABLE Usuario(
Cedula varchar(8) NOT NULL PRIMARY KEY,
Clave varchar(6) NOT NULL CHECK (Clave LIKE  '[A-Za-z][A-Za-z][A-Za-z][0-9][0-9][^a-zA-Z0-9]'),
NombreCompleto varchar(20) NOT NULL,
CHECK (Cedula LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'));


CREATE TABLE Administrador(
Cedula varchar(20) NOT NULL PRIMARY KEY,
Cargo varchar(20) NOT NULL
);

CREATE TABLE Espectador(
Cedula varchar(20) NOT NULL PRIMARY KEY,
Correo varchar(20) NOT NULL CHECK(Mail  LIKE '%_@_%_.__%'),
SuscripcionBoletin varchar(20) NOT NULL
);


CREATE TABLE Sala(
Numero int(10) NOT NULL PRIMARY KEY,
Nombre varchar(20) NOT NULL CHECK (Nombre LIKE  '[A-Za-z][A-Za-z][A-Za-z][0-9][0-9][^a-zA-Z0-9]'),
Ubicacion varchar(20) NOT NULL,
Capacidad int(10) NOT NULL ,
Accesibilidad boolean,
Superficie int(10) NOT NULL ,
check ( Numero >= 1)
);

CREATE TABLE Funcion(
ID varchar(20) NOT NULL PRIMARY KEY,
FechaHora DateTime NOT NULL,
PrecioEntrada Float(20) NOT NULL
);


CREATE TABLE Temporada(
Numero int(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
Codigo int(20),
FechaInicio DateTime NOT NULL,
FechaFin DateTime NOT NULL,
CHECK (FechaInicio <  FechaFin),
CHECK (Numero >= 0),
FOREIGN KEY (Codigo) REFERENCES Obra(Codigo)
);



CREATE TABLE Obra(
Codigo varchar(7) NOT NULL PRIMARY KEY,
-- Poster varchar(50) NOT NULL,
Titulo varchar(20) NOT NULL,
Genero varchar(20) NOT NULL,
DirigidaPor varchar(20) NOT NULL,
Duracion varchar(10) NOT NULL,
Argumento varchar(50) NOT NULL,
CHECK (Codigo LIKE  '[A-Za-z][A-Za-z][A-Za-z][0-9][0-9][0-9][0-9]')
-- falta ver lo de la autorelacion
);

-- relaciones
CREATE TABLE Reserva(
Codigo varchar(7) NOT NULL ,
 -- Cedula varchar(20) NOT NULL,
ID varchar(20) NOT NULL ,
CantidadEntradas int(10)NOT NULL,
FOREIGN KEY (ID) REFERENCES Funcion(ID),
FOREIGN KEY (Codigo) REFERENCES Funcion(Codigo)
);


CREATE TABLE PuestaEn(
ID varchar(20) NOT NULL PRIMARY KEY,
Numero int(10)NOT NULL
);

-- ver porque pa mi nos falta algo del identity

-- SP´s
/*
1) SP_AgregarSala* (IN Num int(10), IN Nom varchar(20),in Ubi varchar(20), in Cap int(10),in Acc boolean,in Sup  int(10),out Salida varchar(50))
 
2) SP_ModificarSala*( IN Num int(10),IN Nom varchar(20),in Ubi varchar(20), in Cap int(10),in Acc boolean,in Sup  int(10),out Salida varchar(50))
 
3) SP_EliminarSala*(IN Num int(10),out Salida varchar(50))
4) SP_ListarSalas*
5) SP_ListarObras*
6) SP_AgregarObra* (Cod varchar(7),Tit varchar(20),Gen varchar(20),Dir varchar(20),Dur varchar(10) ,Arg varchar(50),out Salida varchar(50))

7) SP_ModificarObra* (Cod varchar(7),Tit varchar(20),Gen varchar(20),Dir varchar(20),Dur varchar(10) ,Arg varchar(50),out Salida varchar(50))

8) SP_EliminarObra*(ver) (Cod varchar(7),out Salida varchar(50))

9) SP_AgregarTemporadaAObra* (Num int(20),Cod int(20),FeI DateTime ,FeF  DateTime,out Salida varchar(50))
10) SP_AgregarObraSimilar(Cod varchar(7),Tit varchar(20),Gen varchar(20),Dir varchar(20),Dur varchar(10) ,Arg varchar(50),out Salida varchar(50))

11) SP_EliminarObraSimilar(Cod varchar(7),out Salida varchar(50))

12) SP_AgreagrFuncion (ID varchar(20),FeH DateTime,PrE Float(20),out Salida varchar(50))
13) SP_ListarObrasEnCartel
14) SP_AltaEspectador* (Ced varchar(20),Cor varchar(20),Sus varchar(20),out Salida varchar(50))
15) SP_BajaEspectador* (Ced varchar(20),out Salida varchar(50))
16) SP_ModificarEspectador* (Ced varchar(20),Cor varchar(20),Sus varchar(20),out Salida varchar(50))
17) SP_ReservarEntradas ( Ced varchar(20) ,ID varchar(20),Can int(10),out Salida varchar(50))
18) SP_ConsultarReservasVigentes ( Ced varchar(20) ,ID varchar(20))
19) SP_BuscarUltimaTemporadaDeObra*(Cod varchar(7))

*/

DELIMITER //
CREATE PROCEDURE SP_AgregarSala (IN Num int(10), IN Nom varchar(20),in Ubi varchar(20), in Cap int(10),in Acc boolean,in Sup  int(10), out Salida varchar(50))
 
 cuerpo:Begin 
  If not Exists( select* from Sala where Numero = Num)
	then
    SET Salida = 'Ya existe esta Sala'; 
	leave cuerpo;
	End If;
    
		insert into Sala(Numero,Nombre,Ubicacio,Capacidad ,Accesibilidad ,Superficie) values (Num , Nom , Ubi, Cap , Acc , Sup) 
			
    SET Salida = 'Sala insertada'; 
end ;


CREATE PROCEDURE SP_ModificarSala(IN Num int(10), IN Nom varchar(20),in Ubi varchar(20), in Cap int(10),in Acc boolean,in Sup  int(10),out Salida varchar(50))

 cuerpo:begin 
  If not Exists( select* from Sala where Numero = Num)
 then
     SET Salida = 'No existe esta Sala'; 
leave cuerpo;
end if;
	Update Sala set Nombre=Nom,Ubicacio=Ubi,Capacidad = Cap ,Accesibilidad = Acc,Superficie = Sup where Numero = num;
	SET Salida = 'Sala modificada ';
end ;



DELIMITER //
CREATE PROCEDURE SP_EliminarSala  (Num int(10),out Salida varchar(50))

 cuerpo:begin
  If not Exists( select* from Sala where Numero = Num)
	then
	  SET Salida = 'No existe esta Sala'; 
	leave cuerpo; -- ver la parte de la obra
	end if;

	delete
    from Sala
    where Numero = Num;
	SET Salida = 'Sala Borrada'; 
end ;




CREATE PROCEDURE SP_ListarSalas()
cuerpo :Begin
	select *
	from Sala
End;



CREATE PROCEDURE SP_ListarObras()

cuerpo :Begin
	select*
	from Obra
End;




CREATE PROCEDURE  SP_EliminarObra(in Cod varchar(7),out Salida varchar(50))


cuerpo:Begin 
 If not Exists( select* from Obra where Codigo = Cod)
  
 then
 	SET Salida = 'La Obra no existe'; 
leave cuerpo;

end if;

	DELETE
    FROM Obra
    WHERE Codigo = Cod;
    	SET Salida = 'Obra Borrada'; 
END;




delimiter//

CREATE PROCEDURE SP_ModificarObra(in Cod varchar(7),in Tit varchar(20),in Gen varchar(20),in Dir varchar(20),in Dur varchar(10) ,in Arg varchar(50),out Salida varchar(50))


cuerpo:begin
If not Exists( select* from Obra where Codigo = Cod)
	then
	SET Salida = 'Obra No existe'; 
	leave cuerpo;
end if;

			Update Obra Set Titulo=Tit , Genero =Gen, DirigidaPor = Dir, Duracion = Dur,  Argumento = Arg where Codigo = Cod;
			SET Salida = 'Obra Modificada';
		end ;






CREATE PROCEDURE SP_AltaEspectador(in Ced varchar(20),in Cor varchar(20),in Sus varchar(20),out Salida varchar(50))
cuerpo:begin
	If  Exists( select* from Espectador where Cedula = Ced)
	then
	SET Salida = 'El Espectador ya existe';
leave cuerpo;
end if;
			insert into Espectador (Cedula,Correo,SuscripcionBoletin) values (Ced ,Cor ,Sus)
            	SET Salida = 'Espectador dado de alta';
end ;




CREATE PROCEDURE SP_BajaEspectador(in Ced varchar(20),out Salida varchar(50))
cuerpo:Begin 
	If not  Exists( select* from Espectador where Cedula = Ced)
	then
	SET Salida = 'El Espectador NO existe';
leave cuerpo;
end if;
	BEGIN
	DELETE
    FROM Espectador
    WHERE Cedula = Ced;
    
	BEGIN
	DELETE
    FROM Reservas
    WHERE Cedula = Ced;
    
    SET Salida = 'El Espectador se borró';
END;





CREATE PROCEDURE SP_ModificarEspectador(in Ced varchar(20),in Cor varchar(20),in Sus varchar(20),out Salida varchar(50))
cuerpo:begin
	If not  Exists( select* from Espectador where Cedula = Ced)
	then
	SET Salida = 'El Espectador NO existe';
leave cuerpo;
end if;
			Update Espectador set  Correo = Cor,SuscripcionBoletin = Sus
            SET Salida = 'El Espectador se modificó';
end ; -- En estos casos será el propio espectador quien podrá modificar sus datos o eliminar su cuenta.

/*CREATE TABLE Temporada(
Numero int(20) NOT NULL PRIMARY KEY,
Codigo int(20),
FechaInicio DateTime NOT NULL,
FechaFin DateTime NOT NULL,*/

CREATE PROCEDURE  SP_AgregarTemporadaAObra (in Num int(20),in Cod int(20),in FeI DateTime ,in FeF  DateTime,out Salida varchar(50))
cuerpo:begin
	If not  Exists( select* from Obra where Codigo = Cod)
		then
		SET Salida = 'La obra no existe';
    Else
		If Exists( Select* from Temporada where Numero = Num)
        then
		SET Salida = 'La Temporada ya existe';

leave cuerpo;
end if;
		Insert Temporada(Numero,Codigo,FechaInicio,FechaFin) values(Num,Cod,FeI,FeF)
		SET Salida = 'Temporada Ingresada con exito';
end ; 

CREATE PROCEDURE SP_BuscarUltimaTemporadaDeObra(in Cod varchar(7))
cuerpo :Begin
/*
SELECT * 
FROM table_name
ORDER BY id DESC
LIMIT 1*/

	select MAX(*)
	From Temporada
	where Codigo = Cod
end;


CREATE PROCEDURE SP_ReservarEntradas ( in Cod varchar(20) ,in ID varchar(20),in Can int(10),out Salida varchar(50))
cuerpo :Begin

end;


