#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

const lli INF = 1LL << 60;


lli c1, c2, x, y;

bool CheckPosition(lli number){
    lli unvalid = number / (x * y);

    lli giftsC1 = (c1 - ( (number / y) - unvalid) );
    if (giftsC1 < 0)   
    {
        giftsC1 = 0;
    }

    lli giftsC2 = (c2 - ( (number / x) - unvalid) );
    if (giftsC2 < 0)   
    {
        giftsC2 = 0;
    }

    lli intersection = (number - (number / y) - (number / x) + unvalid );

    return ( (giftsC1 + giftsC2) <= intersection);
}

lli BinarySearch(lli left, lli right){
    lli mid = left + ((right - left) / 2);

    if ( CheckPosition(mid) && !CheckPosition(mid-1) )
    {
        return mid;
    }else if ( CheckPosition(mid) )
    {
        return BinarySearch(left, mid - 1);
    }else{
        return BinarySearch(mid + 1, right);
    }
}

int main( )
{
    cin>>c1>>c2>>x>>y;
    cout<<BinarySearch(c1+c2,INF)<<endl;
    return 0;
}