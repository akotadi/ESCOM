
void askData(){

	cout<<"Bienvenido al sistema de solucion de Problemas de Programacion Lineal"<<endl;
	
	cout<<"Introduca el numero de variables (Maximo 4): ";
	cin>>nVariables;
	while (nVariables > 4)
	{
		cout<<"Numero de variables excedido, intente nuevamente: ";
		cin>>nVariables;
	}
	
	aFormulaObjetivo.resize(nVariables,0);
	cout<<"Introduzca el valor de la variables en la funcion Z:\n";
	for (int i = 0; i < nVariables; ++i)
	{
		cout<<"\tx"<<i<<" = ";
		cin>>aFormulaObjetivo[i];
		//cout<<endl;
	}

	aLimites.resize(nVariables);
	for (int i = 0; i < nVariables; ++i)
	{
		cout<<"Introduzca el limite izquierdo de la variable x"<<i<<":\n";
		cin>>aLimites[i].first;
		cout<<"";
		cout<<"Introduzca el limite derecho de la variable x"<<i<<":\n";
		cin>>aLimites[i].second;
		cout<<"";
		cout<<endl;
	}

	cout<<"\nQue desea hacer?\n\t1) Maximizar\n\t2) Minimizar"<<endl;
	cin>>nOpcion;
	while (nOpcion != 1 && nOpcion != 2)
	{
		cout<<"Opcion no valida, intente nuevamente: ";
		cin>>nOpcion;
	}

	cout<<"\nIntroduzca el numero de restricciones (Maximo 5): ";
	cin>>nRestricciones;
	while (nRestricciones > 5)
	{
		cout<<"Numero de restricciones excedido, intente nuevamente: ";
		cin>>nRestricciones;
	}

	aRestricciones.resize( nRestricciones, vector<double>(nVariables+2) );
	for (int i = 0; i < nRestricciones; ++i)
	{
		cout<<"\tIntroduzca el valor de la variables para la R"<<i<<":\n";
		int j;
		for (j = 0; j < nVariables; ++j)
		{
			cout<<"\t\tx"<<j<<" = ";
			cin>>aRestricciones[i][j];
		}
		cout<<"\n\tQue desea hacer?\n\t\t1) <=\n\t\t2) >=\n\t";
		cin>>aRestricciones[i][j];
		while (aRestricciones[i][j] != 1 && aRestricciones[i][j] != 2)
		{
			cout<<"Opcion no valida, intente nuevamente: ";
			cin>>aRestricciones[i][j];
		}
		cout<<"\tIntroduzca el valor de la variable a igualar para la R"<<i<<": ";
		cin>>aRestricciones[i][++j];
		cout<<"";
		cout<<endl;
	}

	cout<<"\nIntroduzca el numero de poblacion: ";
	cin>>nPoblacion;

	cout<<"\nIntroduzca el numero de iteraciones (maximo 100): ";
	cin>>nIteraciones;
	while (nIteraciones > 100)
	{
		cout<<"Numero de iteraciones excedido, intente nuevamente: ";
		cin>>nIteraciones;
	}

	cout<<"\nIntroduzca el numero de bits de precision: ";
	cin>>nBits;
}
