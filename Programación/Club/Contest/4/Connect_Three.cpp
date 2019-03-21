#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
    pair<int,int> a, b, c;
    int x,y;
    cin >> x >> y;
    a = {x, y};
    cin >> x >> y;
    b = {x, y};
    cin >> x >> y;
    c = {x, y};

    // AB.first | AC.first
    set<pair<int,int>> result;
    result.insert(a);
    result.insert(b);
    result.insert(c);
    for (int i = a.first; i != b.first; (a.first > b.first)?i--:i++)
    {
        result.insert(make_pair(i, a.second));
    }
    result.insert(make_pair(b.first, a.second));
    for (int i = a.second; i != b.second; (a.second > b.second)?i--:i++)
    {
        result.insert(make_pair(b.first, i));
    }
    for (int i = a.first; i != c.first; (a.first > c.first)?i--:i++)
    {
        result.insert(make_pair(i, a.second));
    }
    result.insert(make_pair(c.first, a.second));
    for (int i = a.second; i != c.second; (a.second > c.second)?i--:i++)
    {
        result.insert(make_pair(c.first, i));
    }

    // AB.first | AC.second
    set<pair<int,int>> aux;
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);
    for (int i = a.first; i != b.first; (a.first > b.first)?i--:i++)
    {
        aux.insert(make_pair(i, a.second));
    }
    aux.insert(make_pair(b.first, a.second));
    for (int i = a.second; i != b.second; (a.second > b.second)?i--:i++)
    {
        aux.insert(make_pair(b.first, i));
    }
    for (int i = a.second; i != c.second; (a.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(a.first, i));
    }
    aux.insert(make_pair(a.first, c.second));
    for (int i = a.first; i != c.first; (a.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, c.second));
    }

    result = (aux.size() < result.size())?aux:result;

    // AB.second | AC.first
    aux.clear();
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);
    for (int i = a.second; i != b.second; (a.second > b.second)?i--:i++)
    {
        aux.insert(make_pair(a.first, i));
    }
    aux.insert(make_pair(a.first, b.second));
    for (int i = a.first; i != b.first; (a.first > b.first)?i--:i++)
    {
        aux.insert(make_pair(i, b.second));
    }
    for (int i = a.first; i != c.first; (a.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, a.second));
    }
    aux.insert(make_pair(c.first, a.second));
    for (int i = a.second; i != c.second; (a.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(c.first, i));
    }

    result = (aux.size() < result.size())?aux:result;

    // AB.second | AC.second
    aux.clear();
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);
    for (int i = a.second; i != b.second; (a.second > b.second)?i--:i++)
    {
        aux.insert(make_pair(a.first, i));
    }
    aux.insert(make_pair(a.first, b.second));
    for (int i = a.first; i != b.first; (a.first > b.first)?i--:i++)
    {
        aux.insert(make_pair(i, b.second));
    }
    for (int i = a.second; i != c.second; (a.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(a.first, i));
    }
    aux.insert(make_pair(a.first, c.second));
    for (int i = a.first; i != c.first; (a.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, c.second));
    }

    result = (aux.size() < result.size())?aux:result;

    // AB.first | BC.first
    aux.clear();
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);    
    for (int i = a.first; i != b.first; (a.first > b.first)?i--:i++)
    {
        aux.insert(make_pair(i, a.second));
    }
    aux.insert(make_pair(b.first, a.second));
    for (int i = a.second; i != b.second; (a.second > b.second)?i--:i++)
    {
        aux.insert(make_pair(b.first, i));
    }
    for (int i = b.first; i != c.first; (b.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, b.second));
    }
    aux.insert(make_pair(c.first, b.second));
    for (int i = b.second; i != c.second; (b.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(c.first, i));
    }

    result = (aux.size() < result.size())?aux:result;

    // AB.first | BC.second
    aux.clear();
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);
    for (int i = a.first; i != b.first; (a.first > b.first)?i--:i++)
    {
        aux.insert(make_pair(i, a.second));
    }
    aux.insert(make_pair(b.first, a.second));
    for (int i = a.second; i != b.second; (a.second > b.second)?i--:i++)
    {
        aux.insert(make_pair(b.first, i));
    }
    for (int i = b.second; i != c.second; (b.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(b.first, i));
    }
    aux.insert(make_pair(b.first, c.second));
    for (int i = b.first; i != c.first; (b.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, c.second));
    }

    result = (aux.size() < result.size())?aux:result;

    // AB.second | BC.first
    aux.clear();
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);
    for (int i = a.second; i != b.second; (a.second > b.second)?i--:i++)
    {
        aux.insert(make_pair(a.first, i));
    }
    aux.insert(make_pair(a.first, b.second));
    for (int i = a.first; i != b.first; (a.first > b.first)?i--:i++)
    {
        aux.insert(make_pair(i, b.second));
    }
    for (int i = b.first; i != c.first; (b.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, b.second));
    }
    aux.insert(make_pair(c.first, b.second));
    for (int i = b.second; i != c.second; (b.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(c.first, i));
    }

    result = (aux.size() < result.size())?aux:result;

    // AB.second | BC.second
    aux.clear();
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);
    for (int i = a.second; i != b.second; (a.second > b.second)?i--:i++)
    {
        aux.insert(make_pair(a.first, i));
    }
    aux.insert(make_pair(a.first, b.second));
    for (int i = a.first; i != b.first; (a.first > b.first)?i--:i++)
    {
        aux.insert(make_pair(i, b.second));
    }
    for (int i = b.second; i != c.second; (b.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(b.first, i));
    }
    aux.insert(make_pair(b.first, c.second));
    for (int i = b.first; i != c.first; (b.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, c.second));
    }

    result = (aux.size() < result.size())?aux:result;

    // AC.first | BC.first
    aux.clear();
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);    
    for (int i = a.first; i != c.first; (a.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, a.second));
    }
    aux.insert(make_pair(c.first, a.second));
    for (int i = a.second; i != c.second; (a.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(c.first, i));
    }
    for (int i = b.first; i != c.first; (b.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, b.second));
    }
    aux.insert(make_pair(c.first, b.second));
    for (int i = b.second; i != c.second; (b.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(c.first, i));
    }

    result = (aux.size() < result.size())?aux:result;

    // AC.first | BC.second
    aux.clear();
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);
    for (int i = a.first; i != c.first; (a.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, a.second));
    }
    aux.insert(make_pair(c.first, a.second));
    for (int i = a.second; i != c.second; (a.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(c.first, i));
    }
    for (int i = b.second; i != c.second; (b.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(b.first, i));
    }
    aux.insert(make_pair(b.first, c.second));
    for (int i = b.first; i != c.first; (b.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, c.second));
    }

    result = (aux.size() < result.size())?aux:result;

    // AC.second | BC.first
    aux.clear();
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);
    for (int i = a.second; i != c.second; (a.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(a.first, i));
    }
    aux.insert(make_pair(a.first, c.second));
    for (int i = a.first; i != c.first; (a.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, c.second));
    }
    for (int i = b.first; i != c.first; (b.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, b.second));
    }
    aux.insert(make_pair(c.first, b.second));
    for (int i = b.second; i != c.second; (b.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(c.first, i));
    }

    result = (aux.size() < result.size())?aux:result;

    // AC.second | BC.second
    aux.clear();
    aux.insert(a);
    aux.insert(b);
    aux.insert(c);
    for (int i = a.second; i != c.second; (a.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(a.first, i));
    }
    aux.insert(make_pair(a.first, c.second));
    for (int i = a.first; i != c.first; (a.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, c.second));
    }
    for (int i = b.second; i != c.second; (b.second > c.second)?i--:i++)
    {
        aux.insert(make_pair(b.first, i));
    }
    aux.insert(make_pair(b.first, c.second));
    for (int i = b.first; i != c.first; (b.first > c.first)?i--:i++)
    {
        aux.insert(make_pair(i, c.second));
    }

    result = (aux.size() < result.size())?aux:result;


    cout << result.size() << endl;
    for(auto it : result){
        cout << it.first << " " << it.second << endl;
    }
    return 0;
}
