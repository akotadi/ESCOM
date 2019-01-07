<?php
	require("../conexion.php");
	$conectar=conectar();
	//$usuario=$_SESSION['username'];
	if ($conectar) {
		$idClub=$_POST['ID'];
		$Boleta=$_POST['Boleta'];
		$usuario=mysqli_query($conectar,"SELECT idUsuario from estudiante where boleta = '" . $Boleta . "'");
		$ID=mysqli_fetch_array($usuario);
		if(mysqli_query($conectar,"UPDATE registrado SET cupo=cupo-1 WHERE idClub=" . $idClub . ";")){
			$SQL="INSERT INTO registrado_has_estudiante (Registrado_idClub,Registrado_idEje,Estudiante_idUsuario,cantidadHora) VALUES(" . $idClub . ",(SELECT idEje FROM registrado WHERE idClub=" . $idClub . "),".$ID[0].",0);";
			if(mysqli_query($conectar,$SQL))
			{
				echo "<p><h1>Se inscribio correctamente el club</p>";
				header("Location: index.html");
			}
			else
			{
				echo "<p> Se ha producido un error 2</p>";
			}
		}
		else
		{
			echo "<p> Se ha producido un error</p>";
		}
		mysqli_close($conectar);
	}
?>