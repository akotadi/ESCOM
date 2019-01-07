<?php
session_start();
?>
<?php
	$usuario = $_POST['boleta'];
	$pass = $_POST['password'];

	// Cuando no se han ingresado datos
	if(empty($usuario) || empty($pass))
	{
		header("Location: sesion.html");
		exit();
	}
	include ("conexion.php");
	include ("obtenerUsuario.php");
	// Probamos la conexion
	$conexion = conectar();
	if($conexion)
	{
		$result = mysqli_query($conexion,"SELECT * FROM usuario where idUsuario='" . $usuario . "'");
		$extraido=$result->fetch_array();
		// Valida que el usuario y contraseña sean validos
		if($extraido['idUsuario'] ==  $usuario && $extraido['pass'] ==  $pass)
		{
			$_SESSION['loggedin'] = true;
			$_SESSION['username'] = $usuario;
			$_SESSION['start'] = time();
			$_SESSION['expire'] = $_SESSION['start'] + (40*60);
			if ($extraido['tipo'] == 'A') {
				header("Location: Administrador/index.html");
			}
			else if ($extraido['tipo'] == 'C') {
				header("Location: Coordinador/index.html");
			}
			else{
				header("Location: Alumno/index.html");
			}
		}
		else
		{
			header("Location: index.html");
			exit();
		}
	}
	mysqli_close($conexion);
?>