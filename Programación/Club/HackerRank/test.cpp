#include <bits/stdc++.h>

using namespace std;

vector<string> split_string(string);


void dfs(int s, vector<bool>& visited, vector<vector<int>>& cities){
    visited[s] = true;
    for (int i = 0; i < cities[s].size(); ++i)
    {
        if (visited[cities[s][i]] == false)
        {
            dfs(cities[s][i], visited, cities);
        }
    }
}

// Complete the roadsAndLibraries function below.
long roadsAndLibraries(int n, int c_lib, int c_road, vector<vector<int>>& cities) {
    if (c_road > c_lib)
    {
        return c_lib * n;
    }
    long result = 0;
    vector<bool> visited(n,false);
    cout<<endl;
    for (int i = 0; i < cities.size(); ++i)
    {
        for (int j = 0; j < cities[i].size(); ++j)
        {
            cout<<cities[i][j]<<"\t";
        }
        cout<<endl;
    }
    // for (int i = 0; i < n; ++i)
    // {
    //     if (visited[i] == false)
    //     {
    //         dfs(i, visited, cities);
    //         result++;
    //     }
    // }
    return result*c_lib;
}

int main()
{
    int q;
    cin >> q;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    for (int q_itr = 0; q_itr < q; q_itr++) {
        string nmC_libC_road_temp;
        getline(cin, nmC_libC_road_temp);

        vector<string> nmC_libC_road = split_string(nmC_libC_road_temp);

        int n = stoi(nmC_libC_road[0]);

        int m = stoi(nmC_libC_road[1]);

        int c_lib = stoi(nmC_libC_road[2]);

        int c_road = stoi(nmC_libC_road[3]);

        vector<vector<int>> cities(m);
        for (int i = 0; i < m; i++) {
            cities[i].resize(2);

            for (int j = 0; j < 2; j++) {
                cin >> cities[i][j];
            }

            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }

        long result = roadsAndLibraries(n, c_lib, c_road, cities);

        cout << result << "\n";
    }

    return 0;
}

vector<string> split_string(string input_string) {
    string::iterator new_end = unique(input_string.begin(), input_string.end(), [] (const char &x, const char &y) {
        return x == y and x == ' ';
    });

    input_string.erase(new_end, input_string.end());

    while (input_string[input_string.length() - 1] == ' ') {
        input_string.pop_back();
    }

    vector<string> splits;
    char delimiter = ' ';

    size_t i = 0;
    size_t pos = input_string.find(delimiter);

    while (pos != string::npos) {
        splits.push_back(input_string.substr(i, pos - i));

        i = pos + 1;
        pos = input_string.find(delimiter, i);
    }

    splits.push_back(input_string.substr(i, min(pos, input_string.length()) - i + 1));

    return splits;
}
