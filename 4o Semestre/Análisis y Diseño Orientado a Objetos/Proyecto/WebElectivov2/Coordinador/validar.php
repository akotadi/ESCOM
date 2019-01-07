<?php
	session_start();
	require ("../conexion.php");
	$conectar = conectar();
	$AntContraseña=$_POST['AContraseña'];
	$NuevaContraseña=$_POST['NContraseña'];
	$CContraseña=$_POST['NCContraseña'];
	$ID=$_GET['username'];
	echo "<p><h1>Entrando con $ID</p>";
	$result = mysqli_query($conectar,"SELECT pass from usuario where idUsuario = '" . $ID . "'");
	$extraido=$result->fetch_array();
	if($AntContraseña==$extraido['pass'])
	{
		if($NuevaContraseña==$CContraseña)
		{
			if(mysqli_query($conectar,"UPDATE usuario set pass='" . $NuevaContraseña . "' where idUsuario='" . $ID . "'"))
			{
				echo "<p><h1>Se actualizo exitosamente tu contraseña</p>";
				header("Location: index.html");
			}
			else
			{
				echo "<p><h1>No se pudo actualizar tu contraseña</p>";
				header("Location: validar.php");
			}
		}
		else
		{
			echo "<p><h1>La nueva contraseña no coincide con su confirmación</p>";
			header("Location: validar.php");
		}
	}
	else
	{
		echo "<p><h1>Ingrese su contraseña anterior correctamente '" . $ID . "'</p>";
		header("Location: validar.php");
	}
    mysqli_close($conectar);
?>