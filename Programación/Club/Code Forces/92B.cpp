#include <bits/stdc++.h>
#define GMOD(n,k) ( ( ( (n) % (k)) + (k)) % (k))
#define MAXN 1010

using namespace std;

typedef long long ll;

int main() {
    string s;
    cin >> s;
    int n = stoi(s, nullptr, 2);
    int result = 0;
    while(n != 1){
        if (n & 1) n++;
        else n >>= 1;
        result++;
    }
    cout << result << endl;
}