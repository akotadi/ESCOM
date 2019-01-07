#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
    int n;
    cin>>n;
    for (int i = 0; i < n; ++i)
    {
        string number;
        cin>>number;
        stringstream geek(number); 
        int x = 0;
        geek >> x;
        int result = 0;
        for (int j = 0; j < number.length(); ++j)
        {
            int y = number.at(j) - '0';
            if (y != 0)
            {
                if (x % y == 0)
                {
                    result++;
                }
            }
        }
        cout<<result<<endl;
    }
    return 0;
}