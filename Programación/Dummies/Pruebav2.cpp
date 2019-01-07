// field width
#include <stdio.h>
#include <iostream>

using namespace std;   // std::cout, std::left

int main () {
  /*cout << 100 << '\n';
  cout.width(5); // 5 space
  cout << 100 << '\n'; // 100 in the end of the space
  cout.fill('x'); 
  cout.width(20); // 20 'x'
  cout << left << 100 << '\n'; // 100 in the left of the x's

  cout << "\n\n";*/

  cout.setf(ios::showpos); 
  cout.setf(ios::scientific); 
  cout << 123 << " " << 123.23 << endl; 

  cout << "\n\n";
 
  cout.precision(2);                 // two digits after decimal point 
  cout.width(10);                    // in a field of 10 characters 
  cout << 123 << " "; 
  cout.width(10);                    // set width to 10 
  cout << 123.23 << endl; 

  cout << "\n\n";
 
  cout.fill('#');                    // fill using # 
  cout.width(10);                    // in a field of 10 characters 
  cout << 123 << " "; 
  cout.width(10);                    // set width to 10 
  cout << 123.23;

  cout << "\n\n";
  return 0;
}