function reset(){
	document.getElementById("variables").innerHTML = "<h1>Variables</h1>";
	document.getElementById("resultados").innerHTML = "";
	document.getElementById("res").innerHTML = "";
	document.getElementById("tabla").innerHTML = "<h1>Tablas</h1>";
}

function variables() {
	// Creacion de variables
	var div = document.createElement("div"); // Crea la division de "variables"
	var selVar = document.getElementById("numVariables");
	var selRes = document.getElementById("numRestricciones");
	var newB = document.createElement("button");
	var poblB = document.createElement("input");
	var iterB = document.createElement("input");
	var oldDiv = document.getElementById("variables");
	var parentDiv = oldDiv.parentNode; // Busca quien es el padre del <div> "variables"

	div.innerHTML = "<h1>Variables</h1>";
	div.setAttribute("id", "variables");
	parentDiv.replaceChild(div, oldDiv); // Remplaza el viejo <div> por el nuevo


	// Agregamos las entradas para los limites de las variables
	div.appendChild(document.createTextNode("Limites"));
	div.appendChild(document.createElement("br"));
	for (var i = 1; i <= selVar.options[selVar.selectedIndex].value; i++) {
		// Nota: Las variables de solo 1 caracter son solo auxiliares
		var a = document.createElement("input");
		var b = document.createTextNode(" <= x"+ i +" <= ");
		var c = document.createElement("input");

		a.setAttribute("id", "min"+i);
		c.setAttribute("id", "max"+i);

		div.appendChild(a);
		div.appendChild(b);
		div.appendChild(c);
		div.appendChild(document.createElement("br"));
	}
	div.appendChild(document.createElement("br"));

	// Creamos espacio para Z

	div.appendChild(document.createTextNode("Z = "));

	for (var i = 1; i <= selVar.options[selVar.selectedIndex].value; i++) {
		if (i==selVar.options[selVar.selectedIndex].value) {
			var a = document.createElement("input");
			var b = document.createTextNode(" x"+ i +" ");

			a.setAttribute("id", "coefobj" + i);

			div.appendChild(a);
			div.appendChild(b);
		}else{
			var a = document.createElement("input");
			var b = document.createTextNode(" x"+ i +"+ ");

			a.setAttribute("id", "coefobj" + i);

			div.appendChild(a);
			div.appendChild(b);
		}
	}

	div.appendChild(document.createElement("br"));
	div.appendChild(document.createElement("br"));

	// Agregamos las entradas de los coeficientes de las variables en las restricciones
	for (var i = 1; i <= selRes.options[selRes.selectedIndex].value; i++) {
		for (var j = 1; j <= selVar.options[selVar.selectedIndex].value; j++) {
			if (j==selVar.options[selVar.selectedIndex].value) {
				var a = document.createElement("input");
				var b = document.createTextNode(" x"+ j +" ");

				a.setAttribute("id", "coef" + i + "_" + j);

				div.appendChild(a);
				div.appendChild(b);
			}else{
				var a = document.createElement("input");
				var b = document.createTextNode(" x"+ j +"+ ");

				a.setAttribute("id", "coef" + i + "_" + j);

				div.appendChild(a);
				div.appendChild(b);
			}

		}
		var c = document.createElement("select");
		var d = document.createElement("input");
		var op1 = document.createElement("option");
		var op2 = document.createElement("option");

		op1.appendChild(document.createTextNode("<="));
		op1.setAttribute("value", "lessthan");
		op2.appendChild(document.createTextNode(">="));
		op2.setAttribute("value", "morethan");

		c.appendChild(op1);
		c.appendChild(op2);

		c.setAttribute("id", "r" + i);
		d.setAttribute("id", "eql" + i);
		
		div.appendChild(c);
		div.appendChild(d);

		
		div.appendChild(document.createElement("br"));
	}

	poblB.setAttribute("id", "poblacion");
	iterB.setAttribute("id", "iteraciones");

	div.appendChild(document.createElement("br"));
	div.appendChild(document.createTextNode("Poblacion: "));
	div.appendChild(poblB);
	div.appendChild(document.createTextNode(" Iteraciones: "));
	div.appendChild(iterB);
	
	div.appendChild(document.createElement("br"));

	newB.setAttribute("onclick", "tabla()"); // Creamos un <button> para el siguiente paso
	newB.appendChild(document.createTextNode("Resolver"));
	div.appendChild(newB);
}

function tabla(){
	// Aqui comienza el algoritmo de Numeros Aleatorios para resolver el P.L.
	// Nota: Importar los numeros de variables y restricciones, ademas de valuarlas
	// para obtener Z
	var div = document.getElementById("tabla");
	div.innerHTML = "<h1>Tablas</h1>";
	for (var it = 1; it <= document.getElementById("iteraciones").value; it++) {
		// Declaracion de variables
		var selVar = document.getElementById("numVariables");
		var selRes = document.getElementById("numRestricciones");
		var oldDiv = document.getElementById("tabla");
		var parentDiv = oldDiv.parentNode; // Busca quien es el padre del <div> "tabla"
		var b;

		div.setAttribute("id", "tabla");
		parentDiv.replaceChild(div, oldDiv); // Remplaza el viejo <div> por el nuevo

		// Creacion de Header de la tabla

		var tabRan = document.createElement("table");
		tabRan.setAttribute("id", "tabRan");

		div.appendChild(tabRan);

		var a = document.createElement("tr"); // La variable "a" solo servira para agregar el Header en la tabla
		tabRan.appendChild(a);
		for (var i = 1; i <= selVar.options[selVar.selectedIndex].value; i++) {
			b = document.createElement("th");
			b.innerHTML = "x"+i;
			a.appendChild(b);
		}

		for (var i = 1; i <= selRes.options[selRes.selectedIndex].value; i++) {
			b = document.createElement("th");
			b.innerHTML = "r"+i;
			a.appendChild(b);
		}

		b = document.createElement("th");
		b.innerHTML = "Z";
		a.appendChild(b);

		for (var i = 1; i <= document.getElementById("poblacion").value; i++) {
			tabRan.appendChild(aleatorio(it));
		}

		tabRes(it);
	}

	// Mostramos el mayor de la tabala de resultados

	res();
}

function res() {
	var z = document.getElementsByClassName("z");
	var val = z[0];

	if (document.getElementById("maxmin").value == "max") {
		for (var i = 1; i < document.getElementById("iteraciones").value; i++) {
			if (parseInt(z[i].innerHTML) >= parseInt(val.innerHTML))
				val = z[i];
		}
		document.getElementById("res").innerHTML = "Max = " + val.innerHTML;
	}
	else {
		for (var i = 1; i < document.getElementById("iteraciones").value; i++) {
			if (parseInt(z[i].innerHTML) <= parseInt(val.innerHTML))
				val = z[i];
		}
		document.getElementById("res").innerHTML = "Min = " + val.innerHTML;
	}

}

function tabRes(numClass) {
	
	if (!document.getElementById("resultados").hasChildNodes()) {
		var selVar = document.getElementById("numVariables");
		var selRes = document.getElementById("numRestricciones");
		var tabl = document.createElement("table");
		var div = document.getElementById("resultados");
		// Creacion de Header de la tabla

		div.innerHTML = "<h1>Resultados</h1>";
		tabl.setAttribute("id", "tabRes")
		div.appendChild(tabl);

		var a = document.createElement("tr"); // La variable "a" solo servira para agregar el Header en la tabla
		tabl.appendChild(a);
		for (var i = 1; i <= selVar.options[selVar.selectedIndex].value; i++) {
			b = document.createElement("th");
			b.innerHTML = "x"+i;
			a.appendChild(b);
		}

		for (var i = 1; i <= selRes.options[selRes.selectedIndex].value; i++) {
			b = document.createElement("th");
			b.innerHTML = "r"+i;
			a.appendChild(b);
		}

		b = document.createElement("th");
		b.innerHTML = "Z";
		a.appendChild(b);
	}

	var div = document.getElementById("resultados");
	var tabl = document.getElementById("tabRes");
	var setClass = document.getElementsByClassName("z"+numClass);
	var val = setClass[0];
	var i = 0;
	while (i < setClass.length && val.innerHTML == "X") {
		i++;
		val = setClass[i];
	}

	if (document.getElementById("maxmin").value == "max") {
		for (var i = 1; i < setClass.length; i++) {
			if (setClass[i].innerHTML != "X" && (parseInt(setClass[i].innerHTML) >= parseInt(val.innerHTML)))
				val = setClass[i];
		}
	}
	else {
		for (var i = 1; i < setClass.length; i++) {
			if (setClass[i].innerHTML != "X" && (parseInt(setClass[i].innerHTML) <= parseInt(val.innerHTML)))
				val = setClass[i];
		}
	}

	val.setAttribute("class", "z");
	tabl.appendChild(val.parentNode);
}

function aleatorio(numClass){
	var selVar = document.getElementById("numVariables");
	var selRes = document.getElementById("numRestricciones");
	var fila = document.createElement("tr");
	var zfinal = 0;
	var r = [0,0,0,0,0];
	var aux = 1;

	for (var i = 1 ; i <= selVar.options[selVar.selectedIndex].value; i++) {
		var mini = document.getElementById("min"+i).value;
		var maxi = document.getElementById("max"+i).value;
		var col = document.createElement("td");
		var numRan = randInt(mini, maxi);

		col.innerHTML = numRan;
		fila.appendChild(col);

		for (var j = 1 ; j <= selRes.options[selRes.selectedIndex].value; j++) {
			r[j-1] += numRan * document.getElementById("coef" + j + "_" + i).value;
		}

		zfinal += numRan * document.getElementById("coefobj" + i).value;
	}

	for (var i = 1 ; i <= selRes.options[selRes.selectedIndex].value; i++) {
		var col = document.createElement("td");
		col.setAttribute("id", "ifr"+i);

		if (document.getElementById("r"+i).value == "lessthan")
			col.innerHTML = (r[i-1] <= document.getElementById("eql" + i).value) ? "1":"0";
		else
			col.innerHTML = (r[i-1] >= document.getElementById("eql" + i).value) ? "1":"0";
		aux *= parseInt(col.innerHTML);
		fila.appendChild(col);
	}
	
	var col = document.createElement("td");
	col.setAttribute("class", "z"+numClass);
	col.innerHTML = (aux==1) ? zfinal:"X";
	fila.appendChild(col);

	return fila;
}

function randInt(min, max) {
	return Math.random() * (max - min) + min;
}

/*
Notas:
	Agregar opcion de numero de iteraciones
	Agregar el resultado "X" cuando alguna de las variables no cumpla alguna restriccion
	Buscar forma de obtener el mayor Z
*/