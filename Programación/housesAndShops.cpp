#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int myints[] = {1,5,20,11,16};
	vector<int> stores (myints, myints+5); 

	int myints2[] = {3, 5,10,17, 20};
	vector<int> houses (myints2, myints2+5);

	vector<int> aux(houses.size());

	sort (stores.begin(), stores.end());

	int index = 0;
	for (vector<int>::iterator i = houses.begin(); i != houses.end(); ++i, ++index)
	{
		auto low = lower_bound (stores.begin(), stores.end(), *i);
		if (*low != *i && low != stores.end())
		{
			low--;
		}else if(*low == 0){
		    low = stores.begin();
		}
		auto up = upper_bound (stores.begin(), stores.end(), *i);
		if(*up == 0){
		    up = stores.end()-1;
		}
		if (*i - *low == *up - *i || *i - *low < *up - *i)
		{
		    if (*low == 0){
		        aux[index] = *up;
		    }else{
		        aux[index] = *low;
		    }
		}else{
			aux[index] = *up;
		}
	}

	return 0;
}