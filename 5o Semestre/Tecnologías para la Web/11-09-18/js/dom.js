function js1(){
	alert("ESCOM")
}

function js2(){
	alert("IPN")
}

function crear() {
	var etiqueta = document.createElement("p");
	var contenido = document.createTextNode("Un párrafo en tiempo real");
	etiqueta.appendChild(contenido);
	etiqueta.style.fontSize = "240px";
	etiqueta.id = "nuevop";
	document.getElementById('respDOM').appendChild(etiqueta);
}

function cambiar() {
	var etiqueta = document.getElementById('nuevop');
	etiqueta.textContent = "Cambie el contenido de un párrafo creado en tiempo real";
	etiqueta.style.backgroundColor = "#FF0";
}

function eliminar() {
	var etiqueta = document.getElementById("nuevop");
	etiqueta.parentNode.removeChild(etiqueta);
}

document.getElementById("escom").addEventListener("click",js1);
document.getElementById("ipn").onclick = js2;
document.getElementById("crear").addEventListener("click",crear);
document.getElementById("cambiar").onclick = cambiar;
document.getElementById("eliminar").addEventListener("click",eliminar);
