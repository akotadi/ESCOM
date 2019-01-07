<?php
session_start();
if(isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true)
{

}
else 
{
	header("Location: ../index.html");
	exit;
}
$now = time();
if($now > $_SESSION['expire'])
{
	session_destroy();
	header("Location: ../index.html");	
}
?>

<!DOCTYPE html>
<html lang="es">
	<head>
		<title>WebElectivo &mdash; Sistema de gestión de UAE</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="../css/style.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	</head>
	
	<style>
		body,h1,h2,h3,h4,h5,h6 {
			font-family: "Lato", sans-serif
		}
		.mySlides {
			display:none
		}
		.w3-tag, .fa {
			cursor:pointer
		}
		.w3-tag {
			height:15px;
			width:15px;
			padding:0;
			margin-top:6px
		}
		.w3-bar,h1,button {
			font-family: "Montserrat", sans-serif
		}
		.fa-anchor,.fa-coffee {
			font-size:200px
		}
	</style>
	
	<body>
		
		<!-- Navbar -->
		<div class="w3-top">
			<div class="w3-bar w3-wine w3-card w3-left-align w3-large">
				<a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-wine" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu">
					<i class="fa fa-bars"></i>
				</a>
				<a href="index.html" class="active w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Inicio</a>
				<div class="w3-dropdown-hover w3-right">
					<button class="w3-button w3-hide-small w3-padding-large">Panel de Control</button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block">
						<a href="validarhoras.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Validar Horas</a>
						<a href="contrasenia.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Cambiar Contraseña</a>
						<a href="../index.html" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Cerrar Sesión</a>
					</div>
				</div>
				<a href="perfil.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white w3-right">Perfil</a>
				<div class="w3-dropdown-hover w3-right">
					<button class="w3-button w3-hide-small w3-padding-large">Clubes</button>
					<div class="w3-dropdown-content w3-card-4 w3-bar-block">
						<a href="publicaciones.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Publicaciones</a>
						<a href="misclubes.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mis Clubes</a>
					</div>
				</div>
				
			</div>

			<!-- Navbar on small screens -->
			<div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium w3-large">
				<a href="misclubes.php" class="w3-bar-item w3-button w3-padding-large">Clubes</a>
				<a href="validarhoras.php" class="w3-bar-item w3-button w3-padding-large">Validar Horas</a>
				<a href="perfil.php" class="w3-bar-item w3-button w3-padding-large">Perfil</a>
				<a href="../index.html" class="w3-bar-item w3-button w3-padding-large">Cerrar Sesión</a>
			</div>
		</div>


		<!-- Header -->
		<header class="w3-container w3-wine w3-center" >

			<div style="padding:50px">
				<img src="../img/HeaderFinal2.jpg" style="width:100%">
			</div>

			<?php
				require ("../conexion.php");
				$conectar = conectar();
				if (isset($_POST['idclub'])) {
					$idclub=$_POST['idclub'];
				}else{
					$idclub=$_GET['idclub'];
				}
				if ($conectar) {
					$result2 = mysqli_query($conectar,"SELECT * FROM club where idclub='".$idclub."'");
					$Usuario=$result2->fetch_array();
					echo "<h1 class='w3-jumbo'>".$Usuario['nombre']."</h1>";
					mysqli_close($conectar);
				}

			?>

			
		</header>

		<!-- First Grid -->
		<div class="w3-container">
			<hr>
			<div class="w3-center">
				<p w3-class="w3-large">Lista de alumnos</p>
			</div>
			<div class="w3-responsive w3-card-4">
				<table class="w3-table w3-striped w3-bordered">
					<thead>
						<tr class="w3-theme w3-black">
							<th>ID</th>
							<th>Nombre</th>
							<th>Paterno</th>
							<th>Materno</th>
							<th>Boleta</th>
							<th>Acción</th>
						</tr>
					</thead>
					<tbody>

						<?php
							if (isset($_POST['idclub'])) {
								$idclub=$_POST['idclub'];
							}else{
								$idclub=$_GET['idclub'];
							}
							$conectar = conectar();
							if ($conectar) {
								$consulta = "select u.idusuario as idusuario, u.nombre as nombre, u.appaterno as ap, u.apmaterno as am, e.boleta as boleta from usuario u, estudiante e, registrado_has_estudiante re, registrado r where u.idusuario=e.idusuario and e.idusuario=re.estudiante_idusuario and re.registrado_idclub=r.idclub and re.registrado_ideje=r.ideje and r.idclub=$idclub;";
								$Registro = mysqli_query($conectar, $consulta);
								while($Ren = mysqli_fetch_array($Registro))
								{
									echo "<tr>";
									echo "<td>" . $Ren['idusuario'] . "</td>";
									echo "<td>" . $Ren['nombre'] . "</td>";
									echo "<td>" . $Ren['ap'] . "</td>";
									echo "<td>" . $Ren['am'] . "</td>";
									echo "<td>" . $Ren['boleta'] . "</td>";
									echo "<td><a href='contar.php?idusuario=$Ren[0]&amp;idclub=$idclub' class='w3-button w3-grey'>Sumar Hora</a></td></tr>";
									echo "</tr>";
								} 
								mysqli_free_result($Registro);
								mysqli_close($conectar);
							}
							
						?>

					</tbody>
				</table>
			</div>
			<hr>
		</div>

		<div class="w3-container w3-black w3-center w3-opacity w3-padding-64">
			<h1 class="w3-margin w3-xlarge">Cita del día: </h1>
			<p style="font-size: large;">“Puede que lo que hacemos no traiga siempre la felicidad, pero si no hacemos nada, no habrá felicidad”. (Albert Camus)</p>
		</div>

		<!-- Footer -->
		<footer class="w3-container w3-padding-64 w3-center w3-opacity">
			
			<!--
			<div class="w3-xlarge w3-padding-32">
				<i class="fa fa-facebook-official w3-hover-opacity"></i>
				<i class="fa fa-instagram w3-hover-opacity"></i>
				<i class="fa fa-snapchat w3-hover-opacity"></i>
				<i class="fa fa-pinterest-p w3-hover-opacity"></i>
				<i class="fa fa-twitter w3-hover-opacity"></i>
				<i class="fa fa-linkedin w3-hover-opacity"></i>
			</div>
			-->

			<p>Powered by <a href="https://www.facebook.com/chadcarreto" target="_blank">"El Chad"</a></p>
		</footer>

		<script src="../js/main.js"></script>

	</body>
</html>
