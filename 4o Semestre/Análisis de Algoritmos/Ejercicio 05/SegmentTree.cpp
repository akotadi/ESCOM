#include <bits/stdc++.h>
using namespace std;

const int INF = 1 << 30;

template<class T> struct SegTree {

    T dato; 
    int i, d;
    SegTree* izq, *der;

    SegTree(int I, int D) : izq(NULL), der(NULL), i(I), d(D), dato() {}
    
    ~SegTree() {
        if (izq) delete izq;
        if (der) delete der;
    }
    
    T Construir() {
        if (i == d) return dato = T();
        int m = (i + d) >> 1;
        izq = new SegTree(i, m);
        der = new SegTree(m + 1, d);
        return dato = izq->Construir() + der->Construir();
    }
    
    T Actualizar(int a, T v) {
        if (a < i || d < a) return dato;
        if (a == i && d == a) return dato = v;
        if (!izq) {
            int m = (i + d) >> 1;
            izq = new SegTree(i, m);
            der = new SegTree(m + 1, d);
        }
        return dato = izq->Actualizar(a, v) + der->Actualizar(a, v);
    }
    
    T Query(int a, int b) {
        if (b < i || d < a) return T();
        if (a <= i && d <= b) return dato;
        return izq? izq->Query(a, b) + der->Query(a, b): T();
    }
};

int main(int argc, char const *argv[])
{
	SegTree<int> seg(0,5);
	int m = seg.Construir();
	cout<<m<<endl;
	int n[] = {-3,6,-1,7,-8,2};
	for (int i = 0; i < 6; ++i)
	{
		m = seg.Actualizar(i,n[i]);
		cout<<m<<endl;
	}
	m = seg.Query(2,5);
	cout<<m<<endl;
	m = seg.Query(1,3);
	cout<<m<<endl;
	m = seg.Query(2,3);
	cout<<m<<endl;
	return 0;
}