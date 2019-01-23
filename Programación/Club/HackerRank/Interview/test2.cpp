#include <bits/stdc++.h>

using namespace std;

vector<string> split_string(string);

long ST[40000008], lazy[40000008];

void push(int nodo) {
    ST[nodo*2] += lazy[nodo];
    lazy[nodo*2] += lazy[nodo];
    ST[nodo*2+1] += lazy[nodo];
    lazy[nodo*2+1] += lazy[nodo];
    lazy[nodo] = 0;
}

void update(int nodo, int ini, int fin, int l, int r, int val) {
    if (l > r) 
        return;
    if (l == ini && fin == r) {
        ST[nodo] += val;
        lazy[nodo] += val;
    } else {
        push(nodo);
        int mitad = (ini + fin) / 2;
        update(nodo*2, ini, mitad, l, min(r, mitad), val);
        update(nodo*2+1, mitad+1, fin, max(l, mitad+1), r, val);
        ST[nodo] = max(ST[nodo*2], ST[nodo*2+1]);
    }
}

long query(int nodo, int ini, int fin, int l, int r) {
    if (l > r)
        return 0;
    if (l <= ini && fin <= r)
        return ST[nodo];
    push(nodo);
    int mitad = (ini + fin) / 2;
    return max(query(nodo*2, ini, mitad, l, min(r, mitad)), 
               query(nodo*2+1, mitad+1, fin, max(l, mitad+1), r));
}

void initializeST(int nodo, int ini, int fin) {
    if (ini == fin) {
        ST[nodo] = 0;
    } else {
        int mitad = (ini + fin) / 2;
        initializeST(nodo*2, ini, mitad);
        initializeST(nodo*2+1, mitad+1, fin);
        ST[nodo] = ST[nodo*2] + ST[nodo*2 + 1];
    }
}

// Complete the arrayManipulation function below.
long arrayManipulation(int n, vector<vector<int>> queries) {
    initializeST(1, 1, n);
    for (int i = 0; i < queries.size(); ++i) {
        update(1, 1, n, queries[i][0], queries[i][1], queries[i][2]);
    }
    return query(1, 1, n, 1, n);
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string nm_temp;
    getline(cin, nm_temp);

    vector<string> nm = split_string(nm_temp);

    int n = stoi(nm[0]);

    int m = stoi(nm[1]);

    vector<vector<int>> queries(m);
    for (int i = 0; i < m; i++) {
        queries[i].resize(3);

        for (int j = 0; j < 3; j++) {
            cin >> queries[i][j];
        }

        cin.ignore(numeric_limits<streamsize>::max(), '\n');
    }

    long result = arrayManipulation(n, queries);

    fout << result << "\n";
    cout << result << endl;

    fout.close();

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
