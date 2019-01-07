<?php
	$idClub=$_POST['idClub'];
	if (empty($idClub)) {
		$_SESSION['idClub'] = "";
		header("Location: inscribir.php");
		exit();
	}else{
		$_SESSION['idClub'] = $idClub;
		header("Location: inscribir.php");
		exit();
	}
?>