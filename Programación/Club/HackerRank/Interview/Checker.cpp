#include <bits/stdc++.h>

using namespace std;

// int main(int argc, char const *argv[])
// {
//     string s1 = "Hola";
//     string s2 = "Adios";
//     cout<<s1.compare(s2)<<endl;
//     cout<<s2.compare(s1)<<endl;
//     cout<<s1.compare("Hola")<<endl;
//     cout<<s1.compare("hola")<<endl;
//     return 0;
// }

struct Player {
    int score;
    string name;
};

class Checker{
public:
    // complete this method
    static int comparator(Player a, Player b)  {
        if (a.score == b.score)
        {
            if (a.name.compare(b.name) == 0)
            {
                return 0;
            }else{
                return (a.name.compare(b.name) > 0)?-1:1;
            }
        }else{
            return (a.score > b.score)?1:-1;
        }
    }
};

bool compare(Player a, Player b) {
    if(Checker::comparator(a,b) == -1)
        return false;
    return true;
}
int main()
{
    int n;
    cin >> n;
    vector< Player > players;
    string name;
    int score;
    for(int i = 0; i < n; i++){
        cin >> name >> score;
        Player player;
        player.name = name, player.score = score;
        players.push_back(player);
    }
    sort(players.begin(), players.end(), compare);
    for(int i = 0; i < players.size(); i++) {
        cout << players[i].name << " " << players[i].score << endl;
    }
    return 0;
}