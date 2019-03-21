#include <bits/stdc++.h>

using namespace std;

typedef unsigned long long ull;

int main(int argc, char const *argv[])
{
    ull n, q;
    cin >> n >> q;

    for (int i = 0; i < q; ++i)
    {
        ull x, y;
        cin >> x >> y;
        ull ans = n * (x - 1) + y;
        ans = (ans + 1) / 2;
        ull sum = x + y;
        ans += (sum % 2 == 1) ? (n * n + 1) / 2 : 0;
        cout << ans << endl;
    }
    return 0;
}
