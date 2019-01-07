<?php
	
	$Boleta=$_POST['Boleta'];
	$CURP=$_POST['CURP'];
	$Contraseña=$_POST['Contraseña'];
	$CContra=$_POST['CContraseña'];
	$Email=$_POST['Email'];
	$Nombre=$_POST['Nombre'];
	$apPaterno=$_POST['aPP'];
	$apMaterno=$_POST['aPM'];

	// Cuando no se han ingresado datos
	if(empty($Boleta) || empty($CURP) || empty($Contraseña) || empty($CContra) || empty($Email) || empty($Nombre) || empty($apPaterno) || empty($apMaterno))
	{
		header("Location: registrarse.html");
		exit();
	}

	include ("conexion.php");
	$conexion=conectar();

	if($conexion)
	{

		if($Contraseña==$CContra)
		{

			if(mysqli_query($conexion,"INSERT into usuario (pass,curp,tipo,email,nombre,apPaterno,apMaterno) values ('". $Contraseña . "','" . $CURP . "','E','" . $Email . "','" . $Nombre . "','" . $apPaterno . "','" . $apMaterno . "')"))
			{
	            if(mysqli_query($conexion,"INSERT into estudiante (boleta,totalHora,idUsuario) values ('". $Boleta . "',0, (SELECT idUsuario FROM usuario where curp='" . $CURP . "') )"))
		        {
		        	echo "<p><h1>La alta se ha realizado con exito</p>";
		        	header("Location: sesion.html");
		        }
		        else
		        {
		            echo "<p> No se ha podido guardar al estudiante $Nombre</p>";	
		            header("Location: registrarse.html");
		        }
				
			}
			else
			{
				echo "<p> Se ha producido un error para $Nombre</p>";
				header("Location: registrarse.html");
			}
	    }
	    else
	    {
	     	echo "<p> Las contraseñas no coinciden</p>";
	     	header("Location: registrarse.html");
	    }
	}
	mysqli_close($conexion);
?>