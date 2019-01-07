#include <iostream>                                                     //Include Libraries
#include <vector>                                                       //Include Libraries
using namespace std;                                                    //Bad practice

typedef long long lli;                                                  //Just a so long name, sorry

lli NumberOfIntsFromFriend1, NumberOfIntsFromFriend2;                   //Global Vars
lli BadPrimesFor1, BadPrimesFor2;                                       //Global Vars

bool Valid(lli n) {                                                     //Is n a valid sequence?
    lli NotValid  = n /(BadPrimesFor1 * BadPrimesFor2);                 //Not valid intergers
    lli Remove1 = (n / BadPrimesFor1) - NotValid;                       //Remove places
    lli Remove2 = (n / BadPrimesFor2) - NotValid;                       //Remove places

    lli Missing1 = max(NumberOfIntsFromFriend1 - Remove2, (lli) 0);     //Missing numbers
    lli Missing2 = max(NumberOfIntsFromFriend2 - Remove1, (lli) 0);     //Missing numbers

    return (n - Remove1 - Remove2 - NotValid >= Missing1 + Missing2);   //Is n enough?
}

lli BinarySearch (lli Initial, lli Final) {                             //Binary Search
    cout<<"Binary Search with "<<Initial<<" to "<<Final<<endl;
    lli Middle = Initial + (Final - Initial) / 2;                       //Find middle
    cout<<"Middle = "<<Middle<<endl;

    if (Valid(Middle)) {                                                //If we found the one
        if (not Valid(Middle -1)) return Middle;                        //Return it!
        return BinarySearch(Initial, Middle - 1);                       //If not, find previous one
    }
    else return BinarySearch(Middle + 1, Final);                        //Find binary search
}


int main() {                                                            //Main

    cin >> NumberOfIntsFromFriend1 >> NumberOfIntsFromFriend2;          //Read
    cin >> BadPrimesFor1 >> BadPrimesFor2;                              //Read

    cout << BinarySearch(
        NumberOfIntsFromFriend1 + NumberOfIntsFromFriend2, 
        100) << "\n"; 

    return 0;
}