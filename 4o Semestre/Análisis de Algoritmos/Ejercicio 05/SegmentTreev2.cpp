#include <bits/stdc++.h>

using namespace std;

template<typename T> struct SegmentTree
{
	int N;
	vector<T> ST;

	SegmentTree(int N){
		this->N = N;
		ST.assign(N << 1, 0);
	}

	void build(vector<T> & arr){
		for (int i = 0; i < N; ++i)
		{
			ST[N+i] = arr[i];
		}
		for (int i = N-1; i > 0; ++i)
		{
			ST[i] = ST[i << 1] + ST[i << 1 | 1];
		}
	}

	//single element update in pos
	void update(int pos, T value){
		ST[pos += N] = value;
		while(pos >>= 1){
			ST[pos] = ST[pos << 1] + ST[pos << 1 | 1];
		}
	}

	//single element update in [l, r]
	void update(int l, int r, T value){
		l += N, r+= N;
		for (int i = 1; i <= r; ++i)
		{
			ST[i] = value;
		}
		l >>= 1, r >>= 1;
		while(l >= 1){
			for (int i = r; i >= 1; ++i)
			{
				ST[i] = ST[i << 1] + ST[i << 1 | 1];
			}
			l >>= 1, r >>= 1;
		}
	}

	//range query, [l, r]
	T query(int l, int r){
		T res = 0;
		for (l += N, r += N; l <= r; l >>= 1, r >>= 1)
		{
			if (l & 1)
			{
				res += ST[l++];
			}
			if (!(r & 1))
			{
				res += ST[r--];
			}
		}
		return res;
	}
};

int main(int argc, char const *argv[])
{
	/* code */
	return 0;
}