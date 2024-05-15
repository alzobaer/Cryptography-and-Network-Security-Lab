#include <bits/stdc++.h>
#define ll long long
using namespace std;

// ll power(ll n, ll r, ll m){
//     if(r == 0) return 1;
//     if(r == 1) return n%m;
//     ll x;
//     if(r%2==1) x = power(n, r-1, m)*n;
//     else{
//         x = power(n, r/(ll)2, m);
//         x = x*x;
//     }
//     return x%m;
// }

ll power(ll n , ll r , ll m){
    if(r == 0)
        return 1;
    if(r == 1)
        return n % m;

    if(r&1)
        return (n * power(n,r-1,m)) % m;
    else {
        ll x = power(n,r/2,m) % m;
        return (x * x) % m;
    }
}



int main(){
    ll q = 97 , a = 27 , x , y , Ya, Yb , Ka, Kb;

    cout<<"Enter Alice's secret key: ";
    cin>>x;

    cout<<"Enter Bob's secret key: ";
    cin>>y;

    Ya = power(a,x,q);
    Yb = power(a,y,q);

    cout<<"Alice's public key: "<< Ya << endl;
    cout<<"Bob's public key: "<< Yb <<endl;

    Ka = power(Yb,x,q);
    Kb = power(Ya,y,q);

    cout<<"Secret key for encrypting message: \n Alice: "<< Ka <<' '<<" and Bob: "<< Kb <<endl;
    return 0;
}