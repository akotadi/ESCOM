<?php
	require ("../conexion.php");
	$conectar = conectar();
	$idusuario = $_GET['idusuario'];
	$idclub = $_GET['idclub'];
	if ($conectar) {
	    if(mysqli_query($conectar,"UPDATE registrado_has_estudiante set cantidadhora = cantidadhora+1 where estudiante_idusuario = '" . $idusuario . "' and registrado_idclub = '".$idclub."'"))
		{
	       echo "<p><h1>Tu voto se realizo con exito con $idusuario $idclub</p>";
	       header("Location: registrarhoras.php?idclub=$idclub");
		}
		else
		{
	       echo "<p> Se ha producido un error</p>";
	       header("Location: registrarhoras.php?idclub=$idclub");
		}

		mysqli_close($conectar);
	}
	
	
?>