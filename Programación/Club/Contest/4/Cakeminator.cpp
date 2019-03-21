#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
    int r, c;
    cin >> r >> c;
    char board[r][c];
    for (int i = 0; i < r; ++i)
    {
        for (int j = 0; j < c; ++j)
        {
            cin >> board[i][j];
        }
    }

    int result = 0, rows = 0, columns = 0;
    for (int i = 0; i < r; ++i)
    {
        for (int j = 0; j < c; ++j)
        {
            if (board[i][j] == 'S')
                break;
            else if(j == c-1)
                rows++;
        }
    }
    result += (rows*c);
    for (int j = 0; j < c; ++j)
    {
        for (int i = 0; i < r; ++i)
        {
            if (board[i][j] == 'S')
                break;
            else if(i == r-1)
                columns++;
        }
    }
    result += (columns*(r-rows));
    cout << result << endl;
    return 0;
}
