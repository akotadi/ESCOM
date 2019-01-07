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

		</header>

		<hr>

		<!-- First Grid -->
		<div class="w3-row w3-container w3-center">
			<div class="w3-quarter w3-container">
				<img src="../img/Avatar.png" style="width:100%">
				<a href="" class="w3-button w3-padding-large w3-xlarge w3-margin-top w3-grey w3-text-black w3-border-black">Cambiar Avatar</a>
				<br>
				<br>
			</div>
			<div class="w3-threequarter w3-container">
				<div class="w3-row w3-container">
					<div class="w3-twothird w3-container w3-left-align w3-large">

						<?php
							require ("../conexion.php");
							$ID=$_SESSION['username'];
							$conectar = conectar();
							if ($conectar) {
								$consulta = "select nombre, appaterno, apmaterno from usuario where $ID=idUsuario;";
								$nombre = mysqli_query($conectar,$consulta);
								$result = mysqli_fetch_array($nombre);
								echo "<label>Nombre: ".$result['nombre']."   ".$result['appaterno']."   ".$result['apmaterno']."</label><br><br>";
							}
							mysqli_close($conectar);
						?>

						<div class="w3-container">
							<hr>
							<div class="w3-center">
								<p w3-class="w3-large">Clubes administrados</p>
							</div>
							<div class="w3-responsive w3-card-4">
								<table class="w3-table w3-striped w3-bordered">
									<thead>
										<tr class="w3-theme w3-black">
											<th>Nombre</th>
											<th>Descripción</th>
											<th>Cupo</th>
										</tr>
									</thead>
									<tbody>

										<?php
											$ID=$_SESSION['username'];
											$conectar = conectar();
											if ($conectar) {
												$consulta = "select r.cupo as cupo, c.nombre as nombre, c.descripcion as descripcion from coordinador_has_club cc, registrado r, club c where $ID=cc.idusuario and cc.idclub=r.idclub and r.idclub=c.idclub and r.ideje=c.ideje;";
												$Registro = mysqli_query($conectar, $consulta);
												while($Ren = mysqli_fetch_array($Registro))
												{
													echo "<tr>";
													echo "<td>" . $Ren['nombre'] . "</td>";
													echo "<td>" . $Ren['descripcion'] . "</td>";
													echo "<td>" . $Ren['cupo'] . "</td>";
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
					</div>
				</div>
			</div>
		</div>

<!-- Fill table with PHP

	<?php
		$connection = mysqli_connect('localhost', 'root', '', 'users');
	?>
	<html>
		<head></head>
		<body>
			<table name = "userDetails">
				<tr>
					<th>User ID</th>
					<th>First Name</th>
					<th>email</th>
				</tr>
				<?php
					$sql = "SELECT id, firstname,email FROM usertable";
					$result = mysqli_query($connection, $sql);
					if(mysqli_num_rows($result) > 0){
					while ($row = mysqli_fetch_assoc($result)) {
				?>
				<tr>
					<td>$row['id'];</td>
					<td>$row['firstname'];</td>
					<td>$row['email'];</td>
				</tr>
				<?php      
					}
					}
					else
					{
				?>
				<tr>
					<td colspan=3>Data is not available</td>
				</tr>
				<?php
					}
				?>
			</table>
		</body>
	<html/>
	
-->

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

		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		
		<script src="../js/main.js"></script>

	</body>
</html>
