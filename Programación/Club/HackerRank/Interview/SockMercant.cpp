#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
    unordered_map<int, int> numbers;
    int n;
    cin>>n;
    for (int i = 0; i < n; ++i)
    {
        int x;
        cin>>x;
        numbers[x]++;
    }
    int result = 0;
    for ( auto it = numbers.begin(); it != numbers.end(); ++it ){
        if (it->secon != 0)
        {
            result += (it->second)/2;
        }
    }
    cout<<result<<endl;
    return 0;
}