<?php
	function conectar(){
		$user="root";
		$pass="root";
		$server="localhost";
		$db="webelectivo";
		$con=mysqli_connect($server, $user, $pass,$db);
		 mysqli_select_db($con, $db);
		return $con;
	}
?>