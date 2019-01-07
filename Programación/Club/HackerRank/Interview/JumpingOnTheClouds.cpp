#include <bits/stdc++.h>

using namespace std;

// Complete the repeatedString function below.
long repeatedString(string s, long n) {
    long result = 0;
    if (n < s.length())
    {
        for (int i = 0; i < n; ++i)
        {
            if (s.at(i) == 'a')
            {
                result++;
            }
        }
    }else{
        for (int i = 0; i < s.length(); ++i)
        {
            if (s.at(i) == 'a')
            {
                result++;
            }
        }
    }
    if (n > s.length())
    {
        result*=(n/s.length());
        if (n%s.length() != 0)
        {
            for (int i = 0; i < n%s.length(); ++i)
            {
                if (s.at(i) == 'a')
                {
                    result++;
                }
            }
        }
    }
    return result;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string s;
    getline(cin, s);

    long n;
    cin >> n;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    long result = repeatedString(s, n);

    fout << result << "\n";

    fout.close();

    return 0;
}
