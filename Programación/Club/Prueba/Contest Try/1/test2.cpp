#include <bits/stdc++.h>
#define FORR(i, I, F) for(auto i = (I), f = (F); i < f; ++i)
#define FOR(i, F) FORR(i, 0, F)
#define INLINE __attribute__((always_inline))
#define DB(X) cout << X << endl
 
using namespace std;
 
struct Arista
{
    int ciudadDestino;
    int peso;
};
 
struct Vertice
{
    vector<Arista> arUniones;
 
    bool visitado;
    int bestWay;
};
 
using Grafo = vector<Vertice>;
 
 
int BFS (Grafo& g, int ciudadActual, int ciudadDestino)
{
    queue<int> q;
    q.push(ciudadActual);
 
    for(Vertice& v : g)
    {
        v.bestWay = -1;
        v.visitado = false;
    }
 
    g[ciudadActual].visitado = true;
 
    for(Arista& a : g[ciudadActual].arUniones)
        g[ciudadActual].bestWay = max(a.peso, g[ciudadActual].bestWay);
 
 
    while(q.size())
    {
        Vertice& x = g[q.front()];
        q.pop();
 
        for(Arista& a : x.arUniones)
        {
            Vertice& y = g[a.ciudadDestino];
 
            int heavy = min(x.bestWay, a.peso);
 
            y.bestWay = max(y.bestWay, heavy);
 
            if(!y.visitado)
            {
                y.visitado = true;
                q.push(a.ciudadDestino);
            }
        }
    }
 
    return g[ciudadDestino].bestWay;
}
 
 
int main ()
{
    int N, R;

    int idx = 0;
 
    while(cin >> N >> R && N && R)
    {
        Grafo g(N);
 
        int C1, C2, P;
 
        FOR(i, R)
        {
            cin >> C1 >> C2 >> P;
 
            g[C1-1].arUniones.push_back(Arista{C2-1, P});
            g[C2-1].arUniones.push_back(Arista{C1-1, P});
        }
 
        int ciudadActual, ciudadDestino, nTuristas;
 
        cin >> ciudadActual >> ciudadDestino >> nTuristas;
 
        int answer = BFS(g, ciudadActual-1, ciudadDestino-1);

        cout<<++idx<<(nTuristas % answer ? nTuristas / answer + 1 : nTuristas / answer)<<"\n\n";
    }
 
    return 0;
}