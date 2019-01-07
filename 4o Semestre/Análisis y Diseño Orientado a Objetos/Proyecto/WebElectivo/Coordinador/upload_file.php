<?php
if(isset($_POST['submit_image']))
{
	$uploadfile=$_FILES["upload_file"]["tmp_name"];
	$check = $_FILES["upload_file"]["name"];
	if (check_file_uploaded_name($check) && check_file_uploaded_length($check)) {
		$folder="../img/";
		if (move_uploaded_file($uploadfile, $folder.$check)) {
			echo "<p><h1>Se actualizo exitosamente tu contraseña</p>";
			 header("Location: publicaciones.php");
		}else{
			echo "<p><h1>Se actualizo incorrectamente tu contraseña</p>";
			header("Location: actividad.php");
		}
	}else{
		echo "<p><h1>Erro actualizo incorrectamente tu contraseña</p>";
		header("Location: actividad.php");
	}
  
  exit();
}

function check_file_uploaded_name ($filename)
{
	return (bool) ((preg_match("`^[-0-9A-Z_\.]+$`i",$filename)) ? true : false);
}

function check_file_uploaded_length ($filename)
{
	return (bool) ((mb_strlen($filename,"UTF-8") < 100000000000000) ? true : false);
}
?>