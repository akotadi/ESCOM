#include <bits/stdc++.h>
#define GMOD(n,k) ( ( ( (n) % (k)) + (k)) % (k))
#define MAXN 1010

using namespace std;

typedef long long ll;

int main() {
    ll n, m;
    cin >> n >> m;

    ll result = 0;
    for (int i = 1; i <= m; ++i)
    {
        for (int j = 1; j <= m; ++j)
        {
            if (GMOD((i*i+j*j), m) == 0)
            {
                ll n1 = ((n - i)/m)+1;
                ll n2 = ((n - j)/m)+1;
                result +=  n1*n2;
            }
        }
    }

    cout << result << endl;
}