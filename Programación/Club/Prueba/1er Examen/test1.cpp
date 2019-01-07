#include <bits/stdc++.h>
#define INF 0xFFFFFFFF

using namespace std;

typedef long int li;

int n;
li minTime = INF;
vector< pair<int, int> > values;

li minTimeHomework(int points, li time, int index){
	if (points <= 0)
	{
		if (time < minTime)
		{
			minTime = time;
		}
		return time;
	}
	if (++index == n || time > minTime)
	{
		return INF;
	}
	return (li)min(minTimeHomework(points,time,index),minTimeHomework(points-values[index].first,time+values[index].second,index));
}

int main(int argc, char const *argv[])
{
	cin >> n;
	int s;
	cin >> s;
	int v, t;
	values.resize(n);
	for (int i = 0; i < n; ++i)
	{
		cin >> v >> t;
		values[i] = make_pair(v,t);
	}
	cout << (li)min(minTimeHomework(s,0,0),minTimeHomework(s-values[0].first,values[0].second,0)) << "\n";
	return 0;
}