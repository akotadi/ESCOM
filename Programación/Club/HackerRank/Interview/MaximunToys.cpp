#include <bits/stdc++.h>

using namespace std;

const int INF = 1 << 30;

vector<string> split_string(string);

template<typename T> struct SegmentTreeDin{
    SegmentTreeDin *left, *right;
    int l, r;
    T value, lazy, maxValue = 0;
 
    SegmentTreeDin(int start, int end, vector<T> & arr): left(NULL), right(NULL), l(start), r(end), value(0), lazy(0){
        if(l == r) value = arr[l];
        else{
            int half = l + ((r - l) >> 1);
            left = new SegmentTreeDin(l, half, arr);
            right = new SegmentTreeDin(half+1, r, arr);
            value = left->value + right->value;
        }
    }
 
    void propagate(T dif){
        value += (r - l + 1) * dif;
        if(l != r){
            left->lazy += dif;
            right->lazy += dif;
        }else{
            maxValue = max(maxValue, value);
        }
    }
 
    T query(int start, int end){
        if(lazy != 0){
            propagate(lazy);
            lazy = 0;
        }
        if(end < l || r < start) return 0;
        if(start <= l && r <= end) return value;
        else return left->query(start, end) + right->query(start, end);
    }
 
    void update(int start, int end, T dif){
        if(lazy != 0){
            propagate(lazy);
            lazy = 0;
        }
        if(end < l || r < start) return;
        if(start <= l && r <= end) propagate(dif);
        else{
            left->update(start, end, dif);
            right->update(start, end, dif);
            value = left->value + right->value;
        }
    }

    void update(int i, T value){
        update(i, i, value);
    }
};

// Complete the arrayManipulation function below.
long arrayManipulation(int n, vector<vector<int>> queries) {
    vector<long> v(n+1,0);
    SegmentTreeDin<long> segTree(0,n+1,v);
    for (int i = 0; i < queries.size(); ++i)
    {
        segTree.update(queries[i][0],queries[i][1],queries[i][2]);
    }
    long result = -INF;
    for (int i = 0; i <= n; ++i)
    {
        result = max(result,segTree.query(i,i));
    }
    return result;
}

int main()
{

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

    cout << result << "\n";

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
