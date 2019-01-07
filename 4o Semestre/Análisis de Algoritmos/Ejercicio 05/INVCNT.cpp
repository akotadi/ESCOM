#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

int n;

class SegmentTree {

	vector<int> segmentNode;

	public:
	void init(int n) {
		int N = 4 * n;
		segmentNode.resize(N, 0);
	}

	void insert(int node, int left, int right, const int indx) {
		if(indx < left or indx > right) {
			return;
		}

		if(left == right and indx == left) {
			segmentNode[node]++;
			return;
		}

		int leftNode = node << 1;
		int rightNode = leftNode | 1;
		int mid = left + (right - left) / 2;

		insert(leftNode, left, mid, indx);
		insert(rightNode, mid + 1, right, indx);

		segmentNode[node] = segmentNode[leftNode] + segmentNode[rightNode];
	}

	int query(int node, int left, int right, const int L, const int R) {

		if(left > R or right < L) {
			return 0;
		}

		if(left >= L and right <= R) {
			return segmentNode[node];
		}

		int leftNode = node << 1;
		int rightNode = leftNode | 1;
		int mid = left + (right - left) / 2;

		return query(leftNode, left, mid, L, R) + query(rightNode, mid + 1, right, L, R);
	}

};

lli countGreater(vector<int>& nums) {
	lli result = 0;
	if(nums.empty()) {
		return 0;
	}
	vector<pair<int, int> > data(n);
	for(int i = 0; i < n; ++i) {
		data[i] = pair<int, int>(nums[i], i);
	}
	sort(data.begin(), data.end(), greater<pair<int, int> >());
	SegmentTree segmentTree;
	segmentTree.init(n);
	for(int i = 0; i < n; ++i) {
		result += segmentTree.query(1, 0, n - 1, 0, data[i].second);
		segmentTree.insert(1, 0, n - 1, data[i].second);
	}
	return result;
}

int main(int argc, char const *argv[])
{
	int t;
	cin>>t;
	for (int i = 0; i < t; ++i)
	{
		cin>>n;
		vector<int> data(n,0);
		for (int j = 0; j < n; ++j)
		{
			cin>>data[j];
		}
		for (int j = 0; j < n; ++j)
		{
			cout<<data[j]<<" - ";
		}
		cout<<endl;
		cout<<countGreater(data)<<endl;
	}
	return 0;
}
