#include <bits/stdc++.h>

using namespace std;

// Complete the countingValleys function below.
int countingValleys(int n, string s) {
    int result = 0, current = 0;
    bool flag = false;
    for (int i = 0; i < n; ++i)
    {
        if (s.at(i) == 'U')
        {
            current++;
        }else if (s.at(i) == 'D')
        {
            if (current == 0)
            {
                flag = true;
            }
            current--;
        }
        if (current == 0)
        {
            if (flag)
            {
                result++;
            }
            flag = false;
        }

    }
    return result;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int n;
    cin >> n;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    string s;
    getline(cin, s);

    int result = countingValleys(n, s);

    fout << result << "\n";

    fout.close();

    return 0;
}
