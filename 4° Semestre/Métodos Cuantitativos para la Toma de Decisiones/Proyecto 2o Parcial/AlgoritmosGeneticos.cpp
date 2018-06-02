#include <bits/stdc++.h>

const int INF = 1 << 30;

using namespace std;

// Usamos este tipo de dato para evitar negativos
typedef unsigned long int ulong;

typedef function<bool(pair<int, int>, pair<int, int>)> Comparator;



int nVariables, nRestricciones, nOpcion, nPoblacion, nIteraciones, nBits, nTotalBits = 0, nSurvivors = 0;

vector<double> aFormulaObjetivo, aFormulaEstatica, aValuesZ, aAcumulateZ;
vector< pair<double,double> > aLimites;
vector< pair<int, int> > aCountedValues;
vector< vector<double> > aRestricciones, aValuesPoblation;
vector<int> aBitsVariables, aMaxVariables;
vector< vector<ulong> > aPobladores;

// Map usado para contar el número de apariciones y encontrar los vectores más dominantes.
unordered_map<int,int> mapValues;
// Map usado para evitar que se repitan los sujetos a evaluar.
unordered_map<double,int> mapRepetitions;

// En este elemento guardaremos el elemento final junto a su clave
pair<int,double> finalValueOfZ;

// El comparador nos ordenará el map con base en su valor ( # de apariciones ) en lugar de hacerlo por śu llave
Comparator compFunctor = [](pair<int, int> elem1 ,pair<int, int> elem2)
	{
		return elem1.second > elem2.second;
	};

// Los datos se piden en una función secundaria para facilitar el trabajo
#include "PideDatos.h"

// Función que ordenará el map depositando el resultado en un set
void sortMap(){
	
	set<pair<int, int>, Comparator> setOfValues(
		mapValues.begin(), mapValues.end(), compFunctor);

	aCountedValues.clear();
	aCountedValues.resize(setOfValues.size());

	copy(setOfValues.begin(), setOfValues.end(), aCountedValues.begin());

}

// Función que generará elementos al azar entre 0 y 1, comparándolos contra la acumulada de Z
void countRandomValues(){
	mapValues.clear();
	for (int i = 0; i < nPoblacion; ++i)
	{
		double dRandom = ((double) rand() / (RAND_MAX));
		for (int j = 0; j < nPoblacion; ++j)
		{
			if (dRandom < aAcumulateZ[j])
			{
				mapValues[j]++;
				break;
			}
		}
	}
	sortMap();
}

// Función que nos indicará si el poblador cumple con las restricciones, al fallar en alguna, la función devolverá un false
bool checkRestrictions(vector<double> aTest){
	for (int i = 0; i < nRestricciones; ++i)
	{
		double dAux = 0;
		int j = 0;
		for (j = 0; j < nVariables; ++j)
		{
			dAux += aRestricciones[i][j] * aTest[j];
		}
		if (aRestricciones[i][j] == 1)
		{
			if (dAux > aRestricciones[i][++j])
			{
				return false;
			}
		} else {
			if (dAux < aRestricciones[i][++j])
			{
				return false;
			}
		}
	}
	return true;
}

// Función que mutará un poblador con base en su clave específica
void mutatePoblator(int nIndex){

	vector<ulong> aFuturePoblator;
	vector<double> aValueFuturePoblator(nVariables);
	
	do{
		int nRandomVariable = rand() % nVariables;
		int nRandomBit = rand() % aBitsVariables[nRandomVariable];
		
		aFuturePoblator = aPobladores[nIndex];
		
		bitset<sizeof(ulong)*8> bFuturePoblator(aFuturePoblator[nRandomVariable]);
		bFuturePoblator.flip(nRandomBit);

		aFuturePoblator[nRandomVariable] = bFuturePoblator.to_ulong();
		
		aValueFuturePoblator = aValuesPoblation[nIndex];

		double dAux = (double)(bFuturePoblator.to_ulong()) * aFormulaEstatica[nRandomVariable];
		aValueFuturePoblator[nRandomVariable] = aLimites[nRandomVariable].first + dAux;

	} while(!checkRestrictions(aValueFuturePoblator));
	aPobladores[nIndex] = aFuturePoblator;
	aValuesPoblation[nIndex] = aValueFuturePoblator;
}

// Función que mutará un poblador con base al vector más dominante y lo añadirá a los demás pobladores.
void mutatePoblator(){

	vector<ulong> aFuturePoblator;
	vector<double> aValueFuturePoblator(nVariables);
	
	do{
		int nRandomVariable = rand() % nVariables;
		int nRandomBit = rand() % aBitsVariables[nRandomVariable];
		
		aFuturePoblator = aPobladores[0];
		
		bitset<sizeof(ulong)*8> bFuturePoblator(aFuturePoblator[nRandomVariable]);
		bFuturePoblator.flip(nRandomBit);

		aFuturePoblator[nRandomVariable] = bFuturePoblator.to_ulong();
		
		aValueFuturePoblator = aValuesPoblation[0];

		double dAux = (double)(bFuturePoblator.to_ulong()) * aFormulaEstatica[nRandomVariable];
		aValueFuturePoblator[nRandomVariable] = aLimites[nRandomVariable].first + dAux;

	} while(!checkRestrictions(aValueFuturePoblator));
	aPobladores.push_back(aFuturePoblator);
	aValuesPoblation.push_back(aValueFuturePoblator);
}

// Función que generará un poblador al azar añadiéndolo en un espacio específico, se usa al iniciar el programa
void generatePoblator(int nIndex){
	do{
		for (int j = 0; j < nVariables; ++j)
		{
			aPobladores[nIndex][j] = rand() % aMaxVariables[j];
			double dAux = (double)aPobladores[nIndex][j] * aFormulaEstatica[j];
			aValuesPoblation[nIndex][j] = aLimites[j].first + dAux;
		}
	} while(!checkRestrictions(aValuesPoblation[nIndex]));
}

// Función que calcula el valor de z y lo añade a su respectiva posición
void calculateZ(int nIndex){
	double dAux = 0;
	cout<<"Value of z"<<nIndex<<" for";
	for (int i = 0; i < nVariables; ++i)
	{
		cout<<" x"<<i<<" = "<< aValuesPoblation[nIndex][i]<<" * "<<aFormulaObjetivo[i];
		dAux += aFormulaObjetivo[i] * aValuesPoblation[nIndex][i];
	}
	cout<<" is = "<<dAux<<endl;
	aValuesZ[nIndex] = dAux;
}

// Función que calculará los valores de Z, así como su acumulada 
void calculateValues(){

	mapRepetitions.clear();

	aValuesZ.clear();
	aValuesZ.resize(nPoblacion);
	double dAux = 0, minorValue = 0, maxValue = 0;
	int i = 0;
	while (i < nPoblacion)
	{
		calculateZ(i);
		if (aValuesZ[i] > maxValue)
		{
			maxValue = aValuesZ[i];
		}
		if (aValuesZ[i] < 0)
		{
			if (aValuesZ[i] < minorValue)
			{
				dAux += (minorValue - aValuesZ[i]) * i;
				minorValue = aValuesZ[i];
			}else{
				dAux -= (minorValue - aValuesZ[i]);
			}
		}else{
			dAux += (aValuesZ[i] - minorValue);
		}

		unordered_map<double,int>::const_iterator got = mapRepetitions.find (aValuesZ[i]);
		if ( got == mapRepetitions.end() ){
			mapRepetitions[aValuesZ[i]]++;
			i++;
		}
		else
			mutatePoblator(i);
	}

	cout<<"Minor value is "<<minorValue<<endl;
	maxValue -= minorValue;
	cout<<"Max value is "<<maxValue<<endl;

	aAcumulateZ.clear();
	aAcumulateZ.resize(nPoblacion);
	//cout<<"The acumulate is "<<dAux<<endl;
	i=0;
	if (nOpcion == 1)
	{
		aAcumulateZ[i] = aValuesZ[i] / dAux;
		
	}else{
		aAcumulateZ[i] = (maxValue - (aValuesZ[i] - minorValue)) / dAux;
	}
	//cout<<aAcumulateZ[i];
	for (i = 1; i < nPoblacion - 1; ++i)
	{
		if (nOpcion == 1)
		{
			aAcumulateZ[i] = ( aValuesZ[i] / dAux ) + aAcumulateZ[i-1];
			
		}else{
			aAcumulateZ[i] = ((maxValue - (aValuesZ[i] - minorValue)) / dAux) + aAcumulateZ[i-1];
		}
		//cout<<"\t"<<aAcumulateZ[i];
	}
	aAcumulateZ[i] = 1;
	//cout<<"\t"<<aAcumulateZ[i]<<endl;
}

// En la última iteración únicamente se sacan los valores de Z y se guarda el más alto o el más bajo según se haya solicitado
void calculateValuesFinal(){
	aValuesZ.clear();
	aValuesZ.resize(nPoblacion);
	cout<<"\n===== Iteración Final =====\n"<<endl;
	if (nOpcion == 1)
	{
		finalValueOfZ = make_pair(-1,0.0);
		for (int i = 0; i < nPoblacion; ++i)
		{
			calculateZ(i);
			if (aValuesZ[i] > finalValueOfZ.second)
			{
				finalValueOfZ = make_pair(i,aValuesZ[i]);
			}
		}
	}else{
		finalValueOfZ = make_pair(-1,INF);
		for (int i = 0; i < nPoblacion; ++i)
		{
			calculateZ(i);
			if (aValuesZ[i] < finalValueOfZ.second)
			{
				finalValueOfZ = make_pair(i,aValuesZ[i]);
			}
		}
	}
	
}

// Función que inicializará la población y calculará la fórmula necesaria para obtener el valor de la variable
void startPoblation(){
	
	for (int i = 0; i < nVariables; ++i)
	{
		double dRange = aLimites[i].second - aLimites[i].first;
		double dExponent = pow(2,aBitsVariables[i]) - 1;
		aFormulaEstatica[i] = (dRange / dExponent);
	}
	
	for (int i = 0; i < nPoblacion; ++i)
	{
		generatePoblator(i);
	}

}

// Función que calculará el número de bits necesarios para cada variable, así como su máximo valor posible
void calculateBits(){
	for (int i = 0; i < nVariables; ++i)
	{
		double dRange = aLimites[i].second - aLimites[i].first;
		double dExponent = pow(10,nBits);
		double dLog = log2(dRange*dExponent);
		double dAux = ceil(dLog);
		aBitsVariables[i] = dAux;
		aMaxVariables[i] = pow(2,aBitsVariables[i]);
		nTotalBits += dAux;
	}
}

// Inicializamos por primera vez los vectores a utilizar
void initializeVectors(){
	aBitsVariables.resize(nVariables);
	aMaxVariables.resize(nVariables);
	aFormulaEstatica.resize(nVariables);
	aPobladores.resize( nPoblacion, vector<ulong>(nVariables) );
	aValuesPoblation.resize( nPoblacion, vector<double>(nVariables) );
	aValuesZ.resize(nPoblacion);
	aAcumulateZ.resize(nPoblacion,0);
}



int main(int argc, char const *argv[])
{
	// Función que pide los datos desde terminal
	askData();

	// Función que genera los valores random desde el reloj en lugar del algoritmo
	srand( time(0) );

	// Iniciamos vectores, calculamos bits y generamos la primer población necesaria
	initializeVectors();
	calculateBits();
	startPoblation();
	cout<<endl;

	// Iniciamos las iteraciones
	for (int i = 0; i < nIteraciones; ++i)
	{
		cout<<"\n===== Iteración "<<i<<" =====\n"<<endl;

		// En caso de que falten pobladores, se generan a partir del más dominante
		while (aPobladores.size() < nPoblacion)
		{
			mutatePoblator();
		}

		// Calculamos los valores necesarios, tanto Z como la acumulada de Z
		calculateValues();

		// Generamos los valores random a calcular en la acumulada
		countRandomValues();

		// Generamos vectores auxiliares donde copiaremos los individuos más dominantes
		vector< vector<ulong> > aPobladoresAux(nPoblacion, vector<ulong>(nVariables));
		vector< vector<double> > aValuesPoblationAux(nPoblacion, vector<double>(nVariables));
		for (int j = 0; j < aCountedValues.size(); ++j)
		{
			aPobladoresAux[j] = aPobladores[aCountedValues[j].first];
			aValuesPoblationAux[j] = aValuesPoblation[aCountedValues[j].first];
		}
		aPobladores.clear();
		aValuesPoblation.clear();
		for (int j = 0; j < aCountedValues.size(); ++j)
		{
			aPobladores.push_back(aPobladoresAux[j]);
			aValuesPoblation.push_back(aValuesPoblationAux[j]);
		}
	}

	// En la última iteración, rellenamos los pobladores faltantes y calculamos sus valores
	while (aPobladores.size() < nPoblacion)
	{
		mutatePoblator();
	}
	calculateValuesFinal();
	cout<<"\nSolucion optima para Z es: "<<finalValueOfZ.second<<" \nCon:\n";
	for (int i = 0; i < nVariables; ++i)
	{
		cout<<"\tx"<<i<<" = "<<aValuesPoblation[finalValueOfZ.first][i]<<endl;
	}

	
	return 0;
}