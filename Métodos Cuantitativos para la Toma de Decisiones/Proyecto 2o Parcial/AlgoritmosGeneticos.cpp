#include <bits/stdc++.h>

using namespace std;

typedef unsigned long int ulong;

typedef function<bool(pair<int, int>, pair<int, int>)> Comparator;



int nVariables, nRestricciones, nOpcion, nPoblacion, nIteraciones, nBits, nTotalBits = 0, nSurvivors = 0;
vector<double> aFormulaObjetivo, aFormulaEstatica, aValuesZ, aAcumulateZ;
vector< pair<double,double> > aLimites;
vector< pair<int, int> > aCountedValues;
vector< vector<double> > aRestricciones, aValuesPoblation;
vector<int> aBitsVariables, aMaxVariables;
vector< vector<ulong> > aPobladores;
unordered_map<int,int> mapValues;
pair<int,double> maxZ;

Comparator compFunctor = [](pair<int, int> elem1 ,pair<int, int> elem2)
	{
		return elem1.second > elem2.second;
	};

#include "PideDatos.h"

void sortMap(){

	set<pair<int, int>, Comparator> setOfValues(
		mapValues.begin(), mapValues.end(), compFunctor);

	aCountedValues.clear();
	copy(setOfValues.begin(), setOfValues.end(), aCountedValues.begin());
}

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

bool checkRestrictions(vector<double> aTest){
	bool flag = false;
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
			if (dAux <= aRestricciones[i][++j])
			{
				flag = true;
			} else{
				return false;
			}
		} else {
			if (dAux >= aRestricciones[i][++j])
			{
				flag = true;
			} else{
				return false;
			}
		}
	}
	return flag;
}

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

void calculateZ(int nIndex){
	double dAux = 0;
	for (int i = 0; i < nVariables; ++i)
	{
		dAux += aFormulaObjetivo[i] * aPobladores[nIndex][i];
	}
	aValuesZ[nIndex] = dAux;
}

void calculateValuesFinal(){
	aValuesZ.clear();
	aValuesZ.resize(nPoblacion);
	maxZ = make_pair(0,0.0);
	for (int i = 0; i < nPoblacion; ++i)
	{
		calculateZ(i);
		if (aValuesZ[i] > maxZ.second)
		{
			maxZ = make_pair(i,aValuesZ[i]);
		}
	}
}

void calculateValues(){
	aValuesZ.clear();
	aValuesZ.resize(nPoblacion);
	double dAux = 0;
	for (int i = 0; i < nPoblacion; ++i)
	{
		calculateZ(i);
		dAux += aValuesZ[i];
	}

	aAcumulateZ.clear();
	aAcumulateZ.resize(nPoblacion);
	int i = 0;
	aAcumulateZ[i] = aValuesZ[i] / dAux;
	for (i = 1; i < nPoblacion - 1; ++i)
	{
		aAcumulateZ[i] = ( aValuesZ[i] / dAux ) + aAcumulateZ[i-1];
	}
	aAcumulateZ[i] = 1;
}

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

void initializeVectors(){
	aBitsVariables.resize(nVariables);
	aFormulaEstatica.resize(nVariables);
	aPobladores.resize( nPoblacion, vector<ulong>(nVariables) );
	aValuesPoblation.resize( nPoblacion, vector<double>(nVariables) );
	aValuesZ.resize(nPoblacion);
	aAcumulateZ.resize(nPoblacion,0);
}



int main(int argc, char const *argv[])
{
	askData();

	/*cout<<"Datos en nVariables = "<<nVariables<<endl;
	cout<<"Datos en nOpcion = "<<nOpcion<<endl;
	cout<<"Datos en nRestricciones = "<<nRestricciones<<endl;
	cout<<"Datos en nPoblacion = "<<nPoblacion<<endl;
	cout<<"Datos en nIteraciones = "<<nIteraciones<<endl;
	cout<<"Datos en nBits = "<<nBits<<endl;

	cout<<"Datos en Formula Objetivo = "<<endl;
	for (int i = 0; i < aFormulaObjetivo.size(); ++i)
	{
		cout<<"\t"<<aFormulaObjetivo[i];
	}
	cout<<endl;

	cout<<"Datos en Limites = "<<endl;
	for (int i = 0; i < aLimites.size(); ++i)
	{
		cout<<"\t"<<aLimites[i].first<<"<=x"<<i<<"<="<<aLimites[i].second<<endl;
	}
	cout<<endl;

	cout<<"Datos en Restricciones = "<<endl;
	for (int i = 0; i < aRestricciones.size(); ++i)
	{
		cout<<"r"<<i;
		for (int j = 0; j < aRestricciones[i].size(); ++j)
		{
			cout<<"\t"<<aRestricciones[i][j];
		}
		cout<<endl;
	}
	cout<<endl;*/


	cout<<"\n1\n"<<endl;
	initializeVectors();
	cout<<"\n2\n"<<endl;
	calculateBits();
	/*cout<<"\n3\n"<<endl;
	startPoblation();
	cout<<"\n4\n"<<endl;
	for (int i = 0; i < nIteraciones; ++i)
	{
		cout<<"\nIteración "<<i<<"\n"<<endl;
		while (aPobladores.size() < nPoblacion)
		{
			mutatePoblator();
		}
		calculateValues();
		countRandomValues();
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
	while (aPobladores.size() < nPoblacion)
	{
		mutatePoblator();
	}
	calculateValuesFinal();
	cout<<"Maximo Z es: "<<maxZ.second<<" \nCon:\n";
	for (int i = 0; i < nVariables; ++i)
	{
		cout<<"\tx"<<i<<" = "<<aValuesPoblation[maxZ.first][i]<<endl;
	}

	*/
	return 0;
}