if (!escribirArchivo(algo)) {
	/* error */
}

if (!escribirArchivo(algoMas)) { // dentro de los if van líneas de manejo del error
	/* error */
}

void escribeAlgo(String algo, String algoMas){ 
	try{ // no todo lo que queremos hacer va en un try
		// dentro de un try pongo llamadas a métodos que puedan fallar
		// se usa try-catch para hacer manejo de error
		escribirArchivo(algo);
		escribirArchivo(algoMas);
	}
	catch(Exception e){
		e.printStackTrace();
		return;
	}
}

void escribirArchivo(String algo){
	if (archivoNoExiste) {
		throw new fileNotFoundException();
	}
	else{
		/* escribir algo en el archivo */
	}
}