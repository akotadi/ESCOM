<?php

	function tipoUsuario($idusuario, $conex){
		$tipo=mysqli_query($conex, "SELECT tipo FROM usuario WHERE idusuario=".$idusuario.""); 
		$row = mysqli_fetch_array($tipo);

		return $row[0];
	}

?>