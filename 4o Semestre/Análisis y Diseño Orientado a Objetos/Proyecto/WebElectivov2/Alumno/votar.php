<?php
	require ("../conexion.php");
	$conectar = conectar();
	$club = $_GET['idClub'];
	if ($conectar) {
	    if(mysqli_query($conectar,"UPDATE candidato set voto = voto+1 where iidClub = '" . $club . "'"))
		{
	       echo "<p><h1>Tu voto se realizo con exito con $club</p>";
	       header("Location: votaciones.php");
		}
		else
		{
	       echo "<p> Se ha producido un error</p>";
	       header("Location: votaciones.php");
		}

		mysqli_close($conectar);
	}
	
	
?>