#include <iostream>
#include <iomanip>

using namespace std;

int main(int argc, char const *argv[])
{
	int n;
	cin >> n;
	double result = 0;
	for (int i = 0; i < n; ++i)
	{
		int aux;
		cin >> aux;
		result += aux;
	}
	cout << fixed;
	cout << setprecision(12);
	cout << (result / n) << endl;
	return 0;
}