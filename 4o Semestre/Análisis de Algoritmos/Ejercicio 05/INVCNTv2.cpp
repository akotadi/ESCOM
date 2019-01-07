#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

template<typename T> struct FenwickTree{
	int N;
	vector<T> bit;

	FenwickTree(int N){
		this->N = N;
		bit.assign(N, 0);
	}

	//single element increment
	void update(int pos, T value){
		while(pos < N){
			bit[pos] += value;
			pos |= pos + 1;
		}
	}

	//range query, [0, r]
	T query(int r){
		T res = 0;
		while(r >= 0){
			res += bit[r];
			r = (r & (r + 1)) - 1;
		}
		return res;
	}

	//range query, [l, r]
	T query(int l, int r){
		return query(r) - query(l - 1);
	}
};

template <class T>  
inline void GetNumber(T &Number) {

	Number = 0;
	T NumberSign = 1;
	char CurrentChar = getchar_unlocked();

	while (CurrentChar < '0' or CurrentChar > '9') {
		if (CurrentChar == '-')  NumberSign = -1;
		CurrentChar = getchar_unlocked();
	}

	while (CurrentChar >= '0' and CurrentChar <= '9') {
		Number = (Number << 3) + (Number << 1) + CurrentChar - '0';
		CurrentChar = getchar_unlocked();
	}

	Number *= NumberSign;
}

lli CountInversions(lli Data[], lli Size, lli MaxElement) {

	FenwickTree<lli> BIT(MaxElement + 1);
	lli Count = 0;
	for (int i = 0; i < Size; ++i) {
		BIT.update(Data[i], 1);

		Count += BIT.query(Data[i] + 1, MaxElement);
	}

	return Count;
}



int main() {

	lli TestCases;
	GetNumber<lli>(TestCases);
	for (int i = 0; i < TestCases; ++i)
	{
		lli Size;
		GetNumber<lli>(Size);
	
		lli Data[Size];
		lli MaxElement = 0;
	
		for (lli j = 0; j < Size; ++j) {
			GetNumber<long long int>(Data[j]);
			MaxElement = max(MaxElement, Data[j]);
		}
	
		printf("%lld\n", CountInversions(Data, Size, MaxElement));
	}

	return 0;
}