<%-- 
    Document   : index
    Created on : 27/08/2018, 11:21:57 PM
    Author     : manue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="IE=edge">
    <title>Registro de Materia</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/main.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
    crossorigin="anonymous">
    
</head>
<body>
    <h1>Registro de Unidad de Aprendizaje</h1>
    <div class="container">
        <form id="registroUA">
            <!-- Fila principal para las tres columas principales -->
            <div class="containerrow">
                <!-- Coulmna principal 1: Primera seccion del formulario -->
                <div class="containercolumn">
                    <center>Datos Generales<br></center>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="nombremateria">Nombre*:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="nombremateria" name="nombre">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="proposito">Proposito*:</label>
                        </div>
                        <div class="col-75">
                            <input type="text" class="form-control" id="proposito" name="proposito">
                        </div>
                    </div>
                    <div class="row form-group">
                            <div class="col-25">
                                <label form="form1" for="objetivo">Objetivo*:</label>
                            </div>
                            <div class="col-75">
                                <input type="text" class="form-control" id="objetivo" name="objetivo">
                            </div>
                        </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="areainf">Area de informacion*:</label>
                        </div>
                        <div class="col-75">
                            <input type="text" class="form-control" id="areainf" name="area_de_informacion">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="nivel">Nivel*:</label>
                        </div>
                        <div class="col-75">
                            <input type="text" class="form-control" id="nivel" name="nivel">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="ctepic">CTEPIC*:</label>
                        </div>
                        <div class="col-75">
                            <input type="text" class="form-control" id="ctepic" name="CTEPIC">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="modalidad">Modalidad*:</label>
                        </div>
                        <div class="col-75">
                            <input type="text" class="form-control" id="modalidad" name="modalidad">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="vigencia">Vigencia*:</label>
                        </div>
                        <div class="col-75">
                            <input type="text" class="form-control" id="vigencia" name="vigencia">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="catcc">CATCC*:</label>
                        </div>
                        <div class="col-75">
                            <input type="text" class="form-control" id="catcc" name="CATCC">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="intencion">Intencion educativa*:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="intencion" name="">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="orientacion">Orientacion Educativa*:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="orientacion" name="orientacion_educativa">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="aprobadopor">Aprobado por*:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="aprobadopor" name="aprobado_por">
                        </div>
                    </div>
                </div>

                <!-- Coulmna principal 2: Segunda seccion del formulario -->
                <div class="containercolumn">
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="tipo">Tipo*:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="tipo" name="tipo">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="autorizadopor">Autorizado por*:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="autorizadopor" name="autorizado_por">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="revisadopor">Revisado por*:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="revisadopor" name="revisado_por">
                        </div>
                    </div>
        
                    <center><br>Datos de unidad tematica<br></center>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="nombretema">Nombre:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="nombretema" name="nombreUnidad_tematica">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="numero">Numero:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="numero" name="numero">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="desc">Descriccion:</label> <!--Nota: En el disenio propuesto esta mal escrito-->
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="desc">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="competencia">Competencia:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="competencia" name="competencia">
                        </div>
                    </div>
        
                    <center><br>Tiempos de asignación<br></center>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="horasTS">Horas Teoria / Semana:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="horasTS" name="horas_teoria_semana">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="horasPS">Horas Practica / Semana:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="horasPS" name="horas_practica_semana">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="horasTN">Horas Teoria / Nivel:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="horasTN" name="horas_teoria_nivel">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="horasTotal">Horas Total / Nivel:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="horasTotal" name="horas_total_nivel">
                        </div>
                    </div>
                </div>

                <!-- Coulmna principal 3: Tercera seccion del formulario -->
                <div class="containercolumn">
                    <center><br>Contenidos<br></center>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="nombrecontenido">Nombre:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="nombrecontenido" name="nombreContenido">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="numerocontenido">Número:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="numerocontenido" name="numeroContenido">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-25">
                            <label form="form1" for="horascontenido">Horas:</label>
                        </div>
                        <div class="col-75">
                                <input type="text" class="form-control" id="horascontenido" name="horas">
                        </div>
                    </div>
                    <div class="row form-group">
                        <button id="guardar" type="submit" class="btn btn-primary">Guardar</button>
                        <button id="registrar" type="submit" class="btn btn-primary">Registrar</button>
                    </div>
                </div>

                <!-- Fin de fila principal -->
            </div>
        </form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
    crossorigin="anonymous"></script>
    <script>
        $("#registrar").click(function (e) {
            e.preventDefault();
            $.post("/VerificarDatos", $("#registroUA").serialize(), function (data) {
                alert(data);
            })
            .fail(function (err) {
                alert(err.stringify());
            });
        });
    </script>
</body>
</html>