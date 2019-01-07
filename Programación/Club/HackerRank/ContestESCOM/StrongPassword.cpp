#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
    // numbers = "0123456789"
    // lower_case = "abcdefghijklmnopqrstuvwxyz"
    // upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    // special_characters = "!@#$%^&*()-+"

    unordered_map<char, bool> wordMap;
 
    wordMap.insert( { '!', true });
    wordMap.insert( { '@', true });
    wordMap.insert( { '#', true });
    wordMap.insert( { '$', true });
    wordMap.insert( { '%', true });
    wordMap.insert( { '^', true });
    wordMap.insert( { '&', true });
    wordMap.insert( { '*', true });
    wordMap.insert( { '(', true });
    wordMap.insert( { ')', true });
    wordMap.insert( { '-', true });
    wordMap.insert( { '+', true });

    int n;
    cin>>n;
    vector<char> phrase(n);
    bool uC = false, lC = false, sC = false, nC = false;
    for (int i = 0; i < n; ++i)
    {
        cin>>phrase[i];
        if (islower(phrase[i]))
        {
            lC = true;
        }else if (isupper(phrase[i]))
        {
            uC = true;
        }else if (isdigit(phrase[i]))
        {
            nC = true;
        }else
        {
            unordered_map<char,bool>::const_iterator got = wordMap.find (phrase[i]);

              if ( got == wordMap.end() )
                continue;
              else
                sC = true;
        }
    }
    int result = 0;
    if (!uC)
    {
        result++;
    }
    if (!lC)
    {
        result++;
    }
    if (!sC)
    {
        result++;
    }
    if (!nC)
    {
        result++;
    }
    if (result + n < 6)
    {
        cout << 6-n <<endl;
    }else{
        cout<<result<<endl;
    }
    return 0;
}