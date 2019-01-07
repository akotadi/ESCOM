#include <bits/stdc++.h>

using namespace std;

vector<int> prefixArray(string& pattern) {

    vector<int> prefixArr(pattern.size());
    for (int i = 0, j = 1; j < pattern.size();)
    {
        if (pattern[i] == pattern[j])
        {
            i++;
            prefixArr[j] = i;
            j++;
        } else {
            if (i != 0)
                i = prefixArr[i - 1];
            else {
                prefixArr[j] = 0;
                j++;
            }
        }
    }
    return prefixArr;
}

int countSubstring(vector<int>& v){
    bool count = true;
    int result = 0, index = 0;
    for (int i = 0; i < v.size(); ++i)
    {
        if (count)
        {
            if (v[i] == 0)
            {
                result++;
            }else{
                count = false;
                index = v[i];
            }
        }else{
            if (v[i] != ++index)
            {
                return v.size();
            }
        }
    }
    return result;
}

/*
vector<int> kmp(string& str, string& pattern) {
    vector<int> positions;
    if (pattern.size() == 0)
        return positions;
    vector<int> prefixArr = prefixArray(pattern);

    cout<<"Prefijos"<<endl;
    for (int i = 0; i < prefixArr.size(); ++i)
    {
    	cout<<prefixArr[i]<<" ";
    }
    cout<<endl;

    int aux = 0, index = 0;

    for (int i = 0, j = 0; j < str.size();) {
        if (pattern[i] == str[j]) {
            j++;
            i++;
        } else {
            if (i != 0) {
            	if (i > aux)
            	{
            		aux = i;
            		index = j;
            	}
                i = prefixArr[i - 1];
            } else {
                j++;
            }
        }
        if (i == pattern.size()) {
            positions.push_back(j + 1 - pattern.size());
            i = prefixArr[i - 1];
        }
    }

    cout<<aux<<endl<<index<<endl;

    return positions;
}
*/

int main() {
    string s;
    cin>>s;
    vector<int> result;
    result = prefixArray(s);
    cout<<countSubstring(result)<<endl;
    return 0;
}